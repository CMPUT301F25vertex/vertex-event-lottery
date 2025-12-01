package com.pluck.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Interests
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.pluck.ui.model.Event
import com.pluck.ui.model.EventInterests
import com.pluck.ui.theme.autoTextColor
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class EventFilter(
    val label: String,
    val condition: (Event) -> Boolean
)

/**
 * Full screen event browser
 */
@Composable
fun EventBrowser(
    filters: List<EventFilter>,
    events: List<Event>,
    onSelectEvent: (Event) -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,

    currentUserId: String = "",

    overviewHero: @Composable () -> Unit,
    bottomNavBar: @Composable () -> Unit
) {
    val confettiTrigger by remember { mutableStateOf(false) }
    var activeFilter: EventFilter by remember { mutableStateOf(filters[0]) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var interestsExpanded by remember { mutableStateOf(false) }
    var selectedInterestIds by rememberSaveable { mutableStateOf(setOf<String>()) }
    var availabilityStart by remember { mutableStateOf<LocalDate?>(null) }
    var availabilityEnd by remember { mutableStateOf<LocalDate?>(null) }
    var showAvailabilityPicker by remember { mutableStateOf(false) }

    val filteredEvents = remember(events, activeFilter, searchQuery, selectedInterestIds, availabilityStart, availabilityEnd) {
        events
            // Filter based on active filter
            .filter { activeFilter.condition(it) }

            // Then apply search to only events that follow the filter
            .filter { event ->
                searchQuery.isBlank() ||
                event.title.contains(searchQuery, ignoreCase = true) ||
                event.description.contains(searchQuery, ignoreCase = true) ||
                event.location.contains(searchQuery, ignoreCase = true) ||
                event.organizerName.contains(searchQuery, ignoreCase = true)
            }

            // Filter based on interests
            .filter { event ->
                if (selectedInterestIds.isEmpty()) true else event.interests.any { it in selectedInterestIds }
            }

            // Filter based on availability
            .filter { event ->
                if (availabilityStart != null && availabilityEnd != null) {
                    !event.date.isBefore(availabilityStart) && !event.date.isAfter(availabilityEnd)
                } else {
                    true
                }
            }
    }.sortedBy { event -> event.eventDateTime }

    val listElements = mutableListOf<ComposableItem>()

    listElements.add(ComposableItem{
        overviewHero()
    })

    listElements.add(ComposableItem {
        key("search_bar") {
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = { value -> searchQuery = value }
            )
        }
    })

    val availabilityLabel = remember(availabilityStart, availabilityEnd) {
        if (availabilityStart != null && availabilityEnd != null) {
            val fmt = DateTimeFormatter.ofPattern("MMM d")
            "${availabilityStart!!.format(fmt)} – ${availabilityEnd!!.format(fmt)}"
        } else {
            null
        }
    }

    listElements.add(ComposableItem {
        FilterRow(
            filters = filters,
            selectedFilter = activeFilter,
            onSelected = { filter -> activeFilter = filter },
            selectedInterestCount = selectedInterestIds.size,
            onClickInterests = { interestsExpanded = true },
            interestsExpanded = interestsExpanded,
            onDismissInterests = { interestsExpanded = false },
            onToggleInterest = { id ->
                selectedInterestIds = if (selectedInterestIds.contains(id)) selectedInterestIds - id else selectedInterestIds + id
            },
            isInterestSelected = { id -> selectedInterestIds.contains(id) },
            availabilityLabel = availabilityLabel,
            onClickAvailability = { showAvailabilityPicker = true }
        )
    })

    if (filteredEvents.isEmpty()) {
        listElements.add(ComposableItem {
            EmptyState()
        })
    } else {
        for (event in filteredEvents) {
            listElements.add(ComposableItem {
                EventCard(
                    event = event,
                    onClick = { onSelectEvent(event) },
                    currentUserId = currentUserId
                )
            })
        }
    }

    AutoHidingBarScroller(
        listElements = listElements,
        bottomBar = {
            bottomNavBar()
        },
        onRefresh = onRefresh,
        isRefreshing = isRefreshing
    ) {
        ConfettiBurst(
            trigger = confettiTrigger,
            modifier = Modifier
                .fillMaxSize()
        )
    }

    if (showAvailabilityPicker) {
        val defaultStart = availabilityStart ?: LocalDate.now()
        val defaultEnd = availabilityEnd ?: defaultStart.plusDays(7)
        PLuckDateRangePicker(
            onDateSelected = { start, end ->
                availabilityStart = start
                availabilityEnd = end
            },
            onDismiss = { showAvailabilityPicker = false },
            defaultStartDate = defaultStart,
            defaultEndDate = defaultEnd,
            onClear = {
                availabilityStart = null
                availabilityEnd = null
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EventCard(
    event: Event,
    currentUserId: String,
    accentColor: Color = PluckPalette.Secondary,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        shadowElevation = 12.dp,
        border = BorderStroke(2.dp, accentColor.copy(alpha = 0.15f))
    )
    {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Colorful header strip
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                color = accentColor.copy(alpha = 0.12f),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = event.title,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = PluckPalette.Primary,
                            fontSize = 20.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    EventBadge(event)
                }
            }

            val hasPoster = !event.posterUrl.isNullOrBlank()

            // Content area
            if (hasPoster) {
                AsyncImage(
                    model = event.posterUrl,
                    contentDescription = "${event.title} poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 200.dp),
                    alignment = Alignment.Center
                )

                Row(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                )
                {
                    IconTextRow(
                        icon = Icons.Outlined.Schedule,
                        label = event.dateNoTimeLabel,
                        accentColor = accentColor
                    )
                    IconTextRow(
                        icon = Icons.Outlined.LocationOn,
                        label = event.location,
                        accentColor = accentColor
                    )
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                )
                {
                    IconTextRow(
                        icon = Icons.Outlined.Schedule,
                        label = event.dateLabel,
                        accentColor = accentColor
                    )
                    IconTextRow(
                        icon = Icons.Outlined.LocationOn,
                        label = event.location,
                        accentColor = accentColor
                    )
                }
            }

            if (currentUserId == event.organizerId) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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

@Composable
private fun EventBadge(event: Event) {
    val (statusText, statusColor) = when {
        event.isPastEvent -> "Past Event" to PluckPalette.Secondary
        event.isWaitlistFull -> "Event Full" to PluckPalette.Decline
        else -> "${event.waitlistCount} Waiting" to PluckPalette.Accept
    }

    RoundedTextButton(
        onClick = { },
        text = statusText,
        textColor = autoTextColor(statusColor),
        shapeColor = statusColor,
        textSize = 14,
        style = MaterialTheme.typography.labelLarge.copy(
                color = autoTextColor(statusColor),
                fontWeight = FontWeight.SemiBold
        )
    )
}

@Composable
private fun IconTextRow(
    icon: ImageVector,
    label: String,
    accentColor: Color
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(36.dp),
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
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Primary,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
private fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        tonalElevation = 2.dp,
        shadowElevation = 4.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
    ) {
        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Search events by title, location, or organizer...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = PluckPalette.Muted,
                    maxLines = 1
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = PluckPalette.Primary
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = PluckPalette.Surface,
                unfocusedContainerColor = PluckPalette.Surface,
                focusedIndicatorColor = PluckPalette.Primary.copy(alpha = 0.3f),
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Primary
            )
        )
    }
}

/**
 * Row containing event filters
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterRow(
    filters: List<EventFilter>,
    selectedFilter: EventFilter,
    onSelected: (EventFilter) -> Unit,
    selectedInterestCount: Int,
    onClickInterests: () -> Unit,
    interestsExpanded: Boolean,
    onDismissInterests: () -> Unit,
    onToggleInterest: (String) -> Unit,
    isInterestSelected: (String) -> Boolean,
    availabilityLabel: String?,
    onClickAvailability: () -> Unit
) {
    Column {
        val filtersAsStrings = filters.map { it.label }

        PLuckChipRow(
            chips = filtersAsStrings,
            selectedChip = selectedFilter.label,
            onSelect = { chip -> onSelected(filters.find { it.label == chip } ?: filters[0]) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = PluckPalette.Surface,
                    tonalElevation = 0.dp,
                    shadowElevation = 4.dp,
                ) {
                    FilterChip(
                        modifier = Modifier.fillMaxWidth(),
                        selected = selectedInterestCount > 0,
                        onClick = onClickInterests,
                        label = {
                            val count = selectedInterestCount
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Interests,
                                    contentDescription = null,
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (count > 0) "Interests ($count)" else "Interests",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = PluckPalette.Surface,
                            selectedContainerColor = PluckPalette.Surface,
                            labelColor = PluckPalette.Primary,
                            selectedLabelColor = PluckPalette.Primary
                        ),
                        border = null
                    )
                }

                androidx.compose.material3.DropdownMenu(
                    expanded = interestsExpanded,
                    onDismissRequest = onDismissInterests,
                    containerColor = PluckPalette.Surface,
                    tonalElevation = 2.dp,
                    shadowElevation = 12.dp
                ) {
                    EventInterests.all.forEach { interest ->
                        val selected = isInterestSelected(interest.id)
                        androidx.compose.material3.DropdownMenuItem(
                            text = {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    androidx.compose.material3.Checkbox(
                                        checked = selected,
                                        onCheckedChange = { onToggleInterest(interest.id) }
                                    )
                                    Text(interest.label)
                                }
                            },
                            onClick = { onToggleInterest(interest.id) }
                        )
                    }
                }
            }

            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp),
                color = PluckPalette.Surface,
                tonalElevation = 0.dp,
                shadowElevation = 4.dp,
            ) {
                FilterChip(
                    modifier = Modifier.fillMaxWidth(),
                    selected = availabilityLabel != null,
                    onClick = onClickAvailability,
                    label = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Schedule,
                                contentDescription = null,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = availabilityLabel ?: "Availability",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = PluckPalette.Surface,
                        selectedContainerColor = PluckPalette.Surface,
                        labelColor = PluckPalette.Primary,
                        selectedLabelColor = PluckPalette.Primary
                    ),
                    border = null
                )
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface,
        shadowElevation = 12.dp,
        border = BorderStroke(
            1.dp,
            PluckPalette.Primary.copy(alpha = 0.05f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No events match your filters",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = PluckPalette.Primary
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Adjust your filters or check back soon for new events.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PluckPalette.Muted
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}
