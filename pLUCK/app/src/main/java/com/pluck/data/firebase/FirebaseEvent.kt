package com.pluck.data.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import com.pluck.ui.model.DrawStatus
import com.pluck.ui.model.Event
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

/**
 * FirebaseEvent.kt
 *
 * Purpose: Firestore-compatible data model for event storage and retrieval. Serves as the
 * persistence layer representation of events, handling conversion between Firebase Timestamp
 * objects and Java LocalDate/LocalDateTime for UI consumption.
 *
 * Design Pattern: Data Transfer Object (DTO) pattern for Firebase Firestore.
 * Outstanding Issues: None.
 */

/**
 * Firebase-compatible event data model for Firestore serialization/deserialization.
 * Converted to/from UI Event model for display and business logic.
 *
 * @property id Firestore document ID (auto-generated).
 * @property title Event name displayed to users.
 * @property description Detailed event description.
 * @property location Event venue or location description.
 * @property dateTimestamp Event start date/time as Firebase Timestamp.
 * @property capacity Maximum number of confirmed attendees.
 * @property enrolled Current number of confirmed attendees.
 * @property organizerId Device ID of the event organizer.
 * @property organizerName Display name of the event organizer.
 * @property waitlistCount Current number of entrants on the waitlist.
 * @property waitlistCapacity Maximum waitlist size.
 * @property qrCodeData QR code payload for event check-in.
 * @property imageUrl Optional poster image URL from Firebase Storage.
 * @property registrationStartTimestamp Optional registration opening time.
 * @property registrationEndTimestamp Optional registration closing time.
 * @property samplingCount Number of entrants to draw in lottery.
 * @property drawDateTimestamp Optional lottery draw date.
 * @property drawStatus Current lottery status (PENDING, COMPLETED, CANCELLED).
 * @property acceptanceDeadline Hours after draw for entrants to accept invitation.
 * @property requiresGeolocation Whether joining requires location permission (US 02.02.03).
 * @property latitude Optional event latitude coordinate.
 * @property longitude Optional event longitude coordinate.
 * @property isActive Whether the event is active (soft delete support).
 * @property createdAt Server-side creation timestamp.
 * @property updatedAt Server-side last update timestamp.
 */
data class FirebaseEvent(
    @DocumentId
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val location: String = "",
    val dateTimestamp: Timestamp = Timestamp.now(),
    val capacity: Int = 0,
    val enrolled: Int = 0,
    val organizerId: String = "",
    val organizerName: String = "",
    val waitlistCount: Int = 0,
    val waitlistCapacity: Int = 0,
    val qrCodeData: String = "",
    val imageUrl: String? = null,
    val registrationStartTimestamp: Timestamp? = null,
    val registrationEndTimestamp: Timestamp? = null,
    val samplingCount: Int = 0,
    val drawDateTimestamp: Timestamp? = null,
    val drawStatus: String = DrawStatus.PENDING.name,
    val acceptanceDeadline: Int = 24,
    val requiresGeolocation: Boolean = false,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val isActive: Boolean = true,
    @ServerTimestamp
    val createdAt: Timestamp? = null,
    @ServerTimestamp
    val updatedAt: Timestamp? = null
) {
    constructor() : this(
        id = "",
        title = "",
        description = "",
        location = "",
        dateTimestamp = Timestamp.now(),
        capacity = 0,
        enrolled = 0,
        organizerId = "",
        organizerName = "",
        waitlistCount = 0,
        waitlistCapacity = 0,
        qrCodeData = ""
    )

    /**
     * Convert Firebase event to UI Event model
     */
    fun toEvent(): Event {
        val eventDateTime = dateTimestamp.toLocalDateTime()
        val registrationStartDateTime = registrationStartTimestamp?.toLocalDateTime()
        val registrationEndDateTime = registrationEndTimestamp?.toLocalDateTime()

        return Event(
            id = id,
            title = title,
            description = description,
            location = location,
            date = eventDateTime.toLocalDate(),
            eventTime = eventDateTime.toLocalTime(),
            capacity = capacity,
            enrolled = enrolled,
            organizerName = organizerName,
            organizerId = organizerId,
            waitlistCount = waitlistCount,
            waitlistCapacity = waitlistCapacity,
            qrCodeData = qrCodeData.ifEmpty { id },
            posterUrl = imageUrl,
            registrationStart = registrationStartDateTime?.toLocalDate(),
            registrationStartTime = registrationStartDateTime?.toLocalTime(),
            registrationEnd = registrationEndDateTime?.toLocalDate(),
            registrationEndTime = registrationEndDateTime?.toLocalTime(),
            samplingCount = samplingCount,
            drawDate = drawDateTimestamp?.toLocalDate(),
            drawStatus = try {
                DrawStatus.valueOf(drawStatus)
            } catch (e: Exception) {
                DrawStatus.PENDING
            },
            acceptanceDeadline = acceptanceDeadline,
            requiresGeolocation = requiresGeolocation,
            latitude = latitude,
            longitude = longitude
        )
    }

    companion object {
        /**
         * Convert UI Event model to Firebase event
         */
        fun fromEvent(event: Event, organizerId: String = ""): FirebaseEvent {
            return FirebaseEvent(
                id = event.id,
                title = event.title,
                description = event.description,
                location = event.location,
                dateTimestamp = toTimestamp(event.date, event.eventTime),
                capacity = event.capacity,
                enrolled = event.enrolled,
                organizerId = event.organizerId.ifBlank { organizerId },
                organizerName = event.organizerName,
                waitlistCount = event.waitlistCount,
                waitlistCapacity = event.waitlistCapacity,
                qrCodeData = event.qrCodeData,
                imageUrl = event.posterUrl,
                registrationStartTimestamp = event.registrationStart?.let {
                    toTimestamp(it, event.registrationStartTime)
                },
                registrationEndTimestamp = event.registrationEnd?.let {
                    toTimestamp(it, event.registrationEndTime)
                },
                samplingCount = event.samplingCount,
                drawDateTimestamp = event.drawDate?.toTimestamp(),
                drawStatus = event.drawStatus.name,
                acceptanceDeadline = event.acceptanceDeadline,
                requiresGeolocation = event.requiresGeolocation,
                latitude = event.latitude,
                longitude = event.longitude
            )
        }
    }
}

private fun Timestamp.toLocalDateTime(): LocalDateTime =
    Instant.ofEpochSecond(seconds, nanoseconds.toLong())
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()

private fun Timestamp.toLocalDate(): LocalDate = toLocalDateTime().toLocalDate()

private fun toTimestamp(date: LocalDate, time: LocalTime?): Timestamp {
    val localDateTime = LocalDateTime.of(date, time ?: LocalTime.MIDNIGHT)
    return localDateTime.toTimestamp()
}

private fun LocalDate.toTimestamp(): Timestamp =
    toTimestamp(this, LocalTime.MIDNIGHT)

private fun LocalDateTime.toTimestamp(): Timestamp {
    val instant = atZone(ZoneId.systemDefault()).toInstant()
    return Timestamp(instant.epochSecond, instant.nano)
}
