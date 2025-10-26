/**
 * PLuckNavHost.kt
 *
 * Purpose: Central navigation configuration for the pLUCK application.
 * Manages screen routing, authentication flow, and navigation state.
 *
 * Design Pattern: Navigation Component with state hoisting and sealed class destinations
 *
 * Outstanding Issues: None
 *
 * Navigation Flow:
 * - New users: DeviceLogin -> CreateAccountScreen -> HomeScreen
 * - Returning users (auto-login ON): DeviceLogin -> auto-navigate to HomeScreen (1 sec delay)
 * - Returning users (auto-login OFF): DeviceLogin -> WelcomeBackScreen -> HomeScreen
 */
package com.pluck.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.pluck.data.DeviceAuthResult
import com.pluck.data.DeviceAuthenticator
import com.pluck.data.DeviceAuthPreferences
import com.pluck.ui.model.EventRepository
import com.pluck.ui.model.EntrantProfile
import com.pluck.ui.components.BottomNavBar
import com.pluck.ui.screens.CreateAccountScreen
import com.pluck.ui.screens.CreateEventScreen
import com.pluck.ui.screens.EventDetailScreen
import com.pluck.ui.screens.HomeScreen
import com.pluck.ui.screens.MyEventsScreen
import com.pluck.ui.screens.NotificationsScreen
import com.pluck.ui.screens.OrganizerDashboardScreen
import com.pluck.ui.screens.PlaceholderScreen
import com.pluck.ui.screens.ProfileScreen
import com.pluck.ui.screens.SettingsScreen
import com.pluck.ui.screens.WaitlistScreen
import com.pluck.ui.screens.WaitlistEntry
import com.pluck.ui.screens.WelcomeBackScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed class PLuckDestination(val route: String) {
    object DeviceLogin : PLuckDestination("device_login")
    object EventList : PLuckDestination("event_list")
    object EventDetail : PLuckDestination("event_detail") {
        const val EVENT_ID_ARG = "eventId"
        val routeWithArg = "$route/{$EVENT_ID_ARG}"
        fun createRoute(eventId: String) = "$route/$eventId"
    }
    object Waitlist : PLuckDestination("waitlist") {
        const val EVENT_ID_ARG = "eventId"
        val routeWithArg = "$route/{$EVENT_ID_ARG}"
        fun createRoute(eventId: String) = "$route/$eventId"
    }
    object CreateEvent : PLuckDestination("create_event")
    object MyEvents : PLuckDestination("my_events")
    object Profile : PLuckDestination("profile")
    object Notifications : PLuckDestination("notifications")
    object Settings : PLuckDestination("settings")
    object OrganizerDashboard : PLuckDestination("organizer_dashboard")
    object AdminDashboard : PLuckDestination("admin_dashboard")
}

@Composable
fun PLuckNavHost(
    navController: NavHostController = rememberNavController(),
    onDarkModeChange: (Boolean) -> Unit = {},
    onThemeChange: (String) -> Unit = {}
) {
    val navigator = remember(navController) { PLuckNavigator(navController) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val authenticator = remember(context) { DeviceAuthenticator(context.applicationContext) }
    val authPreferences = remember(context) { DeviceAuthPreferences(context.applicationContext) }

    var currentUser by remember { mutableStateOf<EntrantProfile?>(null) }
    var loginInProgress by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf<String?>(null) }
    var initializingSession by remember { mutableStateOf(true) }
    var deviceId by remember { mutableStateOf(authenticator.currentDeviceId()) }
    var autoLoginEnabled by rememberSaveable { mutableStateOf(authPreferences.isAutoLoginEnabled()) }

    val allEvents = remember { EventRepository.getEvents() }

    // Only runs once on app start to fetch existing profile
    LaunchedEffect(authenticator) {
        initializingSession = true
        deviceId = authenticator.currentDeviceId()
        kotlin.runCatching { authenticator.fetchExistingProfile() }
            .onSuccess { profile ->
                if (profile != null) {
                    currentUser = profile
                    val route = navController.currentBackStackEntry?.destination?.route
                    if (autoLoginEnabled && route == PLuckDestination.DeviceLogin.route) {
                        // Add 1 second delay for smooth UX transition
                        delay(1000)
                        navigator.toEventList(clearBackStack = true)
                    }
                }
            }
        initializingSession = false
    }

    // This LaunchedEffect only triggers once on app start, not when toggle is changed
    LaunchedEffect(initializingSession, currentUser) {
        if (!initializingSession && autoLoginEnabled && currentUser != null) {
            val route = navController.currentBackStackEntry?.destination?.route
            if (route == PLuckDestination.DeviceLogin.route) {
                // Add 1 second delay for smooth UX transition
                delay(1000)
                navigator.toEventList(clearBackStack = true)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = PLuckDestination.DeviceLogin.route
    ) {
        composable(PLuckDestination.DeviceLogin.route) {
            // Conditional screen based on user state
            // Note: Returning users with auto-login enabled are auto-navigated
            // to HomeScreen via LaunchedEffect (they never see this screen)

            val user = currentUser
            when {
                // New user - show account creation
                user == null -> {
                    CreateAccountScreen(
                        deviceId = deviceId,
                        isLoading = loginInProgress || initializingSession,
                        errorMessage = loginError,
                        autoLoginEnabled = autoLoginEnabled,
                        onAutoLoginToggle = { enabled ->
                            autoLoginEnabled = enabled
                            authPreferences.setAutoLoginEnabled(enabled)
                        },
                        onCreateAccount = { displayName, email, phoneNumber ->
                            scope.launch {
                                if (loginInProgress) return@launch
                                loginError = null
                                loginInProgress = true

                                when (val result = authenticator.registerOrSignIn(displayName, email, phoneNumber)) {
                                    is DeviceAuthResult.Success -> {
                                        currentUser = result.profile
                                        deviceId = result.profile.deviceId
                                        navigator.toEventList(clearBackStack = true)
                                    }
                                    is DeviceAuthResult.Error -> {
                                        loginError = result.message
                                    }
                                }
                                loginInProgress = false
                            }
                        }
                    )
                }
                // Returning user WITHOUT auto-login - show welcome back
                !autoLoginEnabled -> {
                    WelcomeBackScreen(
                        userName = user.displayName,
                        deviceId = deviceId,
                        isLoading = loginInProgress || initializingSession,
                        autoLoginEnabled = autoLoginEnabled,
                        onAutoLoginToggle = { enabled ->
                            autoLoginEnabled = enabled
                            authPreferences.setAutoLoginEnabled(enabled)
                        },
                        onContinue = {
                            navigator.toEventList(clearBackStack = true)
                        }
                    )
                }
            }
        }
        composable(PLuckDestination.EventList.route) {
            // Home screen with bottom navigation
            HomeScreen(
                userName = currentUser?.displayName,
                events = allEvents,
                isLoading = false,
                currentRoute = navController.currentBackStackEntry?.destination?.route,
                onSelectEvent = { event ->
                    navigator.toEventDetail(event.id)
                },
                onNavigate = { route ->
                    when (route) {
                        "event_list" -> { /* Already here */ }
                        "notifications" -> navigator.toNotifications()
                        "settings" -> navigator.toSettings()
                        "profile" -> navigator.toProfile()
                    }
                },
                onCreateEvent = {
                    navigator.toCreateEvent()
                }
            )
        }
        composable(
            route = PLuckDestination.EventDetail.routeWithArg,
            arguments = listOf(
                navArgument(PLuckDestination.EventDetail.EVENT_ID_ARG) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString(PLuckDestination.EventDetail.EVENT_ID_ARG)
            val event = remember(eventId) { eventId?.let(EventRepository::getEvent) }

            if (event != null) {
                EventDetailScreen(
                    event = event,
                    onJoinEvent = { /* TODO: Implement join event logic */ },
                    onViewWaitlist = { eventToView ->
                        navigator.toWaitlist(eventToView.id)
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            } else {
                PlaceholderScreen(
                    title = "Event not found",
                    description = "We couldn't find the selected event."
                )
            }
        }
        composable(
            route = PLuckDestination.Waitlist.routeWithArg,
            arguments = listOf(
                navArgument(PLuckDestination.Waitlist.EVENT_ID_ARG) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString(PLuckDestination.Waitlist.EVENT_ID_ARG)
            val event = remember(eventId) { eventId?.let(EventRepository::getEvent) }

            if (event != null) {
                // Generate sample waitlist entries
                val waitlistEntries = remember(event.id) {
                    val names = listOf("Alice Johnson", "Bob Smith", "Charlie Davis", "Diana Chen", "Ethan Brown")
                    names.take(event.waitlistCount.coerceAtMost(5)).mapIndexed { index, name ->
                        WaitlistEntry(
                            id = "waitlist-${event.id}-$index",
                            userName = name,
                            position = index + 1,
                            joinedDate = java.time.LocalDate.now().minusDays((5 - index).toLong()),
                            isCurrentUser = index == 1 // Make the second person the current user
                        )
                    }
                }

                WaitlistScreen(
                    event = event,
                    waitlistEntries = waitlistEntries,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            } else {
                PlaceholderScreen(
                    title = "Event not found",
                    description = "We couldn't find the selected event."
                )
            }
        }
        composable(PLuckDestination.Profile.route) {
            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        currentRoute = navController.currentBackStackEntry?.destination?.route,
                        onNavigate = { route ->
                            when (route) {
                                "event_list" -> navigator.toEventList()
                                "notifications" -> navigator.toNotifications()
                                "settings" -> navigator.toSettings()
                                "profile" -> { /* Already here */ }
                            }
                        },
                        onCreateEvent = {
                            navigator.toCreateEvent()
                        }
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    ProfileScreen(
                        userName = currentUser?.displayName ?: "Anonymous",
                        userEmail = currentUser?.email,
                        deviceId = deviceId,
                        isLoading = loginInProgress,
                        onMyEvents = {
                            navigator.toMyEvents()
                        },
                        onOrganizerDashboard = {
                            navigator.toOrganizer()
                        },
                        onSignOut = {
                            currentUser = null
                            loginError = null
                            FirebaseAuth.getInstance().signOut()
                            initializingSession = false
                            navController.navigate(PLuckDestination.DeviceLogin.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        },
                        onDeleteAccount = {
                            scope.launch {
                                loginInProgress = true
                                when (authenticator.deleteAccount()) {
                                    is DeviceAuthResult.Success -> {
                                        currentUser = null
                                        loginError = null
                                        FirebaseAuth.getInstance().signOut()
                                        initializingSession = false
                                        navController.navigate(PLuckDestination.DeviceLogin.route) {
                                            popUpTo(0) { inclusive = true }
                                        }
                                    }
                                    is DeviceAuthResult.Error -> {
                                        loginError = "Failed to delete account"
                                    }
                                }
                                loginInProgress = false
                            }
                        }
                    )
                }
            }
        }
        composable(PLuckDestination.Notifications.route) {
            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        currentRoute = navController.currentBackStackEntry?.destination?.route,
                        onNavigate = { route ->
                            when (route) {
                                "event_list" -> navigator.toEventList()
                                "notifications" -> { /* Already here */ }
                                "settings" -> navigator.toSettings()
                                "profile" -> navigator.toProfile()
                            }
                        },
                        onCreateEvent = {
                            navigator.toCreateEvent()
                        }
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    NotificationsScreen(
                        onEventDetails = { notification ->
                            // Navigate to event detail screen
                            // Extract event ID from notification (for now use a placeholder)
                            // In production, NotificationItem should contain an eventId field
                            val eventId = "1" // TODO: Get from notification.eventId
                            navigator.toEventDetail(eventId)
                        },
                        onAccept = { notification ->
                            // TODO: Implement accept event logic
                            // This would typically call a backend API to accept the event invitation
                        },
                        onDecline = { notification ->
                            // TODO: Implement decline event logic
                            // This would typically call a backend API to decline the event invitation
                        }
                    )
                }
            }
        }
        composable(PLuckDestination.CreateEvent.route) {
            CreateEventScreen(
                isLoading = loginInProgress,
                onCreateEvent = { title, description, location, date, capacity ->
                    // TODO: Implement event creation backend logic
                    scope.launch {
                        loginInProgress = true
                        // Simulate API call
                        delay(1500)
                        loginInProgress = false
                        // Navigate back to home after successful creation
                        navigator.toEventList()
                    }
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
        composable(PLuckDestination.Settings.route) {
            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        currentRoute = navController.currentBackStackEntry?.destination?.route,
                        onNavigate = { route ->
                            when (route) {
                                "event_list" -> navigator.toEventList()
                                "notifications" -> navigator.toNotifications()
                                "settings" -> { /* Already here */ }
                                "profile" -> navigator.toProfile()
                            }
                        },
                        onCreateEvent = {
                            navigator.toCreateEvent()
                        }
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    val themePrefs = remember { com.pluck.ui.theme.ThemePreferences(context) }
                    val isDarkMode = remember { themePrefs.isDarkModeEnabled() }

                    SettingsScreen(
                        darkModeEnabled = isDarkMode,
                        onDarkModeChange = onDarkModeChange,
                        onNavigateToThemePicker = {
                            navController.navigate("theme_picker")
                        }
                    )
                }
            }
        }
        composable(PLuckDestination.MyEvents.route) {
            Scaffold(
                bottomBar = {
                    BottomNavBar(
                        currentRoute = navController.currentBackStackEntry?.destination?.route,
                        onNavigate = { route ->
                            when (route) {
                                "event_list" -> navigator.toEventList()
                                "notifications" -> navigator.toNotifications()
                                "settings" -> navigator.toSettings()
                                "profile" -> navigator.toProfile()
                            }
                        },
                        onCreateEvent = {
                            navigator.toCreateEvent()
                        }
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    MyEventsScreen(
                        events = emptyList(), // TODO: Get user's events from repository
                        isLoading = false,
                        onEventClick = { event ->
                            navigator.toEventDetail(event.id)
                        }
                    )
                }
            }
        }
        composable(PLuckDestination.OrganizerDashboard.route) {
            OrganizerDashboardScreen(
                organizerName = currentUser?.displayName ?: "Organizer",
                stats = com.pluck.ui.screens.OrganizerStats(
                    totalEvents = 0,
                    activeEvents = 0,
                    totalParticipants = 0
                ),
                events = emptyList(), // TODO: Get organizer's events from repository
                isLoading = false,
                onCreateEvent = {
                    navigator.toCreateEvent()
                },
                onEventClick = { event ->
                    navigator.toEventDetail(event.id)
                },
                onEditEvent = { event ->
                    // TODO: Navigate to edit event screen
                },
                onViewParticipants = { event ->
                    // TODO: Navigate to participants screen
                }
            )
        }
        composable(PLuckDestination.AdminDashboard.route) {
            PlaceholderScreen(
                title = "Admin dashboard",
                description = "Review organizers, approve events, and moderate the platform."
            )
        }
        composable("theme_picker") {
            val themePrefs = remember { com.pluck.ui.theme.ThemePreferences(context) }
            val currentThemeId = remember { themePrefs.getSelectedThemeId() }

            com.pluck.ui.screens.ThemePickerScreen(
                currentThemeId = currentThemeId,
                themes = com.pluck.ui.theme.ThemeManager.getAllThemes(),
                onThemeSelected = { themeId ->
                    onThemeChange(themeId)
                },
                onResetToDefault = {
                    onThemeChange("blue")
                    onDarkModeChange(false)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

class PLuckNavigator(private val navController: NavHostController) {
    fun toEventList(clearBackStack: Boolean = false) = navController.navigate(PLuckDestination.EventList.route) {
        if (clearBackStack) {
            popUpTo(PLuckDestination.DeviceLogin.route) { inclusive = true }
        }
    }
    fun toEventDetail(eventId: String) = navController.navigate(PLuckDestination.EventDetail.createRoute(eventId))
    fun toWaitlist(eventId: String) = navController.navigate(PLuckDestination.Waitlist.createRoute(eventId))
    fun toCreateEvent() = navController.navigate(PLuckDestination.CreateEvent.route)
    fun toMyEvents() = navController.navigate(PLuckDestination.MyEvents.route)
    fun toProfile() = navController.navigate(PLuckDestination.Profile.route)
    fun toNotifications() = navController.navigate(PLuckDestination.Notifications.route)
    fun toSettings() = navController.navigate(PLuckDestination.Settings.route)
    fun toOrganizer() = navController.navigate(PLuckDestination.OrganizerDashboard.route)
    fun toAdmin() = navController.navigate(PLuckDestination.AdminDashboard.route)
}
