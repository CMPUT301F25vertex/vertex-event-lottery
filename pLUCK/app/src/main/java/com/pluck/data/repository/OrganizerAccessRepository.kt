package com.pluck.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.pluck.data.firebase.UserRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

/**
 * Repository responsible for managing organizer access and registration.
 *
 * Users can freely upgrade to organizer role unless they have been banned.
 * When a user successfully registers as organizer:
 * - Their role in users/{deviceId} is updated to ORGANIZER
 * - If they were banned, registration fails and they must appeal
 */
class OrganizerAccessRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val usersCollection = firestore.collection("entrants")
    private val eventsCollection = firestore.collection("events")

    /**
     * Register user as an organizer (no password required)
     */
    suspend fun registerAsOrganizer(deviceId: String): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            // Check if user exists and is not banned
            val userDoc = usersCollection.document(deviceId).get().await()
            if (!userDoc.exists()) {
                throw IllegalStateException("User profile not found.")
            }

            val isBanned = userDoc.getBoolean("isOrganizerBanned") ?: false
            if (isBanned) {
                throw IllegalArgumentException("You have been banned from organizer access. Please submit an appeal.")
            }

            val currentRole = userDoc.getString("role")
            if (currentRole == UserRole.ORGANIZER.name) {
                throw IllegalStateException("You are already an organizer.")
            }

            // Update user role to ORGANIZER
            val updates = mapOf(
                "role" to UserRole.ORGANIZER.name,
                "hasOutstandingAppeal" to false,
                "updatedAt" to FieldValue.serverTimestamp()
            )
            usersCollection.document(deviceId).set(updates, SetOptions.merge()).await()
            Unit
        }
    }

    /**
     * Downgrade organizer to entrant and delete all their events
     */
    suspend fun downgradeToEntrant(deviceId: String): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            // Get user document
            val userDoc = usersCollection.document(deviceId).get().await()
            if (!userDoc.exists()) {
                throw IllegalStateException("User profile not found.")
            }

            val currentRole = userDoc.getString("role")
            if (currentRole != UserRole.ORGANIZER.name) {
                throw IllegalStateException("User is not an organizer.")
            }

            // Delete all events created by this organizer
            val eventsSnapshot = eventsCollection
                .whereEqualTo("organizerId", deviceId)
                .get()
                .await()

            // Batch delete all events
            val batch = firestore.batch()
            eventsSnapshot.documents.forEach { eventDoc ->
                batch.delete(eventDoc.reference)
            }
            batch.commit().await()

            // Downgrade user role to ENTRANT
            val updates = mapOf(
                "role" to UserRole.ENTRANT.name,
                "updatedAt" to FieldValue.serverTimestamp()
            )
            usersCollection.document(deviceId).update(updates).await()
            Unit
        }
    }
}
