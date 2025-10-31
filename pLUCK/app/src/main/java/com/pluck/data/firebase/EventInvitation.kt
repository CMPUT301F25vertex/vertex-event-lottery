package com.pluck.data.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

/**
 * EventInvitation.kt
 *
 * Purpose: Model for event invitations sent to waitlisted users.
 * Users must accept invitations before being added to the chosen/enrolled list.
 * Supports wave-based lottery draws where organizers can run multiple draws until event fills.
 *
 * Design Pattern: Data Transfer Object (DTO) pattern for Firebase Firestore.
 */

/**
 * Invitation status enum
 */
enum class InvitationStatus {
    PENDING,    // Sent, awaiting user response
    ACCEPTED,   // User accepted the invitation
    DECLINED,   // User declined the invitation
    EXPIRED     // Slot filled by someone else / event full when accepting
}

/**
 * Firebase model for event invitations
 *
 * @property id Firestore document ID (auto-generated)
 * @property eventId The event this invitation is for
 * @property userId Device ID of the invited user
 * @property userDisplayName User's display name at time of invitation
 * @property status Current status of the invitation
 * @property sentAt Timestamp when invitation was sent
 * @property respondedAt Timestamp when user responded (accepted/declined)
 * @property drawWave Which lottery draw wave this invitation came from
 */
data class EventInvitation(
    @DocumentId
    val id: String = "",
    val eventId: String = "",
    val userId: String = "",
    val userDisplayName: String = "",
    val status: InvitationStatus = InvitationStatus.PENDING,
    @ServerTimestamp
    val sentAt: Timestamp? = null,
    val respondedAt: Timestamp? = null,
    val drawWave: Int = 1
) {
    constructor() : this(
        id = "",
        eventId = "",
        userId = "",
        userDisplayName = "",
        status = InvitationStatus.PENDING,
        drawWave = 1
    )
}
