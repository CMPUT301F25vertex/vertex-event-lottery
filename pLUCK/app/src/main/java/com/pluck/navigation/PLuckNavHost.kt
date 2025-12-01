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
 * - Returning users (auto-login ON): DeviceLogin -> WelcomeBackScreen (300ms) -> HomeScreen
 * - Returning users (auto-login OFF): DeviceLogin -> WelcomeBackScreen -> HomeScreen (manual)
 */
package com.pluck.navigation

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
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
import com.pluck.data.repository.AdminAccessRepository
import com.pluck.data.repository.OrganizerAccessRepository
import com.pluck.data.repository.AppealRepository
import com.pluck.data.firebase.UserRole
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
import com.pluck.ui.screens.ChosenEntrantsScreen
import com.pluck.ui.screens.RunDrawDialog
import com.pluck.ui.screens.AdminDashboardScreen
import com.pluck.ui.screens.EntrantLocationsMapScreen
import com.pluck.ui.viewmodel.AdminViewModel
import com.pluck.ui.theme.ThemeManager
import com.pluck.ui.theme.ThemePreferences
import com.pluck.ui.viewmodel.EventViewModel
import com.pluck.ui.viewmodel.NotificationsViewModel
import com.pluck.ui.viewmodel.WaitlistViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDateTime

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
    object ChosenEntrants : PLuckDestination("chosen_entrants") {
        const val EVENT_ID_ARG = "eventId"
        val routeWithArg = "$route/{$EVENT_ID_ARG}"
        fun createRoute(eventId: String) = "$route/$eventId"
    }
    object EntrantLocationsMap : PLuckDestination("entrant_locations_map") {
        const val EVENT_ID_ARG = "eventId"
        val routeWithArg = "$route/{$EVENT_ID_ARG}"
        fun createRoute(eventId: String) = "$route/$eventId"
    }
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
    val adminAccessRepository = remember { AdminAccessRepository() }
    val organizerAccessRepository = remember { OrganizerAccessRepository() }
    val appealRepository = remember { AppealRepository() }

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
    var isAdminDevice by remember { mutableStateOf(false) }
    var adminCheckError by remember { mutableStateOf<String?>(null) }
    var showAdminRegistrationDialog by remember { mutableStateOf(false) }
    var adminRegistrationPassword by remember { mutableStateOf("") }
    var adminRegistrationError by remember { mutableStateOf<String?>(null) }
    var adminRegistrationInProgress by remember { mutableStateOf(false) }

    val eventViewModel: EventViewModel = viewModel()
    val waitlistViewModel: WaitlistViewModel = viewModel()
    val notificationsViewModel: NotificationsViewModel = viewModel()
    val adminViewModel: AdminViewModel = viewModel()

    val events by eventViewModel.events.collectAsState()
    val selectedEvent by eventViewModel.selectedEvent.collectAsState()
    val eventsLoading by eventViewModel.isLoading.collectAsState()
    val eventError by eventViewModel.error.collectAsState()

    val waitlistEntries by waitlistViewModel.waitlistEntries.collectAsState()
    val chosenEntries by waitlistViewModel.chosenEntries.collectAsState()
    val chosenStats by waitlistViewModel.chosenStats.collectAsState()
    val waitlistLoading by waitlistViewModel.isLoading.collectAsState()
    val waitlistError by waitlistViewModel.error.collectAsState()
    val userWaitlistEntryId by waitlistViewModel.userWaitlistEntryId.collectAsState()
    val userWaitlistStatus by waitlistViewModel.userWaitlistStatus.collectAsState()
    val userEventHistory by waitlistViewModel.userEventHistory.collectAsState()
    val notifications by notificationsViewModel.notifications.collectAsState()

    val adminEvents by adminViewModel.events.collectAsState()
    val adminUsers by adminViewModel.users.collectAsState()
    val adminOrganizers by adminViewModel.organizers.collectAsState()
    val adminImages by adminViewModel.images.collectAsState()
    val adminNotifications by adminViewModel.notifications.collectAsState()
    val adminStats by adminViewModel.stats.collectAsState()
    val adminLoading by adminViewModel.isLoading.collectAsState()
    val adminAppeals by adminViewModel.appeals.collectAsState()
    val notificationsLoading by notificationsViewModel.isLoading.collectAsState()
    val processingNotificationIds by notificationsViewModel.processingNotificationIds.collectAsState()
    val notificationError by notificationsViewModel.error.collectAsState()
    val inviteFeedback by notificationsViewModel.inviteFeedback.collectAsState()
    val inviteInProgress by notificationsViewModel.isInviteInProgress.collectAsState()
    val navigateToEventDetails by notificationsViewModel.navigateToEventDetails.collectAsState()

    LaunchedEffect(deviceId) {
        if (deviceId.isBlank()) return@LaunchedEffect

        authenticator.observeProfile(deviceId).collect { profile ->
            val previousProfile = currentUser
            if (profile != null) {
                val lostOrganizer = previousProfile?.role == UserRole.ORGANIZER && profile.role != UserRole.ORGANIZER
                val newlyBanned = profile.isOrganizerBanned && previousProfile?.isOrganizerBanned != true

                currentUser = profile

                if (lostOrganizer || newlyBanned) {
                    profileUpdateError = null
                    profileUpdateMessage =
                        "Organizer access revoked. Your events have been closed. Submit an appeal if this was a mistake."
                    eventViewModel.loadEvents()
                    Toast.makeText(
                        context,
                        "Organizer access revoked. Your events have been closed.",
                        Toast.LENGTH_LONG
                    ).show()

                    if (navController.currentBackStackEntry?.destination?.route == PLuckDestination.OrganizerDashboard.route) {
                        navController.popBackStack()
                    }
                }
            } else {
                currentUser = null
            }
        }
    }

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
                        // Brief delay to show welcome screen before navigating
                        delay(300)
                        navigator.toEventList(clearBackStack = true)
                    }
                }
            }
        initializingSession = false
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

    LaunchedEffect(deviceId, currentUser) {
        if (deviceId.isNotBlank()) {
            adminAccessRepository.isDeviceAdmin(deviceId)
                .onSuccess { isAdminDevice = it }
                .onFailure {
                    isAdminDevice = false
                    adminCheckError = it.message
                }
        } else {
            isAdminDevice = false
        }
    }

    LaunchedEffect(adminCheckError) {
        adminCheckError?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            adminCheckError = null

            Log.e("ADMIN ERROR", message)
        }
    }

    LaunchedEffect(notificationError) {
        notificationError?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            notificationsViewModel.clearError()

            Log.e("NOTIFICATION ERROR", message)
        }
    }

    LaunchedEffect(waitlistError) {
        waitlistError?.let { message ->
            Toast.makeText(context, "Waitlist Error: $message", Toast.LENGTH_LONG).show()
            waitlistViewModel.clearError()

            Log.e("WAITLIST ERROR", message)
        }
    }

    LaunchedEffect(eventError) {
        eventError?.let { message ->
            Toast.makeText(context, "Event Error: $message", Toast.LENGTH_LONG).show()
            eventViewModel.clearError()

            Log.e("EVENT ERROR", message)
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
            // Note: Returning users with auto-login enabled see WelcomeBackScreen briefly
            // before auto-navigating to HomeScreen (300ms delay for smooth transition)

            val user = currentUser
            when {
                // New user - show account creation
                user == null -> {
                    // Pre-fill with saved profile data if available
                    val savedName = authPreferences.getSavedDisplayName()
                    val savedEmail = authPreferences.getSavedEmail()
                    val savedPhone = authPreferences.getSavedPhoneNumber()

                    CreateAccountScreen(
                        deviceId = deviceId,
                        isLoading = loginInProgress || initializingSession,
                        errorMessage = loginError,
                        autoLoginEnabled = autoLoginEnabled,
                        initialDisplayName = savedName ?: "",
                        initialEmail = savedEmail ?: "",
                        initialPhoneNumber = savedPhone ?: "",
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
                // Returning user - show welcome back (with or without auto-login)
                else -> {
                    @Suppress("KotlinConstantConditions")
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
            val isOrganizer = currentUser?.role == UserRole.ORGANIZER
            HomeScreen(
                userName = currentUser?.displayName,
                events = events,
                isLoading = eventsLoading,
                currentRoute = navController.currentBackStackEntry?.destination?.route,
                isOrganizer = isOrganizer,
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
            val currentUserId = currentUser?.deviceId.orEmpty()

            LaunchedEffect(eventId) {
                notificationsViewModel.clearFeedback()
            }

            LaunchedEffect(eventId) {
                if (eventId != null) {
                    // Use observeEvent for real-time updates to event data (waitlistCount, etc.)
                    eventViewModel.observeEvent(eventId)
                }
            }

            LaunchedEffect(eventId, currentUserId) {
                if (eventId != null && currentUserId.isNotBlank()) {
                    waitlistViewModel.checkUserWaitlistStatus(eventId, currentUserId)
                }
            }

            // Use selectedEvent directly for real-time updates, fallback to events list for initial load
            val resolvedEvent = selectedEvent?.takeIf { it.id == eventId }
                ?: events.firstOrNull { it.id == eventId }

            when {
                resolvedEvent != null -> {
                    val isUserWaiting = userWaitlistStatus == WaitlistStatus.WAITING ||
                            userWaitlistStatus == WaitlistStatus.SELECTED ||
                            userWaitlistStatus == WaitlistStatus.INVITED
                    val isUserConfirmed = userWaitlistStatus == WaitlistStatus.ACCEPTED
                    val organizerId = currentUser?.deviceId.orEmpty()
                    val isEventOrganizer = currentUser?.let { profile ->
                        val organizerMatch = resolvedEvent.organizerId.isNotBlank() &&
                                resolvedEvent.organizerId == profile.deviceId
                        val fallbackMatch = resolvedEvent.organizerId.isBlank() &&
                                profile.displayName.isNotBlank() &&
                                resolvedEvent.organizerName.equals(profile.displayName, ignoreCase = true)
                        organizerMatch || fallbackMatch
                    } ?: false

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

                            // Capture location if event requires geolocation (US 02.02.03)
                            // When enabled, location is MANDATORY - users cannot join without it
                            if (eventToJoin.requiresGeolocation) {
                                scope.launch {
                                    try {
                                        @SuppressLint("MissingPermission")
                                        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                                        val cancellationToken = CancellationTokenSource()
                                        @SuppressLint("MissingPermission")
                                        val locationResult = fusedLocationClient.getCurrentLocation(
                                            Priority.PRIORITY_HIGH_ACCURACY,
                                            cancellationToken.token
                                        )

                                        locationResult.addOnSuccessListener { location ->
                                            if (location == null) {
                                                // Location is MANDATORY - block join if unavailable
                                                Toast.makeText(context, "This event requires location sharing. Please enable location services and try again.", Toast.LENGTH_LONG).show()
                                                return@addOnSuccessListener
                                            }
                                            scope.launch {
                                                waitlistViewModel.joinWaitlist(
                                                    eventId = eventToJoin.id,
                                                    userId = profile.deviceId,
                                                    userName = profile.displayName,
                                                    latitude = location.latitude,
                                                    longitude = location.longitude
                                                ) {
                                                    // Refresh user status and event data immediately
                                                    waitlistViewModel.checkUserWaitlistStatus(eventToJoin.id, profile.deviceId)
                                                    eventViewModel.loadEvent(eventToJoin.id)
                                                    waitlistViewModel.loadUserEventHistory(profile.deviceId)
                                                    // Toast.makeText(context, "You just joined the waitlist!", Toast.LENGTH_LONG).show()
                                                }
                                            }
                                        }.addOnFailureListener {
                                            // Location is MANDATORY - block join on failure
                                            Toast.makeText(context, "Failed to get location. This event requires location sharing to join.", Toast.LENGTH_LONG).show()
                                        }
                                    } catch (e: SecurityException) {
                                        // Location permission MANDATORY - block join if denied
                                        Toast.makeText(context, "Location permission required. Please grant location access in settings to join this event.", Toast.LENGTH_LONG).show()
                                    }
                                }
                            } else {
                                // No geolocation required, join directly without location
                                scope.launch {
                                    waitlistViewModel.joinWaitlist(
                                        eventId = eventToJoin.id,
                                        userId = profile.deviceId,
                                        userName = profile.displayName,
                                        latitude = null,
                                        longitude = null
                                    ) {
                                        // Refresh user status and event data immediately
                                        waitlistViewModel.checkUserWaitlistStatus(eventToJoin.id, profile.deviceId)
                                        eventViewModel.loadEvent(eventToJoin.id)
                                        waitlistViewModel.loadUserEventHistory(profile.deviceId)
                                        // Toast.makeText(context, "You just joined the waitlist!", Toast.LENGTH_LONG).show()
                                    }
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
                                            val deviceId = profile.deviceId
                                            waitlistViewModel.checkUserWaitlistStatus(eventToLeave.id, deviceId)
                                            waitlistViewModel.loadUserEventHistory(deviceId)
                                        }
                                        eventViewModel.loadEvent(eventToLeave.id)
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
                        isEventOrganizer = isEventOrganizer,
                        onEditPoster = { eventToEdit ->
                            navigator.toEditEvent(eventToEdit.id)
                        },
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
                                userWaitlistStatus == WaitlistStatus.SELECTED ||
                                userWaitlistStatus == WaitlistStatus.INVITED,
                        isUserConfirmed = userWaitlistStatus == WaitlistStatus.ACCEPTED,
                        onBack = { navController.popBackStack() },
                        onJoinWaitlist = join@{
                            val profile = currentUser
                            if (profile == null) {
                                Toast.makeText(context, "Please sign in to join waitlist.", Toast.LENGTH_LONG).show()
                                return@join
                            }

                            // Capture location if event requires geolocation (US 02.02.03)
                            // When enabled, location is MANDATORY - users cannot join without it
                            if (resolvedEvent.requiresGeolocation) {
                                scope.launch {
                                    try {
                                        @SuppressLint("MissingPermission")
                                        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                                        val cancellationToken = CancellationTokenSource()
                                        @SuppressLint("MissingPermission")
                                        val locationResult = fusedLocationClient.getCurrentLocation(
                                            Priority.PRIORITY_HIGH_ACCURACY,
                                            cancellationToken.token
                                        )

                                        locationResult.addOnSuccessListener { location ->
                                            if (location == null) {
                                                // Location is MANDATORY - block join if unavailable
                                                Toast.makeText(context, "This event requires location sharing. Please enable location services and try again.", Toast.LENGTH_LONG).show()
                                                return@addOnSuccessListener
                                            }
                                            scope.launch {
                                                waitlistViewModel.joinWaitlist(
                                                    eventId = resolvedEvent.id,
                                                    userId = profile.deviceId,
                                                    userName = profile.displayName,
                                                    latitude = location.latitude,
                                                    longitude = location.longitude
                                                ) {
                                                    // Refresh user status and event data immediately
                                                    waitlistViewModel.checkUserWaitlistStatus(resolvedEvent.id, profile.deviceId)
                                                    eventViewModel.loadEvent(resolvedEvent.id)
                                                    waitlistViewModel.loadUserEventHistory(profile.deviceId)
                                                }
                                            }
                                        }.addOnFailureListener {
                                            // Location is MANDATORY - block join on failure
                                            Toast.makeText(context, "Failed to get location. This event requires location sharing to join.", Toast.LENGTH_LONG).show()
                                        }
                                    } catch (e: SecurityException) {
                                        // Location permission MANDATORY - block join if denied
                                        Toast.makeText(context, "Location permission required. Please grant location access in settings to join this event.", Toast.LENGTH_LONG).show()
                                    }
                                }
                            } else {
                                // No geolocation required, join directly without location
                                scope.launch {
                                    waitlistViewModel.joinWaitlist(
                                        eventId = resolvedEvent.id,
                                        userId = profile.deviceId,
                                        userName = profile.displayName,
                                        latitude = null,
                                        longitude = null
                                    ) {
                                        // Refresh user status and event data immediately
                                        waitlistViewModel.checkUserWaitlistStatus(resolvedEvent.id, profile.deviceId)
                                        eventViewModel.loadEvent(resolvedEvent.id)
                                        waitlistViewModel.loadUserEventHistory(profile.deviceId)
                                    }
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
                                        val deviceId = profile.deviceId
                                        waitlistViewModel.checkUserWaitlistStatus(eventId, deviceId)
                                        waitlistViewModel.loadUserEventHistory(deviceId)
                                    }
                                    eventId?.let { eventViewModel.loadEvent(it) }
                                }
                            }
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
            val isOrganizer = currentUser?.role == UserRole.ORGANIZER
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
                        },
                        showCreateButton = isOrganizer
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
                        profileImageUrl = currentUser?.profileImageUrl,
                        deviceId = deviceId,
                        isLoading = loginInProgress,
                        isUpdatingProfile = profileUpdating,
                        isAdmin = isAdminDevice,
                        isOrganizer = currentUser?.role == UserRole.ORGANIZER,
                        isOrganizerBanned = currentUser?.isOrganizerBanned == true,
                        hasOutstandingAppeal = currentUser?.hasOutstandingAppeal == true,
                        updateMessage = profileUpdateMessage,
                        updateError = profileUpdateError,
                        onProfileImageUploadStarted = {
                            profileUpdateMessage = null
                            profileUpdateError = null
                        },
                        onProfileImageUploaded = { url ->
                            currentUser = currentUser?.copy(profileImageUrl = url)
                            profileUpdateError = null
                            profileUpdateMessage = "Profile photo updated successfully."
                        },
                        onProfileImageUploadFailed = { message ->
                            profileUpdateMessage = null
                            profileUpdateError = message
                        },
                        onRemoveProfileImage = {
                            scope.launch {
                                profileUpdateMessage = null
                                profileUpdateError = null

                                val result = authenticator.clearProfileImage()
                                when (result) {
                                    is DeviceAuthResult.Success -> {
                                        currentUser = result.profile
                                        profileUpdateMessage = "Profile photo removed."
                                    }
                                    is DeviceAuthResult.Error -> {
                                        profileUpdateError = result.message
                                    }
                                }
                            }
                        },
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
                        onAdminDashboard = {
                            if (isAdminDevice) {
                                navigator.toAdmin()
                            }
                        },
                        onRegisterAdmin = {
                            adminRegistrationError = null
                            adminRegistrationPassword = ""
                            showAdminRegistrationDialog = true
                        },
                        onBecomeOrganizer = {
                            scope.launch {
                                profileUpdateMessage = null
                                profileUpdateError = null
                                if (deviceId.isBlank()) {
                                    profileUpdateError = "Device ID unavailable. Please try again."
                                    return@launch
                                }
                                val profile = currentUser
                                if (profile == null) {
                                    profileUpdateError = "Profile unavailable. Please try again."
                                    return@launch
                                }
                                organizerAccessRepository.registerAsOrganizer(deviceId)
                                    .onSuccess {
                                        currentUser = profile.copy(
                                            role = UserRole.ORGANIZER,
                                            isOrganizerBanned = false
                                        )
                                        profileUpdateMessage = "Organizer tools unlocked."
                                    }
                                    .onFailure { error ->
                                        profileUpdateError = error.message
                                            ?: "Failed to register as organizer."
                                    }
                            }
                        },
                        onDowngradeFromOrganizer = {
                            scope.launch {
                                profileUpdateMessage = null
                                profileUpdateError = null
                                if (deviceId.isBlank()) {
                                    profileUpdateError = "Device ID unavailable. Please try again."
                                    return@launch
                                }
                                val profile = currentUser
                                if (profile == null) {
                                    profileUpdateError = "Profile unavailable. Please try again."
                                    return@launch
                                }
                                organizerAccessRepository.downgradeToEntrant(deviceId)
                                    .onSuccess {
                                        currentUser = profile.copy(role = UserRole.ENTRANT)
                                        profileUpdateMessage =
                                            "Organizer access removed. Existing events deleted."
                                    }
                                    .onFailure { error ->
                                        profileUpdateError = error.message
                                            ?: "Failed to downgrade organizer access."
                                    }
                            }
                        },
                        onSubmitAppeal = { message ->
                            scope.launch {
                                profileUpdateMessage = null
                                profileUpdateError = null
                                if (deviceId.isBlank()) {
                                    profileUpdateError = "Device ID unavailable. Please try again."
                                    return@launch
                                }
                                val profile = currentUser
                                if (profile == null) {
                                    profileUpdateError = "Profile unavailable. Please try again."
                                    return@launch
                                }
                                appealRepository.submitAppeal(
                                    userId = deviceId,
                                    displayName = profile.displayName,
                                    email = profile.email ?: "",
                                    message = message
                                ).onSuccess { _ ->
                                    currentUser = profile.copy(hasOutstandingAppeal = true)
                                    profileUpdateMessage = "Appeal submitted successfully."
                                }.onFailure { error ->
                                    profileUpdateError = error.message
                                        ?: "Failed to submit appeal."
                                }
                            }
                        },
                        onSignOut = {
                            // Save profile data before signing out for pre-filling
                            currentUser?.let { user ->
                                authPreferences.saveProfileData(
                                    displayName = user.displayName,
                                    email = user.email,
                                    phoneNumber = user.phoneNumber
                                )
                            }

                            currentUser = null
                            loginError = null
                            profileUpdateMessage = null
                            profileUpdateError = null
                            isAdminDevice = false
                            showAdminRegistrationDialog = false
                            adminRegistrationPassword = ""
                            adminRegistrationError = null
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
                                        // Clear saved profile data on account deletion
                                        authPreferences.clearProfileData()

                                        currentUser = null
                                        loginError = null
                                        profileUpdateMessage = null
                                        profileUpdateError = null
                                        isAdminDevice = false
                                        showAdminRegistrationDialog = false
                                        adminRegistrationPassword = ""
                                        adminRegistrationError = null
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
            val isOrganizer = currentUser?.role == UserRole.ORGANIZER
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
                        },
                        showCreateButton = isOrganizer
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
                            notificationsViewModel.acceptNotification(notification, currentUser) { eventId ->
                                val profile = currentUser
                                if (eventId != null && profile != null) {
                                    val deviceId = profile.deviceId
                                    waitlistViewModel.refreshUserMembership(eventId, deviceId)
                                    waitlistViewModel.loadUserEventHistory(deviceId)
                                    eventViewModel.loadEvent(eventId)
                                }
                            }
                        },
                        onDecline = { notification ->
                            notificationsViewModel.declineNotification(notification) { eventId ->
                                val profile = currentUser
                                if (eventId != null && profile != null) {
                                    val deviceId = profile.deviceId
                                    waitlistViewModel.refreshUserMembership(eventId, deviceId)
                                    waitlistViewModel.loadUserEventHistory(deviceId)
                                    eventViewModel.loadEvent(eventId)
                                }
                            }
                        },
                        onProfileClick = {
                            navigator.toProfile()
                        },
                        onClearAll = {
                            notificationsViewModel.clearAllNotifications(currentUser)
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

                    val eventDateTime = LocalDateTime.of(request.eventDate, request.eventTime)
                    val now = LocalDateTime.now()

                    if (eventDateTime.isBefore(now)) {
                        formError = "Event start must be in the future."
                        return@CreateEventScreen
                    }

                    val registrationStartDateTime = LocalDateTime.of(
                        request.registrationStartDate,
                        request.registrationStartTime
                    )
                    val registrationEndDateTime = LocalDateTime.of(
                        request.registrationEndDate,
                        request.registrationEndTime
                    )

                    if (registrationStartDateTime.isAfter(eventDateTime)) {
                        formError = "Registration cannot open after the event starts."
                        return@CreateEventScreen
                    }

                    if (registrationEndDateTime.isBefore(registrationStartDateTime)) {
                        formError = "Registration must close after it opens."
                        return@CreateEventScreen
                    }

                    if (registrationEndDateTime.isAfter(eventDateTime)) {
                        formError = "Registration must close before the event starts."
                        return@CreateEventScreen
                    }

                    val capacityValue = request.capacity.toIntOrNull()
                    if (capacityValue == null || capacityValue <= 0) {
                        formError = "Capacity must be a positive number."
                        return@CreateEventScreen
                    }

                    val waitlistCapacity = request.waitlistLimit.toIntOrDefault(Int.MAX_VALUE)
                    if (waitlistCapacity <= 0) {
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
                        date = request.eventDate,
                        eventTime = request.eventTime,
                        capacity = capacityValue,
                        enrolled = 0,
                        organizerName = organizer.displayName,
                        organizerId = organizer.deviceId,
                        waitlistCapacity = waitlistCapacity,
                        posterUrl = request.posterUrl,
                        registrationStart = request.registrationStartDate,
                        registrationStartTime = request.registrationStartTime,
                        registrationEnd = request.registrationEndDate,
                        registrationEndTime = request.registrationEndTime,
                        samplingCount = samplingCount,
                        requiresGeolocation = request.requiresGeolocation
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
            val isOrganizer = currentUser?.role == UserRole.ORGANIZER
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
                        },
                        showCreateButton = isOrganizer
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
            val isOrganizer = currentUser?.role == UserRole.ORGANIZER
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
                        },
                        showCreateButton = isOrganizer
                    )
                }
            ) { paddingValues ->
                val myEvents = remember(
                    events,
                    userEventHistory,
                    currentUser?.deviceId
                ) {
                    val organizerId = currentUser?.deviceId.orEmpty()
                    val activeHistory = userEventHistory.filterNot { history ->
                        history.status == WaitlistStatus.CANCELLED ||
                                history.status == WaitlistStatus.DECLINED
                    }
                    val historyByEventId = activeHistory.associateBy { it.event.id }

                    // Create a map of event IDs to active events (only events that still exist)
                    val activeEventsById = events.associateBy { it.id }

                    // Only include events that the user has created or joined AND still exist
                    val userRelatedEvents = buildMap {
                        // Add events the user created (only if they still exist and are active)
                        events.filter { event ->
                            event.organizerId == organizerId
                        }.forEach { event ->
                            put(event.id, event)
                        }

                        // Add events the user joined (from waitlist history)
                        // Only include if the event still exists in the active events list
                        activeHistory.forEach { history ->
                            val activeEvent = activeEventsById[history.event.id]
                            if (activeEvent != null) {
                                putIfAbsent(history.event.id, activeEvent)
                            }
                        }
                    }.values

                    userRelatedEvents.map { event ->
                        val history = historyByEventId[event.id]
                        val isCreatedByUser = event.organizerId == organizerId

                        val status = when {
                            // If user created this event, status based on date
                            isCreatedByUser -> {
                                if (event.isPastEvent) EventStatus.PAST else EventStatus.UPCOMING
                            }
                            // If user has waitlist history, use that status
                            history != null -> when (history.status) {
                                WaitlistStatus.ACCEPTED -> EventStatus.CONFIRMED
                                WaitlistStatus.INVITED,
                                WaitlistStatus.SELECTED,
                                WaitlistStatus.WAITING -> EventStatus.WAITLIST
                                WaitlistStatus.DECLINED,
                                WaitlistStatus.CANCELLED -> if (event.isPastEvent) {
                                    EventStatus.PAST
                                } else {
                                    EventStatus.UPCOMING
                                }
                            }
                            // Default fallback
                            else -> if (event.isPastEvent) EventStatus.PAST else EventStatus.UPCOMING
                        }

                        MyEventItem(
                            event = event,
                            status = status,
                            isCreatedByUser = isCreatedByUser,
                            joinedDate = history?.joinedDate,
                            historyStatus = history?.status
                        )
                    }.sortedWith(compareBy { it.event.eventDateTime })
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
                        },
                        onBack = {
                            val popped = navController.popBackStack()
                            if (!popped) {
                                navigator.toProfile()
                            }
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

            var totalRejections by remember { mutableStateOf(0) }

            // Observe rejection counts in real-time for all organizer events
            LaunchedEffect(organizerEvents) {
                if (organizerEvents.isEmpty()) {
                    totalRejections = 0
                    return@LaunchedEffect
                }

                val rejectionFlows = organizerEvents.map { event ->
                    waitlistViewModel.observeRejectionCountForEvent(event.id)
                }

                if (rejectionFlows.size == 1) {
                    rejectionFlows.first().collect { count ->
                        totalRejections = count
                    }
                } else {
                    kotlinx.coroutines.flow.combine(rejectionFlows) { counts ->
                        counts.sum()
                    }.collect { count ->
                        totalRejections = count
                    }
                }
            }

            val organizerStats = remember(organizerEvents, totalRejections) {
                OrganizerStats(
                    totalEvents = organizerEvents.size,
                    activeEvents = organizerEvents.count { !it.isFull },
                    totalParticipants = organizerEvents.sumOf { it.enrolled },
                    totalRejections = totalRejections
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
                onRunDraw = { event ->
                    // Run the draw for this event
                    eventViewModel.runDraw(event, waitlistViewModel, onSuccess = {
                        // Refresh the event after draw
                        currentUser?.deviceId?.let { orgId ->
                            eventViewModel.loadEventsByOrganizer(orgId)
                        }
                    })
                },
                onViewWaitlist = { event ->
                    navigator.toWaitlist(event.id)
                },
                onManageChosenEntrants = { event ->
                    navigator.toChosenEntrants(event.id)
                },
                onViewEntrantLocations = { event ->
                    navigator.toEntrantLocationsMap(event.id)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(PLuckDestination.AdminDashboard.route) {
            LaunchedEffect(Unit) {
                adminViewModel.loadAllImages()
            }
            if (isAdminDevice) {
                AdminDashboardScreen(
                    stats = adminStats,
                    events = adminEvents,
                    users = adminUsers,
                    organizers = adminOrganizers,
                    appeals = adminAppeals,
                    images = adminImages,
                    notifications = adminNotifications,
                    isLoading = adminLoading,
                    onRemoveEvent = { eventId ->
                        adminViewModel.removeEvent(eventId)
                    },
                    onRemoveProfile = { profileId ->
                        adminViewModel.removeProfile(profileId)
                    },
                    onRemoveImage = { imageId ->
                        adminViewModel.removeImage(imageId)
                    },
                    onRemoveOrganizer = { organizerId ->
                        adminViewModel.removeOrganizer(organizerId)
                    },
                    onApproveAppeal = { appealId, notes ->
                        val adminActorId = currentUser?.deviceId?.takeIf { it.isNotBlank() } ?: deviceId
                        adminViewModel.approveAppeal(appealId, adminActorId, notes)
                    },
                    onRejectAppeal = { appealId, notes ->
                        val adminActorId = currentUser?.deviceId?.takeIf { it.isNotBlank() } ?: deviceId
                        adminViewModel.rejectAppeal(appealId, adminActorId, notes)
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            } else {
                PlaceholderScreen(
                    title = "Admin Access Required",
                    description = "Only approved admin devices can view this dashboard. Register your device to gain access.",
                    actionLabel = "Register as Admin",
                    onAction = {
                        adminRegistrationError = null
                        adminRegistrationPassword = ""
                        showAdminRegistrationDialog = true
                    }
                )
            }
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
        composable(
            route = PLuckDestination.ChosenEntrants.routeWithArg,
            arguments = listOf(navArgument(PLuckDestination.ChosenEntrants.EVENT_ID_ARG) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString(PLuckDestination.ChosenEntrants.EVENT_ID_ARG) ?: return@composable

            // Load the event and chosen entrants
            LaunchedEffect(eventId) {
                eventViewModel.loadEvent(eventId)
                waitlistViewModel.loadWaitlist(eventId)
                waitlistViewModel.loadChosenEntries(eventId)
            }

            // Only render if event is loaded
            selectedEvent?.let { event ->
                val currentUserIdForChosen = currentUser?.deviceId.orEmpty()
                LaunchedEffect(eventId, currentUserIdForChosen) {
                    if (eventId.isNotBlank()) {
                        waitlistViewModel.observeWaitlist(eventId, currentUserIdForChosen)
                        waitlistViewModel.observeChosenEntries(eventId, currentUserIdForChosen)
                    }
                }

                val waitingCount = waitlistEntries.count {
                    it.status == WaitlistStatus.WAITING ||
                            it.status == WaitlistStatus.INVITED ||
                            it.status == WaitlistStatus.SELECTED
                }
                val availableSpots = (event.capacity - event.enrolled).coerceAtLeast(0)
                val currentWave = if (event.samplingCount > 0) {
                    (chosenEntries.size / event.samplingCount) + 1
                } else {
                    1
                }
                var showRunDrawDialog by remember { mutableStateOf(false) }

                if (showRunDrawDialog) {
                    RunDrawDialog(
                        eventTitle = event.title,
                        waitingCount = waitingCount,
                        capacity = event.capacity,
                        enrolled = event.enrolled,
                        currentWave = currentWave,
                        samplingCount = event.samplingCount,
                        onConfirm = { drawSize ->
                            showRunDrawDialog = false
                            scope.launch {
                                eventViewModel.runDraw(
                                    event = event,
                                    waitlistViewModel = waitlistViewModel,
                                    drawSize = drawSize,
                                    onSuccess = {
                                        eventViewModel.loadEvent(event.id)
                                    }
                                )
                            }
                        },
                        onDismiss = { showRunDrawDialog = false }
                    )
                }

                ChosenEntrantsScreen(
                    event = event,
                    chosenEntrants = chosenEntries.map { entry ->
                        com.pluck.ui.screens.ChosenEntrant(
                            id = entry.id,
                            userId = entry.userId,
                            userName = entry.userName,
                            status = entry.status,
                            selectedAt = null
                        )
                    },
                    decisionStats = chosenStats,
                    waitingCount = waitingCount,
                    availableSpots = availableSpots,
                    isLoading = waitlistLoading || eventsLoading,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onExportCSV = {
                        waitlistViewModel.exportChosenEntrantsToCSV(
                            context = context,
                            event = event,
                            entrants = chosenEntries
                        ) { intent ->
                            context.startActivity(intent)
                        }
                    },
                    onRunDraw = { showRunDrawDialog = true },
                    onRemoveEntrant = { entrant ->
                        scope.launch {
                            waitlistViewModel.removeChosenEntrant(
                                eventId = event.id,
                                waitlistEntryId = entrant.id,
                                userId = entrant.userId,
                                currentUserId = currentUserIdForChosen
                            ) {
                                eventViewModel.loadEvent(event.id)
                            }
                        }
                    }
                )
            }
        }

        // EntrantLocationsMapScreen - Display entrant locations on map (US 02.02.02)
        composable(
            route = PLuckDestination.EntrantLocationsMap.routeWithArg,
            arguments = listOf(navArgument(PLuckDestination.EntrantLocationsMap.EVENT_ID_ARG) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString(PLuckDestination.EntrantLocationsMap.EVENT_ID_ARG) ?: return@composable

            // Load the event and waitlist entries
            LaunchedEffect(eventId) {
                eventViewModel.loadEvent(eventId)
                waitlistViewModel.loadWaitlist(eventId)
            }

            // Only render if event is loaded
            selectedEvent?.let { event ->
                EntrantLocationsMapScreen(
                    event = event,
                    entrants = waitlistEntries,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }

    if (showAdminRegistrationDialog) {
        AlertDialog(
            onDismissRequest = {
                if (!adminRegistrationInProgress) {
                    showAdminRegistrationDialog = false
                    adminRegistrationPassword = ""
                    adminRegistrationError = null
                }
            },
            title = { Text("Register as Admin") },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Enter the admin password to unlock the admin dashboard.")
                    OutlinedTextField(
                        value = adminRegistrationPassword,
                        onValueChange = { adminRegistrationPassword = it },
                        label = { Text("Admin Password") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (!adminRegistrationError.isNullOrBlank()) {
                        Text(
                            text = adminRegistrationError!!,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (adminRegistrationPassword.isBlank() || adminRegistrationInProgress) return@TextButton
                        adminRegistrationError = null
                        adminRegistrationInProgress = true
                        scope.launch {
                            adminAccessRepository.registerDevice(
                                password = adminRegistrationPassword.trim(),
                                deviceId = deviceId,
                                displayName = currentUser?.displayName
                            ).onSuccess {
                                isAdminDevice = true
                                Toast.makeText(context, "Admin access granted.", Toast.LENGTH_LONG).show()
                                showAdminRegistrationDialog = false
                                adminRegistrationPassword = ""
                                adminRegistrationError = null
                            }.onFailure { error ->
                                adminRegistrationError = error.message ?: "Failed to register as admin."
                            }
                            adminRegistrationInProgress = false
                        }
                    },
                    enabled = adminRegistrationPassword.isNotBlank() && !adminRegistrationInProgress
                ) {
                    if (adminRegistrationInProgress) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Register")
                    }
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        if (!adminRegistrationInProgress) {
                            showAdminRegistrationDialog = false
                            adminRegistrationPassword = ""
                            adminRegistrationError = null
                        }
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
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
    fun toChosenEntrants(eventId: String) = navController.navigate(PLuckDestination.ChosenEntrants.createRoute(eventId))
    fun toEntrantLocationsMap(eventId: String) = navController.navigate(PLuckDestination.EntrantLocationsMap.createRoute(eventId))
}

fun String.toIntOrDefault(defaultValue: Int = 0): Int {
    return try {
        this.toInt()
    } catch (e: NumberFormatException) {
        defaultValue
    }
}
