package com.pluck.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.pluck.data.firebase.FirebaseWaitlistEntry
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.ui.model.Event
import com.pluck.ui.screens.WaitlistEntry
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

/**
 * Repository for waitlist operations with Firebase Firestore
 *
 * Handles all waitlist-related operations including:
 * - Joining waitlist
 * - Leaving waitlist
 * - Accepting/declining invitations
 * - Running lottery selection
 * - Querying waitlist entries
 */
data class WaitlistMembership(
    val entryId: String,
    val status: WaitlistStatus
)

class WaitlistRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val eventRepository: EventRepository = EventRepository()
) {
    private val waitlistCollection = firestore.collection("waitlist")

    /**
     * Join an event's waitlist
     *
     * @param eventId The event to join
     * @param userId User joining the waitlist
     * @param userName Display name of the user
     * @return Result with the waitlist entry ID or error
     */
    suspend fun joinWaitlist(
        eventId: String,
        userId: String,
        userName: String
    ): Result<String> {
        return try {
            val existingMembership = getUserWaitlistMembership(eventId, userId).getOrThrow()
            existingMembership?.let { membership ->
                if (membership.status.isActiveMembership()) {
                    return Result.success(membership.entryId)
                }
            }
            // Get current waitlist size for position
            val currentSize = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .whereEqualTo("status", WaitlistStatus.WAITING.name)
                .get()
                .await()
                .size()

            val entry = FirebaseWaitlistEntry(
                eventId = eventId,
                userId = userId,
                userName = userName,
                position = currentSize + 1,
                joinedTimestamp = Timestamp.now(),
                status = WaitlistStatus.WAITING
            )

            val docRef = waitlistCollection.document()
            val entryWithId = entry.copy(id = docRef.id)

            docRef.set(entryWithId).await()

            // Update event waitlist count
            eventRepository.updateWaitlistCount(eventId, currentSize + 1)

            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Directly sign up for an event when seats are available
     */
    suspend fun signUpDirect(
        eventId: String,
        userId: String,
        userName: String
    ): Result<String> {
        return try {
            // Check for active membership only
            val existingMembership = getUserWaitlistMembership(eventId, userId).getOrThrow()
            if (existingMembership != null) {
                return Result.failure(IllegalStateException("You're already registered for this event."))
            }

            eventRepository.incrementEnrolled(eventId)
                .onFailure { return Result.failure(it) }

            val entry = FirebaseWaitlistEntry(
                eventId = eventId,
                userId = userId,
                userName = userName,
                position = 0,
                joinedTimestamp = Timestamp.now(),
                status = WaitlistStatus.ACCEPTED
            )

            val docRef = waitlistCollection.document()
            val entryWithId = entry.copy(id = docRef.id)
            docRef.set(entryWithId).await()

            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Leave a waitlist (cancel)
     *
     * @param waitlistEntryId The waitlist entry ID to cancel
     * @return Result indicating success or error
     */
    suspend fun leaveWaitlist(waitlistEntryId: String): Result<Unit> {
        return try {
            val entry = waitlistCollection.document(waitlistEntryId).get().await()
            val eventId = entry.getString("eventId") ?: ""
            val status = entry.getString("status") ?: WaitlistStatus.WAITING.name

            // Update status to CANCELLED
            waitlistCollection.document(waitlistEntryId)
                .update("status", WaitlistStatus.CANCELLED.name)
                .await()

            if (status == WaitlistStatus.ACCEPTED.name) {
                eventRepository.decrementEnrolled(eventId)
            }

            // Update event waitlist count
            val newCount = getWaitlistCount(eventId)
            eventRepository.updateWaitlistCount(eventId, newCount)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Accept a lottery invitation
     *
     * @param waitlistEntryId The waitlist entry ID
     * @return Result indicating success or error
     */
    suspend fun acceptInvitation(waitlistEntryId: String): Result<Unit> {
        return try {
            val entry = waitlistCollection.document(waitlistEntryId).get().await()
            val eventId = entry.getString("eventId") ?: ""

            // Update status to ACCEPTED
            waitlistCollection.document(waitlistEntryId)
                .update("status", WaitlistStatus.ACCEPTED.name)
                .await()

            // Increment enrolled count in event
            eventRepository.incrementEnrolled(eventId)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Decline a lottery invitation
     *
     * @param waitlistEntryId The waitlist entry ID
     * @return Result indicating success or error
     */
    suspend fun declineInvitation(waitlistEntryId: String): Result<Unit> {
        return try {
            waitlistCollection.document(waitlistEntryId)
                .update("status", WaitlistStatus.DECLINED.name)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Run lottery to select random entrants from waitlist
     *
     * @param eventId The event ID
     * @param numberOfWinners How many to select
     * @return Result with list of selected entry IDs or error
     */
    suspend fun runLottery(eventId: String, numberOfWinners: Int): Result<List<String>> {
        return try {
            // Get all waiting entries
            val snapshot = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .whereEqualTo("status", WaitlistStatus.WAITING.name)
                .get()
                .await()

            val waitingEntries = snapshot.documents

            if (waitingEntries.isEmpty()) {
                return Result.success(emptyList())
            }

            // Randomly select winners
            val selectedEntries = waitingEntries
                .shuffled()
                .take(numberOfWinners.coerceAtMost(waitingEntries.size))

            // Update selected entries to SELECTED status
            val batch = firestore.batch()
            val selectedIds = mutableListOf<String>()

            selectedEntries.forEach { doc ->
                batch.update(doc.reference, "status", WaitlistStatus.SELECTED.name)
                selectedIds.add(doc.id)
            }

            batch.commit().await()

            Result.success(selectedIds)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get all waitlist entries for an event (waiting status only)
     *
     * @param eventId The event ID
     * @param currentUserId Optional user ID to mark as current user
     * @return Result with list of waitlist entries or error
     */
    suspend fun getWaitlistEntries(
        eventId: String,
        currentUserId: String = ""
    ): Result<List<WaitlistEntry>> {
        return try {
            val snapshot = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .get()
                .await()

            val entries = snapshot.documents.mapNotNull { doc ->
                doc.toObject(FirebaseWaitlistEntry::class.java)
                    ?.takeIf { it.status == WaitlistStatus.WAITING }
                    ?.toWaitlistEntry(currentUserId)
            }

            Result.success(entries.dedupeByUser().sortedBy { it.position })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get chosen/selected entries for an event
     *
     * @param eventId The event ID
     * @param currentUserId Optional user ID to mark as current user
     * @return Result with list of chosen entries or error
     */
    suspend fun getChosenEntries(
        eventId: String,
        currentUserId: String = ""
    ): Result<List<WaitlistEntry>> {
        return try {
            val snapshot = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .get()
                .await()

            val entries = snapshot.documents.mapNotNull { doc ->
                doc.toObject(FirebaseWaitlistEntry::class.java)
                    ?.takeIf { it.status == WaitlistStatus.SELECTED }
                    ?.toWaitlistEntry(currentUserId)
            }

            Result.success(entries.dedupeByUser().sortedBy { it.position })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get accepted entries for an event
     *
     * @param eventId The event ID
     * @param currentUserId Optional user ID to mark as current user
     * @return Result with list of accepted entries or error
     */
    suspend fun getAcceptedEntries(
        eventId: String,
        currentUserId: String = ""
    ): Result<List<WaitlistEntry>> {
        return try {
            val snapshot = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .get()
                .await()

            val entries = snapshot.documents.mapNotNull { doc ->
                doc.toObject(FirebaseWaitlistEntry::class.java)
                    ?.takeIf { it.status == WaitlistStatus.ACCEPTED }
                    ?.toWaitlistEntry(currentUserId)
            }

            Result.success(entries.dedupeByUser().sortedBy { it.position })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Check if a user is on an event's waitlist
     *
     * @param eventId The event ID
     * @param userId The user ID
     * @return Result with waitlist entry ID or null if not on waitlist
     */
    suspend fun getUserWaitlistMembership(eventId: String, userId: String): Result<WaitlistMembership?> {
        return try {
            val snapshot = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .whereEqualTo("userId", userId)
                .get()
                .await()

            val membership = snapshot.documents.mapNotNull { doc ->
                doc.toObject(FirebaseWaitlistEntry::class.java)
                    ?.takeIf { it.status.isActiveMembership() }
                    ?.let { WaitlistMembership(doc.id, it.status) }
            }.firstOrNull()
            Result.success(membership)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserEventHistory(userId: String): Result<List<UserEventHistory>> {
        return try {
            val snapshot = waitlistCollection
                .whereEqualTo("userId", userId)
                .get()
                .await()

            val history = snapshot.documents.mapNotNull { doc ->
                val entry = doc.toObject(FirebaseWaitlistEntry::class.java) ?: return@mapNotNull null
                val event = eventRepository.getEvent(entry.eventId).getOrNull() ?: return@mapNotNull null
                val joinedDate = Instant.ofEpochSecond(entry.joinedTimestamp.seconds)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                UserEventHistory(
                    event = event,
                    status = entry.status,
                    joinedDate = joinedDate
                )
            }.sortedByDescending { it.joinedDate }

            Result.success(history)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get current waitlist count for an event
     *
     * @param eventId The event ID
     * @return Current waitlist count
     */
    private suspend fun getWaitlistCount(eventId: String): Int {
        return try {
            waitlistCollection
                .whereEqualTo("eventId", eventId)
                .whereEqualTo("status", WaitlistStatus.WAITING.name)
                .get()
                .await()
                .size()
        } catch (e: Exception) {
            0
        }
    }

    /**
     * Observe real-time waitlist updates for an event
     *
     * @param eventId The event ID
     * @param currentUserId Optional user ID
     * @return Flow of waitlist entries
     */
    fun observeWaitlist(eventId: String, currentUserId: String = ""): Flow<List<WaitlistEntry>> =
        callbackFlow {
            val listener = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        val entries = snapshot.documents.mapNotNull { doc ->
                            doc.toObject(FirebaseWaitlistEntry::class.java)
                                ?.takeIf { it.status == WaitlistStatus.WAITING }
                                ?.toWaitlistEntry(currentUserId)
                        }
                        trySend(entries.dedupeByUser().sortedBy { it.position })
                    }
                }

            awaitClose { listener.remove() }
        }

    /**
     * Observe real-time chosen entries for an event
     *
     * @param eventId The event ID
     * @param currentUserId Optional user ID
     * @return Flow of chosen entries
     */
    fun observeChosenEntries(eventId: String, currentUserId: String = ""): Flow<List<WaitlistEntry>> =
        callbackFlow {
            val listener = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        val entries = snapshot.documents.mapNotNull { doc ->
                            doc.toObject(FirebaseWaitlistEntry::class.java)
                                ?.takeIf { it.status == WaitlistStatus.SELECTED }
                                ?.toWaitlistEntry(currentUserId)
                        }
                        trySend(entries.dedupeByUser().sortedBy { it.position })
                    }
                }

            awaitClose { listener.remove() }
        }
}

private fun List<WaitlistEntry>.dedupeByUser(): List<WaitlistEntry> {
    return this
        .groupBy { if (it.userId.isNotBlank()) it.userId else it.id }
        .map { (_, entries) ->
            entries.minByOrNull { it.position } ?: entries.first()
        }
        .sortedBy { it.position }
}

private fun WaitlistStatus?.isActiveMembership(): Boolean {
    return this == WaitlistStatus.WAITING ||
        this == WaitlistStatus.SELECTED ||
        this == WaitlistStatus.ACCEPTED
}

data class UserEventHistory(
    val event: Event,
    val status: WaitlistStatus,
    val joinedDate: LocalDate
)
