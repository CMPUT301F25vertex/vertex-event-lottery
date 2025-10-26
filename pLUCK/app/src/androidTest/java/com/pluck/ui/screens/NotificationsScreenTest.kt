package com.pluck.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pluck.ui.theme.PluckTheme
import com.pluck.ui.model.NotificationCategory
import com.pluck.ui.model.NotificationFilter
import com.pluck.ui.model.NotificationItem
import com.pluck.ui.model.NotificationStatus
import com.pluck.ui.model.NotificationButtons
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * NotificationsScreenTest.kt
 *
 * Purpose: Compose instrumentation tests for the new notifications experience.
 *
 * Design Pattern: UI behaviour verification using Compose test APIs.
 *
 * Outstanding Issues: None
 */
@RunWith(AndroidJUnit4::class)
class NotificationsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()

    private val sampleNotifications = listOf(
        NotificationItem(
            id = "unread",
            title = "You were selected!",
            subtitle = "Sample Event",
            detail = "Confirm by Friday.",
            category = NotificationCategory.SELECTION,
            status = NotificationStatus.UNREAD,
            accentColor = androidx.compose.ui.graphics.Color(0xFFFE2C55)
        ),
        NotificationItem(
            id = "read",
            title = "Not selected this draw",
            subtitle = "Another Event",
            detail = "We'll notify you next time.",
            category = NotificationCategory.NOT_SELECTED,
            status = NotificationStatus.READ,
            accentColor = androidx.compose.ui.graphics.Color(0xFF2C2C2C),
            callToActionButtons = NotificationButtons(showAccept = false, showDecline = false)
        )
    )

    @Test
    fun notificationsScreen_displaysUnreadByDefault() {
        composeTestRule.setContent {
            PluckTheme {
                NotificationsScreen(initialNotifications = sampleNotifications)
            }
        }

        composeTestRule.onNodeWithText("Notifications").assertIsDisplayed()
        composeTestRule.onNodeWithTag("${NotificationsTestTags.CardPrefix}unread").assertIsDisplayed()
    }

    @Test
    fun notificationsScreen_switchingTabsShowsReadItems() {
        composeTestRule.setContent {
            PluckTheme {
                NotificationsScreen(initialNotifications = sampleNotifications)
            }
        }

        composeTestRule.onNodeWithTag(NotificationsTestTags.TabRead).performClick()

        // After switching to Read tab, the read notification should be displayed
        composeTestRule.onNodeWithTag("${NotificationsTestTags.CardPrefix}read").assertIsDisplayed()
    }

    @Test
    fun notificationsScreen_eventDetailsButtonVisibleWhenEnabled() {
        composeTestRule.setContent {
            PluckTheme {
                NotificationsScreen(initialNotifications = sampleNotifications)
            }
        }

        composeTestRule.onNodeWithText("Event Details").assertIsDisplayed()
    }
}
