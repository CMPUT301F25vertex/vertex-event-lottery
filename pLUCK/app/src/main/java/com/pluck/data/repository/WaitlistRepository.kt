package com.pluck.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
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
        userName: String,
        latitude: Double? = null,
        longitude: Double? = null
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
                status = WaitlistStatus.WAITING,
                latitude = latitude,
                longitude = longitude
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
     * Decline a lottery invitation and optionally draw replacement (US 01.05.01, US 02.05.03)
     *
     * @param waitlistEntryId The waitlist entry ID
     * @param drawReplacement Whether to automatically draw a replacement from waiting list
     * @param event Optional event details for replacement notification
     * @return Result indicating success or error
     */
    suspend fun declineInvitation(
        waitlistEntryId: String,
        drawReplacement: Boolean = true,
        event: Event? = null
    ): Result<Unit> {
        return try {
            // Get entry details before declining
            val entryDoc = waitlistCollection.document(waitlistEntryId).get().await()
            val eventId = entryDoc.getString("eventId") ?: ""

            // Update status to DECLINED
            waitlistCollection.document(waitlistEntryId)
                .update(
                    mapOf(
                        "status" to WaitlistStatus.DECLINED.name,
                        "declinedAt" to FieldValue.serverTimestamp()
                    )
                )
                .await()

            // FIX: Always draw one replacement when someone declines (simpler logic)
            // The person who declined opened up a slot, so fill it if possible
            if (drawReplacement && eventId.isNotBlank() && event != null) {
                // Draw one replacement from waiting list to fill the declined slot
                val replacementResult = drawReplacementEntrant(eventId, event)
                // Log result but don't fail if replacement fails (no more waiting entrants)
                replacementResult.onFailure {
                    // Replacement failed - no more waiting entrants available
                }
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Draw a replacement entrant from waiting list (US 02.05.03)
     *
     * @param eventId The event ID
     * @param event The event details for notification
     * @return Result with selected entry ID or error
     */
    suspend fun drawReplacementEntrant(eventId: String, event: Event): Result<String> {
        return try {
            // Get all waiting entries
            val snapshot = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .whereEqualTo("status", WaitlistStatus.WAITING.name)
                .get()
                .await()

            if (snapshot.documents.isEmpty()) {
                return Result.failure(Exception("No waiting entrants available"))
            }

            // Randomly select one replacement
            val selectedDoc = snapshot.documents.random()
            val now = Timestamp.now()

            // Update to SELECTED status
            waitlistCollection.document(selectedDoc.id)
                .update(
                    mapOf(
                        "status" to WaitlistStatus.SELECTED.name,
                        "selectedAt" to now,
                        "updatedAt" to FieldValue.serverTimestamp(),
                        "isReplacement" to true
                    )
                )
                .await()

            // Create notification for replacement entrant
            val entry = selectedDoc.toObject(FirebaseWaitlistEntry::class.java)
            if (entry != null) {
                val notificationRef = firestore.collection("notifications").document()
                val notification = hashMapOf(
                    "id" to notificationRef.id,
                    "userId" to entry.userId,
                    "userName" to entry.userName,
                    "organizerId" to event.organizerId,
                    "eventId" to eventId,
                    "eventTitle" to event.title,
                    "waitlistEntryId" to selectedDoc.id,
                    "title" to "You've been selected!",
                    "subtitle" to event.title,
                    "detail" to "Great news! A spot has opened up and you've been selected for ${event.title}. Please accept your invitation within ${event.acceptanceDeadline} hours.",
                    "category" to "SELECTION",
                    "status" to "UNREAD",
                    "accentColor" to "#22D3EE",
                    "showEventDetails" to true,
                    "showAccept" to true,
                    "showDecline" to true,
                    "isAccepted" to false,
                    "isDeclined" to false,
                    "isReplacement" to true,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "updatedAt" to FieldValue.serverTimestamp(),
                    "deadlineTimestamp" to Timestamp(now.seconds + (event.acceptanceDeadline * 3600), 0)
                )
                firestore.collection("notifications").document(notificationRef.id).set(notification).await()
            }

            Result.success(selectedDoc.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Propagates a new entrant display name across all waitlist entries tied to the user.
     */
    suspend fun updateEntrantName(userId: String, displayName: String): Result<Unit> {
        if (userId.isBlank()) return Result.failure(IllegalArgumentException("User ID required."))
        val trimmedName = displayName.trim()
        if (trimmedName.isBlank()) return Result.failure(IllegalArgumentException("Display name required."))

        return try {
            val documents = waitlistCollection.whereEqualTo("userId", userId).get().await()
            if (documents.isEmpty) {
                Result.success(Unit)
            } else {
                val batch = firestore.batch()
                documents.documents.forEach { doc ->
                    batch.update(
                        doc.reference,
                        mapOf(
                            "userName" to trimmedName,
                            "updatedAt" to FieldValue.serverTimestamp()
                        )
                    )
                }
                batch.commit().await()
                Result.success(Unit)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Removes every waitlist entry associated with the given user and normalises event counters.
     */
    suspend fun purgeEntrant(userId: String): Result<Unit> {
        if (userId.isBlank()) return Result.success(Unit)

        return try {
            val snapshot = waitlistCollection.whereEqualTo("userId", userId).get().await()
            if (snapshot.isEmpty) {
                return Result.success(Unit)
            }

            val affectedWaitingEvents = mutableSetOf<String>()
            val affectedAcceptedEvents = mutableSetOf<String>()

            val batch = firestore.batch()
            snapshot.documents.forEach { doc ->
                val entry = doc.toObject(FirebaseWaitlistEntry::class.java) ?: return@forEach
                when (entry.status) {
                    WaitlistStatus.WAITING -> affectedWaitingEvents.add(entry.eventId)
                    WaitlistStatus.ACCEPTED -> affectedAcceptedEvents.add(entry.eventId)
                    else -> Unit
                }
                batch.delete(doc.reference)
            }
            batch.commit().await()

            affectedAcceptedEvents.forEach { eventId ->
                eventRepository.decrementEnrolled(eventId)
            }
            affectedWaitingEvents.forEach { eventId ->
                val waitlistCount = getWaitlistCount(eventId)
                eventRepository.updateWaitlistCount(eventId, waitlistCount)
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Deletes all waitlist entries for an event and resets its attendance counters.
     */
    suspend fun purgeEvent(eventId: String): Result<Unit> {
        if (eventId.isBlank()) return Result.success(Unit)

        return try {
            val snapshot = waitlistCollection.whereEqualTo("eventId", eventId).get().await()
            if (!snapshot.isEmpty) {
                val batch = firestore.batch()
                snapshot.documents.forEach { doc -> batch.delete(doc.reference) }
                batch.commit().await()
            }
            eventRepository.resetAttendance(eventId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Run lottery to select random entrants from waitlist and notify them
     *
     * @param eventId The event ID
     * @param numberOfWinners How many to select (sampling count)
     * @param event The event details for notification creation
     * @return Result with list of selected entry IDs or error
     */
    suspend fun runLottery(eventId: String, numberOfWinners: Int, event: Event? = null): Result<List<String>> {
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

            // Update selected entries to SELECTED status and create notifications
            val batch = firestore.batch()
            val selectedIds = mutableListOf<String>()
            val now = Timestamp.now()

            selectedEntries.forEach { doc ->
                batch.update(doc.reference, mapOf(
                    "status" to WaitlistStatus.SELECTED.name,
                    "selectedAt" to now,
                    "updatedAt" to FieldValue.serverTimestamp()
                ))
                selectedIds.add(doc.id)

                // Create notification for selected entrant
                val entry = doc.toObject(FirebaseWaitlistEntry::class.java)
                if (entry != null && event != null) {
                    val notificationRef = firestore.collection("notifications").document()
                    val notification = hashMapOf(
                        "id" to notificationRef.id,
                        "userId" to entry.userId,
                        "userName" to entry.userName,
                        "organizerId" to event.organizerId,
                        "eventId" to eventId,
                        "eventTitle" to event.title,
                        "waitlistEntryId" to doc.id,
                        "title" to "You've been selected!",
                        "subtitle" to event.title,
                        "detail" to "Congratulations! You've been selected for ${event.title}. Please accept your invitation within ${event.acceptanceDeadline} hours.",
                        "category" to "SELECTION",
                        "status" to "UNREAD",
                        "accentColor" to "#6366F1",
                        "showEventDetails" to true,
                        "showAccept" to true,
                        "showDecline" to true,
                        "isAccepted" to false,
                        "isDeclined" to false,
                        "createdAt" to FieldValue.serverTimestamp(),
                        "updatedAt" to FieldValue.serverTimestamp(),
                        "deadlineTimestamp" to Timestamp(now.seconds + (event.acceptanceDeadline * 3600), 0)
                    )
                    batch.set(notificationRef, notification)
                }
            }

            batch.commit().await()

            Result.success(selectedIds)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Notify non-chosen entrants after lottery draw (US 01.04.02)
     *
     * @param eventId The event ID
     * @param event The event details for notification creation
     * @return Result with count of notified entrants or error
     */
    suspend fun notifyNonChosenEntrants(eventId: String, event: Event): Result<Int> {
        return try {
            // Get all waiting entries (those not selected)
            val snapshot = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .whereEqualTo("status", WaitlistStatus.WAITING.name)
                .get()
                .await()

            val batch = firestore.batch()
            var notifiedCount = 0

            snapshot.documents.forEach { doc ->
                val entry = doc.toObject(FirebaseWaitlistEntry::class.java)
                if (entry != null) {
                    // Create notification for non-chosen entrant
                    val notificationRef = firestore.collection("notifications").document()
                    val notification = hashMapOf(
                        "id" to notificationRef.id,
                        "userId" to entry.userId,
                        "userName" to entry.userName,
                        "organizerId" to event.organizerId,
                        "eventId" to eventId,
                        "eventTitle" to event.title,
                        "waitlistEntryId" to doc.id,
                        "title" to "Draw Results",
                        "subtitle" to event.title,
                        "detail" to "Unfortunately, you were not selected in the lottery draw for ${event.title}. Thank you for your interest!",
                        "category" to "REJECTION",
                        "status" to "UNREAD",
                        "accentColor" to "#F97316",
                        "showEventDetails" to true,
                        "showAccept" to false,
                        "showDecline" to false,
                        "isAccepted" to false,
                        "isDeclined" to false,
                        "createdAt" to FieldValue.serverTimestamp(),
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                    batch.set(notificationRef, notification)
                    notifiedCount++
                }
            }

            batch.commit().await()
            Result.success(notifiedCount)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Send custom notification to waitlist entrants (US 02.07.01, 02.07.02, 02.07.03)
     *
     * @param eventId The event ID
     * @param event The event details
     * @param title Notification title
     * @param message Notification message
     * @param targetStatus Optional: filter by status (WAITING, SELECTED, ACCEPTED, etc.)
     * @param category Notification category (default: ORGANIZER_UPDATE)
     * @return Result with count of notified entrants or error
     */
    suspend fun sendCustomNotification(
        eventId: String,
        event: Event,
        title: String,
        message: String,
        targetStatus: WaitlistStatus? = null,
        category: String = "ORGANIZER_UPDATE"
    ): Result<Int> {
        return try {
            val query = if (targetStatus != null) {
                waitlistCollection
                    .whereEqualTo("eventId", eventId)
                    .whereEqualTo("status", targetStatus.name)
            } else {
                waitlistCollection
                    .whereEqualTo("eventId", eventId)
            }

            val snapshot = query.get().await()
            val batch = firestore.batch()
            var notifiedCount = 0

            snapshot.documents.forEach { doc ->
                val entry = doc.toObject(FirebaseWaitlistEntry::class.java)
                if (entry != null) {
                    val notificationRef = firestore.collection("notifications").document()
                    val notification = hashMapOf(
                        "id" to notificationRef.id,
                        "userId" to entry.userId,
                        "userName" to entry.userName,
                        "organizerId" to event.organizerId,
                        "eventId" to eventId,
                        "eventTitle" to event.title,
                        "waitlistEntryId" to doc.id,
                        "title" to title,
                        "subtitle" to event.title,
                        "detail" to message,
                        "category" to category,
                        "status" to "UNREAD",
                        "accentColor" to "#6366F1",
                        "showEventDetails" to true,
                        "showAccept" to false,
                        "showDecline" to false,
                        "isAccepted" to false,
                        "isDeclined" to false,
                        "createdAt" to FieldValue.serverTimestamp(),
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                    batch.set(notificationRef, notification)
                    notifiedCount++
                }
            }

            batch.commit().await()
            Result.success(notifiedCount)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Cancel non-responsive entrants who haven't accepted after deadline (US 02.06.04)
     *
     * @param eventId The event ID
     * @param event The event details for replacement drawing
     * @return Result with count of cancelled entrants or error
     */
    suspend fun cancelNonResponsiveEntrants(eventId: String, event: Event): Result<Int> {
        return try {
            val now = Timestamp.now()

            // Get all SELECTED entries that are past their deadline
            val snapshot = waitlistCollection
                .whereEqualTo("eventId", eventId)
                .whereEqualTo("status", WaitlistStatus.SELECTED.name)
                .get()
                .await()

            val batch = firestore.batch()
            var cancelledCount = 0

            snapshot.documents.forEach { doc ->
                val entry = doc.toObject(FirebaseWaitlistEntry::class.java)
                if (entry != null) {
                    val selectedAt = doc.getTimestamp("selectedAt")
                    if (selectedAt != null) {
                        val deadlineSeconds = selectedAt.seconds + (event.acceptanceDeadline * 3600)

                        // If past deadline, cancel this entry
                        if (now.seconds > deadlineSeconds) {
                            batch.update(doc.reference, mapOf(
                                "status" to WaitlistStatus.CANCELLED.name,
                                "cancelledAt" to FieldValue.serverTimestamp(),
                                "cancellationReason" to "Non-responsive"
                            ))

                            // Create notification about cancellation
                            val notificationRef = firestore.collection("notifications").document()
                            val notification = hashMapOf(
                                "id" to notificationRef.id,
                                "userId" to entry.userId,
                                "userName" to entry.userName,
                                "organizerId" to event.organizerId,
                                "eventId" to eventId,
                                "eventTitle" to event.title,
                                "waitlistEntryId" to doc.id,
                                "title" to "Invitation Expired",
                                "subtitle" to event.title,
                                "detail" to "Your invitation to ${event.title} has expired due to non-response within the ${event.acceptanceDeadline}-hour deadline.",
                                "category" to "CANCELLATION",
                                "status" to "UNREAD",
                                "accentColor" to "#EF4444",
                                "showEventDetails" to true,
                                "showAccept" to false,
                                "showDecline" to false,
                                "isAccepted" to false,
                                "isDeclined" to false,
                                "createdAt" to FieldValue.serverTimestamp(),
                                "updatedAt" to FieldValue.serverTimestamp()
                            )
                            batch.set(notificationRef, notification)
                            cancelledCount++
                        }
                    }
                }
            }

            if (cancelledCount > 0) {
                batch.commit().await()

                // Draw replacement entrants for cancelled ones
                repeat(cancelledCount) {
                    drawReplacementEntrant(eventId, event)
                }
            }

            Result.success(cancelledCount)
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
     * Get chosen/selected entries for an event (US 02.06.02, 02.06.03)
     * Returns entries with statuses: SELECTED, ACCEPTED, DECLINED, CANCELLED
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
                    // Include all draw-related statuses
                    ?.takeIf {
                        it.status == WaitlistStatus.SELECTED ||
                        it.status == WaitlistStatus.ACCEPTED ||
                        it.status == WaitlistStatus.DECLINED ||
                        it.status == WaitlistStatus.CANCELLED
                    }
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
