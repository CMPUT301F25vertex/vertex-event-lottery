package com.pluck.data.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

/**
 * Firebase user profile model
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
    @ServerTimestamp
    val createdAt: Timestamp? = null,
    @ServerTimestamp
    val updatedAt: Timestamp? = null
) {
    constructor() : this(
        id = "",
        email = "",
        displayName = "",
        role = UserRole.ENTRANT
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
