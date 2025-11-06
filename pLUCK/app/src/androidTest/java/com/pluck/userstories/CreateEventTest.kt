package com.pluck.userstories

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import com.pluck.ui.screens.CreateAccountTestTags
import com.pluck.ui.screens.CreateEventScreen
import com.pluck.ui.screens.CreateEventTestTags
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

class CreateEventTest {
    @get:Rule
    val composeRule = createComposeRule()

    // US 02.05.02 As an organizer I want to set the system to sample a specified number of
    // attendees to register for the event.
    // TODO

    // US 02.04.01 As an organizer I want to upload an event poster to the event details page to
    // provide visual information to entrants.
    // TODO

    // US 02.01.04 As an organizer, I want to set a registration period.
    @Test
    fun test_set_registration_period() {
        composeRule.setContent {
            CreateEventScreen()
        }

//        composeRule.onNodeWithText("Registration Opens (Date) *").printToLog("TESTING_TAG")

        // TODO
    }

    // US 02.03.01 As an organizer I want to OPTIONALLY limit the number of entrants who can join
    // my waiting list.
    @Test
    fun test_set_waitlist_limit() {
        composeRule.setContent {
            CreateEventScreen()
        }

//        composeRule.onNodeWithTag(CreateEventTestTags.WaitlistLength).performTextInput("47")

        // TODO
    }
}
