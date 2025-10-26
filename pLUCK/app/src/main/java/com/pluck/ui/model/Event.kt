package com.pluck.ui.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.DeprecationLevel

/**
 * Immutable representation of an event displayed throughout pLUCK.
 *
 * The primary constructor exposes all metadata used by newer screens. Secondary constructors keep
 * backwards compatibility with the precompiled instrumentation suite that still instantiates
 * events without the waitlist/qr parameters.
 */
data class Event(
    val id: String,
    val title: String,
    val description: String,
    val location: String,
    val date: LocalDate,
    val capacity: Int,
    val enrolled: Int,
    val organizerName: String,
    val organizerId: String = "",
    val waitlistCount: Int = 0,
    val waitlistCapacity: Int = capacity * 2,
    val qrCodeData: String = id,
    val posterUrl: String? = null,
    val registrationStart: LocalDate? = null,
    val registrationEnd: LocalDate? = null,
    val samplingCount: Int = 0
) {
    /** Legacy overload kept for instrumentation builds that predate waitlist metadata. */
    constructor(
        id: String,
        title: String,
        description: String,
        location: String,
        date: LocalDate,
        capacity: Int,
        enrolled: Int,
        organizerName: String
    ) : this(
        id = id,
        title = title,
        description = description,
        location = location,
        date = date,
        capacity = capacity,
        enrolled = enrolled,
        organizerName = organizerName,
        organizerId = "",
        waitlistCount = 0,
        waitlistCapacity = capacity * 2
    )

    /**
     * Binary compatibility shim matching the constructor baked into the instructor APK.
     * The extra [legacyImageRes] parameter is ignored at runtime but keeps the JVM signature stable.
     */
    @Deprecated(
        message = "Binary compatibility shim for instrumentation APKs expecting a legacy image resource parameter.",
        level = DeprecationLevel.HIDDEN
    )
    constructor(
        id: String,
        title: String,
        description: String,
        location: String,
        date: LocalDate,
        capacity: Int,
        enrolled: Int,
        organizerName: String,
        waitlistCount: Int,
        waitlistCapacity: Int,
        @Suppress("UNUSED_PARAMETER") legacyImageRes: Int
    ) : this(
        id = id,
        title = title,
        description = description,
        location = location,
        date = date,
        capacity = capacity,
        enrolled = enrolled,
        organizerName = organizerName,
        organizerId = "",
        waitlistCount = waitlistCount,
        waitlistCapacity = waitlistCapacity
    )

    val shortDescription: String
        get() = description.take(120)

    val dateLabel: String
        get() = date.format(DATE_FORMATTER)

    val remainingSpots: Int
        get() = (capacity - enrolled).coerceAtLeast(0)

    val waitlistAvailable: Int
        get() = (waitlistCapacity - waitlistCount).coerceAtLeast(0)

    val isWaitlistFull: Boolean
        get() = waitlistCount >= waitlistCapacity

    val isFull: Boolean
        get() = enrolled >= capacity

    val isRegistrationOpen: Boolean
        get() {
            val today = LocalDate.now()
            val startOk = registrationStart?.let { !today.isBefore(it) } ?: true
            val endOk = registrationEnd?.let { !today.isAfter(it) } ?: true
            return startOk && endOk
        }

    val isPastEvent: Boolean
        get() = date.isBefore(LocalDate.now())

    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy")
    }
}
