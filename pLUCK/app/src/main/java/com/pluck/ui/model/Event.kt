package com.pluck.ui.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
 * @property capacity Maximum confirmed attendees.
 * @property enrolled Current confirmed attendee count.
 * @property organizerName Display name of the hosting organization.
 * @property organizerId Optional stable organizer identifier used for filtering.
 * @property waitlistCount Currently queued entrants for replacement draws.
 * @property waitlistCapacity Maximum entrants allowed on the waitlist.
 * @property qrCodeData Encoded payload rendered inside QR check-in surfaces.
 * @property posterUrl Optional remote asset associated with the event.
 * @property registrationStart Optional opening date for registration.
 * @property registrationEnd Optional closing date for registration.
 * @property samplingCount Number of entrants to sample in a lottery draw.
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
    /**
     * @return A preview-friendly description limited to 120 characters.
     */
    val shortDescription: String
        get() = description.take(120)

    /**
     * @return Formatted date string suitable for cards and summaries.
     */
    val dateLabel: String
        get() = date.format(DATE_FORMATTER)

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
            val today = LocalDate.now()
            val startOk = registrationStart?.let { !today.isBefore(it) } ?: true
            val endOk = registrationEnd?.let { !today.isAfter(it) } ?: true
            return startOk && endOk
        }

    /**
     * @return True when the event date has passed.
     */
    val isPastEvent: Boolean
        get() = date.isBefore(LocalDate.now())

    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy")
    }
}
