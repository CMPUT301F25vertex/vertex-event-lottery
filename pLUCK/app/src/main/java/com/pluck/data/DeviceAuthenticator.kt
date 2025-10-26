package com.pluck.data

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pluck.ui.model.EntrantProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.Instant

sealed class DeviceAuthResult {
    data class Success(val profile: EntrantProfile) : DeviceAuthResult()
    data class Error(val message: String, val throwable: Throwable? = null) : DeviceAuthResult()
}

class DeviceAuthenticator(private val context: Context) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val deviceIdProvider = DeviceIdProvider(context.applicationContext)

    private suspend fun ensureFirebaseUser() {
        if (auth.currentUser != null) return
        auth.signInAnonymously().await()
    }

    private fun resolveDeviceId(): String = deviceIdProvider.getOrCreateId()

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

    fun currentDeviceId(): String = resolveDeviceId()

    /**
     * Deletes the user's account from Firebase Firestore.
     * This removes the entrant document associated with the current device ID.
     *
     * @return DeviceAuthResult indicating success or failure
     */
    suspend fun deleteAccount(): DeviceAuthResult {
        val deviceId = resolveDeviceId()
        return try {
            withContext(Dispatchers.IO) {
                ensureFirebaseUser()
                firestore.collection("entrants").document(deviceId).delete().await()
            }
            DeviceAuthResult.Success(
                EntrantProfile(
                    deviceId = deviceId,
                    displayName = "",
                    email = null,
                    phoneNumber = null,
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

    private fun com.google.firebase.firestore.DocumentSnapshot.toEntrantProfile(): EntrantProfile {
        val firebaseUid = getString("firebaseUid") ?: ""
        val deviceId = id
        val displayName = getString("displayName").orEmpty()
        val email = getString("email").orEmpty().ifBlank { null }
        val phoneNumber = getString("phoneNumber").orEmpty().ifBlank { null }
        val createdAt = getTimestamp("createdAt")?.toDate()?.toInstant()
        val updatedAt = getTimestamp("updatedAt")?.toDate()?.toInstant()
        return EntrantProfile(
            deviceId = deviceId,
            displayName = displayName,
            email = email,
            phoneNumber = phoneNumber,
            firebaseUid = firebaseUid,
            createdAt = createdAt,
            updatedAt = updatedAt ?: createdAt
        )
    }

    companion object {
        private const val TAG = "DeviceAuthenticator"
    }
}
