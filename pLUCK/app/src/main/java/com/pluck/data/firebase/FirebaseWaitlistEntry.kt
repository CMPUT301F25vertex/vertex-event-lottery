package com.pluck.data.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import com.pluck.ui.screens.WaitlistEntry
import java.time.Instant
import java.time.ZoneId

/**
 * FirebaseWaitlistEntry.kt
 *
 * Purpose: Firestore-compatible data model for waitlist entry storage and retrieval. Represents
 * an entrant's position in an event's waitlist queue, including their lottery selection status
 * and optional geolocation data for organizer analytics.
 *
 * Design Pattern: Data Transfer Object (DTO) pattern for Firebase Firestore.
 * Outstanding Issues: None.
 */

/**
 * Firebase waitlist entry model for Firestore serialization/deserialization.
 *
 * @property id Firestore document ID (auto-generated).
 * @property eventId ID of the event this entry belongs to.
 * @property userId Device ID of the entrant.
 * @property userName Display name of the entrant at time of joining.
 * @property position Queue position (1-indexed).
 * @property joinedTimestamp When the entrant joined the waitlist.
 * @property status Current status (WAITING, SELECTED, ACCEPTED, DECLINED, CANCELLED).
 * @property latitude Optional latitude if geolocation was required (US 02.02.03).
 * @property longitude Optional longitude if geolocation was required (US 02.02.03).
 * @property createdAt Server-side creation timestamp.
 * @property updatedAt Server-side last update timestamp.
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
    val latitude: Double? = null,
    val longitude: Double? = null,
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
            isChosen = status == WaitlistStatus.ACCEPTED ||
                status == WaitlistStatus.INVITED ||
                status == WaitlistStatus.SELECTED,
            status = status,
            latitude = latitude,
            longitude = longitude
        )
    }
}

/**
 * Waitlist entry status
 */
enum class WaitlistStatus {
    WAITING,      // On waitlist, not yet selected
    INVITED,      // Has pending invitation from lottery draw
    SELECTED,     // Chosen in lottery (legacy - use INVITED instead)
    ACCEPTED,     // User accepted the invitation (now enrolled)
    DECLINED,     // User declined the invitation (back to WAITING)
    CANCELLED     // User left the waitlist
}
