package com.pluck.data.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

/**
 * OrganizerAppeal.kt
 *
 * Purpose: Model for organizer access appeals when a user has been banned from organizer role.
 * Banned users can submit an appeal with a message, and admins can review and reinstate them.
 *
 * Design Pattern: Data Transfer Object (DTO) pattern for Firebase Firestore.
 */

/**
 * Appeal status enum
 */
enum class AppealStatus {
    PENDING,    // Appeal submitted, awaiting admin review
    APPROVED,   // Admin approved and reinstated user
    REJECTED    // Admin rejected the appeal
}

/**
 * Firebase model for organizer access appeals
 *
 * @property id Firestore document ID (auto-generated)
 * @property userId Device ID of the user submitting the appeal
 * @property displayName User's display name at time of appeal
 * @property email User's email at time of appeal
 * @property message Appeal message from the user
 * @property status Current status of the appeal
 * @property submittedAt Timestamp when appeal was submitted
 * @property reviewedAt Timestamp when admin reviewed the appeal
 * @property reviewedBy Device ID of admin who reviewed (if applicable)
 * @property adminNotes Notes from admin about the decision
 */
data class OrganizerAppeal(
    @DocumentId
    val id: String = "",
    val userId: String = "",
    val displayName: String = "",
    val email: String = "",
    val message: String = "",
    val status: AppealStatus = AppealStatus.PENDING,
    @ServerTimestamp
    val submittedAt: Timestamp? = null,
    val reviewedAt: Timestamp? = null,
    val reviewedBy: String? = null,
    val adminNotes: String? = null
) {
    constructor() : this(
        id = "",
        userId = "",
        displayName = "",
        email = "",
        message = "",
        status = AppealStatus.PENDING
    )
}
