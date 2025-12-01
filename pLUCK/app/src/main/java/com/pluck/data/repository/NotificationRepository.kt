package com.pluck.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.toObject
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.ui.model.InviteContactType
import com.pluck.ui.model.NotificationButtons
import com.pluck.ui.model.NotificationCategory
import com.pluck.ui.model.NotificationItem
import com.pluck.ui.model.NotificationStatus
import com.pluck.ui.model.EntrantProfile
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.time.format.DateTimeFormatter

/**
 * NotificationRepository.kt
 *
 * Purpose: Repository for managing push notifications and event invitations with Firebase Firestore
 *
 * Design Pattern: Repository pattern with real-time Flow-based observation
 *
 * Features:
 * - Real-time notification streaming per user (by deviceId, email, or phone)
 * - Send event invitations via email or phone lookup (US 02.09.01)
 * - Accept/decline invitation handling with waitlist integration
 * - Notification lifecycle management (create, read, delete)
 * - Multi-field user identification (deviceId, email, phone)
 * - Automatic notification cleanup when users/events are deleted
 *
 * Notification Types:
 * - SELECTION: Lottery draw winner notifications
 * - REJECTION: Non-selected entrant notifications
 * - WAITLIST: General waitlist updates
 * - ORGANIZER_UPDATE: Custom messages from organizers
 *
 * Outstanding Issues: None
 */
class NotificationRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val waitlistRepository: WaitlistRepository = WaitlistRepository(),
    private val eventRepository: EventRepository = EventRepository()
) {
    private val notificationsCollection = firestore.collection("notifications")
    private val entrantsCollection = firestore.collection("entrants")

    fun observeNotifications(profile: EntrantProfile?): Flow<List<NotificationItem>> {
        if (profile == null) {
            return flowOf(emptyList())
        }

        val flows = buildList {
            profile.deviceId.takeIf { !it.isNullOrBlank() }?.let { add(observeNotificationField("userId", it)) }
            profile.email?.trim()?.takeIf { it.isNotBlank() }?.let { add(observeNotificationField("inviteContact", it)) }
            profile.phoneNumber?.trim()?.takeIf { it.isNotBlank() }?.let { add(observeNotificationField("inviteContact", it)) }
        }

        if (flows.isEmpty()) {
            return flowOf(emptyList())
        }

        return combine(flows) { results ->
            results
                .flatMap { it }
                .distinctBy { it.id }
                .sortedByDescending { it.createdAtMillis }
        }
    }

    private fun observeNotificationField(field: String, value: String): Flow<List<NotificationItem>> = callbackFlow {
        val listener = notificationsCollection
            .whereEqualTo(field, value)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    // Handle permission errors gracefully
                    trySend(emptyList())
                    close()
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val notifications = snapshot.documents.mapNotNull { doc ->
                        doc.toObject<FirebaseNotification>()?.toNotificationItem()
                    }
                    trySend(notifications)
                }
            }

        awaitClose { listener.remove() }
    }

    suspend fun sendInvite(
        eventId: String,
        organizerId: String,
        inviteeContact: String,
        inviteType: InviteContactType
    ): Result<Unit> = runCatching {
        val trimmedContact = inviteeContact.trim()
        require(trimmedContact.isNotBlank()) { "Enter an email or phone number." }

        val event = eventRepository.getEvent(eventId).getOrElse {
            throw IllegalArgumentException(it.message ?: "Event not found")
        }

        val inviteeSnapshot = when (inviteType) {
            InviteContactType.EMAIL -> entrantsCollection
                .whereEqualTo("email", trimmedContact)
                .limit(1)
                .get()
                .await()
            InviteContactType.PHONE -> entrantsCollection
                .whereEqualTo("phoneNumber", trimmedContact)
                .limit(1)
                .get()
                .await()
        }

        val inviteeDocument = inviteeSnapshot.documents.firstOrNull()
            ?: throw IllegalArgumentException("No entrant found with $trimmedContact")

        val userId = inviteeDocument.id
        val docRef = notificationsCollection.document()
        val dateLabel = event.date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"))

        val payload = hashMapOf(
            "id" to docRef.id,
            "userId" to userId,
            "organizerId" to organizerId,
            "eventId" to eventId,
            "waitlistEntryId" to "",
            "title" to "You're invited to ${event.title}",
            "subtitle" to event.location,
            "detail" to "Happening on $dateLabel",
            "category" to NotificationCategory.SELECTION.name,
            "status" to NotificationStatus.UNREAD.name,
            "inviteContact" to trimmedContact,
            "inviteType" to inviteType.name,
            "allowAccept" to true,
            "allowDecline" to true,
            "allowEventDetails" to true,
            "createdAt" to FieldValue.serverTimestamp(),
            "updatedAt" to FieldValue.serverTimestamp()
        )

        docRef.set(payload, SetOptions.merge()).await()
    }

    suspend fun acceptNotification(
        notification: NotificationItem,
        userProfile: EntrantProfile
    ): Result<AcceptNotificationResult> = runCatching {
        val docRef = notificationsCollection.document(notification.id)
        val eventId = notification.eventId
        require(eventId.isNotBlank()) { "Notification missing event data." }
        val userId = userProfile.deviceId

        suspend fun resolveAlreadyParticipating(): AcceptNotificationResult {
            val updates = mapOf(
                "status" to NotificationStatus.READ.name,
                "actionTaken" to "already_joined",
                "respondedAt" to FieldValue.serverTimestamp(),
                "updatedAt" to FieldValue.serverTimestamp(),
                "allowAccept" to false,
                "allowDecline" to false
            )
            runCatching {
                docRef.update(updates).await()
            }.onFailure { ex ->
                if (ex !is FirebaseFirestoreException || ex.code != FirebaseFirestoreException.Code.NOT_FOUND) {
                    throw ex
                }
            }

            return AcceptNotificationResult.AlreadyParticipating
        }

        val waitlistEntryId = if (!notification.waitlistEntryId.isNullOrBlank()) {
            // User was selected in a draw - accept the invitation (SELECTED -> ACCEPTED)
            waitlistRepository.acceptInvitation(notification.waitlistEntryId).getOrThrow()
            notification.waitlistEntryId
        } else {
            // No waitlist entry means this is a new invitation - join waitlist (WAITING status)
            val membership = waitlistRepository
                .getUserWaitlistMembership(eventId, userId)
                .getOrThrow()
            if (membership?.status.isActiveMembership()) {
                return@runCatching resolveAlreadyParticipating()
            }
            // Always join waitlist - only organizer draws can make someone SELECTED/ACCEPTED
            waitlistRepository.joinWaitlist(
                eventId = eventId,
                userId = userId,
                userName = userProfile.displayName
            ).getOrThrow()
        }

        val updates = mapOf(
            "status" to NotificationStatus.READ.name,
            "waitlistEntryId" to waitlistEntryId,
            "actionTaken" to "accepted",
            "respondedAt" to FieldValue.serverTimestamp(),
            "updatedAt" to FieldValue.serverTimestamp(),
            "allowAccept" to false,
            "allowDecline" to false
        )
        runCatching {
            docRef.update(updates).await()
        }.onFailure { ex ->
            if (ex !is FirebaseFirestoreException || ex.code != FirebaseFirestoreException.Code.NOT_FOUND) {
                throw ex
            }
        }

        AcceptNotificationResult.Joined
    }

    suspend fun declineNotification(notification: NotificationItem): Result<Unit> = runCatching {
        val docRef = notificationsCollection.document(notification.id)
        notification.waitlistEntryId?.takeIf { it.isNotBlank() }?.let { entryId ->
            // Fetch event information to enable automatic replacement drawing
            val event = notification.eventId.takeIf { it.isNotBlank() }?.let { eventId ->
                eventRepository.getEvent(eventId).getOrNull()
            }
            waitlistRepository.declineInvitation(entryId, event).getOrThrow()
        }

        val updates = mapOf(
            "status" to NotificationStatus.READ.name,
            "actionTaken" to "declined",
            "respondedAt" to FieldValue.serverTimestamp(),
            "updatedAt" to FieldValue.serverTimestamp(),
            "allowAccept" to false,
            "allowDecline" to false
        )
        runCatching {
            docRef.update(updates).await()
        }.onFailure { ex ->
            if (ex !is FirebaseFirestoreException || ex.code != FirebaseFirestoreException.Code.NOT_FOUND) {
                throw ex
            }
        }
    }

    suspend fun markRead(notificationId: String): Result<Unit> = runCatching {
        notificationsCollection.document(notificationId).update(
            mapOf(
                "status" to NotificationStatus.READ.name,
                "updatedAt" to FieldValue.serverTimestamp()
            )
        ).await()
    }

    /**
     * Removes notifications where the current user is the recipient.
     */
    suspend fun deleteNotificationsForUser(userId: String): Result<Unit> {
        if (userId.isBlank()) return Result.success(Unit)

        return runCatching {
            val snapshot = notificationsCollection.whereEqualTo("userId", userId).get().await()
            if (!snapshot.isEmpty) {
                val batch = firestore.batch()
                snapshot.documents.forEach { doc -> batch.delete(doc.reference) }
                batch.commit().await()
            }
        }
    }

    /**
     * Removes notifications sent by an organiser that is being deleted.
     */
    suspend fun deleteNotificationsForOrganizer(organizerId: String): Result<Unit> {
        if (organizerId.isBlank()) return Result.success(Unit)

        return runCatching {
            val snapshot = notificationsCollection.whereEqualTo("organizerId", organizerId).get().await()
            if (!snapshot.isEmpty) {
                val batch = firestore.batch()
                snapshot.documents.forEach { doc -> batch.delete(doc.reference) }
                batch.commit().await()
            }
        }
    }

    /**
     * Removes notifications tied to a specific event (e.g., when the event is deleted).
     */
    suspend fun deleteNotificationsForEvent(eventId: String): Result<Unit> {
        if (eventId.isBlank()) return Result.success(Unit)

        return runCatching {
            val snapshot = notificationsCollection.whereEqualTo("eventId", eventId).get().await()
            if (!snapshot.isEmpty) {
                val batch = firestore.batch()
                snapshot.documents.forEach { doc -> batch.delete(doc.reference) }
                batch.commit().await()
            }
        }
    }

    private fun FirebaseNotification.toNotificationItem(): NotificationItem {
        val categoryEnum = runCatching { NotificationCategory.valueOf(category) }
            .getOrElse { NotificationCategory.WAITLIST }
        val statusEnum = runCatching { NotificationStatus.valueOf(status) }
            .getOrElse { NotificationStatus.UNREAD }
        val inviteTypeEnum = inviteType?.let { runCatching { InviteContactType.valueOf(it) }.getOrNull() }

        val actionTakenValue = when (val value = actionTaken) {
            is String -> value
            is Boolean -> if (value) "completed" else null
            else -> null
        }

        val buttons = NotificationButtons(
            showEventDetails = allowEventDetails,
            showAccept = allowAccept && actionTakenValue.isNullOrBlank(),
            showDecline = allowDecline && actionTakenValue.isNullOrBlank()
        )

        return NotificationItem(
            id = id,
            title = title,
            subtitle = subtitle,
            detail = detail,
            category = categoryEnum,
            status = statusEnum,
            callToActionButtons = buttons,
            eventId = eventId,
            isAccepted = actionTakenValue == "accepted",
            isDeclined = actionTakenValue == "declined",
            waitlistEntryId = waitlistEntryId.takeUnless { it.isNullOrBlank() },
            inviteContact = inviteContact,
            inviteType = inviteTypeEnum,
            createdAtMillis = createdAt?.toDate()?.time ?: 0L
        )
    }
}

private data class FirebaseNotification(
    val id: String = "",
    val userId: String = "",
    val organizerId: String = "",
    val eventId: String = "",
    val waitlistEntryId: String? = null,
    val title: String = "",
    val subtitle: String = "",
    val detail: String = "",
    val category: String = NotificationCategory.WAITLIST.name,
    val status: String = NotificationStatus.UNREAD.name,
    val inviteContact: String? = null,
    val inviteType: String? = null,
    val allowAccept: Boolean = false,
    val allowDecline: Boolean = false,
    val allowEventDetails: Boolean = true,
    val actionTaken: Any? = null,
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
)

sealed class AcceptNotificationResult {
    object Joined : AcceptNotificationResult()
    object AlreadyParticipating : AcceptNotificationResult()
}

private fun WaitlistStatus?.isActiveMembership(): Boolean =
    this == WaitlistStatus.WAITING ||
        this == WaitlistStatus.INVITED ||
        this == WaitlistStatus.SELECTED ||
        this == WaitlistStatus.ACCEPTED
