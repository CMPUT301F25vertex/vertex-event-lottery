/**
 * ChosenEntrantsScreen.kt
 *
 * Purpose: Displays the list of entrants selected in the lottery draw.
 * Organizers can see who was selected, who accepted, declined, or cancelled their invitations.
 * Provides statistics and CSV export functionality for lottery results.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.data.repository.WaitlistDecisionStats
import com.pluck.ui.components.ComposableItem
import com.pluck.ui.components.FullWidthLazyScroll
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.components.SquircleScrollableLazyList
import com.pluck.ui.model.Event
import java.time.LocalDate
import com.pluck.ui.theme.autoTextColor

/**
 * ChosenEntrantsScreen.kt
 *
 * Purpose: Displays the list of entrants selected in the lottery draw.
 * Organizers can see who was selected, who accepted, and who declined.
 *
 * Design Pattern: Stateless screen following pLUCK design language.
 */

data class ChosenEntrant(
    val id: String,
    val userId: String,
    val userName: String,
    val status: WaitlistStatus,
    val selectedAt: Long? = null
)

@Composable
fun ChosenEntrantsScreen(
    event: Event,
    chosenEntrants: List<ChosenEntrant> = emptyList(),
    decisionStats: WaitlistDecisionStats = WaitlistDecisionStats(),
    waitingCount: Int = 0,
    availableSpots: Int = 0,
    isLoading: Boolean = false,
    onBackClick: () -> Unit = {},
    onExportCSV: () -> Unit = {},
    onRunDraw: () -> Unit = {},
    onRemoveEntrant: (ChosenEntrant) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val listElements = mutableListOf<ComposableItem>()

    listElements.add(ComposableItem {
        ChosenEntrantsHeader(
            eventTitle = event.title,
            samplingCount = event.samplingCount,
            capacity = event.capacity,
            onBackClick = onBackClick,
            onExportCSV = onExportCSV
        )
    })

    listElements.add(ComposableItem {
        DrawActionsRow(
            waitingCount = waitingCount,
            availableSpots = availableSpots,
            onRunDraw = onRunDraw
        )
    })

    listElements.add(ComposableItem {
        ChosenEntrantsStats(
            stats = decisionStats,
            capacity = event.capacity
        )
    })

    when {
        isLoading -> listElements.add(ComposableItem { ChosenEntrantsLoadingState() })
        chosenEntrants.isEmpty() -> listElements.add(ComposableItem { ChosenEntrantsEmptyState() })
        else -> {
            listElements.add(ComposableItem {
                Text(
                    text = "Selected Entrants",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = PluckPalette.Primary
                    )
                )
            })

            for (entrant in chosenEntrants) {
                listElements.add(ComposableItem {
                    ChosenEntrantCard(
                        entrant = entrant,
                        onRemove = { onRemoveEntrant(entrant) }
                    )
                })
            }
        }
    }

    PluckLayeredBackground(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        FullWidthLazyScroll(
            listElements = listElements
        )
    }
}

@Composable
private fun ChosenEntrantsHeader(
    eventTitle: String,
    samplingCount: Int,
    capacity: Int,
    onBackClick: () -> Unit,
    onExportCSV: () -> Unit = {}
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = PluckPalette.Primary
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Plucked Entrants",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black,
                        color = PluckPalette.Primary,
                        fontSize = 24.sp
                    )
                )
                Text(
                    text = eventTitle,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "$samplingCount selected • $capacity seats available",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PluckPalette.Muted,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            // Export CSV button (US 02.06.05)
            IconButton(onClick = onExportCSV) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Export CSV",
                    tint = PluckPalette.Secondary
                )
            }
        }
    }
}

@Composable
private fun DrawActionsRow(
    waitingCount: Int,
    availableSpots: Int,
    onRunDraw: () -> Unit
) {
    val canRunDraw = waitingCount > 0 && availableSpots > 0
    val helperText = when {
        availableSpots <= 0 -> "Event is full. Remove entrants or increase capacity to draw again."
        waitingCount <= 0 -> "No entrants waiting. Invite more entrants to join the waitlist."
        else -> "Draw again to invite ${minOf(waitingCount, availableSpots)} entrant(s)."
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Lottery Controls",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PluckPalette.Primary
                        )
                    )
                    Text(
                        text = helperText,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Muted
                        )
                    )
                }

                Button(
                    onClick = onRunDraw,
                    enabled = canRunDraw,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (canRunDraw) PluckPalette.Secondary else PluckPalette.Muted.copy(alpha = 0.2f),
                        contentColor = if (canRunDraw) autoTextColor(PluckPalette.Secondary) else PluckPalette.Muted
                    ),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(text = "Pluck", maxLines = 1)
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DrawStatChip(
                    label = "Waiting",
                    value = waitingCount.toString(),
                    color = PluckPalette.Secondary
                )
                DrawStatChip(
                    label = "Open Spots",
                    value = availableSpots.toString(),
                    color = PluckPalette.Accept
                )
            }
        }
    }
}

@Composable
private fun DrawStatChip(
    label: String,
    value: String,
    color: Color
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = color.copy(alpha = 0.12f),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        border = BorderStroke(1.dp, color.copy(alpha = 0.25f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(RoundedCornerShape(50))
                    .background(color)
            )
            Text(
                text = "$value $label",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = color
                )
            )
        }
    }
}

@Composable
private fun ChosenEntrantsStats(
    stats: WaitlistDecisionStats,
    capacity: Int
) {
    val acceptedCount = stats.accepted
    val pendingCount = stats.pending
    val declinedCount = stats.declined
    val cancelledCount = stats.cancelled

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ChosenEntrantsStatCard(
                label = "Accepted",
                value = "$acceptedCount/$capacity",
                icon = Icons.Outlined.CheckCircle,
                color = PluckPalette.Accept,
                modifier = Modifier.weight(1f)
            )
            ChosenEntrantsStatCard(
                label = "Pending",
                value = pendingCount.toString(),
                icon = Icons.Outlined.HourglassEmpty,
                color = PluckPalette.Secondary,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ChosenEntrantsStatCard(
                label = "Declined",
                value = declinedCount.toString(),
                icon = Icons.Outlined.Cancel,
                color = PluckPalette.Decline,
                modifier = Modifier.weight(1f)
            )
            ChosenEntrantsStatCard(
                label = "Cancelled",
                value = cancelledCount.toString(),
                icon = Icons.Outlined.Cancel,
                color = Color(0xFFEF4444),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ChosenEntrantsStatCard(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 10.dp,
        border = BorderStroke(1.dp, color.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                modifier = Modifier.size(36.dp),
                shape = CircleShape,
                color = color.copy(alpha = 0.16f),
                contentColor = color
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge.copy(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChosenEntrantCard(
    entrant: ChosenEntrant,
    onRemove: () -> Unit = {}
) {
    var showRemoveDialog by remember { mutableStateOf(false) }

    val (statusColor, statusLabel, statusIcon) = when (entrant.status) {
        WaitlistStatus.ACCEPTED -> Triple(PluckPalette.Accept, "Accepted", Icons.Outlined.CheckCircle)
        WaitlistStatus.DECLINED -> Triple(PluckPalette.Decline, "Declined", Icons.Outlined.Cancel)
        WaitlistStatus.CANCELLED -> Triple(Color(0xFFEF4444), "Cancelled", Icons.Outlined.Cancel)
        WaitlistStatus.INVITED,
        WaitlistStatus.SELECTED -> Triple(
            PluckPalette.Secondary,
            "Pending",
            Icons.Outlined.HourglassEmpty
        )
        else -> Triple(PluckPalette.Muted, "Unknown", Icons.Outlined.HourglassEmpty)
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 6.dp,
        border = BorderStroke(1.dp, statusColor.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = entrant.userName,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = PluckPalette.Primary
                    )
                )
                Text(
                    text = "User ID: ${entrant.userId.take(8)}...",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PluckPalette.Muted
                    )
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = statusColor.copy(alpha = 0.12f),
                    border = BorderStroke(1.dp, statusColor.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = statusIcon,
                            contentDescription = null,
                            tint = statusColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = statusLabel,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = statusColor
                            )
                        )
                    }
                }

                // Remove button - only show for ACCEPTED entrants
                if (entrant.status == WaitlistStatus.ACCEPTED) {
                    IconButton(
                        onClick = { showRemoveDialog = true },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Remove entrant",
                            tint = Color(0xFFEF4444),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }

    // Remove confirmation dialog
    if (showRemoveDialog) {
        AlertDialog(
            onDismissRequest = { showRemoveDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = Color(0xFFEF4444),
                    modifier = Modifier.size(48.dp)
                )
            },
            title = {
                Text(
                    text = "Remove Entrant?",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(
                    text = "This will remove ${entrant.userName} from the enrolled list and free up their spot. You can run another draw to fill the gap.",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showRemoveDialog = false
                        onRemove()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEF4444)
                    )
                ) {
                    Text("Remove")
                }
            },
            dismissButton = {
                TextButton(onClick = { showRemoveDialog = false }) {
                    Text("Cancel", color = PluckPalette.Muted)
                }
            }
        )
    }
}

@Composable
private fun ChosenEntrantsLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = PluckPalette.Primary)
    }
}

@Composable
private fun ChosenEntrantsEmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
        Surface(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            color = PluckPalette.Muted.copy(alpha = 0.12f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Outlined.HourglassEmpty,
                    contentDescription = null,
                    tint = PluckPalette.Muted,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Text(
            text = "No entrants selected yet",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = PluckPalette.Primary
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Run the draw to select entrants from the waitlist",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Muted
            ),
            textAlign = TextAlign.Center
        )
        }
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 920)
@Composable
private fun ChosenEntrantsScreenPreview() {
    val sampleEvent = Event(
        id = "1",
        title = "Tech Conference 2024",
        description = "Annual tech conference",
        location = "Convention Center",
        date = LocalDate.now().plusDays(7),
        capacity = 50,
        enrolled = 25,
        organizerName = "Tech Org",
        samplingCount = 100
    )

    val sampleEntrants = listOf(
        ChosenEntrant("1", "user1", "Alice Johnson", WaitlistStatus.ACCEPTED),
        ChosenEntrant("2", "user2", "Bob Smith", WaitlistStatus.INVITED),
        ChosenEntrant("3", "user3", "Carol Davis", WaitlistStatus.DECLINED),
        ChosenEntrant("4", "user4", "David Wilson", WaitlistStatus.CANCELLED),
        ChosenEntrant("5", "user5", "Eve Martinez", WaitlistStatus.SELECTED)
    )

    val sampleStats = WaitlistDecisionStats(
        accepted = 1,
        pending = 2,
        declined = 1,
        cancelled = 1
    )

    ChosenEntrantsScreen(
        event = sampleEvent,
        chosenEntrants = sampleEntrants,
        decisionStats = sampleStats,
        waitingCount = 12,
        availableSpots = 25
    )
}
