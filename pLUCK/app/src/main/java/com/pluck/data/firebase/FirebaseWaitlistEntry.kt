package com.pluck.data.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import com.pluck.ui.screens.WaitlistEntry
import java.time.Instant
import java.time.ZoneId

/**
 * Firebase-compatible waitlist entry model
 */
data class FirebaseWaitlistEntry(
    @DocumentId
    val id: String = "",
    val eventId: String = "",
    val userId: String = "",
    val userName: String = "",
    val position: Int = 0,
    val joinedTimestamp: Timestamp = Timestamp.now(),
    val status: WaitlistStatus = WaitlistStatus.WAITING,
    @ServerTimestamp
    val createdAt: Timestamp? = null,
    @ServerTimestamp
    val updatedAt: Timestamp? = null
) {
    constructor() : this(
        id = "",
        eventId = "",
        userId = "",
        userName = "",
        position = 0,
        joinedTimestamp = Timestamp.now(),
        status = WaitlistStatus.WAITING
    )

    /**
     * Convert to UI WaitlistEntry model
     */
    fun toWaitlistEntry(currentUserId: String = ""): WaitlistEntry {
        val localDate = Instant.ofEpochSecond(joinedTimestamp.seconds)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        return WaitlistEntry(
            id = id,
            userId = userId,
            userName = userName,
            position = position,
            joinedDate = localDate,
            isCurrentUser = userId == currentUserId,
            isChosen = status == WaitlistStatus.SELECTED,
            status = status
        )
    }
}

/**
 * Waitlist entry status
 */
enum class WaitlistStatus {
    WAITING,      // On waitlist, not yet selected
    SELECTED,     // Chosen in lottery
    ACCEPTED,     // User accepted the invitation
    DECLINED,     // User declined the invitation
    CANCELLED     // User left the waitlist
}
