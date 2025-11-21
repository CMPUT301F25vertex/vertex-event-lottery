package com.pluck.data.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

/**
 * FirebaseUser.kt
 *
 * Purpose: Firestore-compatible data model for user profile storage and retrieval. Represents
 * the persistence layer for user accounts, including role management (ENTRANT, ORGANIZER, ADMIN)
 * and contact information for notifications.
 *
 * Design Pattern: Data Transfer Object (DTO) pattern for Firebase Firestore.
 * Outstanding Issues: None.
 */

/**
 * Firebase user profile model for Firestore serialization/deserialization.
 *
 * @property id Firestore document ID (matches device ID).
 * @property email User's email address for notifications.
 * @property displayName User-facing name shown throughout the app.
 * @property phoneNumber Optional phone number for SMS notifications.
 * @property profileImageUrl Optional profile picture URL from Firebase Storage.
 * @property role User role determining permissions (ENTRANT, ORGANIZER, ADMIN).
 * @property isActive Whether the account is active (soft delete support).
 * @property isOrganizerBanned Whether this user is banned from organizer role.
 * @property hasOutstandingAppeal Whether user has a pending organizer appeal.
 * @property createdAt Server-side creation timestamp.
 * @property updatedAt Server-side last update timestamp.
 */
data class FirebaseUser(
    @DocumentId
    val id: String = "",
    val email: String = "",
    val displayName: String = "",
    val phoneNumber: String? = null,
    val profileImageUrl: String? = null,
    val role: UserRole = UserRole.ENTRANT,
    val isActive: Boolean = true,
    val isOrganizerBanned: Boolean = false,
    val hasOutstandingAppeal: Boolean = false,
    @ServerTimestamp
    val createdAt: Timestamp? = null,
    @ServerTimestamp
    val updatedAt: Timestamp? = null,
    val fcmToken: String = ""
) {
    constructor() : this(
        id = "",
        email = "",
        displayName = "",
        role = UserRole.ENTRANT,
        isOrganizerBanned = false,
        hasOutstandingAppeal = false
    )
}

/**
 * User roles in the system
 */
enum class UserRole {
    ENTRANT,      // Regular user who joins events
    ORGANIZER,    // Can create and manage events
    ADMIN         // Full system access
}
