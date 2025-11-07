package com.pluck.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.pluck.ui.model.Event
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

/**
 * Tests for HomeScreen
 * US 01.02.01 - As an entrant I want to browse events
 * US 01.02.02 - As an entrant I want to search and filter events
 */
class HomeScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var mockEvents: List<Event>

    @Before
    fun setup() {
        mockEvents = listOf(
            Event(
                id = "event1",
                title = "Swimming Lessons",
                description = "Learn to swim",
                location = "City Pool",
                date = LocalDate.now().plusDays(5),
                capacity = 30,
                enrolled = 15,
                organizerName = "Pool Manager"
            ),
            Event(
                id = "event2",
                title = "Art Workshop",
                description = "Creative painting",
                location = "Art Studio",
                date = LocalDate.now().plusDays(10),
                capacity = 20,
                enrolled = 5,
                organizerName = "Artist"
            ),
            Event(
                id = "event3",
                title = "Coding Bootcamp",
                description = "Learn programming",
                location = "Tech Hub",
                date = LocalDate.now().plusDays(15),
                capacity = 50,
                enrolled = 50,
                organizerName = "Tech Lead"
            )
        )
    }

//    @Test
//    fun homeScreen_displaysAvailableEvents() {
//        composeRule.setContent {
//            HomeScreen(
//                userName = "Test User",
//                events = mockEvents,
//                isLoading = false,
//                currentRoute = "home",
//                onSelectEvent = {},
//                onNavigate = {}
//            )
//        }
//
//        // Verify events are displayed
//        composeRule.onNodeWithText("Swimming Lessons").assertExists()
//        composeRule.onNodeWithText("Art Workshop").assertExists()
//        composeRule.onNodeWithText("Coding Bootcamp").assertExists()
//    }
//

    @Test
    fun homeScreen_displaysEmptyStateWhenNoEvents() {
        composeRule.setContent {
            HomeScreen(
                userName = "Test User",
                events = emptyList(),
                isLoading = false,
                currentRoute = "home",
                onSelectEvent = {},
                onNavigate = {}
            )
        }

        composeRule.onNodeWithText("No events match your filters just yet.", substring = true).assertExists()
    }

    @Test
    fun homeScreen_eventClickTriggersCallback() {
        var clickedEvent: Event? = null

        composeRule.setContent {
            HomeScreen(
                userName = "Test User",
                events = mockEvents,
                isLoading = false,
                currentRoute = "home",
                onSelectEvent = { event -> clickedEvent = event },
                onNavigate = {}
            )
        }

        composeRule.onNodeWithText("Swimming Lessons").performClick()

        assert(clickedEvent != null) { "Event click callback was not called" }
        assert(clickedEvent?.id == "event1") { "Wrong event was clicked" }
    }

    @Test
    fun homeScreen_displaysFilterCategories() {
        composeRule.setContent {
            HomeScreen(
                userName = "Test User",
                events = mockEvents,
                isLoading = false,
                currentRoute = "home",
                onSelectEvent = {},
                onNavigate = {}
            )
        }

        // Verify category filters are displayed
        composeRule.onNodeWithText("All").assertExists()
        composeRule.onNodeWithText("Upcoming").assertExists()
        composeRule.onNodeWithText("Available").assertExists()
    }

    @Test
    fun homeScreen_filterCategoryClick() {
        composeRule.setContent {
            HomeScreen(
                userName = "Test User",
                events = mockEvents,
                isLoading = false,
                currentRoute = "home",
                onSelectEvent = {},
                onNavigate = {}
            )
        }

        // Click a filter category
        composeRule.onNodeWithText("Upcoming").performClick()

        // Events should still be visible (filtering logic works)
        composeRule.onNodeWithText("Swimming Lessons").assertExists()
    }



}
