package com.pluck.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pluck.ui.model.Event
import com.pluck.ui.model.NotificationItem
import com.pluck.ui.model.NotificationStatus
import com.pluck.ui.model.previewNotifications
import com.pluck.ui.theme.PluckTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class ScreensSmokeTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private val sampleEvent = Event(
        id = "event-1",
        title = "Community Yoga",
        description = "Gentle yoga session for all levels with certified instructors.",
        location = "Vertex Studio",
        date = LocalDate.now().plusDays(3),
        capacity = 20,
        enrolled = 8,
        organizerName = "Vertex Community",
        waitlistCount = 5,
        qrCodeData = "event-1"
    )

    private val sampleEvents = listOf(
        sampleEvent,
        sampleEvent.copy(
            id = "event-2",
            title = "Cooking with Gordon",
            location = "Downtown Kitchen",
            capacity = 12,
            enrolled = 11,
            waitlistCount = 3
        )
    )

    private val sampleMyEvents = listOf(
        MyEventItem(
            event = sampleEvent,
            status = EventStatus.CONFIRMED,
            isCreatedByUser = false
        ),
        MyEventItem(
            event = sampleEvent.copy(id = "event-3", title = "Photography Walk", enrolled = 20),
            status = EventStatus.UPCOMING,
            isCreatedByUser = true
        )
    )

    private val sampleWaitlistEntries = listOf(
        WaitlistEntry(
            id = "waitlist-1",
            userId = "user-a",
            userName = "Jordan Lee",
            position = 1,
            joinedDate = LocalDate.now().minusDays(1)
        ),
        WaitlistEntry(
            id = "waitlist-2",
            userId = "user-b",
            userName = "You",
            position = 2,
            joinedDate = LocalDate.now().minusDays(2),
            isCurrentUser = true
        )
    )

    private val sampleNotifications: List<NotificationItem> = previewNotifications().mapIndexed { index, item ->
        if (index == 0) item else item.copy(status = NotificationStatus.READ)
    }

    @Test
    fun homeScreen_rendersHeroSection() {
        composeRule.setContent {
            PluckTheme {
                HomeScreen(
                    userName = "Caiden",
                    events = sampleEvents,
                    isLoading = false,
                    currentRoute = null,
                    onSelectEvent = {},
                    onNavigate = {}
                )
            }
        }

        composeRule.onNodeWithText("Find your next experience").assertIsDisplayed()
        composeRule.onNodeWithText("All").assertIsDisplayed()
    }

    @Test
    fun createEventScreen_rendersFormFields() {
        composeRule.setContent {
            PluckTheme {
                CreateEventScreen()
            }
        }

        composeRule.onNodeWithText("Event Title").assertIsDisplayed()
        composeRule.onAllNodesWithText("Create Event")[0].assertIsDisplayed()
    }

    @Test
    fun eventDetailScreen_displaysPrimaryActions() {
        composeRule.setContent {
            PluckTheme {
                EventDetailScreen(
                    event = sampleEvent,
                    isUserOnWaitlist = false,
                    isUserConfirmed = false,
                    onJoinEvent = {},
                    onViewWaitlist = {}
                )
            }
        }

        composeRule.onNodeWithText("Community Yoga").assertIsDisplayed()
        composeRule.onNodeWithText("Join Waitlist").assertIsDisplayed()
        composeRule.onNodeWithText("View Waitlist").assertIsDisplayed()
    }

    @Test
    fun notificationsScreen_rendersTabs() {
        composeRule.setContent {
            PluckTheme {
                NotificationsScreen(notifications = sampleNotifications)
            }
        }

        composeRule.onNodeWithText("Notifications").assertIsDisplayed()
        composeRule.onNodeWithText("Unread").assertIsDisplayed()
    }

    @Test
    fun profileScreen_showsQuickActions() {
        composeRule.setContent {
            PluckTheme {
                ProfileScreen(
                    userName = "Caiden Weiss",
                    userEmail = "caiden@example.com",
                    userPhone = "780-555-0100",
                    deviceId = "device-1234",
                    isLoading = false,
                    onSignOut = {},
                    onDeleteAccount = {},
                    onMyEvents = {},
                    onOrganizerDashboard = {}
                )
            }
        }

        composeRule.onNodeWithText("My Events").assertIsDisplayed()
        composeRule.onNodeWithText("Delete Account").assertIsDisplayed()
    }

    @Test
    fun myEventsScreen_showsFilters() {
        composeRule.setContent {
            PluckTheme {
                MyEventsScreen(
                    events = sampleMyEvents,
                    isLoading = false,
                    onEventClick = {}
                )
            }
        }

        composeRule.onNodeWithText("My Events").assertIsDisplayed()
        composeRule.onAllNodesWithText("Upcoming")[0].assertIsDisplayed()
    }

    @Test
    fun organizerDashboard_showsStats() {
        composeRule.setContent {
            PluckTheme {
                OrganizerDashboardScreen(
                    organizerName = "Vertex Team",
                    stats = OrganizerStats(
                        totalEvents = 5,
                        activeEvents = 3,
                        totalParticipants = 120
                    ),
                    events = sampleEvents
                )
            }
        }

        composeRule.onNodeWithText("Organizer Dashboard").assertIsDisplayed()
        composeRule.onNodeWithText("Welcome back, Vertex Team").assertIsDisplayed()
    }

    @Test
    fun waitlistScreen_rendersQueues() {
        composeRule.setContent {
            PluckTheme {
                WaitlistScreen(
                    event = sampleEvent,
                    waitlistEntries = sampleWaitlistEntries
                )
            }
        }

        composeRule.onNodeWithText("Waitlist Queue").assertIsDisplayed()
        composeRule.onNodeWithText("Jordan Lee").assertIsDisplayed()
    }

    @Test
    fun welcomeBackScreen_rendersDeviceCard() {
        composeRule.setContent {
            PluckTheme {
                WelcomeBackScreen(
                    userName = "Jordan Lee",
                    deviceId = "device-5678",
                    isLoading = false,
                    autoLoginEnabled = true,
                    onAutoLoginToggle = {},
                    onContinue = {}
                )
            }
        }

        composeRule.onNodeWithText("Welcome Back").assertIsDisplayed()
        composeRule.onNodeWithText("Device ID: device-5678").assertIsDisplayed()
    }

    @Test
    fun settingsScreen_listsSections() {
        composeRule.setContent {
            PluckTheme {
                SettingsScreen(
                    darkModeEnabled = false,
                    onDarkModeChange = {}
                )
            }
        }

        composeRule.onNodeWithText("Appearance").assertIsDisplayed()
        composeRule.onNodeWithText("Privacy & Security").assertIsDisplayed()
    }

    @Test
    fun placeholderScreen_rendersCallToAction() {
        composeRule.setContent {
            PluckTheme {
                PlaceholderScreen(
                    title = "Coming Soon",
                    description = "This feature is under construction.",
                    actionLabel = "Return",
                    onAction = {}
                )
            }
        }

        composeRule.onNodeWithText("Coming Soon").assertIsDisplayed()
        composeRule.onNodeWithText("Return").assertIsDisplayed()
    }
}

