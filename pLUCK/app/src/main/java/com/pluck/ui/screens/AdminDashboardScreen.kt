package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.model.Event

/**
 * AdminDashboardScreen.kt
 *
 * Purpose: Central admin control panel for platform moderation.
 * Implements US 03.01.01 - 03.08.01 (Browse & Remove Events, Profiles, Images, Organizers, Notification Logs).
 *
 * Design Pattern: Stateful screen following the pLUCK design language with tabbed interface.
 *
 * Outstanding Issues: Profile, Image, Organizer, and Notification features require additional repositories.
 */

enum class AdminTab {
    EVENTS,      // US 03.04.01, 03.01.01
    PROFILES,    // US 03.05.01, 03.02.01
    IMAGES,      // US 03.06.01, 03.03.01
    ORGANIZERS,  // US 03.07.01
    NOTIFICATIONS // US 03.08.01
}

data class AdminStats(
    val totalEvents: Int = 0,
    val totalUsers: Int = 0,
    val totalImages: Int = 0,
    val totalOrganizers: Int = 0,
    val totalNotifications: Int = 0
)

@Composable
fun AdminDashboardScreen(
    stats: AdminStats = AdminStats(),
    events: List<Event> = emptyList(),
    users: List<com.pluck.data.firebase.FirebaseUser> = emptyList(),
    organizers: List<com.pluck.data.firebase.FirebaseUser> = emptyList(),
    images: List<com.pluck.ui.viewmodel.ImageMetadata> = emptyList(),
    notifications: List<com.pluck.ui.viewmodel.NotificationLog> = emptyList(),
    isLoading: Boolean = false,
    onRemoveEvent: (String) -> Unit = {},
    onRemoveProfile: (String) -> Unit = {},
    onRemoveImage: (String) -> Unit = {},
    onRemoveOrganizer: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(AdminTab.EVENTS) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var itemToRemove by remember { mutableStateOf<Pair<String, String>?>(null) }

    PluckLayeredBackground(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Header
            AdminDashboardHeader()

            // Stats Overview
            AdminStatsRow(stats = stats)

            // Tab Selector
            AdminTabSelector(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            // Content Area
            Surface(
                modifier = Modifier
                    .fillMaxSize()
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
                                }
                            )
                            AdminTab.PROFILES -> ProfilesListContent(
                                users = users,
                                onRemove = { profileId ->
                                    itemToRemove = "Profile" to profileId
                                    showConfirmDialog = true
                                }
                            )
                            AdminTab.IMAGES -> ImagesListContent(
                                images = images,
                                onRemove = { imageId ->
                                    itemToRemove = "Image" to imageId
                                    showConfirmDialog = true
                                }
                            )
                            AdminTab.ORGANIZERS -> OrganizersListContent(
                                organizers = organizers,
                                onRemove = { organizerId ->
                                    itemToRemove = "Organizer" to organizerId
                                    showConfirmDialog = true
                                }
                            )
                            AdminTab.NOTIFICATIONS -> NotificationLogsContent(
                                notifications = notifications
                            )
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
    onRemove: (String) -> Unit
) {
    LazyColumn(
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
    onRemove: (String) -> Unit
) {
    LazyColumn(
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
    onRemove: (String) -> Unit
) {
    LazyColumn(
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
                AdminItemCard(
                    title = image.name,
                    subtitle = "Type: ${image.contentType}",
                    detail = "Size: ${image.sizeBytes / 1024} KB • Path: ${image.path}",
                    onRemove = { onRemove(image.id) }
                )
            }
        }
    }
}

@Composable
private fun OrganizersListContent(
    organizers: List<com.pluck.data.firebase.FirebaseUser>,
    onRemove: (String) -> Unit
) {
    LazyColumn(
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
private fun NotificationLogsContent(
    notifications: List<com.pluck.ui.viewmodel.NotificationLog>
) {
    LazyColumn(
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
    onRemove: () -> Unit
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

            IconButton(onClick = onRemove) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Remove",
                    tint = PluckPalette.Decline
                )
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
