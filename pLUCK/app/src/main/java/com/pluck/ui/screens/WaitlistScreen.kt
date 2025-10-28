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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
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
    isUserWaiting: Boolean = false,
    isUserConfirmed: Boolean = false,
    onJoinWaitlist: () -> Unit = {},
    onLeaveWaitlist: () -> Unit = {},
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(WaitlistTab.WAITING) }
    val waitlistSize = waitlistEntries.size
    val waitlistFull = waitlistSize >= event.waitlistCapacity
    val currentUserPosition = waitlistEntries.firstOrNull { it.isCurrentUser }?.position
    val derivedUserWaiting = isUserWaiting || waitlistEntries.any { it.isCurrentUser }
    val derivedUserConfirmed = isUserConfirmed || chosenEntries.any { it.isCurrentUser }

    val entriesListState = rememberLazyListState()
    val rawCollapse by remember(entriesListState) {
        derivedStateOf {
            when {
                entriesListState.firstVisibleItemIndex > 0 -> 1f
                else -> (entriesListState.firstVisibleItemScrollOffset / 260f).coerceIn(0f, 1f)
            }
        }
    }
    val collapseProgress by animateFloatAsState(
        targetValue = rawCollapse,
        label = "waitlistHeaderCollapse"
    )
    val spacerHeight by animateDpAsState(
        targetValue = if (collapseProgress > 0.05f) 24.dp else 56.dp,
        label = "waitlistSpacerHeight"
    )

    LaunchedEffect(selectedTab) {
        entriesListState.scrollToItem(0)
    }

    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(
                modifier = Modifier
                    .padding(top = 24.dp, start = 24.dp)
                    .size(56.dp)
                    .align(Alignment.TopStart),
                shape = CircleShape,
                color = PluckPalette.Surface,
                tonalElevation = 0.dp,
                shadowElevation = 12.dp,
                onClick = onBack
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go back",
                        tint = PluckPalette.Primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Spacer(modifier = Modifier.height(spacerHeight))

                WaitlistHeaderCard(
                    event = event,
                    collapseProgress = collapseProgress
                )

                WaitlistSummaryCard(
                    event = event,
                    waitlistSize = waitlistSize,
                    waitlistCapacity = event.waitlistCapacity,
                    isUserWaiting = derivedUserWaiting,
                    isUserConfirmed = derivedUserConfirmed,
                    currentUserPosition = currentUserPosition,
                    waitlistFull = waitlistFull,
                    onJoinWaitlist = onJoinWaitlist,
                    onLeaveWaitlist = onLeaveWaitlist,
                    modifier = Modifier.animateContentSize()
                )

                WaitlistTabSelector(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it },
                    waitingCount = waitlistEntries.size,
                    chosenCount = chosenEntries.size,
                    modifier = Modifier.animateContentSize()
                )

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize()
                        .widthIn(max = 460.dp),
                    shape = RoundedCornerShape(36.dp),
                    color = PluckPalette.Surface,
                    tonalElevation = 0.dp,
                    shadowElevation = 12.dp,
                    border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
                ) {
                    when (selectedTab) {
                        WaitlistTab.WAITING -> {
                            if (waitlistEntries.isEmpty()) {
                                WaitlistEmptyState(
                                    message = "No one on the waitlist yet",
                                    description = "Be the first to join the waitlist for this event!"
                                )
                            } else {
                                WaitlistEntriesList(
                                    entries = waitlistEntries,
                                    title = "Waitlist Queue",
                                    listState = entriesListState
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
                                    listState = entriesListState
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WaitlistHeaderCard(
    event: Event,
    collapseProgress: Float
) {
    val selectionMessage = if (event.samplingCount > 0) {
        val plural = if (event.samplingCount == 1) "" else "s"
        "Lottery draws randomly select ${event.samplingCount} entrant$plural each time. Everyone else keeps their waitlist spot for the next draw."
    } else {
        "Lottery draws randomly select entrants from the waiting list. If you are not chosen, you remain in line for the next draw."
    }

    val paddingFactor = (1f - 0.35f * collapseProgress).coerceIn(0.65f, 1f)
    val headerPadding by animateDpAsState(
        targetValue = 24.dp * paddingFactor,
        label = "waitlistHeaderPadding"
    )
    val columnSpacing by animateDpAsState(
        targetValue = 16.dp * (1f - 0.3f * collapseProgress).coerceIn(0.6f, 1f),
        label = "waitlistHeaderSpacing"
    )
    val badgeSize by animateDpAsState(
        targetValue = 56.dp * (1f - 0.2f * collapseProgress).coerceIn(0.7f, 1f),
        label = "waitlistHeaderBadge"
    )
    val badgeIconSize by animateDpAsState(
        targetValue = 40.dp * (1f - 0.2f * collapseProgress).coerceIn(0.7f, 1f),
        label = "waitlistHeaderBadgeIcon"
    )
    val titleSize by animateFloatAsState(
        targetValue = if (collapseProgress > 0.6f) 22f else 24f,
        label = "waitlistHeaderTitleSize"
    )
    val shadowElevation by animateDpAsState(
        targetValue = 16.dp * (1f - 0.5f * collapseProgress).coerceIn(0.5f, 1f),
        label = "waitlistHeaderElevation"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp)
            .animateContentSize(),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = shadowElevation,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = headerPadding, vertical = headerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(columnSpacing)
        ) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Black,
                    color = PluckPalette.Primary,
                    fontSize = titleSize.sp
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
                    circleSize = badgeSize,
                    iconSize = badgeIconSize
                )
                WaitlistStatItem(
                    icon = Icons.Outlined.Groups,
                    value = "${event.waitlistCount}",
                    label = "Waitlisted",
                    accentColor = PluckPalette.Tertiary,
                    circleSize = badgeSize,
                    iconSize = badgeIconSize
                )
                WaitlistStatItem(
                    icon = Icons.Outlined.Schedule,
                    value = "${event.waitlistAvailable}",
                    label = "Available",
                    accentColor = PluckPalette.Accept,
                    circleSize = badgeSize,
                    iconSize = badgeIconSize
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

@Composable
private fun WaitlistSummaryCard(
    event: Event,
    waitlistSize: Int,
    waitlistCapacity: Int,
    isUserWaiting: Boolean,
    isUserConfirmed: Boolean,
    currentUserPosition: Int?,
    waitlistFull: Boolean,
    onJoinWaitlist: () -> Unit,
    onLeaveWaitlist: () -> Unit,
    modifier: Modifier = Modifier
) {
    val registrationClosed = !event.isRegistrationOpen
    val isPastEvent = event.isPastEvent
    val primaryLabel = when {
        isPastEvent -> "Event Has Occurred"
        isUserConfirmed -> "Release My Spot"
        isUserWaiting -> "Leave Waitlist"
        waitlistFull -> "Waitlist Full"
        registrationClosed -> "Registration Closed"
        else -> "Join Waitlist"
    }

    val primaryEnabled = when {
        isPastEvent -> false
        isUserConfirmed -> true
        isUserWaiting -> true
        waitlistFull -> false
        registrationClosed -> false
        else -> true
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        shape = RoundedCornerShape(28.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Waitlist status",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = PluckPalette.Primary
                    )
                )
                Text(
                    text = buildString {
                        append(waitlistSize)
                        append(" of ")
                        append(waitlistCapacity)
                        append(" entrants currently on the waitlist.")
                        if (currentUserPosition != null) {
                            append(" You are #")
                            append(currentUserPosition)
                            append(" in line.")
                        }
                    },
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PluckPalette.Muted
                    )
                )
            }

            Button(
                onClick = {
                    if (isUserWaiting || isUserConfirmed) {
                        onLeaveWaitlist()
                    } else {
                        onJoinWaitlist()
                    }
                },
                enabled = primaryEnabled,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isUserWaiting || isUserConfirmed) PluckPalette.Surface else PluckPalette.Primary,
                    contentColor = if (isUserWaiting || isUserConfirmed) PluckPalette.Primary else PluckPalette.Surface,
                    disabledContainerColor = PluckPalette.Muted.copy(alpha = 0.25f),
                    disabledContentColor = PluckPalette.Surface.copy(alpha = 0.8f)
                ),
                border = if (isUserWaiting || isUserConfirmed) BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.4f)) else null,
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(primaryLabel)
            }
        }
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
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
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
    listState: LazyListState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
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

        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            itemsIndexed(entries, key = { _, entry -> entry.id }) { _, entry ->
                WaitlistEntryCard(entry = entry)
            }
        }
    }
}

@Composable
private fun WaitlistEntryCard(entry: WaitlistEntry) {
    val accentColor = when {
        entry.isChosen -> PluckPalette.Accept
        entry.isCurrentUser -> PluckPalette.Secondary
        else -> PluckPalette.Primary
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
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .width(4.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(accentColor.copy(alpha = 0.9f))
            )

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    color = accentColor,
                    tonalElevation = 0.dp,
                    shadowElevation = 4.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "#${entry.position}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = PluckPalette.Surface,
                                fontSize = 16.sp
                            )
                        )
                    }
                }

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
                Text(
                    text = when {
                        entry.isChosen -> "Selected"
                        entry.isCurrentUser -> "You"
                        else -> "In Queue"
                    },
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
        isUserWaiting = true,
        onJoinWaitlist = {},
        onLeaveWaitlist = {},
        onBack = {}
    )
}
