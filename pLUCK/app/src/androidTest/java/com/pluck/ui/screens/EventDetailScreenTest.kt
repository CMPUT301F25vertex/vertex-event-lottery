package com.pluck.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.pluck.ui.model.Event
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * US 01.01.01 - As an entrant I want to join the waiting list for a specific event
 * US 01.01.02 - As an entrant I want to leave the waiting list for a specific event
 */
class EventDetailScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var fakeEvent: Event

    @Before
    fun setup() {
        fakeEvent = Event(
            id = "test-event-1",
            title = "Test Event",
            description = "This is a test event description for testing purposes.",
            location = "Test Location",
            date = LocalDate.now().plusDays(7),
            capacity = 50,
            enrolled = 20,
            organizerName = "Test Organizer",
            waitlistCapacity = 100,
            samplingCount = 10
        )
    }

//    @Test
//    fun eventDetailScreen_displaysCorrectly() {
//        composeRule.setContent {
//            EventDetailScreen(
//                event = fakeEvent,
//                isUserOnWaitlist = false,
//                isUserConfirmed = false,
//                onJoinEvent = {}
//            )
//        }
//
//        // Wait if needed
//        composeRule.waitForIdle()
//
//        // Add assertions to check if the screen is displayed correctly
//        val expectedDate = fakeEvent.date.format(DateTimeFormatter.ofPattern("MMM d, yyyy"))
//        composeRule.onRoot().printToLog("EVENT_UI")
//        composeRule.onNodeWithTag("eventTitle").assertIsDisplayed()
//        composeRule.onNodeWithTag("eventOrganizer").assertIsDisplayed()
//        composeRule.onNodeWithText(expectedDate).assertIsDisplayed()
//        composeRule.onNodeWithTag("eventLocation").assertIsDisplayed()
//        composeRule.onNodeWithTag("eventCapacity").assertIsDisplayed()
//        composeRule.onNodeWithTag("eventDescription").assertIsDisplayed()
//    }


    @Test
    fun eventDetailScreen_joinWaitlistButton_isDisplayedWhenNotOnWaitlist() {
        composeRule.setContent {
            EventDetailScreen(
                event = fakeEvent,
                isUserOnWaitlist = false,
                isUserConfirmed = false,
                onJoinEvent = {}
            )
        }

        // Verify "Join Waitlist" button is displayed and enabled
        composeRule.onNodeWithText("Join Waitlist")
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun eventDetailScreen_joinWaitlistButton_triggersCallback() {
        var joinEventCalled = false
        var capturedEvent: Event? = null

        composeRule.setContent {
            EventDetailScreen(
                event = fakeEvent,
                isUserOnWaitlist = false,
                isUserConfirmed = false,
                onJoinEvent = { event ->
                    joinEventCalled = true
                    capturedEvent = event
                }
            )
        }

        // Click the join button
        composeRule.onNodeWithText("Join Waitlist").performClick()

        // Verify callback was invoked with correct event
        assert(joinEventCalled) { "onJoinEvent callback was not called" }
        assert(capturedEvent == fakeEvent) { "Callback received wrong event" }
    }

    @Test
    fun eventDetailScreen_showsLeaveWaitlistButton_whenUserIsOnWaitlist() {
        composeRule.setContent {
            EventDetailScreen(
                event = fakeEvent,
                isUserOnWaitlist = true,
                isUserConfirmed = false,
                onJoinEvent = {},
                onLeaveWaitlist = {}
            )
        }

        // Verify "Leave Waitlist" button is displayed
        composeRule.onNodeWithText("Leave Waitlist").assertIsDisplayed()

        // Verify status message
        composeRule.onNodeWithText("You're already on this waitlist.").assertIsDisplayed()
    }

    @Test
    fun eventDetailScreen_leaveWaitlistButton_triggersCallback() {
        var leaveWaitlistCalled = false
        var capturedEvent: Event? = null

        composeRule.setContent {
            EventDetailScreen(
                event = fakeEvent,
                isUserOnWaitlist = true,
                isUserConfirmed = false,
                onJoinEvent = {},
                onLeaveWaitlist = { event ->
                    leaveWaitlistCalled = true
                    capturedEvent = event
                }
            )
        }

        // Click leave button
        composeRule.onNodeWithText("Leave Waitlist").performClick()

        // Verify callback was invoked
        assert(leaveWaitlistCalled) { "onLeaveWaitlist callback was not called" }
        assert(capturedEvent == fakeEvent) { "Callback received wrong event" }
    }

    @Test
    fun eventDetailScreen_showsReleaseSpotButton_whenUserIsConfirmed() {
        composeRule.setContent {
            EventDetailScreen(
                event = fakeEvent,
                isUserOnWaitlist = false,
                isUserConfirmed = true,
                onJoinEvent = {},
                onLeaveWaitlist = {}
            )
        }

        // Verify "Release My Spot" button is displayed
        composeRule.onNodeWithText("Release My Spot").assertIsDisplayed()

        // Verify confirmation message
        composeRule.onNodeWithText(
            "You're confirmed for this event. Tap below if you need to release your spot."
        ).assertIsDisplayed()
    }

    @Test
    fun eventDetailScreen_disablesJoinButton_whenWaitlistIsFull() {
        val fullWaitlistEvent = fakeEvent.copy(
            waitlistCount = 100,
            waitlistCapacity = 100
        )

        composeRule.setContent {
            EventDetailScreen(
                event = fullWaitlistEvent,
                isUserOnWaitlist = false,
                isUserConfirmed = false,
                onJoinEvent = {}
            )
        }

        // Verify button shows "Waitlist Full" and is disabled
        composeRule.onNodeWithText("Waitlist Full")
            .assertIsDisplayed()
            .assertIsNotEnabled()

        // Verify message
        composeRule.onNodeWithText("Waitlist is full. Check back after the next draw.")
            .assertIsDisplayed()
    }

    @Test
    fun eventDetailScreen_showsViewWaitlistButton_forOrganizer() {
        composeRule.setContent {
            EventDetailScreen(
                event = fakeEvent,
                isUserOnWaitlist = false,
                isUserConfirmed = false,
                onJoinEvent = {},
                onViewWaitlist = {},
                isEventOrganizer = true
            )
        }

        // Verify organizer sees "View Waitlist" button instead of join
        composeRule.onNodeWithText("View Waitlist").assertIsDisplayed()
    }

    @Test
    fun eventDetailScreen_viewWaitlistButton_triggersCallback() {
        var viewWaitlistCalled = false

        composeRule.setContent {
            EventDetailScreen(
                event = fakeEvent,
                isUserOnWaitlist = false,
                isUserConfirmed = false,
                onJoinEvent = {},
                onViewWaitlist = { viewWaitlistCalled = true },
                isEventOrganizer = true
            )
        }

        // Click view waitlist button
        composeRule.onNodeWithText("View Waitlist").performClick()

        // Verify callback was invoked
        assert(viewWaitlistCalled) { "onViewWaitlist callback was not called" }
    }

    @Test
    fun eventDetailScreen_showsLotteryInformation() {
        composeRule.setContent {
            EventDetailScreen(
                event = fakeEvent,
                isUserOnWaitlist = false,
                isUserConfirmed = false,
                onJoinEvent = {}
            )
        }

        // Verify lottery information is displayed (needs to exist in hierarchy)
        composeRule.onNodeWithText("Lottery & Registration").assertExists()
        composeRule.onNodeWithText("Lottery Draw Notice").assertExists()

        // Verify capacity information
        composeRule.onNodeWithText(
            "Waitlist capped at ${fakeEvent.waitlistCapacity} entrants.",
            substring = true
        ).assertExists()
    }

    @Test
    fun eventDetailScreen_showsPastEventMessage_forPastEvents() {
        val pastEvent = fakeEvent.copy(
            date = LocalDate.now().minusDays(1)
        )

        composeRule.setContent {
            EventDetailScreen(
                event = pastEvent,
                isUserOnWaitlist = false,
                isUserConfirmed = false,
                onJoinEvent = {}
            )
        }

        // Verify past event message is shown
        composeRule.onNodeWithText("This event has already occurred").assertIsDisplayed()
    }
}
