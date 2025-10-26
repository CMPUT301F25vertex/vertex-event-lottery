/**
 * WaitlistScreen.kt
 *
 * Purpose: Displays the waitlist for an event with queue position and status.
 *
 * Design Pattern: Composable UI Screen with list rendering
 *
 * Outstanding Issues: None
 */
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.model.Event
import java.time.LocalDate

/**
 * Data class representing a waitlist entry
 */
data class WaitlistEntry(
    val id: String,
    val userName: String,
    val position: Int,
    val joinedDate: LocalDate,
    val isCurrentUser: Boolean = false
)

/**
 * Waitlist screen showing all users in the queue for an event
 */
@Composable
fun WaitlistScreen(
    event: Event,
    waitlistEntries: List<WaitlistEntry> = emptyList(),
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Back button
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

            // Main content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Spacer(modifier = Modifier.height(56.dp))

                // Header Card
                WaitlistHeaderCard(event = event)

                // Waitlist entries
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .widthIn(max = 460.dp),
                    shape = RoundedCornerShape(36.dp),
                    color = PluckPalette.Surface,
                    tonalElevation = 0.dp,
                    shadowElevation = 12.dp,
                    border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
                ) {
                    when {
                        waitlistEntries.isEmpty() -> WaitlistEmptyState()
                        else -> WaitlistEntriesList(entries = waitlistEntries)
                    }
                }
            }
        }
    }
}

@Composable
private fun WaitlistHeaderCard(event: Event) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 16.dp,
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
                    color = PluckPalette.Primary
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
                    accentColor = PluckPalette.Secondary
                )
                WaitlistStatItem(
                    icon = Icons.Outlined.Groups,
                    value = "${event.waitlistCount}",
                    label = "Waitlisted",
                    accentColor = PluckPalette.Tertiary
                )
                WaitlistStatItem(
                    icon = Icons.Outlined.Schedule,
                    value = "${event.waitlistAvailable}",
                    label = "Available",
                    accentColor = PluckPalette.Accept
                )
            }
        }
    }
}

@Composable
private fun WaitlistStatItem(
    icon: ImageVector,
    value: String,
    label: String,
    accentColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            modifier = Modifier.size(56.dp),
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
                    modifier = Modifier.size(28.dp)
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
private fun WaitlistEntriesList(entries: List<WaitlistEntry>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Waitlist Queue",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = PluckPalette.Primary
            )
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            itemsIndexed(entries, key = { _, entry -> entry.id }) { _, entry ->
                WaitlistEntryCard(entry = entry)
            }
        }
    }
}

@Composable
private fun WaitlistEntryCard(entry: WaitlistEntry) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = if (entry.isCurrentUser) PluckPalette.Secondary.copy(alpha = 0.08f) else PluckPalette.Primary.copy(alpha = 0.04f),
        tonalElevation = 0.dp,
        shadowElevation = if (entry.isCurrentUser) 8.dp else 4.dp,
        border = if (entry.isCurrentUser) BorderStroke(2.dp, PluckPalette.Secondary) else null
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Position badge
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    color = if (entry.isCurrentUser) PluckPalette.Secondary else PluckPalette.Primary,
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

                // User info
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = entry.userName + if (entry.isCurrentUser) " (You)" else "",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PluckPalette.Primary
                        )
                    )
                    Text(
                        text = "Joined ${entry.joinedDate.format(java.time.format.DateTimeFormatter.ofPattern("MMM d"))}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Muted
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun WaitlistEmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No one on the waitlist yet",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = PluckPalette.Primary
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Be the first to join the waitlist for this event!",
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
        title = "Swimming Lessons Event",
        description = "Learn the fundamentals with expert instructors.",
        location = "City Pool",
        date = LocalDate.now().plusDays(3),
        capacity = 20,
        enrolled = 18,
        organizerName = "Vertex Community",
        waitlistCount = 5,
        waitlistCapacity = 40
    )

    val previewEntries = listOf(
        WaitlistEntry(
            id = "1",
            userName = "Alice Johnson",
            position = 1,
            joinedDate = LocalDate.now().minusDays(5)
        ),
        WaitlistEntry(
            id = "2",
            userName = "Bob Smith",
            position = 2,
            joinedDate = LocalDate.now().minusDays(3),
            isCurrentUser = true
        ),
        WaitlistEntry(
            id = "3",
            userName = "Charlie Davis",
            position = 3,
            joinedDate = LocalDate.now().minusDays(2)
        ),
        WaitlistEntry(
            id = "4",
            userName = "Diana Chen",
            position = 4,
            joinedDate = LocalDate.now().minusDays(1)
        ),
        WaitlistEntry(
            id = "5",
            userName = "Ethan Brown",
            position = 5,
            joinedDate = LocalDate.now()
        )
    )

    WaitlistScreen(
        event = previewEvent,
        waitlistEntries = previewEntries,
        onBack = {}
    )
}

