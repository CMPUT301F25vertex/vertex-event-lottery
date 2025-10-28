package com.pluck.ui.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime

class EventTest {

    @Test
    fun dateLabel_includesFormattedTimeWhenProvided() {
        val event = Event(
            id = "event-1",
            title = "Evening Workshop",
            description = "Learning Kotlin together.",
            location = "Main Hall",
            date = LocalDate.of(2025, 1, 10),
            eventTime = LocalTime.of(18, 45),
            capacity = 30,
            enrolled = 10,
            organizerName = "Vertex Community"
        )

        val label = event.dateLabel

        assertTrue("Date label should contain month and day", label.contains("Jan 10, 2025"))
        assertTrue("Date label should contain time in h:mm a format", label.contains("6:45 PM"))
    }

    @Test
    fun isRegistrationOpen_respectsStartAndEndDateTimes() {
        val today = LocalDate.now()
        val event = Event(
            id = "event-2",
            title = "Future Experience",
            description = "Test registration window handling.",
            location = "Innovation Hub",
            date = today.plusDays(5),
            eventTime = LocalTime.NOON,
            capacity = 20,
            enrolled = 0,
            organizerName = "Vertex Community",
            registrationStart = today.minusDays(2),
            registrationStartTime = LocalTime.of(8, 0),
            registrationEnd = today.plusDays(2),
            registrationEndTime = LocalTime.of(21, 0)
        )

        assertTrue("Registration should be open within the configured window.", event.isRegistrationOpen)

        val notOpenYet = event.copy(
            registrationStart = today.plusDays(1),
            registrationStartTime = LocalTime.of(9, 0)
        )
        assertFalse("Registration should be closed before the start date.", notOpenYet.isRegistrationOpen)

        val alreadyClosed = event.copy(
            registrationEnd = today.minusDays(1),
            registrationEndTime = LocalTime.of(15, 0)
        )
        assertFalse("Registration should close after the end date passes.", alreadyClosed.isRegistrationOpen)
    }

    @Test
    fun eventDateTime_combinesDateAndTime() {
        val event = Event(
            id = "event-3",
            title = "Morning Session",
            description = "Combining date and time.",
            location = "Room 101",
            date = LocalDate.of(2025, 4, 20),
            eventTime = LocalTime.of(9, 30),
            capacity = 12,
            enrolled = 4,
            organizerName = "Vertex Community"
        )

        val expected = event.eventDateTime
        assertEquals(2025, expected.year)
        assertEquals(4, expected.monthValue)
        assertEquals(20, expected.dayOfMonth)
        assertEquals(9, expected.hour)
        assertEquals(30, expected.minute)
    }
}

