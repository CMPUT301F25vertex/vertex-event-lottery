package com.pluck.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.pluck.ui.model.Event
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

/**
 * Tests for WaitlistScreen
 * US 02.01.01 - As an organizer I want to view list of entrants
 * US 02.01.03 - As an organizer I want to see selected entrants
 */
class WaitlistScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var mockEvent: Event
    private lateinit var mockWaitlistEntries: List<WaitlistEntry>
    private lateinit var mockChosenEntries: List<WaitlistEntry>

    @Before
    fun setup() {
        mockEvent = Event(
            id = "test-event",
            title = "Test Event",
            description = "Test Description",
            location = "Test Location",
            date = LocalDate.now().plusDays(7),
            capacity = 50,
            enrolled = 10,
            organizerName = "Test Organizer",
            waitlistCapacity = 100
        )

        mockWaitlistEntries = listOf(
            WaitlistEntry(
                id = "entry1",
                userId = "user1",
                userName = "Alice Smith",
                position = 1,
                joinedDate = LocalDate.now().minusDays(3),
                isCurrentUser = false
            ),
            WaitlistEntry(
                id = "entry2",
                userId = "user2",
                userName = "Bob Jones",
                position = 2,
                joinedDate = LocalDate.now().minusDays(2),
                isCurrentUser = false
            )
        )

        mockChosenEntries = listOf(
            WaitlistEntry(
                id = "chosen1",
                userId = "user3",
                userName = "Charlie Brown",
                position = 1,
                joinedDate = LocalDate.now().minusDays(5),
                isChosen = true
            )
        )
    }

    @Test
    fun waitlistScreen_displaysEventTitle() {
        composeRule.setContent {
            WaitlistScreen(
                event = mockEvent,
                waitlistEntries = mockWaitlistEntries
            )
        }

        composeRule.onNodeWithText(mockEvent.title).assertIsDisplayed()
    }

    @Test
    fun waitlistScreen_displaysWaitlistEntries() {
        composeRule.setContent {
            WaitlistScreen(
                event = mockEvent,
                waitlistEntries = mockWaitlistEntries
            )
        }

        // Verify waitlist entries are displayed
        composeRule.onNodeWithText("Alice Smith").assertExists()
        composeRule.onNodeWithText("Bob Jones").assertExists()
    }

    @Test
    fun waitlistScreen_displaysEmptyStateWhenNoEntries() {
        composeRule.setContent {
            WaitlistScreen(
                event = mockEvent,
                waitlistEntries = emptyList()
            )
        }

        composeRule.onNodeWithText("No one on the waitlist yet").assertExists()
    }

    @Test
    fun waitlistScreen_switchesToChosenTab() {
        composeRule.setContent {
            WaitlistScreen(
                event = mockEvent,
                waitlistEntries = mockWaitlistEntries,
                chosenEntries = mockChosenEntries
            )
        }

        // Click on chosen tab
        composeRule.onNodeWithText("Plucked (1)").performClick()

        // Verify chosen entrant is displayed
        composeRule.onNodeWithText("Charlie Brown").assertExists()
    }

    @Test
    fun waitlistScreen_displaysEmptyStateForChosenWhenNoneSelected() {
        composeRule.setContent {
            WaitlistScreen(
                event = mockEvent,
                waitlistEntries = mockWaitlistEntries,
                chosenEntries = emptyList()
            )
        }

        // Switch to chosen tab
        composeRule.onNodeWithText("Plucked (0)").performClick()

        composeRule.onNodeWithText("No entrants plucked yet").assertExists()
    }

    @Test
    fun waitlistScreen_displaysBothWaitingAndChosenCounts() {
        composeRule.setContent {
            WaitlistScreen(
                event = mockEvent,
                waitlistEntries = mockWaitlistEntries,
                chosenEntries = mockChosenEntries
            )
        }

        // Verify tab labels show counts
        composeRule.onNodeWithText("Waiting (2)").assertIsDisplayed()
        composeRule.onNodeWithText("Plucked (1)").assertIsDisplayed()
    }

    @Test
    fun waitlistScreen_backButtonTriggersCallback() {
        var backCalled = false

        composeRule.setContent {
            WaitlistScreen(
                event = mockEvent,
                waitlistEntries = mockWaitlistEntries,
                onBack = { backCalled = true }
            )
        }

        composeRule.onNodeWithContentDescription("Back").performClick()

        assert(backCalled) { "onBack callback was not called" }
    }
}
