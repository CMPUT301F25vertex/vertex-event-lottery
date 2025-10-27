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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.HourglassBottom
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.ui.model.Event
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * MyEventsScreen.kt
 *
 * Purpose: Displays user's joined and created events with filtering capabilities.
 *
 * Design Pattern: Stateful screen following the pLUCK design language.
 *
 * Outstanding Issues: None.
 */

enum class MyEventsFilter(val label: String) {
    ALL("All"),
    UPCOMING("Upcoming"),
    PAST("Past"),
    JOINED("Joined"),
    CREATED("Created")
}

enum class EventStatus {
    UPCOMING,
    PAST,
    CONFIRMED,
    WAITLIST
}

data class MyEventItem(
    val event: Event,
    val status: EventStatus,
    val isCreatedByUser: Boolean = false,
    val joinedDate: LocalDate? = null,
    val historyStatus: WaitlistStatus? = null
)

private val HISTORY_DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

@Composable
fun MyEventsScreen(
    events: List<MyEventItem> = emptyList(),
    isLoading: Boolean = false,
    onEventClick: (Event) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedFilter by remember { mutableStateOf(MyEventsFilter.ALL) }

    val filteredEvents = remember(events, selectedFilter) {
        when (selectedFilter) {
            MyEventsFilter.ALL -> events
            MyEventsFilter.UPCOMING -> events.filter { it.status == EventStatus.UPCOMING }
            MyEventsFilter.PAST -> events.filter { it.status == EventStatus.PAST }
            MyEventsFilter.JOINED -> events.filter { it.historyStatus != null && !it.isCreatedByUser }
            MyEventsFilter.CREATED -> events.filter { it.isCreatedByUser }
        }
    }

    val listState = rememberLazyListState()
    val scrollOffset by remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    val firstVisibleIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }

    // Calculate collapse progress (0f = fully visible, 1f = fully collapsed)
    val heroCollapseProgress by remember {
        derivedStateOf {
            when {
                firstVisibleIndex > 0 -> 1f
                else -> (scrollOffset / 250f).coerceIn(0f, 1f)
            }
        }
    }

    PluckLayeredBackground(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            when {
                isLoading -> MyEventsLoadingState()
                filteredEvents.isEmpty() && firstVisibleIndex == 0 -> {
                    // Static layout for empty state
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        MyEventsHero(
                            eventCount = events.size,
                            selectedFilter = selectedFilter,
                            onFilterSelected = { selectedFilter = it },
                            collapseProgress = 0f
                        )
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
                            MyEventsEmptyState(selectedFilter)
                        }
                    }
                }
                else -> {
                    // Scrollable list with collapsing hero
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        item {
                            MyEventsHero(
                                eventCount = events.size,
                                selectedFilter = selectedFilter,
                                onFilterSelected = { selectedFilter = it },
                                collapseProgress = heroCollapseProgress
                            )
                        }

                        itemsIndexed(filteredEvents, key = { _, item -> item.event.id }) { index, item ->
                            MyEventCard(
                                item = item,
                                accentColor = if (index % 2 == 0) PluckPalette.Secondary else PluckPalette.Tertiary,
                                onClick = { onEventClick(item.event) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MyEventsHero(
    eventCount: Int,
    selectedFilter: MyEventsFilter,
    onFilterSelected: (MyEventsFilter) -> Unit,
    collapseProgress: Float = 0f
) {
    val targetHeight = (1f - collapseProgress * 0.4f)
    val targetAlpha = (1f - collapseProgress).coerceIn(0f, 1f)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp)
            .height((180 * targetHeight).dp)
            .zIndex(1f),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface.copy(alpha = targetAlpha),
        tonalElevation = 0.dp,
        shadowElevation = (18.dp * (1f - collapseProgress * 0.7f)),
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f * targetAlpha))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = (24 * targetHeight).dp),
            verticalArrangement = Arrangement.spacedBy((24 * targetHeight).dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy((8 * targetHeight).dp)
                ) {
                    Text(
                        text = "My Events",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Black,
                            color = PluckPalette.Primary.copy(alpha = targetAlpha),
                            fontSize = (28 - collapseProgress * 8).sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (collapseProgress < 0.8f) {
                        Text(
                            text = if (eventCount > 0) "$eventCount events in your calendar" else "No events yet",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = PluckPalette.Muted.copy(alpha = targetAlpha)
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                if (collapseProgress < 0.7f) {
                    Surface(
                        modifier = Modifier.size((56 * (1f - collapseProgress * 0.5f)).dp),
                        shape = CircleShape,
                        color = PluckPalette.Tertiary.copy(alpha = 0.16f * targetAlpha),
                        contentColor = PluckPalette.Tertiary.copy(alpha = targetAlpha)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
            }

            if (collapseProgress < 0.9f) {
                MyEventsFilterRow(
                    selected = selectedFilter,
                    onSelected = onFilterSelected,
                    alpha = targetAlpha
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyEventsFilterRow(
    selected: MyEventsFilter,
    onSelected: (MyEventsFilter) -> Unit,
    alpha: Float = 1f
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(MyEventsFilter.entries) { filter ->
            FilterChip(
                selected = selected == filter,
                onClick = { onSelected(filter) },
                label = {
                    Text(
                        text = filter.label,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = if (selected == filter) PluckPalette.Primary.copy(alpha = alpha) else PluckPalette.Surface.copy(alpha = alpha),
                    labelColor = if (selected == filter) MaterialTheme.colorScheme.onPrimary.copy(alpha = alpha) else PluckPalette.Primary.copy(alpha = alpha),
                    selectedContainerColor = PluckPalette.Primary.copy(alpha = alpha),
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = alpha)
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = if (selected == filter) PluckPalette.Primary.copy(alpha = alpha) else PluckPalette.Primary.copy(alpha = 0.12f * alpha),
                    selectedBorderColor = PluckPalette.Primary.copy(alpha = alpha),
                    borderWidth = 1.dp
                )
            )
        }
    }
}


@Composable
private fun MyEventCard(
    item: MyEventItem,
    accentColor: Color,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        PluckAccentCircle(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopStart)
                .offset(x = (-10).dp, y = (-10).dp),
            color = accentColor.copy(alpha = 0.12f)
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f),
            shape = RoundedCornerShape(28.dp),
            color = PluckPalette.Surface,
            tonalElevation = 0.dp,
            shadowElevation = 12.dp,
            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.06f)),
            onClick = onClick
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.event.title,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = PluckPalette.Primary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    MyEventStatusBadge(
                        status = item.status,
                        historyStatus = item.historyStatus,
                        isCreatedByUser = item.isCreatedByUser
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MyEventMetaIcon(icon = Icons.Outlined.Schedule)
                    Text(
                        text = item.event.dateLabel,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PluckPalette.Primary
                        )
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MyEventMetaIcon(icon = Icons.Outlined.LocationOn)
                    Text(
                        text = item.event.location,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PluckPalette.Primary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                item.joinedDate?.let { joined ->
                    Text(
                        text = "Joined on ${joined.format(HISTORY_DATE_FORMATTER)}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Muted
                        )
                    )
                }

                if (item.isCreatedByUser) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        color = PluckPalette.Secondary.copy(alpha = 0.08f),
                        border = BorderStroke(1.dp, PluckPalette.Secondary.copy(alpha = 0.16f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Event,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = PluckPalette.Secondary
                            )
                            Text(
                                text = "You're organizing this event",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = PluckPalette.Primary.copy(alpha = 0.8f),
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

private data class BadgeDescriptor(
    val icon: ImageVector,
    val label: String,
    val backgroundColor: Color,
    val contentColor: Color
)

@Composable
private fun MyEventStatusBadge(
    status: EventStatus,
    historyStatus: WaitlistStatus?,
    @Suppress("UNUSED_PARAMETER") isCreatedByUser: Boolean
) {
    val badge = when (historyStatus) {
        WaitlistStatus.ACCEPTED -> BadgeDescriptor(
            Icons.Outlined.CheckCircle,
            "Confirmed",
            PluckPalette.Accept.copy(alpha = 0.16f),
            PluckPalette.Accept
        )
        WaitlistStatus.SELECTED -> BadgeDescriptor(
            Icons.Outlined.HourglassBottom,
            "Selected",
            PluckPalette.Secondary.copy(alpha = 0.16f),
            PluckPalette.Secondary
        )
        WaitlistStatus.WAITING -> BadgeDescriptor(
            Icons.Outlined.HourglassBottom,
            "Waitlist",
            PluckPalette.Secondary.copy(alpha = 0.16f),
            PluckPalette.Secondary
        )
        WaitlistStatus.DECLINED -> BadgeDescriptor(
            Icons.Outlined.Close,
            "Declined",
            PluckPalette.Muted.copy(alpha = 0.16f),
            PluckPalette.Muted
        )
        WaitlistStatus.CANCELLED -> BadgeDescriptor(
            Icons.Outlined.Close,
            "Cancelled",
            PluckPalette.Muted.copy(alpha = 0.16f),
            PluckPalette.Muted
        )
        null -> when (status) {
            EventStatus.PAST -> BadgeDescriptor(
                Icons.Outlined.History,
                "Past",
                PluckPalette.Muted.copy(alpha = 0.16f),
                PluckPalette.Muted
            )
            EventStatus.CONFIRMED -> BadgeDescriptor(
                Icons.Outlined.CheckCircle,
                "Confirmed",
                PluckPalette.Accept.copy(alpha = 0.16f),
                PluckPalette.Accept
            )
            EventStatus.WAITLIST -> BadgeDescriptor(
                Icons.Outlined.HourglassBottom,
                "Waitlist",
                PluckPalette.Secondary.copy(alpha = 0.16f),
                PluckPalette.Secondary
            )
            else -> BadgeDescriptor(
                Icons.Outlined.CalendarMonth,
                "Upcoming",
                PluckPalette.Tertiary.copy(alpha = 0.16f),
                PluckPalette.Tertiary
            )
        }
    }

    val (icon, label, backgroundColor, contentColor) = badge

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = contentColor
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )
            )
        }
    }
}

@Composable
private fun MyEventMetaIcon(icon: ImageVector) {
    Surface(
        modifier = Modifier.size(32.dp),
        shape = CircleShape,
        color = PluckPalette.Primary.copy(alpha = 0.06f),
        contentColor = PluckPalette.Primary
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun MyEventsLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = PluckPalette.Primary)
    }
}

@Composable
private fun MyEventsEmptyState(filter: MyEventsFilter) {
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
            color = PluckPalette.Primary.copy(alpha = 0.08f),
            contentColor = PluckPalette.Primary
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = when (filter) {
                MyEventsFilter.ALL -> "No events yet"
                MyEventsFilter.UPCOMING -> "No upcoming events"
                MyEventsFilter.PAST -> "No past events"
                MyEventsFilter.JOINED -> "You haven't joined any events"
                MyEventsFilter.CREATED -> "You haven't created any events"
            },
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = PluckPalette.Primary
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Browse the home feed to discover and join lottery events.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Muted
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 920)
@Composable
private fun MyEventsScreenPreview() {
    val previewEvents = listOf(
        MyEventItem(
            event = Event(
                id = "1",
                title = "Swimming Lessons",
                description = "Learn swimming fundamentals",
                location = "City Pool",
                date = LocalDate.now().plusDays(5),
                capacity = 20,
                enrolled = 15,
                organizerName = "Vertex Community"
            ),
            status = EventStatus.CONFIRMED,
            isCreatedByUser = false
        ),
        MyEventItem(
            event = Event(
                id = "2",
                title = "Cooking Class",
                description = "Learn to cook Italian cuisine",
                location = "Culinary School",
                date = LocalDate.now().plusDays(10),
                capacity = 15,
                enrolled = 10,
                organizerName = "Chef Mario"
            ),
            status = EventStatus.WAITLIST,
            isCreatedByUser = false
        ),
        MyEventItem(
            event = Event(
                id = "3",
                title = "Yoga Workshop",
                description = "Morning yoga session",
                location = "Community Center",
                date = LocalDate.now().minusDays(2),
                capacity = 25,
                enrolled = 25,
                organizerName = "Wellness Studio"
            ),
            status = EventStatus.PAST,
            isCreatedByUser = false
        ),
        MyEventItem(
            event = Event(
                id = "4",
                title = "Photography Walk",
                description = "Explore urban photography",
                location = "Downtown",
                date = LocalDate.now().plusDays(7),
                capacity = 12,
                enrolled = 8,
                organizerName = "Me"
            ),
            status = EventStatus.UPCOMING,
            isCreatedByUser = true
        )
    )

    MyEventsScreen(
        events = previewEvents,
        isLoading = false
    )
}
