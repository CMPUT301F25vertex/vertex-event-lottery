package com.pluck.ui.model

import java.time.LocalDate

object EventRepository {
    private val sampleEvents = listOf(
        Event(
            id = "1",
            title = "City Night Market",
            description = "Experience the vibrant night market with curated food stalls, live performances, and interactive installations across the downtown plaza.",
            location = "Downtown Plaza",
            date = LocalDate.now().plusDays(5),
            capacity = 200,
            enrolled = 168,
            organizerName = "Events Team",
            waitlistCount = 45,
            waitlistCapacity = 400
        ),
        Event(
            id = "2",
            title = "Community Hackathon",
            description = "A weekend-long collaborative hackathon focused on solving community challenges with technology and creative ideas.",
            location = "Innovation Lab",
            date = LocalDate.now().plusDays(12),
            capacity = 120,
            enrolled = 96,
            organizerName = "Innovation Collective",
            waitlistCount = 28,
            waitlistCapacity = 240
        ),
        Event(
            id = "3",
            title = "Artists in the Park",
            description = "Outdoor art walk showcasing local artists, live mural painting, workshops, and food trucks.",
            location = "Riverview Park",
            date = LocalDate.now().plusDays(20),
            capacity = 300,
            enrolled = 212,
            organizerName = "City Arts Council",
            waitlistCount = 67,
            waitlistCapacity = 600
        )
    )

    fun getEvents(): List<Event> = sampleEvents

    fun getEvent(id: String): Event? = sampleEvents.find { it.id == id }
}
