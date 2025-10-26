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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.model.Event
import java.time.LocalDate

/**
 * EventDetailScreen.kt
 *
 * Purpose: Event detail view with meticulous centering and professional polish.
 *
 * Design Pattern: Stateless composable following pLUCK design language.
 *
 * Outstanding Issues: None.
 */
@Composable
fun EventDetailScreen(
    event: Event,
    onJoinEvent: (Event) -> Unit,
    onViewWaitlist: (Event) -> Unit = {},
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    PluckLayeredBackground(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            // Back button - positioned at top left
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 24.dp, top = 24.dp)
                    .size(56.dp)
                    .zIndex(10f),
                shape = CircleShape,
                color = PluckPalette.Surface,
                contentColor = PluckPalette.Primary,
                tonalElevation = 0.dp,
                shadowElevation = 12.dp,
                onClick = onBack
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Main content column - properly centered
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(36.dp)) // Space for back button

                EventDetailHeroCard(event = event)

                EventDetailInfoSection(event = event)

                EventDetailDescriptionSection(description = event.description)

                EventDetailReminder(remainingSpots = event.remainingSpots, waitlistCount = event.waitlistCount)

                Spacer(modifier = Modifier.height(56.dp)) // Space for bottom buttons

            }

            // Bottom action section - fixed at bottom
            EventDetailBottomActions(
                event = event,
                onJoinEvent = onJoinEvent,
                onViewWaitlist = onViewWaitlist,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            )
        }
    }
}

/**
 * Hero card with event media placeholder
 */
@Composable
private fun EventDetailHeroCard(
    event: Event,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp)
            .zIndex(1f),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 18.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Event media placeholder with colorful gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        color = PluckPalette.Secondary,
                        shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Decorative accent circles
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 20.dp, end = 30.dp)
                        .size(80.dp)
                        .background(
                            color = PluckPalette.Tertiary.copy(alpha = 0.4f),
                            shape = CircleShape
                        )
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 15.dp, start = 40.dp)
                        .size(60.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.25f),
                            shape = CircleShape
                        )
                )
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }

            // Event title and organizer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black,
                        color = PluckPalette.Primary,
                        fontSize = 26.sp
                    ),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Hosted by ${event.organizerName}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted,
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

/**
 * Event information section with date, location, and capacity
 */
@Composable
private fun EventDetailInfoSection(
    event: Event,
    modifier: Modifier = Modifier
) {
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
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            EventInfoRow(
                icon = Icons.Outlined.Schedule,
                label = event.dateLabel,
                accentColor = PluckPalette.Secondary
            )
            EventInfoRow(
                icon = Icons.Outlined.LocationOn,
                label = event.location,
                accentColor = PluckPalette.Tertiary
            )
            EventInfoRow(
                icon = Icons.Outlined.PeopleOutline,
                label = "${event.enrolled}/${event.capacity} enrolled • ${event.remainingSpots} spots left",
                accentColor = PluckPalette.Pink
            )
        }
    }
}

/**
 * Single info row with icon and text
 */
@Composable
private fun EventInfoRow(
    icon: ImageVector,
    label: String,
    accentColor: Color = PluckPalette.Secondary
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(42.dp),
            shape = CircleShape,
            color = accentColor.copy(alpha = 0.15f),
            contentColor = accentColor,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.size(14.dp))
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Primary,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

/**
 * Description section
 */
@Composable
private fun EventDetailDescriptionSection(
    description: String,
    modifier: Modifier = Modifier
) {
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
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "About This Event",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PluckPalette.Muted,
                    lineHeight = 22.sp
                )
            )
        }
    }
}

/**
 * Reminder callout with waitlist info
 */
@Composable
private fun EventDetailReminder(
    remainingSpots: Int,
    waitlistCount: Int = 0,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Lottery Notice
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = PluckPalette.Tertiary.copy(alpha = 0.12f),
            border = BorderStroke(1.5.dp, PluckPalette.Tertiary.copy(alpha = 0.25f)),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    color = PluckPalette.Tertiary.copy(alpha = 0.25f),
                    contentColor = PluckPalette.Tertiary
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarMonth,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Lottery Draw Notice",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = PluckPalette.Primary
                        )
                    )
                    Text(
                        text = "${remainingSpots.coerceAtLeast(0)} seats • ${waitlistCount} on waitlist",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Primary.copy(alpha = 0.75f),
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
    }
}

/**
 * Bottom action buttons - properly centered and fixed
 */
@Composable
private fun EventDetailBottomActions(
    event: Event,
    onJoinEvent: (Event) -> Unit,
    onViewWaitlist: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        shape = RoundedCornerShape(32.dp),
        shadowElevation = 20.dp,
        tonalElevation = 0.dp,
        color = PluckPalette.Surface,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { onJoinEvent(event) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PluckPalette.Primary,
                    contentColor = PluckPalette.Surface
                ),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(
                    text = if (event.remainingSpots > 0) "Join Waiting List" else "Stay Updated",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Button(
                onClick = { onViewWaitlist(event) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PluckPalette.Secondary,
                    contentColor = PluckPalette.Surface
                ),
                border = BorderStroke(0.dp, Color.Transparent),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.PeopleOutline,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "View Waitlist",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 920)
@Composable
private fun EventDetailPreview() {
    EventDetailScreen(
        event = Event(
            id = "preview",
            title = "Swimming Lessons Event",
            description = "Join us for an exciting swimming lesson experience! Our expert instructors will guide you through fundamental techniques in a supportive environment. Perfect for beginners looking to build confidence in the water. This lottery-based registration ensures fair access for all community members.",
            location = "City Pool • 123 Main Street",
            date = LocalDate.now().plusDays(4),
            capacity = 20,
            enrolled = 15,
            organizerName = "Vertex Community Center"
        ),
        onJoinEvent = {}
    )
}
