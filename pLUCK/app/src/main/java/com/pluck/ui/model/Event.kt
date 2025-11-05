package com.pluck.ui.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Event.kt
 *
 * Purpose: Defines the immutable domain model consumed across UI layers for describing a single
 * lottery-backed experience. The helper properties keep UI logic declarative by precomputing
 * derived state such as waitlist availability.
 *
 * Outstanding Issues: None.
 */
/**
 * Immutable representation of an event displayed throughout pLUCK.
 *
 * @property id Stable identifier used for navigation, persistence, and ticketing.
 * @property title User-facing title rendered in feeds and detail surfaces.
 * @property description Markdown-safe description shown in detail views.
 * @property location Human-readable location string surfaced to entrants.
 * @property date Calendar date on which the event begins.
 * @property eventTime Optional time of day when the event begins.
 * @property capacity Maximum confirmed attendees (seats available).
 * @property enrolled Current confirmed attendee count.
 * @property organizerName Display name of the hosting organization.
 * @property organizerId Optional stable organizer identifier used for filtering.
 * @property waitlistCount Currently queued entrants for replacement draws.
 * @property waitlistCapacity Maximum entrants allowed on the waitlist.
 * @property qrCodeData Encoded payload rendered inside QR check-in surfaces.
 * @property posterUrl Optional remote asset associated with the event.
 * @property registrationStart Optional opening date for registration.
 * @property registrationStartTime Optional opening time for registration.
 * @property registrationEnd Optional closing date for registration.
 * @property registrationEndTime Optional closing time for registration.
 * @property samplingCount Number of entrants to sample in a lottery draw.
 * @property drawDate Date when the lottery draw will be executed. Defaults to event date if not specified.
 * @property drawStatus Current status of the draw (PENDING, COMPLETED, CANCELLED).
 * @property acceptanceDeadline Deadline for selected entrants to accept their invitation (hours after draw).
 * @property requiresGeolocation Whether entrants must provide location when joining waitlist (US 02.02.03).
 * @property latitude Optional latitude coordinate for event location.
 * @property longitude Optional longitude coordinate for event location.
 */
data class Event(
    val id: String,
    val title: String,
    val description: String,
    val location: String,
    val date: LocalDate,
    val eventTime: LocalTime? = null,
    val capacity: Int,
    val enrolled: Int,
    val organizerName: String,
    val organizerId: String = "",
    val waitlistCount: Int = 0,
    val waitlistCapacity: Int = capacity * 2,
    val qrCodeData: String = id,
    val posterUrl: String? = null,
    val registrationStart: LocalDate? = null,
    val registrationStartTime: LocalTime? = null,
    val registrationEnd: LocalDate? = null,
    val registrationEndTime: LocalTime? = null,
    val samplingCount: Int = 0,
    val drawDate: LocalDate? = null,
    val drawStatus: DrawStatus = DrawStatus.PENDING,
    val acceptanceDeadline: Int = 24,
    val requiresGeolocation: Boolean = false,
    val latitude: Double? = null,
    val longitude: Double? = null
) {
    /**
     * @return A preview-friendly description limited to 120 characters.
     */
    val shortDescription: String
        get() = description.take(120)

    /**
     * @return Formatted date string with time suitable for cards and summaries.
     */
    val dateLabel: String
        get() = eventTime?.let { time ->
            "${date.format(DATE_FORMATTER)} at ${time.format(TIME_FORMATTER)}"
        } ?: date.format(DATE_FORMATTER)

    /**
     * @return Formatted date string suitable for cards and summaries.
     */
    val dateNoTimeLabel: String
        get() = date.format(DATE_FORMATTER)

    /**
     * @return Combined date and time for the event start.
     */
    val eventDateTime: LocalDateTime
        get() = LocalDateTime.of(date, eventTime ?: LocalTime.MIDNIGHT)

    /**
     * @return Remaining confirmed spots before the event reaches capacity.
     */
    val remainingSpots: Int
        get() = (capacity - enrolled).coerceAtLeast(0)

    /**
     * @return Remaining waitlist capacity after accounting for queued entrants.
     */
    val waitlistAvailable: Int
        get() = (waitlistCapacity - waitlistCount).coerceAtLeast(0)

    /**
     * @return True when the waitlist has reached its configured capacity.
     */
    val isWaitlistFull: Boolean
        get() = waitlistCount >= waitlistCapacity

    /**
     * @return True when confirmed entrants match or exceed capacity.
     */
    val isFull: Boolean
        get() = enrolled >= capacity

    /**
     * @return True when the registration window currently accepts entrants.
     */
    val isRegistrationOpen: Boolean
        get() {
            val now = LocalDateTime.now()
            val startOk = registrationStart?.let { startDate ->
                val startDateTime = LocalDateTime.of(startDate, registrationStartTime ?: LocalTime.MIDNIGHT)
                !now.isBefore(startDateTime)
            } ?: true
            val endOk = registrationEnd?.let { endDate ->
                val endDateTime = LocalDateTime.of(endDate, registrationEndTime ?: LocalTime.MAX)
                !now.isAfter(endDateTime)
            } ?: true
            return startOk && endOk
        }

    /**
     * @return True when the event date has passed.
     */
    val isPastEvent: Boolean
        get() = eventDateTime.isBefore(LocalDateTime.now())

    /**
     * @return The actual draw date (uses drawDate if specified, otherwise falls back to event date).
     */
    val effectiveDrawDate: LocalDate
        get() = drawDate ?: date

    /**
     * @return True when the draw date has arrived or passed.
     */
    val isDrawDateReached: Boolean
        get() = !effectiveDrawDate.isAfter(LocalDate.now())

    /**
     * @return True when the organizer can manually trigger the draw early.
     */
    val canRunDrawEarly: Boolean
        get() = drawStatus == DrawStatus.PENDING &&
            samplingCount > 0 &&
            waitlistCount > 0

    /**
     * @return True when the draw has been completed.
     */
    val isDrawComplete: Boolean
        get() = drawStatus == DrawStatus.COMPLETED

    /**
     * @return Formatted draw date string.
     */
    val drawDateLabel: String
        get() = effectiveDrawDate.format(DATE_FORMATTER)

    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.US)
        private val TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mm a", Locale.US)
    }
}

/**
 * Represents the status of the lottery draw for an event.
 */
enum class DrawStatus {
    /** Draw has not been executed yet */
    PENDING,
    /** Draw has been completed and winners selected */
    COMPLETED,
    /** Draw was cancelled by organizer */
    CANCELLED
}
