package com.pluck.data.firebase

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import com.pluck.ui.model.Event
import com.pluck.ui.model.DrawStatus
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

/**
 * Firebase-compatible event data model
 *
 * This model is used for Firestore serialization/deserialization.
 * Converted to/from UI Event model for display.
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
        val localDate = dateTimestamp.toLocalDate()

        return Event(
            id = id,
            title = title,
            description = description,
            location = location,
            date = localDate,
            capacity = capacity,
            enrolled = enrolled,
            organizerName = organizerName,
            organizerId = organizerId,
            waitlistCount = waitlistCount,
            waitlistCapacity = waitlistCapacity,
            qrCodeData = qrCodeData.ifEmpty { id },
            posterUrl = imageUrl,
            registrationStart = registrationStartTimestamp?.toLocalDate(),
            registrationEnd = registrationEndTimestamp?.toLocalDate(),
            samplingCount = samplingCount,
            drawDate = drawDateTimestamp?.toLocalDate(),
            drawStatus = try {
                DrawStatus.valueOf(drawStatus)
            } catch (e: Exception) {
                DrawStatus.PENDING
            },
            acceptanceDeadline = acceptanceDeadline
        )
    }

    companion object {
        /**
         * Convert UI Event model to Firebase event
         */
        fun fromEvent(event: Event, organizerId: String = ""): FirebaseEvent {
            val instant = event.date.atStartOfDay(ZoneId.systemDefault()).toInstant()
            val timestamp = Timestamp(instant.epochSecond, instant.nano)

            return FirebaseEvent(
                id = event.id,
                title = event.title,
                description = event.description,
                location = event.location,
                dateTimestamp = timestamp,
                capacity = event.capacity,
                enrolled = event.enrolled,
                organizerId = event.organizerId.ifBlank { organizerId },
                organizerName = event.organizerName,
                waitlistCount = event.waitlistCount,
                waitlistCapacity = event.waitlistCapacity,
                qrCodeData = event.qrCodeData,
                imageUrl = event.posterUrl,
                registrationStartTimestamp = event.registrationStart?.toTimestamp(),
                registrationEndTimestamp = event.registrationEnd?.toTimestamp(),
                samplingCount = event.samplingCount,
                drawDateTimestamp = event.drawDate?.toTimestamp(),
                drawStatus = event.drawStatus.name,
                acceptanceDeadline = event.acceptanceDeadline
            )
        }
    }
}

private fun Timestamp.toLocalDate(): LocalDate =
    Instant.ofEpochSecond(seconds).atZone(ZoneId.systemDefault()).toLocalDate()

private fun LocalDate.toTimestamp(): Timestamp {
    val instant = atStartOfDay(ZoneId.systemDefault()).toInstant()
    return Timestamp(instant.epochSecond, instant.nano)
}
