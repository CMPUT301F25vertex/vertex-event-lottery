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

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.pluck.data.DeviceAuthResult
import com.pluck.data.DeviceAuthenticator
import com.pluck.data.DeviceAuthPreferences
import com.pluck.ui.model.Event
import com.pluck.ui.model.EntrantProfile
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.ui.components.BottomNavBar
import com.pluck.ui.screens.CreateAccountScreen
import com.pluck.ui.screens.CreateEventRequest
import com.pluck.ui.screens.CreateEventScreen
import com.pluck.ui.screens.EditEventPosterScreen
import com.pluck.ui.screens.EventDetailScreen
import com.pluck.ui.screens.HomeScreen
import com.pluck.ui.screens.EventStatus
import com.pluck.ui.screens.MyEventItem
import com.pluck.ui.screens.MyEventsScreen
import com.pluck.ui.screens.NotificationsScreen
import com.pluck.ui.screens.OrganizerDashboardScreen
import com.pluck.ui.screens.OrganizerStats
import com.pluck.ui.screens.PlaceholderScreen
import com.pluck.ui.screens.ProfileScreen
import com.pluck.ui.screens.SettingsScreen
import com.pluck.ui.screens.ThemePickerScreen
import com.pluck.ui.screens.WaitlistScreen
import com.pluck.ui.screens.WelcomeBackScreen
import com.pluck.ui.screens.CustomThemeCreatorScreen
import com.pluck.ui.screens.QRScannerScreen
import com.pluck.ui.theme.ThemeManager
import com.pluck.ui.theme.ThemePreferences
import com.pluck.ui.viewmodel.EventViewModel
import com.pluck.ui.viewmodel.NotificationsViewModel
import com.pluck.ui.viewmodel.WaitlistViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeParseException

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
    object EditEvent : PLuckDestination("edit_event") {
        const val EVENT_ID_ARG = "eventId"
        val routeWithArg = "$route/{$EVENT_ID_ARG}"
        fun createRoute(eventId: String) = "$route/$eventId"
    }
    object MyEvents : PLuckDestination("my_events")
    object Profile : PLuckDestination("profile")
    object Notifications : PLuckDestination("notifications")
    object Settings : PLuckDestination("settings")
    object OrganizerDashboard : PLuckDestination("organizer_dashboard")
    object AdminDashboard : PLuckDestination("admin_dashboard")
    object QRScanner : PLuckDestination("qr_scanner")
}

@Composable
fun PLuckNavHost(
    navController: NavHostController = rememberNavController(),
    onDarkModeChange: (Boolean) -> Unit = {},
    currentThemeId: String = "blue",
    onThemeChange: (String) -> Unit = {},
    isDarkMode: Boolean = false
) {
    val navigator = remember(navController) { PLuckNavigator(navController) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val authenticator = remember(context) { DeviceAuthenticator(context.applicationContext) }
    val authPreferences = remember(context) { DeviceAuthPreferences(context.applicationContext) }
    val themePrefs = remember(context) { ThemePreferences(context.applicationContext) }

    var currentUser by remember { mutableStateOf<EntrantProfile?>(null) }
    var loginInProgress by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf<String?>(null) }
    var initializingSession by remember { mutableStateOf(true) }
    var deviceId by remember { mutableStateOf(authenticator.currentDeviceId()) }
    var autoLoginEnabled by rememberSaveable { mutableStateOf(authPreferences.isAutoLoginEnabled()) }
    var customTheme by remember { mutableStateOf(themePrefs.getCustomTheme()) }
    var profileUpdateMessage by remember { mutableStateOf<String?>(null) }
    var profileUpdateError by remember { mutableStateOf<String?>(null) }
    var profileUpdating by remember { mutableStateOf(false) }

    val eventViewModel: EventViewModel = viewModel()
    val waitlistViewModel: WaitlistViewModel = viewModel()
    val notificationsViewModel: NotificationsViewModel = viewModel()

    val events by eventViewModel.events.collectAsState()
    val selectedEvent by eventViewModel.selectedEvent.collectAsState()
    val eventsLoading by eventViewModel.isLoading.collectAsState()
    val eventError by eventViewModel.error.collectAsState()

    val waitlistEntries by waitlistViewModel.waitlistEntries.collectAsState()
    val chosenEntries by waitlistViewModel.chosenEntries.collectAsState()
    val waitlistLoading by waitlistViewModel.isLoading.collectAsState()
    val waitlistError by waitlistViewModel.error.collectAsState()
    val userWaitlistEntryId by waitlistViewModel.userWaitlistEntryId.collectAsState()
    val userWaitlistStatus by waitlistViewModel.userWaitlistStatus.collectAsState()
    val userEventHistory by waitlistViewModel.userEventHistory.collectAsState()
    val notifications by notificationsViewModel.notifications.collectAsState()
    val notificationsLoading by notificationsViewModel.isLoading.collectAsState()
    val processingNotificationIds by notificationsViewModel.processingNotificationIds.collectAsState()
    val notificationError by notificationsViewModel.error.collectAsState()
    val inviteFeedback by notificationsViewModel.inviteFeedback.collectAsState()
    val inviteInProgress by notificationsViewModel.isInviteInProgress.collectAsState()
    val navigateToEventDetails by notificationsViewModel.navigateToEventDetails.collectAsState()

    LaunchedEffect(currentUser) {
        if (currentUser == null) {
            waitlistViewModel.resetUserWaitlistEntry()
        }
    }

    LaunchedEffect(customTheme) {
        ThemeManager.setCustomTheme(customTheme)
    }

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

    LaunchedEffect(currentUser) {
        val device = currentUser?.deviceId.orEmpty()
        if (device.isNotBlank()) {
            waitlistViewModel.loadUserEventHistory(device)
        } else {
            waitlistViewModel.resetUserWaitlistEntry()
        }
        notificationsViewModel.observeNotifications(currentUser)
    }

    LaunchedEffect(notificationError) {
        notificationError?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            notificationsViewModel.clearError()
        }
    }

    LaunchedEffect(waitlistError) {
        waitlistError?.let { message ->
            Toast.makeText(context, "Waitlist Error: $message", Toast.LENGTH_LONG).show()
            waitlistViewModel.clearError()
        }
    }

    LaunchedEffect(eventError) {
        eventError?.let { message ->
            Toast.makeText(context, "Event Error: $message", Toast.LENGTH_LONG).show()
            eventViewModel.clearError()
        }
    }

    LaunchedEffect(navigateToEventDetails) {
        val eventToOpen = navigateToEventDetails
        if (!eventToOpen.isNullOrBlank()) {
            navigator.toEventDetail(eventToOpen)
            notificationsViewModel.clearNavigationRequest()
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
            val userJoinedEventIds = remember(userEventHistory) {
                userEventHistory.map { it.event.id }.toSet()
            }
            HomeScreen(
                userName = currentUser?.displayName,
                events = events,
                isLoading = eventsLoading,
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
                },
                onScanQRCode = {
                    navigator.toQRScanner()
                },
                onRefreshEvents = {
                    // Pull the latest events and the user's joined history on demand.
                    eventViewModel.loadEvents()
                    val refreshDeviceId = currentUser?.deviceId.orEmpty()
                    if (refreshDeviceId.isNotBlank()) {
                        waitlistViewModel.loadUserEventHistory(refreshDeviceId)
                    }
                },
                userJoinedEventIds = userJoinedEventIds
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
            val resolvedEvent = remember(eventId, events, selectedEvent) {
                val fromList = events.firstOrNull { it.id == eventId }
                fromList ?: selectedEvent?.takeIf { it.id == eventId }
            }
            val currentUserId = currentUser?.deviceId.orEmpty()

            LaunchedEffect(eventId) {
                notificationsViewModel.clearFeedback()
            }

            LaunchedEffect(eventId) {
                if (eventId != null && resolvedEvent == null) {
                    eventViewModel.loadEvent(eventId)
                }
            }

            LaunchedEffect(eventId, currentUserId) {
                if (eventId != null && currentUserId.isNotBlank()) {
                    waitlistViewModel.checkUserWaitlistStatus(eventId, currentUserId)
                }
            }

            when {
                resolvedEvent != null -> {
                    val isUserWaiting = userWaitlistStatus == WaitlistStatus.WAITING ||
                        userWaitlistStatus == WaitlistStatus.SELECTED
                    val isUserConfirmed = userWaitlistStatus == WaitlistStatus.ACCEPTED
                    val organizerId = currentUser?.deviceId.orEmpty()
                    val canEditPoster = currentUser?.let { profile ->
                        val organizerMatch = resolvedEvent.organizerId.isNotBlank() &&
                            resolvedEvent.organizerId == profile.deviceId
                        val fallbackMatch = resolvedEvent.organizerId.isBlank() &&
                            profile.displayName.isNotBlank() &&
                            resolvedEvent.organizerName.equals(profile.displayName, ignoreCase = true)
                        organizerMatch || fallbackMatch
                    } ?: false
                    val canInviteEntrants = canEditPoster && organizerId.isNotBlank()

                    EventDetailScreen(
                        event = resolvedEvent,
                        isUserOnWaitlist = isUserWaiting,
                        isUserConfirmed = isUserConfirmed,
                        onJoinEvent = join@{ eventToJoin ->
                            val profile = currentUser
                            if (profile == null) {
                                Toast.makeText(context, "Please sign in to join events.", Toast.LENGTH_LONG).show()
                                return@join
                            }

                            scope.launch {
                                waitlistViewModel.joinWaitlist(
                                    eventId = eventToJoin.id,
                                    userId = profile.deviceId,
                                    userName = profile.displayName
                                ) {
                                    waitlistViewModel.checkUserWaitlistStatus(eventToJoin.id, profile.deviceId)
                                    navigator.toWaitlist(eventToJoin.id)
                                }
                            }
                        },
                        onLeaveWaitlist = { eventToLeave ->
                            val entryId = userWaitlistEntryId
                            val profile = currentUser
                            if (entryId != null) {
                                scope.launch {
                                    waitlistViewModel.leaveWaitlist(entryId) {
                                        if (profile != null) {
                                            waitlistViewModel.checkUserWaitlistStatus(eventToLeave.id, profile.deviceId)
                                        }
                                    }
                                }
                            } else {
                                Toast.makeText(context, "No waitlist entry found to leave.", Toast.LENGTH_LONG).show()
                            }
                        },
                        onViewWaitlist = { eventToView ->
                            navigator.toWaitlist(eventToView.id)
                        },
                        onBack = {
                            navController.popBackStack()
                        },
                        canEditPoster = canEditPoster,
                        onEditPoster = { eventToEdit ->
                            navigator.toEditEvent(eventToEdit.id)
                        },
                        canInviteEntrants = canInviteEntrants,
                        inviteFeedbackMessage = inviteFeedback?.message,
                        inviteFeedbackIsError = inviteFeedback?.isError == true,
                        inviteInProgress = inviteInProgress,
                        onInviteEntrant = { contact, type ->
                            if (organizerId.isNotBlank()) {
                                notificationsViewModel.sendInvite(
                                    eventId = resolvedEvent.id,
                                    organizerId = organizerId,
                                    contact = contact,
                                    type = type
                                )
                            }
                        },
                        onClearInviteFeedback = {
                            notificationsViewModel.clearFeedback()
                        }
                    )
                }
                eventsLoading -> {
                    PlaceholderScreen(
                        title = "Loading event",
                        description = "Fetching the latest details..."
                    )
                }
                eventError != null -> {
                    PlaceholderScreen(
                        title = "Event unavailable",
                        description = eventError ?: "Unable to load the selected event."
                    )
                }
                else -> {
                    PlaceholderScreen(
                        title = "Event not found",
                        description = "We couldn't find the selected event."
                    )
                }
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
            val resolvedEvent = remember(eventId, events, selectedEvent) {
                val fromList = events.firstOrNull { it.id == eventId }
                fromList ?: selectedEvent?.takeIf { it.id == eventId }
            }

            LaunchedEffect(eventId, currentUser?.deviceId) {
                if (eventId != null) {
                    // Observe event for real-time updates
                    eventViewModel.observeEvent(eventId)

                    val currentUserId = currentUser?.deviceId.orEmpty()
                    waitlistViewModel.observeWaitlist(eventId, currentUserId)
                    waitlistViewModel.observeChosenEntries(eventId, currentUserId)
                    if (currentUserId.isNotBlank()) {
                        waitlistViewModel.checkUserWaitlistStatus(eventId, currentUserId)
                    }
                }
            }

            when {
                resolvedEvent != null -> {
                    WaitlistScreen(
                        event = resolvedEvent,
                        waitlistEntries = waitlistEntries,
                        chosenEntries = chosenEntries,
                        isUserWaiting = userWaitlistStatus == WaitlistStatus.WAITING ||
                            userWaitlistStatus == WaitlistStatus.SELECTED,
                        isUserConfirmed = userWaitlistStatus == WaitlistStatus.ACCEPTED,
                        onJoinWaitlist = join@{
                            val profile = currentUser
                            if (profile == null) {
                                Toast.makeText(context, "Please sign in to join waitlist.", Toast.LENGTH_LONG).show()
                                return@join
                            }

                            scope.launch {
                                waitlistViewModel.joinWaitlist(
                                    eventId = resolvedEvent.id,
                                    userId = profile.deviceId,
                                    userName = profile.displayName
                                ) {
                                    waitlistViewModel.checkUserWaitlistStatus(resolvedEvent.id, profile.deviceId)
                                }
                            }
                        },
                        onLeaveWaitlist = leave@{
                            val entryId = userWaitlistEntryId
                            val profile = currentUser
                            if (entryId == null) {
                                Toast.makeText(context, "No waitlist entry found to leave.", Toast.LENGTH_LONG).show()
                                return@leave
                            }

                            scope.launch {
                                waitlistViewModel.leaveWaitlist(entryId) {
                                    if (profile != null && eventId != null) {
                                        waitlistViewModel.checkUserWaitlistStatus(eventId, profile.deviceId)
                                    }
                                }
                            }
                        },
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }
                waitlistLoading || eventsLoading -> {
                    PlaceholderScreen(
                        title = "Loading waitlist",
                        description = "Fetching live entrant positions..."
                    )
                }
                waitlistError != null -> {
                    PlaceholderScreen(
                        title = "Waitlist unavailable",
                        description = waitlistError ?: "Unable to load the waitlist."
                    )
                }
                else -> {
                    PlaceholderScreen(
                        title = "Event not found",
                        description = "We couldn't find the selected event."
                    )
                }
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
                        userPhone = currentUser?.phoneNumber,
                        deviceId = deviceId,
                        isLoading = loginInProgress,
                        isUpdatingProfile = profileUpdating,
                        updateMessage = profileUpdateMessage,
                        updateError = profileUpdateError,
                        onUpdateProfile = { name, email, phone ->
                            scope.launch {
                                profileUpdating = true
                                profileUpdateMessage = null
                                profileUpdateError = null
                                when (val result = authenticator.updateProfile(name, email, phone)) {
                                    is DeviceAuthResult.Success -> {
                                        currentUser = result.profile
                                        waitlistViewModel.refreshEntrantDisplayName(
                                            result.profile.deviceId,
                                            result.profile.displayName
                                        )
                                        profileUpdateMessage = "Profile updated successfully."
                                    }
                                    is DeviceAuthResult.Error -> {
                                        profileUpdateError = result.message
                                    }
                                }
                                profileUpdating = false
                            }
                        },
                        onMyEvents = {
                            navigator.toMyEvents()
                        },
                        onOrganizerDashboard = {
                            navigator.toOrganizer()
                        },
                        onSignOut = {
                            currentUser = null
                            loginError = null
                            profileUpdateMessage = null
                            profileUpdateError = null
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
                                        profileUpdateMessage = null
                                        profileUpdateError = null
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
                        notifications = notifications,
                        isLoading = notificationsLoading,
                        processingNotificationIds = processingNotificationIds,
                        onEventDetails = { notification ->
                            notificationsViewModel.markRead(notification.id)
                            if (notification.eventId.isNotBlank()) {
                                navigator.toEventDetail(notification.eventId)
                            }
                        },
                        onAccept = { notification ->
                            notificationsViewModel.acceptNotification(notification, currentUser)
                        },
                        onDecline = { notification ->
                            notificationsViewModel.declineNotification(notification)
                        },
                        onProfileClick = {
                            navigator.toProfile()
                        }
                    )
                }
            }
        }
        composable(PLuckDestination.CreateEvent.route) {
            var formError by remember { mutableStateOf<String?>(null) }

            CreateEventScreen(
                isLoading = eventsLoading,
                errorMessage = formError ?: eventError,
                onCreateEvent = { request ->
                    formError = null

                    val organizer = currentUser
                    if (organizer == null) {
                        formError = "Sign in to create events."
                        return@CreateEventScreen
                    }

                    val today = LocalDate.now()

                    val parsedDate = try {
                        LocalDate.parse(request.date.trim())
                    } catch (ex: DateTimeParseException) {
                        formError = "Enter the event date as YYYY-MM-DD."
                        return@CreateEventScreen
                    }

                    if (parsedDate.isBefore(today)) {
                        formError = "Event date cannot be in the past. Please choose today or a future date."
                        return@CreateEventScreen
                    }

                    val capacityValue = request.capacity.toIntOrNull()
                    if (capacityValue == null || capacityValue <= 0) {
                        formError = "Capacity must be a positive number."
                        return@CreateEventScreen
                    }

                    val registrationStartDate = try {
                        LocalDate.parse(request.registrationStart.trim())
                    } catch (ex: DateTimeParseException) {
                        formError = "Enter registration start date as YYYY-MM-DD."
                        return@CreateEventScreen
                    }

                    if (registrationStartDate.isAfter(parsedDate)) {
                        formError = "Registration start cannot be after the event date."
                        return@CreateEventScreen
                    }

                    val registrationEndDate = try {
                        LocalDate.parse(request.registrationEnd.trim())
                    } catch (ex: DateTimeParseException) {
                        formError = "Enter registration end date as YYYY-MM-DD."
                        return@CreateEventScreen
                    }

                    if (registrationEndDate.isBefore(registrationStartDate)) {
                        formError = "Registration end date must be on or after the start date."
                        return@CreateEventScreen
                    }

                    if (registrationEndDate.isAfter(parsedDate)) {
                        formError = "Registration must close before or on the event date."
                        return@CreateEventScreen
                    }

                    val waitlistCapacity = request.waitlistLimit.toIntOrNull()
                    if (waitlistCapacity == null || waitlistCapacity <= 0) {
                        formError = "Provide a positive waitlist limit."
                        return@CreateEventScreen
                    }

                    val samplingCount = request.samplingCount.toIntOrNull()
                    if (samplingCount == null || samplingCount <= 0) {
                        formError = "Sampling count must be a positive number."
                        return@CreateEventScreen
                    }

                    val newEvent = Event(
                        id = "",
                        title = request.title.trim(),
                        description = request.description.trim(),
                        location = request.location.trim(),
                        date = parsedDate,
                        capacity = capacityValue,
                        enrolled = 0,
                        organizerName = organizer.displayName,
                        waitlistCapacity = waitlistCapacity,
                        posterUrl = request.posterUrl,
                        registrationStart = registrationStartDate,
                        registrationEnd = registrationEndDate,
                        samplingCount = samplingCount
                    )

                    eventViewModel.createEvent(newEvent, organizer.deviceId) { eventId ->
                        formError = null
                        navController.popBackStack()
                        navigator.toEventDetail(eventId)
                    }
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = PLuckDestination.EditEvent.routeWithArg,
            arguments = listOf(
                navArgument(PLuckDestination.EditEvent.EVENT_ID_ARG) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString(PLuckDestination.EditEvent.EVENT_ID_ARG)
            val resolvedEvent = remember(eventId, events, selectedEvent) {
                val fromList = events.firstOrNull { it.id == eventId }
                fromList ?: selectedEvent?.takeIf { it.id == eventId }
            }

            LaunchedEffect(eventId) {
                if (!eventId.isNullOrBlank() && resolvedEvent == null) {
                    eventViewModel.loadEvent(eventId)
                }
            }

            when {
                resolvedEvent != null -> {
                    EditEventPosterScreen(
                        eventTitle = resolvedEvent.title,
                        currentPosterUrl = resolvedEvent.posterUrl,
                        isSaving = eventsLoading,
                        errorMessage = eventError,
                        onBack = { navController.popBackStack() },
                        onClearError = { eventViewModel.clearError() },
                        onSavePoster = { posterUrl ->
                            val updates = mutableMapOf<String, Any>(
                                "updatedAt" to FieldValue.serverTimestamp()
                            )
                            if (posterUrl.isNullOrBlank()) {
                                updates["imageUrl"] = FieldValue.delete()
                            } else {
                                updates["imageUrl"] = posterUrl
                            }
                            eventViewModel.updateEvent(resolvedEvent.id, updates) {
                                navController.popBackStack()
                            }
                        }
                    )
                }
                eventsLoading -> {
                    PlaceholderScreen(
                        title = "Updating event",
                        description = "Fetching event details..."
                    )
                }
                eventError != null -> {
                    PlaceholderScreen(
                        title = "Event unavailable",
                        description = eventError ?: "Unable to edit this event right now.",
                        actionLabel = "Try again",
                        onAction = {
                            if (!eventId.isNullOrBlank()) {
                                eventViewModel.loadEvent(eventId)
                            }
                        }
                    )
                }
                else -> {
                    PlaceholderScreen(
                        title = "Event not found",
                        description = "We could not locate that event."
                    )
                }
            }
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
                val myEvents = remember(
                    events,
                    userEventHistory,
                    currentUser?.deviceId,
                    currentUser?.displayName
                ) {
                    val today = LocalDate.now()
                    val organizerId = currentUser?.deviceId.orEmpty()
                    val organizerName = currentUser?.displayName.orEmpty()
                    val activeHistory = userEventHistory.filterNot { history ->
                        history.status == WaitlistStatus.CANCELLED ||
                            history.status == WaitlistStatus.DECLINED
                    }
                    val historyByEventId = activeHistory.associateBy { it.event.id }

                    // Only include events that the user has created or joined
                    val userRelatedEvents = buildMap<String, Event> {
                        // Add events the user created
                        events.filter { event ->
                            event.organizerId == organizerId ||
                                (organizerName.isNotBlank() &&
                                    event.organizerName.equals(organizerName, ignoreCase = true))
                        }.forEach { event ->
                            put(event.id, event)
                        }

                        // Add events the user joined (from waitlist history)
                        activeHistory.forEach { history ->
                            putIfAbsent(history.event.id, history.event)
                        }
                    }.values

                    userRelatedEvents.map { event ->
                        val history = historyByEventId[event.id]
                        val isCreatedByUser = event.organizerId == organizerId ||
                            (organizerName.isNotBlank() &&
                                event.organizerName.equals(organizerName, ignoreCase = true))

                        val status = when {
                            // If user created this event, status based on date
                            isCreatedByUser -> {
                                if (event.date.isBefore(today)) EventStatus.PAST else EventStatus.UPCOMING
                            }
                            // If user has waitlist history, use that status
                            history != null -> when (history.status) {
                                WaitlistStatus.ACCEPTED -> EventStatus.CONFIRMED
                                WaitlistStatus.SELECTED,
                                WaitlistStatus.WAITING -> EventStatus.WAITLIST
                                WaitlistStatus.DECLINED,
                                WaitlistStatus.CANCELLED -> if (event.date.isBefore(today)) {
                                    EventStatus.PAST
                                } else {
                                    EventStatus.UPCOMING
                                }
                            }
                            // Default fallback
                            else -> when {
                                event.date.isBefore(today) -> EventStatus.PAST
                                else -> EventStatus.UPCOMING
                            }
                        }

                        MyEventItem(
                            event = event,
                            status = status,
                            isCreatedByUser = isCreatedByUser,
                            joinedDate = history?.joinedDate,
                            historyStatus = history?.status
                        )
                    }.sortedWith(compareBy<MyEventItem> { it.event.date })
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    MyEventsScreen(
                        events = myEvents,
                        isLoading = eventsLoading,
                        onEventClick = { event ->
                            navigator.toEventDetail(event.id)
                        }
                    )
                }
            }
        }
        composable(PLuckDestination.OrganizerDashboard.route) {
            val organizerEvents = remember(events, currentUser?.deviceId, currentUser?.displayName) {
                val organizerId = currentUser?.deviceId
                val organizerName = currentUser?.displayName

                if (organizerId.isNullOrBlank() && organizerName.isNullOrBlank()) {
                    emptyList()
                } else {
                    // Filter by organizerId first (most reliable), fallback to name comparison
                    events.filter { event ->
                        event.organizerId == organizerId ||
                            (organizerName?.isNotBlank() == true &&
                                event.organizerName.equals(organizerName, ignoreCase = true))
                    }
                }
            }
            val organizerStats = remember(organizerEvents) {
                OrganizerStats(
                    totalEvents = organizerEvents.size,
                    activeEvents = organizerEvents.count { !it.isFull },
                    totalParticipants = organizerEvents.sumOf { it.enrolled }
                )
            }
            OrganizerDashboardScreen(
                organizerName = currentUser?.displayName ?: "Organizer",
                stats = organizerStats,
                events = organizerEvents,
                isLoading = eventsLoading,
                onCreateEvent = {
                    navigator.toCreateEvent()
                },
                onEventClick = { event ->
                    navigator.toEventDetail(event.id)
                },
                onEditEvent = { event ->
                    navigator.toEditEvent(event.id)
                },
                onViewParticipants = { _ ->
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
            ThemePickerScreen(
                currentThemeId = currentThemeId,
                themes = ThemeManager.getAllThemes(),
                customTheme = customTheme,
                onThemeSelected = { themeId ->
                    if (themeId != "custom" || customTheme != null) {
                        onThemeChange(themeId)
                    }
                },
                onCreateOrEditCustomTheme = {
                    navController.navigate("custom_theme_creator")
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
        composable("custom_theme_creator") {
            CustomThemeCreatorScreen(
                initialTheme = customTheme ?: ThemeManager.getThemeById(currentThemeId),
                onSave = { newTheme ->
                    themePrefs.saveCustomTheme(newTheme)
                    customTheme = newTheme
                    ThemeManager.setCustomTheme(newTheme)
                    onThemeChange("custom")
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(PLuckDestination.QRScanner.route) {
            QRScannerScreen(
                onBack = {
                    navController.popBackStack()
                },
                onEventScanned = { eventId ->
                    // Navigate to event detail when QR code is scanned
                    navController.popBackStack()
                    navigator.toEventDetail(eventId)
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
    fun toEditEvent(eventId: String) = navController.navigate(PLuckDestination.EditEvent.createRoute(eventId))
    fun toWaitlist(eventId: String) = navController.navigate(PLuckDestination.Waitlist.createRoute(eventId))
    fun toCreateEvent() = navController.navigate(PLuckDestination.CreateEvent.route)
    fun toMyEvents() = navController.navigate(PLuckDestination.MyEvents.route)
    fun toProfile() = navController.navigate(PLuckDestination.Profile.route)
    fun toNotifications() = navController.navigate(PLuckDestination.Notifications.route)
    fun toSettings() = navController.navigate(PLuckDestination.Settings.route)
    fun toOrganizer() = navController.navigate(PLuckDestination.OrganizerDashboard.route)
    fun toAdmin() = navController.navigate(PLuckDestination.AdminDashboard.route)
    fun toQRScanner() = navController.navigate(PLuckDestination.QRScanner.route)
}
