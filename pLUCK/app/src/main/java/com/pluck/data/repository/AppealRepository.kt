package com.pluck.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.pluck.data.firebase.AppealStatus
import com.pluck.data.firebase.OrganizerAppeal
import com.pluck.data.firebase.UserRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

/**
 * Repository for managing organizer access appeals.
 */
class AppealRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val appealsCollection = firestore.collection("organizerAppeals")
    private val usersCollection = firestore.collection("entrants")

    /**
     * Submit a new appeal for organizer access
     */
    suspend fun submitAppeal(
        userId: String,
        displayName: String,
        email: String,
        message: String
    ): Result<String> = withContext(Dispatchers.IO) {
        runCatching {
            // Check if user is actually banned
            val userDoc = usersCollection.document(userId).get().await()
            val isBanned = userDoc.getBoolean("isOrganizerBanned") ?: false
            if (!isBanned) {
                throw IllegalStateException("You are not banned from organizer access.")
            }

            // Check if user already has outstanding appeal
            val hasOutstanding = userDoc.getBoolean("hasOutstandingAppeal") ?: false
            if (hasOutstanding) {
                throw IllegalStateException("You already have a pending appeal.")
            }

            // Create appeal
            val appeal = OrganizerAppeal(
                userId = userId,
                displayName = displayName,
                email = email,
                message = message,
                status = AppealStatus.PENDING
            )

            val docRef = appealsCollection.add(appeal).await()

            // Mark user as having outstanding appeal
            usersCollection.document(userId).update(
                "hasOutstandingAppeal", true,
                "updatedAt", FieldValue.serverTimestamp()
            ).await()

            docRef.id
        }
    }

    /**
     * Get all appeals (for admin view)
     */
    fun getAllAppeals(): Flow<List<OrganizerAppeal>> = callbackFlow {
        val listener = appealsCollection
            .orderBy("submittedAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val appeals = snapshot.documents.mapNotNull {
                        it.toObject(OrganizerAppeal::class.java)
                    }
                    trySend(appeals)
                }
            }
        awaitClose { listener.remove() }
    }

    /**
     * Get pending appeals only
     */
    fun getPendingAppeals(): Flow<List<OrganizerAppeal>> = callbackFlow {
        val listener = appealsCollection
            .whereEqualTo("status", AppealStatus.PENDING.name)
            .orderBy("submittedAt", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val appeals = snapshot.documents.mapNotNull {
                        it.toObject(OrganizerAppeal::class.java)
                    }
                    trySend(appeals)
                }
            }
        awaitClose { listener.remove() }
    }

    /**
     * Approve an appeal and reinstate organizer access
     */
    suspend fun approveAppeal(
        appealId: String,
        adminId: String,
        adminNotes: String? = null
    ): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val appealDoc = appealsCollection.document(appealId).get().await()
            val appeal = appealDoc.toObject(OrganizerAppeal::class.java)
                ?: throw IllegalStateException("Appeal not found")

            if (appeal.status != AppealStatus.PENDING) {
                throw IllegalStateException("Appeal has already been reviewed")
            }

            // Update appeal status
            appealsCollection.document(appealId).update(
                mapOf(
                    "status" to AppealStatus.APPROVED.name,
                    "reviewedAt" to FieldValue.serverTimestamp(),
                    "reviewedBy" to adminId,
                    "adminNotes" to adminNotes
                )
            ).await()

            // Reinstate user: remove ban and outstanding appeal flag, set role to ENTRANT
            usersCollection.document(appeal.userId).update(
                mapOf(
                    "isOrganizerBanned" to false,
                    "hasOutstandingAppeal" to false,
                    "role" to UserRole.ENTRANT.name,  // Reset to ENTRANT, they can re-register
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()

            Unit
        }
    }

    /**
     * Reject an appeal
     */
    suspend fun rejectAppeal(
        appealId: String,
        adminId: String,
        adminNotes: String? = null
    ): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val appealDoc = appealsCollection.document(appealId).get().await()
            val appeal = appealDoc.toObject(OrganizerAppeal::class.java)
                ?: throw IllegalStateException("Appeal not found")

            if (appeal.status != AppealStatus.PENDING) {
                throw IllegalStateException("Appeal has already been reviewed")
            }

            // Update appeal status
            appealsCollection.document(appealId).update(
                mapOf(
                    "status" to AppealStatus.REJECTED.name,
                    "reviewedAt" to FieldValue.serverTimestamp(),
                    "reviewedBy" to adminId,
                    "adminNotes" to adminNotes
                )
            ).await()

            // Remove outstanding appeal flag
            usersCollection.document(appeal.userId).update(
                "hasOutstandingAppeal", false,
                "updatedAt", FieldValue.serverTimestamp()
            ).await()

            Unit
        }
    }
}
