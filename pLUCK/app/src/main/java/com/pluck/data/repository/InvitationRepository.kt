package com.pluck.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.pluck.data.firebase.EventInvitation
import com.pluck.data.firebase.InvitationStatus
import com.pluck.data.firebase.WaitlistStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

/**
 * Repository for managing event invitations and lottery draws.
 * Implements wave-based lottery system where:
 * 1. Organizers run draws from waitlist
 * 2. Selected users receive invitations
 * 3. Users must accept before being enrolled
 * 4. Organizers can remove and redraw to fill gaps
 */
class InvitationRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val invitationsCollection = firestore.collection("eventInvitations")
    private val eventsCollection = firestore.collection("events")
    private val waitlistCollection = firestore.collection("waitlists")

    /**
     * Run a lottery draw: randomly select from waitlist, create invitations.
     * Respects event capacity and draw size.
     *
     * @param eventId The event to draw for
     * @param drawSize Maximum number to draw (respects pooling size)
     * @param currentWave Wave number for tracking
     * @return Number of invitations created
     */
    suspend fun runDraw(
        eventId: String,
        drawSize: Int,
        currentWave: Int
    ): Result<Int> = withContext(Dispatchers.IO) {
        runCatching {
            // Get event to check available spots
            val eventDoc = eventsCollection.document(eventId).get().await()
            val capacity = eventDoc.getLong("capacity")?.toInt() ?: 0
            val enrolled = eventDoc.getLong("enrolled")?.toInt() ?: 0
            val available = capacity - enrolled

            if (available <= 0) {
                throw IllegalStateException("Event is already full")
            }

            // Get waiting entrants (only WAITING status)
            val waitlistSnapshot = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .whereEqualTo("status", WaitlistStatus.WAITING.name)
                .get()
                .await()

            val waitingUsers = waitlistSnapshot.documents

            if (waitingUsers.isEmpty()) {
                throw IllegalStateException("No users waiting on the waitlist")
            }

            // Select up to: min(drawSize, available spots, waiting users)
            val selectCount = minOf(drawSize, available, waitingUsers.size)
            val selected = waitingUsers.shuffled().take(selectCount)

            // Create invitations in batch
            val batch = firestore.batch()

            selected.forEach { userDoc ->
                // Create invitation document
                val invitation = EventInvitation(
                    eventId = eventId,
                    userId = userDoc.id,
                    userDisplayName = userDoc.getString("displayName") ?: "Unknown",
                    status = InvitationStatus.PENDING,
                    drawWave = currentWave
                )
                val invitationRef = invitationsCollection.document()
                batch.set(invitationRef, invitation)

                // Update waitlist status to INVITED
                batch.update(
                    userDoc.reference,
                    mapOf(
                        "status" to WaitlistStatus.INVITED.name,
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                )
            }

            batch.commit().await()
            selected.size
        }
    }

    /**
     * Accept an invitation. Checks if event still has space.
     * If full, marks invitation as EXPIRED and returns error.
     *
     * @param invitationId The invitation ID
     * @param userId The user accepting
     * @return "SUCCESS" or throws with "TOO_LATE" message
     */
    suspend fun acceptInvitation(
        invitationId: String,
        userId: String
    ): Result<String> = withContext(Dispatchers.IO) {
        runCatching {
            val invitationDoc = invitationsCollection.document(invitationId).get().await()
            val invitation = invitationDoc.toObject(EventInvitation::class.java)
                ?: throw IllegalStateException("Invitation not found")

            if (invitation.userId != userId) {
                throw IllegalArgumentException("This invitation is not for you")
            }

            if (invitation.status != InvitationStatus.PENDING) {
                throw IllegalStateException("Invitation already responded to")
            }

            // Check if event still has space (atomic check)
            val eventDoc = eventsCollection.document(invitation.eventId).get().await()
            val capacity = eventDoc.getLong("capacity")?.toInt() ?: 0
            val enrolled = eventDoc.getLong("enrolled")?.toInt() ?: 0

            if (enrolled >= capacity) {
                // Too late! Event is full
                val batch = firestore.batch()

                batch.update(
                    invitationsCollection.document(invitationId),
                    mapOf(
                        "status" to InvitationStatus.EXPIRED.name,
                        "respondedAt" to FieldValue.serverTimestamp()
                    )
                )

                // Return to WAITING status
                val waitlistRef = waitlistCollection
                    .whereEqualTo("eventId", invitation.eventId)
                    .whereEqualTo("userId", userId)
                    .get()
                    .await()
                    .documents
                    .firstOrNull()

                waitlistRef?.let {
                    batch.update(it.reference, "status", WaitlistStatus.WAITING.name)
                }

                batch.commit().await()
                throw IllegalStateException("TOO_LATE")
            }

            // Accept invitation - atomic transaction
            val batch = firestore.batch()

            // Update invitation to ACCEPTED
            batch.update(
                invitationsCollection.document(invitationId),
                mapOf(
                    "status" to InvitationStatus.ACCEPTED.name,
                    "respondedAt" to FieldValue.serverTimestamp()
                )
            )

            // Update waitlist to ACCEPTED (enrolled)
            val waitlistRef = waitlistCollection
                .whereEqualTo("eventId", invitation.eventId)
                .whereEqualTo("userId", userId)
                .get()
                .await()
                .documents
                .firstOrNull()

            waitlistRef?.let {
                batch.update(
                    it.reference,
                    mapOf(
                        "status" to WaitlistStatus.ACCEPTED.name,
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                )
            }

            // Increment event enrolled count
            batch.update(
                eventsCollection.document(invitation.eventId),
                "enrolled",
                FieldValue.increment(1)
            )

            batch.commit().await()
            "SUCCESS"
        }
    }

    /**
     * Decline an invitation. Returns user to WAITING status.
     *
     * @param invitationId The invitation ID
     * @param userId The user declining
     */
    suspend fun declineInvitation(
        invitationId: String,
        userId: String
    ): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val invitationDoc = invitationsCollection.document(invitationId).get().await()
            val invitation = invitationDoc.toObject(EventInvitation::class.java)
                ?: throw IllegalStateException("Invitation not found")

            if (invitation.userId != userId) {
                throw IllegalArgumentException("This invitation is not for you")
            }

            if (invitation.status != InvitationStatus.PENDING) {
                throw IllegalStateException("Invitation already responded to")
            }

            val batch = firestore.batch()

            // Update invitation to DECLINED
            batch.update(
                invitationsCollection.document(invitationId),
                mapOf(
                    "status" to InvitationStatus.DECLINED.name,
                    "respondedAt" to FieldValue.serverTimestamp()
                )
            )

            // Return to WAITING status
            val waitlistRef = waitlistCollection
                .whereEqualTo("eventId", invitation.eventId)
                .whereEqualTo("userId", userId)
                .get()
                .await()
                .documents
                .firstOrNull()

            waitlistRef?.let {
                batch.update(
                    it.reference,
                    mapOf(
                        "status" to WaitlistStatus.WAITING.name,
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                )
            }

            batch.commit().await()
            Unit
        }
    }

    /**
     * Remove a chosen/accepted entrant from the event.
     * Organizer action to free up a spot for redraw.
     *
     * @param eventId The event ID
     * @param userId The user to remove
     */
    suspend fun removeChosenEntrant(
        eventId: String,
        userId: String
    ): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val batch = firestore.batch()

            // Find and expire their accepted invitation
            val invitationSnapshot = invitationsCollection
                .whereEqualTo("eventId", eventId)
                .whereEqualTo("userId", userId)
                .whereEqualTo("status", InvitationStatus.ACCEPTED.name)
                .get()
                .await()
                .documents
                .firstOrNull()

            invitationSnapshot?.let {
                batch.update(it.reference, "status", InvitationStatus.EXPIRED.name)
            }

            // Remove from waitlist entirely
            val waitlistRef = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .whereEqualTo("userId", userId)
                .get()
                .await()
                .documents
                .firstOrNull()

            waitlistRef?.let {
                batch.delete(it.reference)
            }

            // Decrement enrolled count
            batch.update(
                eventsCollection.document(eventId),
                "enrolled",
                FieldValue.increment(-1)
            )

            batch.commit().await()
            Unit
        }
    }

    /**
     * Get all pending invitations for a user across all events.
     * Real-time updates via Flow.
     *
     * @param userId The user ID
     * @return Flow of pending invitations
     */
    fun getUserInvitations(userId: String): Flow<List<EventInvitation>> = callbackFlow {
        val listener = invitationsCollection
            .whereEqualTo("userId", userId)
            .whereEqualTo("status", InvitationStatus.PENDING.name)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val invitations = snapshot.documents.mapNotNull {
                        it.toObject(EventInvitation::class.java)
                    }
                    trySend(invitations)
                }
            }
        awaitClose { listener.remove() }
    }

    /**
     * Get all invitations for an event (for organizer view).
     *
     * @param eventId The event ID
     * @return Flow of all invitations
     */
    fun getEventInvitations(eventId: String): Flow<List<EventInvitation>> = callbackFlow {
        val listener = invitationsCollection
            .whereEqualTo("eventId", eventId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val invitations = snapshot.documents.mapNotNull {
                        it.toObject(EventInvitation::class.java)
                    }
                    trySend(invitations)
                }
            }
        awaitClose { listener.remove() }
    }
}
