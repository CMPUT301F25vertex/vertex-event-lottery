/**
 * AdminDashboardScreen.kt
 *
 * Purpose: Admin dashboard for platform moderation and content management.
 * Provides a tabbed interface for browsing and removing events, profiles, images, organizers, and viewing notification logs.
 * Implements user stories US 03.01.01 through US 03.08.01 for administrative functions.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.model.Event
import com.pluck.ui.viewmodel.ImageSource

/**
 * Admin dashboard for platform moderation and content management.
 * Provides a tabbed interface for browsing and removing events, profiles, images,
 * organizers, and viewing notification logs.
 *
 * Implements user stories US 03.01.01 through US 03.08.01.
 */

/** Available tabs in the admin dashboard */
enum class AdminTab {
    EVENTS,         // Browse and remove events
    PROFILES,       // Browse and remove user profiles
    IMAGES,         // Browse and remove uploaded images
    ORGANIZERS,     // Remove organizer privileges from users
    APPEALS,        // Review and manage organizer access appeals
    NOTIFICATIONS   // View notification history
}

/**
 * Statistics displayed in the admin dashboard overview.
 */
data class AdminStats(
    val totalEvents: Int = 0,
    val totalUsers: Int = 0,
    val totalImages: Int = 0,
    val totalOrganizers: Int = 0,
    val totalNotifications: Int = 0
)

/**
 * Admin dashboard screen with tabbed interface for platform moderation.
 *
 * @param stats Overview statistics for the platform
 * @param events All events in the system
 * @param users All user profiles
 * @param organizers All users with organizer role
 * @param images All uploaded image metadata
 * @param notifications Notification history logs
 * @param isLoading Whether data is currently being loaded
 * @param onRemoveEvent Called when an event should be deleted
 * @param onRemoveProfile Called when a user profile should be deleted
 * @param onRemoveImage Called when an image should be deleted
 * @param onRemoveOrganizer Called when organizer role should be revoked
 */
@Composable
fun AdminDashboardScreen(
    stats: AdminStats = AdminStats(),
    events: List<Event> = emptyList(),
    users: List<com.pluck.data.firebase.FirebaseUser> = emptyList(),
    organizers: List<com.pluck.data.firebase.FirebaseUser> = emptyList(),
    appeals: List<com.pluck.data.firebase.OrganizerAppeal> = emptyList(),
    images: List<com.pluck.ui.viewmodel.ImageMetadata> = emptyList(),
    notifications: List<com.pluck.ui.viewmodel.NotificationLog> = emptyList(),
    isLoading: Boolean = false,
    onRemoveEvent: (String) -> Unit = {},
    onRemoveProfile: (String) -> Unit = {},
    onRemoveImage: (String) -> Unit = {},
    onRemoveOrganizer: (String) -> Unit = {},
    onApproveAppeal: (String, String?) -> Unit = { _, _ -> },
    onRejectAppeal: (String, String?) -> Unit = { _, _ -> },
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(AdminTab.EVENTS) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var itemToRemove by remember { mutableStateOf<Pair<String, String>?>(null) }

    val eventsListState = rememberLazyListState()
    val profilesListState = rememberLazyListState()
    val imagesListState = rememberLazyListState()
    val organizersListState = rememberLazyListState()
    val appealsListState = rememberLazyListState()
    val notificationsListState = rememberLazyListState()

    val activeListState = when (selectedTab) {
        AdminTab.EVENTS -> eventsListState
        AdminTab.PROFILES -> profilesListState
        AdminTab.IMAGES -> imagesListState
        AdminTab.ORGANIZERS -> organizersListState
        AdminTab.APPEALS -> appealsListState
        AdminTab.NOTIFICATIONS -> notificationsListState
    }

    val overviewCollapsed by remember(selectedTab) {
        derivedStateOf {
            activeListState.firstVisibleItemIndex > 0 ||
                activeListState.firstVisibleItemScrollOffset > 48
        }
    }
    val overviewSpacing by animateDpAsState(
        targetValue = if (overviewCollapsed) 12.dp else 24.dp,
        label = "adminOverviewSpacing"
    )

    PluckLayeredBackground(
        modifier = modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Floating back button
            Surface(
                modifier = Modifier
                    .padding(top = 24.dp, start = 24.dp)
                    .size(56.dp)
                    .align(Alignment.TopStart)
                    .zIndex(10f),
                shape = CircleShape,
                color = PluckPalette.Surface,
                tonalElevation = 0.dp,
                shadowElevation = 12.dp,
                onClick = onBack
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go back",
                        tint = PluckPalette.Primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                AnimatedVisibility(visible = !overviewCollapsed) {
                    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                        AdminDashboardHeader()
                        AdminStatsRow(stats = stats)
                    }
                }

                Spacer(modifier = Modifier.height(overviewSpacing))

                AdminTabSelector(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .zIndex(1f),
                    shape = RoundedCornerShape(36.dp),
                    color = PluckPalette.Surface,
                    tonalElevation = 0.dp,
                    shadowElevation = 12.dp,
                    border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
                ) {
                    when {
                        isLoading -> LoadingState()
                        else -> {
                            when (selectedTab) {
                                AdminTab.EVENTS -> EventsListContent(
                                    events = events,
                                    onRemove = { eventId ->
                                        itemToRemove = "Event" to eventId
                                        showConfirmDialog = true
                                    },
                                    listState = eventsListState,
                                    modifier = Modifier.fillMaxSize()
                                )
                                AdminTab.PROFILES -> ProfilesListContent(
                                    users = users,
                                    onRemove = { profileId ->
                                        itemToRemove = "Profile" to profileId
                                        showConfirmDialog = true
                                    },
                                    listState = profilesListState,
                                    modifier = Modifier.fillMaxSize()
                                )
                                AdminTab.IMAGES -> ImagesListContent(
                                    images = images,
                                    onRemove = { imageId ->
                                        itemToRemove = "Image" to imageId
                                        showConfirmDialog = true
                                    },
                                    listState = imagesListState,
                                    modifier = Modifier.fillMaxSize()
                                )
                                AdminTab.ORGANIZERS -> OrganizersListContent(
                                    organizers = organizers,
                                    onRemove = { organizerId ->
                                        itemToRemove = "Organizer" to organizerId
                                        showConfirmDialog = true
                                    },
                                    listState = organizersListState,
                                    modifier = Modifier.fillMaxSize()
                                )
                                AdminTab.APPEALS -> AppealsListContent(
                                    appeals = appeals,
                                    onApprove = onApproveAppeal,
                                    onReject = onRejectAppeal,
                                    listState = appealsListState,
                                    modifier = Modifier.fillMaxSize()
                                )
                                AdminTab.NOTIFICATIONS -> NotificationLogsContent(
                                    notifications = notifications,
                                    listState = notificationsListState,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Confirmation Dialog
    if (showConfirmDialog && itemToRemove != null) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text("Confirm Removal") },
            text = { Text("Are you sure you want to remove this ${itemToRemove!!.first}? This action cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        when (itemToRemove!!.first) {
                            "Event" -> onRemoveEvent(itemToRemove!!.second)
                            "Profile" -> onRemoveProfile(itemToRemove!!.second)
                            "Image" -> onRemoveImage(itemToRemove!!.second)
                            "Organizer" -> onRemoveOrganizer(itemToRemove!!.second)
                        }
                        showConfirmDialog = false
                        itemToRemove = null
                    }
                ) {
                    Text("Remove", color = PluckPalette.Decline)
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun AdminDashboardHeader() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 18.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.AdminPanelSettings,
                contentDescription = null,
                tint = PluckPalette.Primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Admin Dashboard",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Black,
                    color = PluckPalette.Primary,
                    fontSize = 28.sp
                )
            )
        }
    }
}

@Composable
private fun AdminStatsRow(stats: AdminStats) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AdminStatCard(
            label = "Events",
            value = stats.totalEvents.toString(),
            icon = Icons.Outlined.Event,
            color = PluckPalette.Primary,
            modifier = Modifier.weight(1f)
        )
        AdminStatCard(
            label = "Users",
            value = stats.totalUsers.toString(),
            icon = Icons.Outlined.People,
            color = PluckPalette.Secondary,
            modifier = Modifier.weight(1f)
        )
        AdminStatCard(
            label = "Organizers",
            value = stats.totalOrganizers.toString(),
            icon = Icons.Outlined.Business,
            color = PluckPalette.Accept,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun AdminStatCard(
    label: String,
    value: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = color.copy(alpha = 0.16f),
                contentColor = color
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = PluckPalette.Muted
                )
            )
        }
    }
}

@Composable
private fun AdminTabSelector(
    selectedTab: AdminTab,
    onTabSelected: (AdminTab) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AdminTab.values().forEach { tab ->
            val isSelected = selectedTab == tab
            val (icon, label) = when (tab) {
                AdminTab.EVENTS -> Icons.Outlined.Event to "Events"
                AdminTab.PROFILES -> Icons.Outlined.Person to "Profiles"
                AdminTab.IMAGES -> Icons.Outlined.Image to "Images"
                AdminTab.ORGANIZERS -> Icons.Outlined.Business to "Organizers"
                AdminTab.APPEALS -> Icons.Outlined.Gavel to "Appeals"
                AdminTab.NOTIFICATIONS -> Icons.Outlined.Notifications to "Logs"
            }

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onTabSelected(tab) },
                shape = RoundedCornerShape(16.dp),
                color = if (isSelected) PluckPalette.Primary else PluckPalette.Surface,
                tonalElevation = if (isSelected) 8.dp else 0.dp,
                shadowElevation = if (isSelected) 12.dp else 4.dp,
                border = BorderStroke(
                    1.dp,
                    if (isSelected) PluckPalette.Primary else PluckPalette.Primary.copy(alpha = 0.2f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (isSelected) Color.White else PluckPalette.Primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (isSelected) Color.White else PluckPalette.Primary,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun EventsListContent(
    events: List<Event>,
    onRemove: (String) -> Unit,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Browse & Manage Events (US 03.04.01, 03.01.01)",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
        }

        if (events.isEmpty()) {
            item {
                EmptyStateMessage("No events to display")
            }
        } else {
            items(events, key = { it.id }) { event ->
                AdminItemCard(
                    title = event.title,
                    subtitle = "Organizer: ${event.organizerName}",
                    detail = "${event.enrolled}/${event.capacity} enrolled",
                    onRemove = { onRemove(event.id) }
                )
            }
        }
    }
}

@Composable
private fun ProfilesListContent(
    users: List<com.pluck.data.firebase.FirebaseUser>,
    onRemove: (String) -> Unit,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Browse & Remove Profiles (US 03.05.01, 03.02.01)",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
        }

        if (users.isEmpty()) {
            item {
                EmptyStateMessage("No user profiles to display")
            }
        } else {
            items(users, key = { it.id }) { user ->
                AdminItemCard(
                    title = user.displayName,
                    subtitle = "Email: ${user.email}",
                    detail = "Role: ${user.role.name} • ${if (user.isActive) "Active" else "Inactive"}",
                    onRemove = { onRemove(user.id) }
                )
            }
        }
    }
}

@Composable
private fun ImagesListContent(
    images: List<com.pluck.ui.viewmodel.ImageMetadata>,
    onRemove: (String) -> Unit,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    var previewImage by remember { mutableStateOf<com.pluck.ui.viewmodel.ImageMetadata?>(null) }

    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Browse & Remove Images (US 03.06.01, 03.03.01)",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
        }

        if (images.isEmpty()) {
            item {
                EmptyStateMessage("No uploaded images to display")
            }
        } else {
            items(images, key = { it.id }) { image ->
                val sourceLabel = when (image.source) {
                    ImageSource.STORAGE -> "Firebase Storage"
                    ImageSource.EVENT_LINK -> "External Link"
                    ImageSource.PROFILE -> "Profile Photo"
                }
                val sizeLabel = if (image.sizeBytes > 0) {
                    "${image.sizeBytes / 1024} KB"
                } else {
                    "N/A"
                }
                val detailParts = mutableListOf<String>()
                detailParts += "Size: $sizeLabel"
                detailParts += "Path: ${image.path}"
                image.eventTitle?.let { detailParts += "Event: $it" }
                image.userName?.let { detailParts += "User: $it" }
                if (image.userName == null && image.userId != null) {
                    detailParts += "User ID: ${image.userId}"
                }

                AdminItemCard(
                    title = image.name,
                    subtitle = "Type: ${image.contentType} • Source: $sourceLabel",
                    detail = detailParts.joinToString(" • "),
                    onView = { previewImage = image },
                    onRemove = { onRemove(image.id) }
                )
            }
        }
    }

    previewImage?.let { image ->
        AlertDialog(
            onDismissRequest = { previewImage = null },
            title = {
                Text(
                    text = image.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = image.url,
                        contentDescription = image.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    image.eventTitle?.let { title ->
                        Text(
                            text = "Event: $title",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = PluckPalette.Primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                    image.userName?.let { user ->
                        Text(
                            text = "Profile: $user",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = PluckPalette.Primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    } ?: image.userId?.let { id ->
                        Text(
                            text = "Profile ID: $id",
                            style = MaterialTheme.typography.bodySmall.copy(color = PluckPalette.Muted)
                        )
                    }
                    Text(
                        text = "Source: ${when (image.source) {
                            ImageSource.STORAGE -> "Firebase Storage"
                            ImageSource.EVENT_LINK -> "External Link"
                            ImageSource.PROFILE -> "Profile Photo"
                        }}",
                        style = MaterialTheme.typography.bodySmall.copy(color = PluckPalette.Muted)
                    )
                    if (image.sizeBytes > 0) {
                        Text(
                            text = "Size: ${image.sizeBytes / 1024} KB",
                            style = MaterialTheme.typography.bodySmall.copy(color = PluckPalette.Muted)
                        )
                    }
                    Text(
                        text = "URL: ${image.url}",
                        style = MaterialTheme.typography.bodySmall.copy(color = PluckPalette.Secondary)
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { previewImage = null }) {
                    Text("Close")
                }
            }
        )
    }
}

@Composable
private fun OrganizersListContent(
    organizers: List<com.pluck.data.firebase.FirebaseUser>,
    onRemove: (String) -> Unit,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Remove Organizers (US 03.07.01)",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
        }

        if (organizers.isEmpty()) {
            item {
                EmptyStateMessage("No organizers to display")
            }
        } else {
            items(organizers, key = { it.id }) { organizer ->
                AdminItemCard(
                    title = organizer.displayName,
                    subtitle = "Email: ${organizer.email}",
                    detail = "Role: ORGANIZER • ${if (organizer.isActive) "Active" else "Inactive"}",
                    onRemove = { onRemove(organizer.id) }
                )
            }
        }
    }
}

@Composable
private fun AppealsListContent(
    appeals: List<com.pluck.data.firebase.OrganizerAppeal>,
    onApprove: (String, String?) -> Unit,
    onReject: (String, String?) -> Unit,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Organizer Access Appeals",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
        }

        if (appeals.isEmpty()) {
            item {
                EmptyStateMessage("No appeals to review")
            }
        } else {
            items(appeals, key = { it.id }) { appeal ->
                AppealCard(
                    appeal = appeal,
                    onApprove = { notes -> onApprove(appeal.id, notes) },
                    onReject = { notes -> onReject(appeal.id, notes) }
                )
            }
        }
    }
}

@Composable
private fun AppealCard(
    appeal: com.pluck.data.firebase.OrganizerAppeal,
    onApprove: (String?) -> Unit,
    onReject: (String?) -> Unit
) {
    var showNotesDialog by remember { mutableStateOf(false) }
    var action by remember { mutableStateOf<String?>(null) }
    var adminNotes by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 4.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.15f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = appeal.displayName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = PluckPalette.Primary
                        )
                    )
                    Text(
                        text = appeal.email,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Muted
                        )
                    )
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = when (appeal.status) {
                        com.pluck.data.firebase.AppealStatus.PENDING -> PluckPalette.Tertiary
                        com.pluck.data.firebase.AppealStatus.APPROVED -> PluckPalette.Accept
                        com.pluck.data.firebase.AppealStatus.REJECTED -> PluckPalette.Decline
                    }.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = appeal.status.name,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = when (appeal.status) {
                                com.pluck.data.firebase.AppealStatus.PENDING -> PluckPalette.Tertiary
                                com.pluck.data.firebase.AppealStatus.APPROVED -> PluckPalette.Accept
                                com.pluck.data.firebase.AppealStatus.REJECTED -> PluckPalette.Decline
                            }
                        )
                    )
                }
            }

            Text(
                text = "Message:",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = PluckPalette.Primary
                )
            )

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = PluckPalette.Primary.copy(alpha = 0.05f)
            ) {
                Text(
                    text = appeal.message,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Primary
                    )
                )
            }

            if (appeal.status == com.pluck.data.firebase.AppealStatus.PENDING) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            action = "approve"
                            showNotesDialog = true
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PluckPalette.Accept,
                            contentColor = PluckPalette.Surface
                        )
                    ) {
                        Text("Approve")
                    }

                    Button(
                        onClick = {
                            action = "reject"
                            showNotesDialog = true
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PluckPalette.Decline,
                            contentColor = PluckPalette.Surface
                        )
                    ) {
                        Text("Reject")
                    }
                }
            }
        }
    }

    if (showNotesDialog) {
        AlertDialog(
            onDismissRequest = { showNotesDialog = false },
            title = {
                Text(
                    text = "${action?.replaceFirstChar { it.uppercase() }} Appeal?",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = PluckPalette.Primary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Add notes (optional):",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PluckPalette.Primary
                        )
                    )
                    OutlinedTextField(
                        value = adminNotes,
                        onValueChange = { adminNotes = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Admin Notes") },
                        placeholder = { Text("Optional notes about your decision...") },
                        minLines = 3,
                        maxLines = 6
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showNotesDialog = false
                        when (action) {
                            "approve" -> onApprove(adminNotes.ifBlank { null })
                            "reject" -> onReject(adminNotes.ifBlank { null })
                        }
                        adminNotes = ""
                        action = null
                    },
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (action == "approve") PluckPalette.Accept else PluckPalette.Decline,
                        contentColor = PluckPalette.Surface
                    )
                ) {
                    Text(action?.replaceFirstChar { it.uppercase() } ?: "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showNotesDialog = false
                    adminNotes = ""
                    action = null
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun NotificationLogsContent(
    notifications: List<com.pluck.ui.viewmodel.NotificationLog>,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Notification Logs (US 03.08.01)",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
        }

        if (notifications.isEmpty()) {
            item {
                EmptyStateMessage("No notification logs to display")
            }
        } else {
            items(notifications, key = { it.id }) { notification ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    color = PluckPalette.Surface,
                    tonalElevation = 0.dp,
                    shadowElevation = 10.dp,
                    border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = notification.title,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = PluckPalette.Primary
                            )
                        )
                        Text(
                            text = notification.subtitle,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = PluckPalette.Muted
                            )
                        )
                        Text(
                            text = "User: ${notification.userId} • Event: ${notification.eventId}",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = PluckPalette.Secondary,
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Text(
                            text = "Category: ${notification.category} • Status: ${notification.status}",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = PluckPalette.Muted
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AdminItemCard(
    title: String,
    subtitle: String,
    detail: String,
    onView: (() -> Unit)? = null,
    onRemove: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 10.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = PluckPalette.Primary
                    )
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PluckPalette.Muted
                    )
                )
                Text(
                    text = detail,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PluckPalette.Secondary,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                onView?.let {
                    TextButton(onClick = it) {
                        Text("View")
                    }
                }
                onRemove?.let {
                    IconButton(onClick = it) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Remove",
                            tint = PluckPalette.Decline
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = PluckPalette.Primary)
    }
}

@Composable
private fun EmptyStateMessage(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Muted
            )
        )
    }
}
