/**
 * WaitlistScreen.kt
 *
 * Purpose: Displays waitlist details, supporting queue management, chosen entrants, and joining/leave actions.
 * Entrants can view their position in the waitlist and manage their enrollment status.
 * Organizers can view all waitlist entries and perform lottery draws.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.pluck.data.firebase.FirebaseUser
import com.pluck.ui.components.BackButton
import com.pluck.ui.components.ComposableItem
import com.pluck.ui.components.FullWidthLazyScroll
import com.pluck.ui.components.NotificationWriter
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.components.ProfileCircle
import com.pluck.ui.components.SquircleScrollableLazyList
import com.pluck.ui.model.Event
import com.pluck.ui.theme.autoTextColor
import java.time.LocalDate
import java.time.format.DateTimeFormatter
private enum class WaitlistTab {
    WAITING,
    CHOSEN
}

/**
 * Represents a user's entry on an event waitlist.
 *
 * @property latitude Optional latitude coordinate where user joined (US 02.02.02)
 * @property longitude Optional longitude coordinate where user joined (US 02.02.02)
 */
data class WaitlistEntry(
    val id: String,
    val userId: String = "",
    val userName: String,
    val position: Int,
    val joinedDate: LocalDate,
    val isCurrentUser: Boolean = false,
    val isChosen: Boolean = false,
    val status: com.pluck.data.firebase.WaitlistStatus = com.pluck.data.firebase.WaitlistStatus.WAITING,
    val latitude: Double? = null,
    val longitude: Double? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaitlistScreen(
    event: Event,
    waitlistEntries: List<WaitlistEntry> = emptyList(),
    chosenEntries: List<WaitlistEntry> = emptyList(),
    onBack: () -> Unit = {},
    users: List<FirebaseUser> = emptyList(),
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(WaitlistTab.WAITING) }
    val waitlistSize = waitlistEntries.size

    var showNotificationWriterDialog by remember { mutableStateOf(false) }

    val listElements = mutableListOf<ComposableItem>()

    listElements.add(ComposableItem {
        BackButton(
            onBack = onBack
        )
    })

    listElements.add(ComposableItem {
        WaitlistHeaderCard(
            event = event
        )
    })

    listElements.add(ComposableItem {
        Button(
            onClick = {
                showNotificationWriterDialog = true
            },
            modifier = modifier
                .height(40.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PluckPalette.Secondary,
                contentColor = autoTextColor(PluckPalette.Secondary)
            ),
            contentPadding = PaddingValues(8.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "Notify Everyone Waiting",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 1,
            )
        }
    })

    listElements.add(ComposableItem {
        WaitlistTabSelector(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
            waitingCount = waitlistEntries.size,
            chosenCount = chosenEntries.size,
            modifier = Modifier.animateContentSize()
        )
    })

    listElements.add(ComposableItem {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .widthIn(max = 460.dp),
            shape = RoundedCornerShape(36.dp),
            color = PluckPalette.Surface,
            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
        )
        {
            when (selectedTab) {
                WaitlistTab.WAITING -> {
                    if (waitlistEntries.isEmpty()) {
                        WaitlistEmptyState(
                            message = "No one on the waitlist yet",
                            description = ""
                        )
                    } else {
                        WaitlistEntriesList(
                            entries = waitlistEntries,
                            title = "Waitlist Queue",
                            users = users
                        )
                    }
                }
                WaitlistTab.CHOSEN -> {
                    if (chosenEntries.isEmpty()) {
                        WaitlistEmptyState(
                            message = "No entrants chosen yet",
                            description = "Run the lottery to randomly select entrants from the waitlist."
                        )
                    } else {
                        WaitlistEntriesList(
                            entries = chosenEntries,
                            title = "Chosen Entrants",
                            users = users
                        )
                    }
                }
            }
        }
    })

    PluckLayeredBackground(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    )
    {
        FullWidthLazyScroll(
            listElements = listElements
        )
    }

    if (showNotificationWriterDialog) {
        val waitlistUserIds = mutableListOf<String>()

        for (wait in waitlistEntries) {
            waitlistUserIds.add(wait.userId)
        }

        NotificationWriter(
            users = waitlistUserIds,
            onDismiss = {
                showNotificationWriterDialog = false
            },
            onConfirm = {
                showNotificationWriterDialog = false
            }
        )
    }
}

@Composable
private fun WaitlistHeaderCard(
    event: Event
) {
    val selectionMessage = if (event.samplingCount > 0) {
        val plural = if (event.samplingCount == 1) "" else "s"
        "Lottery draws randomly select ${event.samplingCount} entrant$plural each time. Everyone else keeps their waitlist spot for the next draw."
    } else {
        "Lottery draws randomly select entrants from the waiting list. If you are not chosen, you remain in line for the next draw."
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Black,
                    color = PluckPalette.Primary,
                    fontSize = 22f.sp
                ),
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                WaitlistStatItem(
                    icon = Icons.Outlined.Person,
                    value = "${event.enrolled}/${event.capacity}",
                    label = "Enrolled",
                    accentColor = PluckPalette.Secondary,
                    circleSize = 56.dp,
                    iconSize = 40.dp
                )
                WaitlistStatItem(
                    icon = Icons.Outlined.Groups,
                    value = "${event.waitlistCount}",
                    label = "Waitlisted",
                    accentColor = PluckPalette.Tertiary,
                    circleSize = 56.dp,
                    iconSize = 40.dp
                )
                WaitlistStatItem(
                    icon = Icons.Outlined.Schedule,
                    value = "${if (event.waitlistCapacity == Int.MAX_VALUE) "∞" else event.waitlistAvailable}",
                    label = "Available",
                    accentColor = PluckPalette.Accept,
                    circleSize = 56.dp,
                    iconSize = 40.dp
                )
            }

            Text(
                text = selectionMessage,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = PluckPalette.Muted
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun WaitlistStatItem(
    icon: ImageVector,
    value: String,
    label: String,
    accentColor: Color,
    circleSize: Dp = 56.dp,
    iconSize: Dp = 28.dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            modifier = Modifier.size(circleSize),
            shape = CircleShape,
            color = accentColor.copy(alpha = 0.12f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(iconSize)
                )
            }
        }
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WaitlistTabSelector(
    selectedTab: WaitlistTab,
    onTabSelected: (WaitlistTab) -> Unit,
    waitingCount: Int,
    chosenCount: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FilterChip(
                selected = selectedTab == WaitlistTab.WAITING,
                onClick = { onTabSelected(WaitlistTab.WAITING) },
                label = { Text("Waiting ($waitingCount)") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Schedule,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                },
                modifier = Modifier.weight(1f),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = if (selectedTab == WaitlistTab.WAITING) PluckPalette.Tertiary else PluckPalette.Surface,
                    labelColor = if (selectedTab == WaitlistTab.WAITING) autoTextColor(PluckPalette.Tertiary) else PluckPalette.Primary,
                    selectedContainerColor = PluckPalette.Tertiary,
                    selectedLabelColor = autoTextColor(PluckPalette.Tertiary)
                )
            )
            FilterChip(
                selected = selectedTab == WaitlistTab.CHOSEN,
                onClick = { onTabSelected(WaitlistTab.CHOSEN) },
                label = { Text("Chosen ($chosenCount)") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                },
                modifier = Modifier.weight(1f),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = if (selectedTab == WaitlistTab.CHOSEN) PluckPalette.Accept else PluckPalette.Surface,
                    labelColor = if (selectedTab == WaitlistTab.CHOSEN) autoTextColor(PluckPalette.Accept) else PluckPalette.Primary,
                    selectedContainerColor = PluckPalette.Accept,
                    selectedLabelColor = autoTextColor(PluckPalette.Accept)
                )
            )
        }
    }
}

@Composable
private fun WaitlistEntriesList(
    entries: List<WaitlistEntry>,
    title: String,
    users: List<FirebaseUser>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = PluckPalette.Primary
            )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            entries.forEach { entry ->
                val usr: FirebaseUser? = users.find{ user ->
                    user.id == entry.userId
                }

                val userProfileURL = usr?.profileImageUrl ?: ""

                WaitlistEntryCard(
                    entry = entry,
                    userProfileURL = userProfileURL
                )
            }
        }
    }
}

@Composable
private fun WaitlistEntryCard(
    entry: WaitlistEntry,
    userProfileURL: String
) {
    val accentColor = when (entry.status) {
        com.pluck.data.firebase.WaitlistStatus.ACCEPTED -> PluckPalette.Accept
        com.pluck.data.firebase.WaitlistStatus.INVITED -> PluckPalette.Tertiary
        com.pluck.data.firebase.WaitlistStatus.DECLINED -> PluckPalette.Decline
        else -> if (entry.isCurrentUser) PluckPalette.Secondary else PluckPalette.Primary
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = if (entry.isCurrentUser) 10.dp else 4.dp,
        border = BorderStroke(
            width = if (entry.isCurrentUser) 2.dp else 1.dp,
            color = accentColor.copy(alpha = if (entry.isCurrentUser) 0.85f else 0.25f)
        )
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = accentColor.copy(alpha = if (entry.isCurrentUser || entry.isChosen) 0.18f else 0.06f),
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileCircle(
                    userName = entry.userName,
                    profileImageUrl = userProfileURL,
                    isUploading = false,
                    size = 48,
                    modifier = Modifier.size(48.dp),
                    textStyle = MaterialTheme.typography.bodyMedium
                )

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = entry.userName + if (entry.isCurrentUser) " (You)" else "",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PluckPalette.Primary
                        )
                    )
                    val dateFormatter = DateTimeFormatter.ofPattern("MMM d")
                    Text(
                        text = "Joined ${entry.joinedDate.format(dateFormatter)}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Muted
                        )
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = accentColor.copy(alpha = if (entry.isCurrentUser || entry.isChosen) 0.25f else 0.15f),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp
            ) {
                val statusLabel = when (entry.status) {
                    com.pluck.data.firebase.WaitlistStatus.ACCEPTED -> "Confirmed"
                    com.pluck.data.firebase.WaitlistStatus.INVITED -> "Invited"
                    com.pluck.data.firebase.WaitlistStatus.DECLINED -> "Declined"
                    com.pluck.data.firebase.WaitlistStatus.CANCELLED -> "Removed"
                    else -> if (entry.isCurrentUser) "You" else "In Queue"
                }
                Text(
                    text = statusLabel,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = accentColor
                    )
                )
            }
        }
    }
}

@Composable
private fun WaitlistEmptyState(message: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = PluckPalette.Primary
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Muted
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 920)
@Composable
private fun WaitlistScreenPreview() {
    val previewEvent = Event(
        id = "1",
        title = "Swimming Lessons",
        description = "Learn the fundamentals of swimming with certified instructors.",
        location = "City Pool",
        date = LocalDate.now().plusDays(3),
        capacity = 20,
        enrolled = 18,
        organizerName = "Vertex Community",
        waitlistCount = 5,
        waitlistCapacity = 40,
        samplingCount = 3
    )

    val waitlistEntries = listOf(
        WaitlistEntry(
            id = "1",
            userId = "user-1",
            userName = "Alice Johnson",
            position = 1,
            joinedDate = LocalDate.now().minusDays(5),
            isCurrentUser = true
        ),
        WaitlistEntry(
            id = "2",
            userId = "user-2",
            userName = "Bob Smith",
            position = 2,
            joinedDate = LocalDate.now().minusDays(3)
        ),
        WaitlistEntry(
            id = "3",
            userId = "user-3",
            userName = "Charlie Davis",
            position = 3,
            joinedDate = LocalDate.now().minusDays(2)
        )
    )

    val chosenEntries = listOf(
        WaitlistEntry(
            id = "10",
            userId = "user-10",
            userName = "Dana Lee",
            position = 1,
            joinedDate = LocalDate.now().minusDays(4),
            isChosen = true
        ),
        WaitlistEntry(
            id = "11",
            userId = "user-11",
            userName = "Evan Torres",
            position = 2,
            joinedDate = LocalDate.now().minusDays(1),
            isChosen = true
        )
    )

    WaitlistScreen(
        event = previewEvent,
        waitlistEntries = waitlistEntries,
        chosenEntries = chosenEntries,
        onBack = {},
        users = emptyList()
    )
}
