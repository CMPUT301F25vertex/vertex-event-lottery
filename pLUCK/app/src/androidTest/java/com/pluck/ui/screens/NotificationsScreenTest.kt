package com.pluck.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pluck.ui.model.NotificationButtons
import com.pluck.ui.model.NotificationCategory
import com.pluck.ui.model.NotificationFilter
import com.pluck.ui.model.NotificationItem
import com.pluck.ui.model.NotificationStatus
import com.pluck.ui.theme.PluckTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotificationsScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private val unreadNotification = NotificationItem(
        id = "unread-id",
        title = "You were selected!",
        subtitle = "Community Yoga",
        detail = "Confirm your spot before Friday.",
        category = NotificationCategory.SELECTION,
        status = NotificationStatus.UNREAD,
        accentColor = Color(0xFFFE2C55)
    )

    private val readNotification = NotificationItem(
        id = "read-id",
        title = "Draw results posted",
        subtitle = "Cooking with Gordon",
        detail = "Thanks for entering this week's lottery.",
        category = NotificationCategory.NOT_SELECTED,
        status = NotificationStatus.READ,
        accentColor = Color(0xFF2C2C2C),
        callToActionButtons = NotificationButtons(
            showEventDetails = true,
            showAccept = false,
            showDecline = false
        )
    )

    private val notifications = listOf(unreadNotification, readNotification)

    @Test
    fun unreadTab_isSelectedByDefault() {
        composeRule.setContent {
            PluckTheme {
                NotificationsScreen(notifications = notifications)
            }
        }

        composeRule.onNodeWithText("Notifications").assertIsDisplayed()
        composeRule
            .onNodeWithTag("${NotificationsTestTags.CardPrefix}${unreadNotification.id}")
            .assertIsDisplayed()
    }

    @Test
    fun selectingReadTab_showsReadNotifications() {
        composeRule.setContent {
            PluckTheme {
                NotificationsScreen(notifications = notifications)
            }
        }

        composeRule.onNodeWithTag(NotificationsTestTags.TabRead).performClick()

        composeRule
            .onNodeWithTag("${NotificationsTestTags.CardPrefix}${readNotification.id}")
            .assertIsDisplayed()
    }

    @Test
    fun actionButtons_reflectProcessingState() {
        composeRule.setContent {
            PluckTheme {
                NotificationsScreen(
                    notifications = listOf(
                        unreadNotification.copy(
                            callToActionButtons = NotificationButtons(
                                showEventDetails = true,
                                showAccept = true,
                                showDecline = true
                            )
                        )
                    ),
                    processingNotificationIds = setOf(unreadNotification.id)
                )
            }
        }

        composeRule.onNodeWithText("Event Details").assertIsDisplayed().assertIsNotEnabled()
        composeRule.onNodeWithText("Accept").assertIsNotEnabled()
        composeRule.onNodeWithText("Decline").assertIsNotEnabled()
    }

    @Test
    fun actionButtons_areEnabledWhenNotProcessing() {
        composeRule.setContent {
            PluckTheme {
                NotificationsScreen(
                    notifications = listOf(
                        unreadNotification.copy(
                            callToActionButtons = NotificationButtons(
                                showEventDetails = true,
                                showAccept = true,
                                showDecline = true
                            )
                        )
                    )
                )
            }
        }

        composeRule.onNodeWithText("Event Details").assertIsDisplayed().assertIsEnabled()
        composeRule.onNodeWithText("Accept").assertIsEnabled()
        composeRule.onNodeWithText("Decline").assertIsEnabled()
    }
}

