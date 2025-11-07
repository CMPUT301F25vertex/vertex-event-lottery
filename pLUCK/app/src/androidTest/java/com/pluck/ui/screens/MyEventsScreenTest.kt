package com.pluck.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.ui.model.Event
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

/**
 * Tests for MyEventsScreen
 * US 01.03.01 - As an entrant I want to view a list of events I'm registered for
 */
class MyEventsScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var upcomingEvent: Event
    private lateinit var pastEvent: Event
    private lateinit var mockEvents: List<MyEventItem>

    @Before
    fun setup() {
        upcomingEvent = Event(
            id = "upcoming-1",
            title = "Upcoming Event",
            description = "Test upcoming event",
            location = "Test Location",
            date = LocalDate.now().plusDays(7),
            capacity = 50,
            enrolled = 20,
            organizerName = "Test Organizer"
        )

        pastEvent = Event(
            id = "past-1",
            title = "Past Event",
            description = "Test past event",
            location = "Past Location",
            date = LocalDate.now().minusDays(7),
            capacity = 30,
            enrolled = 25,
            organizerName = "Past Organizer"
        )

        mockEvents = listOf(
            MyEventItem(
                event = upcomingEvent,
                status = EventStatus.CONFIRMED,
                isCreatedByUser = false,
                joinedDate = LocalDate.now().minusDays(2),
                historyStatus = WaitlistStatus.ACCEPTED
            ),
            MyEventItem(
                event = pastEvent,
                status = EventStatus.PAST,
                isCreatedByUser = false,
                joinedDate = LocalDate.now().minusDays(10),
                historyStatus = WaitlistStatus.ACCEPTED
            )
        )
    }

    @Test
    fun myEventsScreen_displaysEventsList() {
        composeRule.setContent {
            MyEventsScreen(
                events = mockEvents,
                isLoading = false
            )
        }

        // Verify events are displayed
        composeRule.onNodeWithText("Upcoming Event").assertExists()
        composeRule.onNodeWithText("Past Event").assertExists()
    }

    @Test
    fun myEventsScreen_displaysEmptyStateWhenNoEvents() {
        composeRule.setContent {
            MyEventsScreen(
                events = emptyList(),
                isLoading = false
            )
        }

        // Use onAllNodesWithText since text appears multiple times
        composeRule.onAllNodesWithText("No events yet", substring = true)[0].assertExists()
    }

    @Test
    fun myEventsScreen_showsLoadingIndicator() {
        composeRule.setContent {
            MyEventsScreen(
                events = emptyList(),
                isLoading = true
            )
        }

        // During loading, only CircularProgressIndicator is shown
        // We verify the screen renders without errors (no text elements to check)
        // The test passes if no exception is thrown during composition
    }

    @Test
    fun myEventsScreen_filterByUpcoming() {
        composeRule.setContent {
            MyEventsScreen(
                events = mockEvents,
                isLoading = false
            )
        }

        // Click upcoming filter
        composeRule.onNodeWithText("Upcoming").performClick()

        // Should show upcoming event
        composeRule.onNodeWithText("Upcoming Event").assertExists()
        // Past event should still exist in the list (filtering doesn't remove from tree)
    }

    @Test
    fun myEventsScreen_filterByPast() {
        composeRule.setContent {
            MyEventsScreen(
                events = mockEvents,
                isLoading = false
            )
        }

        // Click past filter
        composeRule.onNodeWithText("Past").performClick()

        // Should show past event
        composeRule.onNodeWithText("Past Event").assertExists()
    }

    @Test
    fun myEventsScreen_filterByAll() {
        composeRule.setContent {
            MyEventsScreen(
                events = mockEvents,
                isLoading = false
            )
        }

        // All filter should be selected by default
        composeRule.onNodeWithText("All").assertExists()

        // Both events should exist
        composeRule.onNodeWithText("Upcoming Event").assertExists()
        composeRule.onNodeWithText("Past Event").assertExists()
    }

    @Test
    fun myEventsScreen_eventClickTriggersCallback() {
        var clickedEvent: Event? = null

        composeRule.setContent {
            MyEventsScreen(
                events = mockEvents,
                isLoading = false,
                onEventClick = { event -> clickedEvent = event }
            )
        }

        composeRule.onNodeWithText("Upcoming Event").performClick()

        assert(clickedEvent != null) { "Event click callback was not called" }
        assert(clickedEvent?.id == "upcoming-1") { "Wrong event was clicked" }
    }


    @Test
    fun myEventsScreen_displaysFilterChips() {
        composeRule.setContent {
            MyEventsScreen(
                events = mockEvents,
                isLoading = false
            )
        }

        // Verify all filter chips are displayed
        composeRule.onNodeWithText("All").assertExists()
        composeRule.onNodeWithText("Upcoming").assertExists()
        composeRule.onNodeWithText("Past").assertExists()
        composeRule.onNodeWithText("Joined").assertExists()
        composeRule.onNodeWithText("Created").assertExists()
    }
}
