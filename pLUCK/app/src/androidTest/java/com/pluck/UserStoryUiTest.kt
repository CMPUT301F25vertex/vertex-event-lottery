package com.pluck

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pluck.ui.model.Event
import com.pluck.ui.screens.CreateAccountScreen
import com.pluck.ui.screens.CreateAccountTestTags
import com.pluck.ui.screens.HomeScreen
import com.pluck.ui.screens.WelcomeBackScreen
import com.pluck.ui.screens.WelcomeBackTestTags
import com.pluck.ui.theme.PluckTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class UserStoryUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun registrationFlow_submitsProvidedDetails() {
        var submittedName: String? = null
        var submittedEmail: String? = null
        var submittedPhone: String? = null

        composeRule.setContent {
            PluckTheme {
                CreateAccountScreen(
                    deviceId = "DEVICE-123",
                    isLoading = false,
                    errorMessage = null,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = {},
                    onCreateAccount = { name, email, phone ->
                        submittedName = name
                        submittedEmail = email
                        submittedPhone = phone
                    }
                )
            }
        }

        composeRule.onNodeWithTag(CreateAccountTestTags.DisplayNameField)
            .performTextInput("Sarah Johnson")
        composeRule.onNodeWithTag(CreateAccountTestTags.EmailField)
            .performTextInput("sarah@example.com")
        composeRule.onNodeWithTag(CreateAccountTestTags.PhoneField)
            .performTextInput("555-0100")
        composeRule.onNodeWithText("Create Account").assertIsEnabled().performClick()

        composeRule.runOnIdle {
            assertEquals("Sarah Johnson", submittedName)
            assertEquals("sarah@example.com", submittedEmail)
            assertEquals("555-0100", submittedPhone)
        }
    }

    @Test
    fun returningUser_canToggleAutoLoginAndContinue() {
        var toggleState: Boolean? = null
        var continueCount = 0

        composeRule.setContent {
            PluckTheme {
                WelcomeBackScreen(
                    userName = "Michael Chen",
                    deviceId = "DEVICE-MC-456",
                    isLoading = false,
                    autoLoginEnabled = false,
                    onAutoLoginToggle = { toggleState = it },
                    onContinue = { continueCount++ }
                )
            }
        }

        composeRule.onNodeWithText("Welcome Back").assertIsDisplayed()

        composeRule.onNodeWithTag(WelcomeBackTestTags.AutoLoginToggle).performClick()
        composeRule.onNodeWithText("Continue").performClick()

        composeRule.runOnIdle {
            assertEquals(true, toggleState)
            assertEquals(1, continueCount)
        }
    }

    @Test
    fun homeExperience_supportsEventSelectionAndNavigation() {
        val sampleEvents = listOf(
            Event(
                id = "event-1",
                title = "Community Showcase",
                description = "A celebration of local creators with live music and art.",
                location = "Vertex Hall",
                date = LocalDate.now().plusDays(5),
                capacity = 120,
                enrolled = 60,
                organizerName = "City Events",
                waitlistCount = 0,
                qrCodeData = "event-1"
            )
        )
        var selectedRoute: String? = null
        var selectedEventId: String? = null

        composeRule.setContent {
            PluckTheme {
                HomeScreen(
                    userName = "Jordan",
                    events = sampleEvents,
                    isLoading = false,
                    currentRoute = "event_list",
                    onSelectEvent = { selectedEventId = it.id },
                    onNavigate = { selectedRoute = it },
                    onScanQRCode = {},
                    dashboards = emptyList(),
                    currentUserId = "",
                    isRefreshing = false,
                    notificationCount = 0
                )
            }
        }

        composeRule.onNodeWithText("Community Showcase").performClick()
        composeRule.onNodeWithTag("test_tag_notifications").performClick()

        composeRule.runOnIdle {
            assertEquals("event-1", selectedEventId)
            assertEquals("notifications", selectedRoute)
        }
    }
}
