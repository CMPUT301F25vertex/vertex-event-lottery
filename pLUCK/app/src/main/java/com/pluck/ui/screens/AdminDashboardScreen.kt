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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pluck.data.firebase.AppealStatus
import com.pluck.data.firebase.FirebaseUser
import com.pluck.data.firebase.OrganizerAppeal
import com.pluck.ui.components.AutoHidingBarScroller
import com.pluck.ui.components.BottomNavBar
import com.pluck.ui.components.ComposableItem
import com.pluck.ui.components.Dashboard
import com.pluck.ui.components.DashboardType
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.model.Event
import com.pluck.ui.viewmodel.ImageMetadata
import com.pluck.ui.viewmodel.ImageSource
import com.pluck.ui.viewmodel.NotificationLog

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
    users: List<FirebaseUser> = emptyList(),
    organizers: List<FirebaseUser> = emptyList(),
    appeals: List<OrganizerAppeal> = emptyList(),
    images: List<ImageMetadata> = emptyList(),
    notifications: List<NotificationLog> = emptyList(),
    isLoading: Boolean = false,
    onRemoveEvent: (String) -> Unit = {},
    onRemoveProfile: (String) -> Unit = {},
    onRemoveImage: (String) -> Unit = {},
    onRemoveOrganizer: (String) -> Unit = {},
    onApproveAppeal: (String, String?) -> Unit = { _, _ -> },
    onRejectAppeal: (String, String?) -> Unit = { _, _ -> },
    currentRoute: String?,
    dashboards: List<Dashboard>,
    onNavigate: (String) -> Unit,
) {
    var selectedTab by remember { mutableStateOf(AdminTab.EVENTS) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var itemToRemove by remember { mutableStateOf<Pair<String, String>?>(null) }

    var previewImage by remember { mutableStateOf<ImageMetadata?>(null) }

    val listElements = mutableListOf<ComposableItem>()

    listElements.add(ComposableItem {
        AdminStatsRow(stats = stats)
    })

    listElements.add(ComposableItem {
        AdminTabSelector(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )
    })

//    listElements.add(ComposableItem {
//        val (icon, label) = getIconLabelForTab(selectedTab)
//
//        AdminDashboardHeader(
//            imageVector = icon,
//            text = label
//        )
//    })

    when {
        isLoading -> listElements.add(ComposableItem { LoadingState() })
        else -> {
            when (selectedTab) {
                AdminTab.EVENTS -> listElements.addAll(EventsListContent(
                    events = events,
                    onRemove = { eventId ->
                        itemToRemove = "Event" to eventId
                        showConfirmDialog = true
                    }
                ))

                AdminTab.PROFILES -> listElements.addAll(ProfilesListContent(
                    users = users,
                    onRemove = { profileId ->
                        itemToRemove = "Profile" to profileId
                        showConfirmDialog = true
                    }
                ))
                AdminTab.IMAGES -> listElements.addAll(ImagesListContent(
                    images = images,
                    onRemove = { imageId ->
                        itemToRemove = "Image" to imageId
                        showConfirmDialog = true
                    },
                    onView = {
                        image -> previewImage = image
                    }
                ))
                AdminTab.ORGANIZERS -> listElements.addAll(OrganizersListContent(
                    organizers = organizers,
                    onRemove = { organizerId ->
                        itemToRemove = "Organizer" to organizerId
                        showConfirmDialog = true
                    }
                ))
                AdminTab.APPEALS -> listElements.addAll(AppealsListContent(
                    appeals = appeals,
                    onApprove = onApproveAppeal,
                    onReject = onRejectAppeal
                ))
                AdminTab.NOTIFICATIONS -> listElements.addAll(NotificationLogsContent(
                    notifications = notifications
                ))
            }
        }
    }

    AutoHidingBarScroller(
        listElements = listElements,
        indexOfPersistentElement = 1,
        bottomBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                onNavigate = onNavigate,
                dashboards = dashboards,
                currentDashboard = DashboardType.Admin
            )
        },
        spacingBetweenItems = 6.dp
    )

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
private fun AdminDashboardHeader(
    imageVector: ImageVector = Icons.Outlined.AdminPanelSettings,
    text: String = "Admin Dashboard"
) {
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
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = PluckPalette.Primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Black,
                    color = PluckPalette.Primary,
                    fontSize = 18.sp
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

private fun getIconLabelForTab(
    tab: AdminTab
): Pair<ImageVector, String> {
    val (icon, label) = when (tab) {
        AdminTab.EVENTS -> Icons.Outlined.Event to "Events"
        AdminTab.PROFILES -> Icons.Outlined.Person to "Profiles"
        AdminTab.IMAGES -> Icons.Outlined.Image to "Images"
        AdminTab.ORGANIZERS -> Icons.Outlined.Business to "Organizers"
        AdminTab.APPEALS -> Icons.Outlined.Gavel to "Appeals"
        AdminTab.NOTIFICATIONS -> Icons.Outlined.Notifications to "Logs"
    }

    return Pair(icon, label)
}

@Composable
private fun AdminTabSelector(
    selectedTab: AdminTab,
    onTabSelected: (AdminTab) -> Unit
) {
    Surface(
        color = PluckPalette.Surface,
        shape = RoundedCornerShape(24.dp)
    ) {
        LazyRow (
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            AdminTab.values().forEach { tab ->
                val isSelected = selectedTab == tab
                val (icon, label) = getIconLabelForTab(tab)
                item {
                    Surface(
                        modifier = Modifier
                            .clickable { onTabSelected(tab) },
                        shape = RoundedCornerShape(16.dp),
                        color = if (isSelected) PluckPalette.Primary else PluckPalette.Surface,
                        tonalElevation = if (isSelected) 8.dp else 0.dp,
                        shadowElevation = if (isSelected) 12.dp else 4.dp,
                        border = BorderStroke(
                            1.dp,
                            if (isSelected) PluckPalette.Primary else PluckPalette.Primary.copy(alpha = 0.2f)
                        )
                    )
                    {
                        Row(
                            modifier = Modifier.padding(6.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
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
    }
}

private fun EventsListContent(
    events: List<Event>,
    onRemove: (String) -> Unit
): List<ComposableItem> {

    val eventElements = mutableListOf<ComposableItem>()

    if (events.isEmpty()) {
        eventElements.add(ComposableItem{
            EmptyStateMessage("No events to display")
        })
    } else {
        for (event in events) {
            eventElements.add(ComposableItem{
                AdminItemCard(
                    title = event.title,
                    subtitle = "Organizer: ${event.organizerName}",
                    detail = "${event.enrolled}/${event.capacity} enrolled",
                    onRemove = { onRemove(event.id) }
                )
            })
        }
    }

    return eventElements
}

private fun ProfilesListContent(
    users: List<FirebaseUser>,
    onRemove: (String) -> Unit
): List<ComposableItem> {
    val profileElements = mutableListOf<ComposableItem>()

    if (users.isEmpty()) {
        profileElements.add(ComposableItem{
            EmptyStateMessage("No user profiles to display")
        })
    } else {
        for (user in users) {
            profileElements.add(ComposableItem{
                AdminItemCard(
                    title = user.displayName,
                    subtitle = "Email: ${user.email}",
                    detail = "Role: ${user.role.name} • ${if (user.isActive) "Active" else "Inactive"}",
                    onRemove = { onRemove(user.id) }
                )
            })
        }
    }

    return profileElements
}

private fun ImagesListContent(
    images: List<ImageMetadata>,
    onRemove: (String) -> Unit,
    onView: (ImageMetadata) -> Unit
): List<ComposableItem> {
    val imageElements = mutableListOf<ComposableItem>()

    if (images.isEmpty()) {
        imageElements.add(ComposableItem {
            EmptyStateMessage("No uploaded images to display")
        })
    } else {
        for (image in images) {
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

            imageElements.add(ComposableItem {
                AdminItemCard(
                    title = image.name,
                    subtitle = "Type: ${image.contentType} • Source: $sourceLabel",
                    detail = detailParts.joinToString(" • "),
                    onView = { onView(image) },
                    onRemove = { onRemove(image.id) }
                )
            })
        }
    }

    return imageElements
}

private fun OrganizersListContent(
    organizers: List<FirebaseUser>,
    onRemove: (String) -> Unit
): List<ComposableItem> {

    val organizerElements = mutableListOf<ComposableItem>()

    if (organizers.isEmpty()) {
        organizerElements.add(ComposableItem {
            EmptyStateMessage("No organizers to display")
        })
    } else {
        for (organizer in organizers) {
            organizerElements.add(ComposableItem {
                AdminItemCard(
                    title = organizer.displayName,
                    subtitle = "Email: ${organizer.email}",
                    detail = "Role: ORGANIZER • ${if (organizer.isActive) "Active" else "Inactive"}",
                    onRemove = { onRemove(organizer.id) }
                )
            })
        }
    }

    return organizerElements
}

private fun AppealsListContent(
    appeals: List<OrganizerAppeal>,
    onApprove: (String, String?) -> Unit,
    onReject: (String, String?) -> Unit
): List<ComposableItem> {

    val appealsElements = mutableListOf<ComposableItem>()

    if (appeals.isEmpty()) {
        appealsElements.add(ComposableItem{
            EmptyStateMessage("No appeals to review")
        })
    } else {
        for (appeal in appeals) {
            appealsElements.add(ComposableItem{
                AppealCard(
                    appeal = appeal,
                    onApprove = { notes -> onApprove(appeal.id, notes) },
                    onReject = { notes -> onReject(appeal.id, notes) }
                )
            })
        }
    }

    return appealsElements
}

@Composable
private fun AppealCard(
    appeal: OrganizerAppeal,
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
                        AppealStatus.PENDING -> PluckPalette.Tertiary
                        AppealStatus.APPROVED -> PluckPalette.Accept
                        AppealStatus.REJECTED -> PluckPalette.Decline
                    }.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = appeal.status.name,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = when (appeal.status) {
                                AppealStatus.PENDING -> PluckPalette.Tertiary
                                AppealStatus.APPROVED -> PluckPalette.Accept
                                AppealStatus.REJECTED -> PluckPalette.Decline
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

            if (appeal.status == AppealStatus.PENDING) {
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

private fun NotificationLogsContent(
    notifications: List<NotificationLog>
): List<ComposableItem> {
    val notificationElements = mutableListOf<ComposableItem>()

    if (notifications.isEmpty()) {
        notificationElements.add(ComposableItem {
            EmptyStateMessage("No notification logs to display")

        })
    }
    else {
        for (notification in notifications) {
            notificationElements.add(ComposableItem {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    color = PluckPalette.Surface,
                    tonalElevation = 0.dp,
                    shadowElevation = 10.dp,
                    border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
                )
                {
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
            })
        }
    }

    return notificationElements
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
