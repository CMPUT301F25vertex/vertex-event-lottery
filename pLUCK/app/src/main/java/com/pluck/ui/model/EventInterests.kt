package com.pluck.ui.model

/**
 * EventInterests.kt
 *
 * Purpose: Defines the predefined set of interests/tags that organizers can apply to events
 * and entrants can use to filter the event feed.
 */

data class EventInterest(
    val id: String,
    val label: String
)

object EventInterests {
    val all: List<EventInterest> = listOf(
        EventInterest("athletics", "Athletics"),
        EventInterest("social", "Social"),
        EventInterest("arts", "Arts"),
        EventInterest("music", "Music"),
        EventInterest("tech", "Tech"),
        EventInterest("academic", "Academic"),
        EventInterest("volunteer", "Volunteer"),
        EventInterest("food", "Food")
    )

    val byId: Map<String, EventInterest> = all.associateBy { it.id }
}
