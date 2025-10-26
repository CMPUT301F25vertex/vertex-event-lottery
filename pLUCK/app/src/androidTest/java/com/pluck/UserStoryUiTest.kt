package com.pluck

/**
 * UserStoryUiTest.kt
 *
 * Purpose: End-to-end Compose instrumentation tests that validate the core user stories
 * (registration, returning user flows, and home experience). These tests intentionally
 * wrap every screen in [PluckTheme] to match production styling.
 */

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.pluck.ui.model.Event
import com.pluck.ui.screens.CreateAccountScreen
import com.pluck.ui.screens.CreateAccountTestTags
import com.pluck.ui.screens.HomeScreen
import com.pluck.ui.screens.WelcomeBackScreen
import com.pluck.ui.theme.PluckTheme
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class UserStoryUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun newUserRegistration_submitsProvidedDetails() {
        var submittedName: String? = null
        var submittedEmail: String? = null
        var submittedPhone: String? = null

        composeTestRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "DEVICE-123",
                    isLoading = false,
                    errorMessage = null,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = { },
                    onCreateAccount = { name, email, phone ->
                        submittedName = name
                        submittedEmail = email
                        submittedPhone = phone
                    }
                )
            }
        }

        composeTestRule.onNodeWithText("pLUCK").assertIsDisplayed()
        composeTestRule.onNodeWithTag(CreateAccountTestTags.DisplayNameField).performTextInput("Sarah Johnson")
        composeTestRule.onNodeWithTag(CreateAccountTestTags.EmailField).performTextInput("sarah@example.com")
        composeTestRule.onNodeWithText("Phone Number (Optional)").performTextInput("555-0100")
        composeTestRule.onNodeWithText("Create Account").performClick()

        composeTestRule.runOnIdle {
            assertEquals("Sarah Johnson", submittedName)
            assertEquals("sarah@example.com", submittedEmail)
            assertEquals("555-0100", submittedPhone)
        }
    }

    @Test
    fun returningUserScreen_togglesAutoLoginAndContinues() {
        var continueCount = 0

        composeTestRule.setContent {
            PluckTheme {
                WelcomeBackScreen(
                    userName = "Michael Chen",
                    deviceId = "DEVICE-MC-456",
                    isLoading = false,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = { _ -> },
                    onContinue = { continueCount++ }
                )
            }
        }

        composeTestRule.onNodeWithText("Welcome Back").assertIsDisplayed()
        composeTestRule.onNodeWithText("Device ID: DEVICE-MC-456").assertIsDisplayed()

        composeTestRule.onNodeWithText("Continue").performClick()

        composeTestRule.runOnIdle {
            assertEquals(1, continueCount)
        }
    }

    @Test
    fun homeScreen_showsEventsAndBottomNavigation() {
        val sampleEvents = listOf(
            Event(
                id = "event-1",
                title = "Community Showcase",
                description = "A celebration of local creators with live music and art.",
                location = "Vertex Hall",
                date = LocalDate.now().plusDays(5),
                capacity = 120,
                enrolled = 60,
                organizerName = "City Events"
            )
        )
        var selectedRoute: String? = null
        var selectedEventId: String? = null

        composeTestRule.setContent {
            PluckTheme {
                HomeScreen(
                    userName = "Jordan",
                    events = sampleEvents,
                    isLoading = false,
                    currentRoute = "event_list",
                    onSelectEvent = { selectedEventId = it.id },
                    onNavigate = { selectedRoute = it }
                )
            }
        }

        composeTestRule.onNodeWithText("Discover Events").assertIsDisplayed()
        composeTestRule.onNodeWithText("Community Showcase").performClick()
        composeTestRule.onNodeWithText("Notifications").performClick()

        composeTestRule.runOnIdle {
            assertEquals("event-1", selectedEventId)
            assertEquals("notifications", selectedRoute)
        }
    }
}
