package com.pluck.ui.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val location: String,
    val date: LocalDate,
    val capacity: Int,
    val enrolled: Int,
    val organizerName: String,
    val waitlistCount: Int = 0,
    val waitlistCapacity: Int = capacity * 2 // Default waitlist is 2x event capacity
) {
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
        waitlistCount = 0,
        waitlistCapacity = capacity * 2
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

    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy")
    }
}
