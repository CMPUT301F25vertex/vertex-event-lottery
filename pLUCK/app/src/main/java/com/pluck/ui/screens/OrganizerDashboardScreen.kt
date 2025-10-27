package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pluck.ui.components.PluckAccentCircle
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.model.Event
import com.pluck.ui.theme.autoTextColor
import java.time.LocalDate

/**
 * OrganizerDashboardScreen.kt
 *
 * Purpose: Dashboard for event organizers to manage their events and view statistics.
 *
 * Design Pattern: Stateful screen following the pLUCK design language.
 *
 * Outstanding Issues: None.
 */

data class OrganizerStats(
    val totalEvents: Int = 0,
    val activeEvents: Int = 0,
    val totalParticipants: Int = 0
)

@Composable
fun OrganizerDashboardScreen(
    organizerName: String = "Organizer",
    stats: OrganizerStats = OrganizerStats(),
    events: List<Event> = emptyList(),
    isLoading: Boolean = false,
    onCreateEvent: () -> Unit = {},
    onEventClick: (Event) -> Unit = {},
    onEditEvent: (Event) -> Unit = {},
    onRunDraw: (Event) -> Unit = {},
    onManageChosenEntrants: (Event) -> Unit = {},
    modifier: Modifier = Modifier
) {
    PluckLayeredBackground(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            OrganizerDashboardHero(
                organizerName = organizerName,
                stats = stats
            )

            OrganizerStatsCards(stats = stats)

            OrganizerCreateEventCard(onClick = onCreateEvent)

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f),
                shape = RoundedCornerShape(36.dp),
                color = PluckPalette.Surface,
                tonalElevation = 0.dp,
                shadowElevation = 12.dp,
                border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
            ) {
                when {
                    isLoading -> OrganizerLoadingState()
                    events.isEmpty() -> OrganizerEmptyState()
                    else -> OrganizerEventsList(
                        events = events,
                        onEventClick = onEventClick,
                        onEditEvent = onEditEvent,
                        onRunDraw = onRunDraw,
                        onManageChosenEntrants = onManageChosenEntrants
                    )
                }
            }
        }
    }
}

@Composable
private fun OrganizerDashboardHero(
    organizerName: String,
    @Suppress("UNUSED_PARAMETER") stats: OrganizerStats
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp)
            .zIndex(1f),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 18.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Organizer Dashboard",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black,
                        color = PluckPalette.Primary,
                        fontSize = 18.sp
                    )
                )
                Text(
                    text = "Welcome back, $organizerName",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted
                    )
                )
            }
            Surface(
                modifier = Modifier.size(56.dp),
                shape = CircleShape,
                color = PluckPalette.Secondary.copy(alpha = 0.16f),
                contentColor = PluckPalette.Secondary
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.BarChart,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun OrganizerStatsCards(stats: OrganizerStats) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OrganizerStatCard(
            icon = Icons.Outlined.Event,
            label = "Total Events",
            value = stats.totalEvents.toString(),
            color = PluckPalette.Secondary,
            modifier = Modifier.weight(1f)
        )
        OrganizerStatCard(
            icon = Icons.Outlined.People,
            label = "Participants",
            value = stats.totalParticipants.toString(),
            color = PluckPalette.Tertiary,
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * Displays a single organizer metric with an icon and supporting label.
 */
@Composable
private fun OrganizerStatCard(
    icon: ImageVector,
    label: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(28.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Black,
                        color = PluckPalette.Primary
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PluckPalette.Muted
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun OrganizerCreateEventCard(onClick: () -> Unit) {
    val onSecondary = autoTextColor(PluckPalette.Secondary)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        shape = RoundedCornerShape(28.dp),
        color = PluckPalette.Secondary,
        contentColor = onSecondary,
        tonalElevation = 0.dp,
        shadowElevation = 16.dp,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Create New Event",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Set up a new lottery event for your community",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = onSecondary.copy(alpha = 0.9f)
                    )
                )
            }
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = onSecondary.copy(alpha = 0.2f),
                contentColor = onSecondary
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun OrganizerEventsList(
    events: List<Event>,
    onEventClick: (Event) -> Unit,
    onEditEvent: (Event) -> Unit,
    onRunDraw: (Event) -> Unit,
    onManageChosenEntrants: (Event) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Text(
                text = "Your Events",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
        }
        itemsIndexed(events, key = { _, event -> event.id }) { index, event ->
            OrganizerEventCard(
                event = event,
                accentColor = if (index % 2 == 0) PluckPalette.Secondary else PluckPalette.Tertiary,
                onEventClick = { onEventClick(event) },
                onEditEvent = { onEditEvent(event) },
                onRunDraw = { onRunDraw(event) },
                onManageChosenEntrants = { onManageChosenEntrants(event) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OrganizerEventCard(
    event: Event,
    accentColor: Color,
    onEventClick: () -> Unit,
    onEditEvent: () -> Unit,
    onRunDraw: () -> Unit,
    onManageChosenEntrants: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        PluckAccentCircle(
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.TopEnd)
                .offset(x = 10.dp, y = (-10).dp),
            color = accentColor.copy(alpha = 0.12f)
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f),
            shape = RoundedCornerShape(24.dp),
            color = PluckPalette.Surface,
            tonalElevation = 0.dp,
            shadowElevation = 10.dp,
            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f)),
            onClick = onEventClick
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = event.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = PluckPalette.Primary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${event.dateLabel} • ${event.location}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PluckPalette.Muted
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        color = accentColor.copy(alpha = 0.08f),
                        border = BorderStroke(1.dp, accentColor.copy(alpha = 0.2f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${event.enrolled}/${event.capacity}",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PluckPalette.Primary
                                )
                            )
                        }
                    }

                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        color = PluckPalette.Accept.copy(alpha = 0.08f),
                        border = BorderStroke(1.dp, PluckPalette.Accept.copy(alpha = 0.2f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${event.remainingSpots} left",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PluckPalette.Accept
                                )
                            )
                        }
                    }
                }

                // Draw Management Buttons
                if (event.isDrawComplete) {
                    // Show "Manage Chosen" button when draw is complete
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OrganizerActionButton(
                            icon = Icons.Outlined.CheckCircle,
                            label = "Manage Chosen",
                            onClick = onManageChosenEntrants,
                            backgroundColor = PluckPalette.Accept,
                            modifier = Modifier.weight(1f)
                        )
                        OrganizerActionButton(
                            icon = Icons.Outlined.Edit,
                            label = "Edit",
                            onClick = onEditEvent,
                            modifier = Modifier.weight(1f)
                        )
                    }
                } else if (event.canRunDrawEarly && event.samplingCount > 0) {
                    // Show "Run Draw" button when organizer can run it early
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OrganizerActionButton(
                            icon = Icons.Outlined.PlayArrow,
                            label = "Run Draw",
                            onClick = onRunDraw,
                            backgroundColor = PluckPalette.Secondary,
                            modifier = Modifier.weight(1f)
                        )
                        OrganizerActionButton(
                            icon = Icons.Outlined.Edit,
                            label = "Edit",
                            onClick = onEditEvent,
                            modifier = Modifier.weight(1f)
                        )
                    }
                } else {
                    // Default: Just show Edit button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OrganizerActionButton(
                            icon = Icons.Outlined.Edit,
                            label = "Edit Event",
                            onClick = onEditEvent,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun OrganizerActionButton(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = PluckPalette.Primary
) {
    val contentColor = autoTextColor(backgroundColor)
    Button(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
private fun OrganizerLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = PluckPalette.Primary)
    }
}

@Composable
private fun OrganizerEmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            color = PluckPalette.Secondary.copy(alpha = 0.12f),
            contentColor = PluckPalette.Secondary
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Outlined.Event,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "No events created yet",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = PluckPalette.Primary
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Create your first event to start managing lottery-based registrations.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Muted
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 920)
@Composable
private fun OrganizerDashboardScreenPreview() {
    val previewEvents = listOf(
        Event(
            id = "1",
            title = "Swimming Lessons",
            description = "Learn swimming fundamentals",
            location = "City Pool",
            date = LocalDate.now().plusDays(5),
            capacity = 20,
            enrolled = 15,
            organizerName = "You"
        ),
        Event(
            id = "2",
            title = "Cooking Class",
            description = "Learn to cook Italian cuisine",
            location = "Culinary School",
            date = LocalDate.now().plusDays(10),
            capacity = 15,
            enrolled = 10,
            organizerName = "You"
        )
    )

    OrganizerDashboardScreen(
        organizerName = "Caiden",
        stats = OrganizerStats(
            totalEvents = 5,
            activeEvents = 3,
            totalParticipants = 87
        ),
        events = previewEvents,
        isLoading = false
    )
}

