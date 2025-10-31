package com.pluck.data

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pluck.data.repository.AdminAccessRepository
import com.pluck.data.repository.EventRepository
import com.pluck.data.repository.NotificationRepository
import com.pluck.data.repository.WaitlistRepository
import com.pluck.ui.model.EntrantProfile
import com.pluck.data.firebase.UserRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.Instant

/**
 * DeviceAuthenticator.kt
 *
 * Purpose: Manages device-based authentication and user account lifecycle for the pLUCK system.
 * Provides a frictionless signup flow by leveraging Firebase Anonymous Authentication combined with
 * device IDs, eliminating the need for passwords or email verification while maintaining user identity
 * across app sessions.
 *
 * Design Pattern: Repository pattern for authentication operations.
 * Outstanding Issues: None.
 */

/**
 * Sealed class representing the result of authentication operations.
 */
sealed class DeviceAuthResult {
    /**
     * Authentication succeeded with the resulting profile.
     * @property profile The user's profile data.
     */
    data class Success(val profile: EntrantProfile) : DeviceAuthResult()

    /**
     * Authentication failed with an error message and optional throwable.
     * @property message Human-readable error message.
     * @property throwable Optional exception that caused the failure.
     */
    data class Error(val message: String, val throwable: Throwable? = null) : DeviceAuthResult()
}

/**
 * Handles device-based authentication, profile management, and account deletion.
 * Integrates Firebase Authentication (anonymous) with Firestore for profile persistence.
 *
 * @property context Application context used for accessing device ID and preferences.
 */
class DeviceAuthenticator(private val context: Context) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val deviceIdProvider = DeviceIdProvider(context.applicationContext)

    /**
     * Ensures a Firebase Authentication user exists for the current session.
     * Signs in anonymously if no user is currently authenticated.
     */
    private suspend fun ensureFirebaseUser() {
        if (auth.currentUser != null) return
        auth.signInAnonymously().await()
    }

    /**
     * Retrieves the stable device ID for this device.
     * @return The unique device identifier.
     */
    private fun resolveDeviceId(): String = deviceIdProvider.getOrCreateId()

    /**
     * Registers a new user or signs in an existing user based on device ID.
     * Creates or updates the Firestore profile document with the provided information.
     *
     * @param displayName Required user-facing display name.
     * @param email Optional email address for notifications.
     * @param phoneNumber Optional phone number for notifications.
     * @return [DeviceAuthResult.Success] with profile, or [DeviceAuthResult.Error] on failure.
     */
    suspend fun registerOrSignIn(displayName: String, email: String?, phoneNumber: String?): DeviceAuthResult {
        if (displayName.isBlank()) {
            return DeviceAuthResult.Error("Display name is required.")
        }
        val deviceId = resolveDeviceId()

        return try {
            val profile = withContext(Dispatchers.IO) {
                ensureFirebaseUser()

                // Update Firebase Auth user profile with display name
                val user = auth.currentUser
                if (user != null) {
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName.trim())
                        .build()
                    user.updateProfile(profileUpdates).await()
                }

                val docRef = firestore.collection("entrants").document(deviceId)
                val existing = docRef.get().await()
                val payload = hashMapOf(
                    "deviceId" to deviceId,
                    "displayName" to displayName.trim(),
                    "email" to email?.trim().orEmpty(),
                    "phoneNumber" to phoneNumber?.trim().orEmpty(),
                    "firebaseUid" to (auth.currentUser?.uid ?: ""),
                    "updatedAt" to FieldValue.serverTimestamp(),
                    "lastActive" to FieldValue.serverTimestamp()
                )
                if (!existing.exists()) {
                    payload["createdAt"] = FieldValue.serverTimestamp()
                    payload["role"] = UserRole.ENTRANT.name
                    payload["isActive"] = true
                    payload["isOrganizerBanned"] = false
                    payload["hasOutstandingAppeal"] = false
                }

                docRef.set(payload, SetOptions.merge()).await()
                val snapshot = docRef.get().await()
                snapshot.toEntrantProfile()
            }
            DeviceAuthResult.Success(profile)
        } catch (t: Throwable) {
            Log.e(TAG, "Device login failed", t)
            val detail = t.message ?: t.localizedMessage ?: t.javaClass.simpleName
            DeviceAuthResult.Error("Device login failed. $detail", t)
        }
    }

    /**
     * Updates an existing user profile with new information.
     * Synchronizes changes to both Firebase Auth and Firestore.
     *
     * @param displayName Required updated display name.
     * @param email Optional updated email address.
     * @param phoneNumber Optional updated phone number.
     * @return [DeviceAuthResult.Success] with updated profile, or [DeviceAuthResult.Error] on failure.
     */
    suspend fun updateProfile(displayName: String, email: String?, phoneNumber: String?): DeviceAuthResult {
        if (displayName.isBlank()) {
            return DeviceAuthResult.Error("Display name is required.")
        }
        val deviceId = resolveDeviceId()
        return try {
            val profile = withContext(Dispatchers.IO) {
                ensureFirebaseUser()
                auth.currentUser?.updateProfile(
                    UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName.trim())
                        .build()
                )?.await()

                val docRef = firestore.collection("entrants").document(deviceId)
                val payload = mapOf(
                    "displayName" to displayName.trim(),
                    "email" to email?.trim().orEmpty(),
                    "phoneNumber" to phoneNumber?.trim().orEmpty(),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
                docRef.set(payload, SetOptions.merge()).await()
                docRef.get().await().toEntrantProfile()
            }
            DeviceAuthResult.Success(profile)
        } catch (t: Throwable) {
            Log.e(TAG, "Profile update failed", t)
            val detail = t.message ?: t.localizedMessage ?: t.javaClass.simpleName
            DeviceAuthResult.Error("Failed to update profile. $detail", t)
        }
    }

    /**
     * Fetches an existing profile for the current device from Firestore.
     * Returns null if no profile exists or if the profile is incomplete (missing display name).
     *
     * @return The user's [EntrantProfile] if found and complete, null otherwise.
     */
    suspend fun fetchExistingProfile(): EntrantProfile? {
        val deviceId = resolveDeviceId()
        return withContext(Dispatchers.IO) {
            ensureFirebaseUser()
            val snapshot = firestore.collection("entrants").document(deviceId).get().await()
            // Only return profile if document exists AND has a display name
            if (snapshot.exists() && !snapshot.getString("displayName").isNullOrBlank()) {
                snapshot.toEntrantProfile()
            } else {
                null
            }
        }
    }

    /**
     * Retrieves the current device ID without requiring authentication.
     * @return The unique device identifier for this device.
     */
    fun currentDeviceId(): String = resolveDeviceId()

    /**
     * Permanently deletes the user's account and all associated data.
     * Removes the entrant from all waitlists, deactivates their events, deletes notifications,
     * removes admin access (if any), and signs out of Firebase.
     *
     * @return [DeviceAuthResult.Success] with empty profile, or [DeviceAuthResult.Error] on failure.
     */
    suspend fun deleteAccount(): DeviceAuthResult {
        val deviceId = resolveDeviceId()
        return try {
            withContext(Dispatchers.IO) {
                ensureFirebaseUser()

                val eventRepository = EventRepository(firestore)
                val waitlistRepository = WaitlistRepository(firestore, eventRepository)
                val notificationRepository = NotificationRepository(firestore, waitlistRepository, eventRepository)

                // Clean up all user data
                waitlistRepository.purgeEntrant(deviceId).getOrThrow()

                val ownedEvents = eventRepository.deactivateEventsByOrganizer(deviceId).getOrThrow()
                ownedEvents.forEach { eventId ->
                    waitlistRepository.purgeEvent(eventId).getOrThrow()
                    notificationRepository.deleteNotificationsForEvent(eventId).getOrThrow()
                }

                notificationRepository.deleteNotificationsForUser(deviceId).getOrThrow()
                notificationRepository.deleteNotificationsForOrganizer(deviceId).getOrThrow()

                AdminAccessRepository(firestore).removeDevice(deviceId)
                    .onFailure { Log.w(TAG, "Failed to remove device from admin list", it) }

                // Delete the Firestore document
                firestore.collection("entrants").document(deviceId).delete().await()

                // Delete the Firebase Auth user before signing out
                // This ensures the anonymous auth session is completely removed
                try {
                    auth.currentUser?.delete()?.await()
                } catch (e: Exception) {
                    Log.w(TAG, "Failed to delete Firebase Auth user (may already be deleted)", e)
                }
            }

            // Clear preferences and sign out
            DeviceAuthPreferences(context).setAutoLoginEnabled(false)
            auth.signOut()

            DeviceAuthResult.Success(
                EntrantProfile(
                    deviceId = deviceId,
                    displayName = "",
                    email = null,
                    phoneNumber = null,
                    profileImageUrl = null,
                    firebaseUid = "",
                    createdAt = null,
                    updatedAt = null
                )
            )
        } catch (t: Throwable) {
            Log.e(TAG, "Account deletion failed", t)
            val detail = t.message ?: t.localizedMessage ?: t.javaClass.simpleName
            DeviceAuthResult.Error("Account deletion failed. $detail", t)
        }
    }

    /**
     * Observes the current user's profile for real-time updates.
     *
     * @param deviceId Device ID of the signed-in user.
     * @return Flow emitting the latest [EntrantProfile] or null if the profile is deleted.
     */
    fun observeProfile(deviceId: String): Flow<EntrantProfile?> = callbackFlow {
        if (deviceId.isBlank()) {
            trySend(null)
            close()
            return@callbackFlow
        }

        val listenerRegistration = firestore.collection("entrants")
            .document(deviceId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    // Handle permission errors gracefully (e.g., when account is deleted)
                    Log.w(TAG, "Profile observation error for $deviceId: ${error.message}", error)
                    trySend(null)
                    close()
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    trySend(snapshot.toEntrantProfile())
                } else {
                    trySend(null)
                }
            }

        awaitClose { listenerRegistration.remove() }
    }

    /**
     * Converts a Firestore DocumentSnapshot to an EntrantProfile domain object.
     * @receiver DocumentSnapshot from the "entrants" collection.
     * @return Populated [EntrantProfile] with data from Firestore.
     */
    private fun com.google.firebase.firestore.DocumentSnapshot.toEntrantProfile(): EntrantProfile {
        val firebaseUid = getString("firebaseUid") ?: ""
        val deviceId = id
        val displayName = getString("displayName").orEmpty()
        val email = getString("email").orEmpty().ifBlank { null }
        val phoneNumber = getString("phoneNumber").orEmpty().ifBlank { null }
        val profileImageUrl = getString("profileImageUrl").orEmpty().ifBlank { null }
        val roleString = getString("role")
        val role = UserRole.values().firstOrNull { it.name.equals(roleString, ignoreCase = true) }
            ?: UserRole.ENTRANT
        val isOrganizerBanned = getBoolean("isOrganizerBanned") ?: false
        val hasOutstandingAppeal = getBoolean("hasOutstandingAppeal") ?: false
        val createdAt = getTimestamp("createdAt")?.toDate()?.toInstant()
        val updatedAt = getTimestamp("updatedAt")?.toDate()?.toInstant()
        return EntrantProfile(
            deviceId = deviceId,
            displayName = displayName,
            email = email,
            phoneNumber = phoneNumber,
            profileImageUrl = profileImageUrl,
            firebaseUid = firebaseUid,
            role = role,
            isOrganizerBanned = isOrganizerBanned,
            hasOutstandingAppeal = hasOutstandingAppeal,
            createdAt = createdAt,
            updatedAt = updatedAt ?: createdAt
        )
    }

    companion object {
        private const val TAG = "DeviceAuthenticator"
    }
}
