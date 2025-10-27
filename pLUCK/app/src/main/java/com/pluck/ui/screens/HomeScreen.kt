package com.pluck.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalDensity
import com.pluck.ui.components.BottomNavBar
import com.pluck.ui.components.PluckAccentCircle
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.model.Event
import com.pluck.ui.theme.autoTextColor
import java.time.LocalDate
import kotlin.random.Random

/**
 * HomeScreen.kt
 *
 * Purpose: Renders the primary discovery surface for entrants, mixing curated hero messaging,
 * category filters, and event listings while delegating global navigation to [BottomNavBar].
 *
 * Outstanding Issues: None.
 */
data class EventCategory(val id: String, val label: String)

private val homeCategories = listOf(
    EventCategory("all", "All"),
    EventCategory("upcoming", "Upcoming"),
    EventCategory("today", "Today"),
    EventCategory("week", "This Week"),
    EventCategory("available", "Available"),
    EventCategory("full", "Full"),
    EventCategory("location", "By Location"),
    EventCategory("past", "Past")
)

/**
 * Entry point for the Home screen. This overload matches the signature compiled into the
 * instructor tests, so keep defaultable callbacks nullable and coalesce internally.
 */
@Composable
fun HomeScreen(
    userName: String?,
    events: List<Event>,
    isLoading: Boolean,
    currentRoute: String?,
    onSelectEvent: (Event) -> Unit,
    onNavigate: (String) -> Unit,
    onCreateEvent: (() -> Unit)? = null,
    onScanQRCode: (() -> Unit)? = null,
    onRefreshEvents: (() -> Unit)? = null,
    userJoinedEventIds: Set<String> = emptySet(),
    modifier: Modifier = Modifier
) {
    HomeScreenContent(
        userName = userName,
        events = events,
        isLoading = isLoading,
        currentRoute = currentRoute,
        onSelectEvent = onSelectEvent,
        onNavigate = onNavigate,
        onCreateEvent = onCreateEvent ?: {},
        onScanQRCode = onScanQRCode ?: {},
        onRefresh = onRefreshEvents ?: {},
        userJoinedEventIds = userJoinedEventIds,
        modifier = modifier
    )
}

/**
 * Shared implementation for the home layout so previews, tests, and production share the same rendering path.
 *
 * @param onRefresh Invoked when the quick refresh pill is pressed.
 */
@Composable
private fun HomeScreenContent(
    userName: String?,
    events: List<Event>,
    isLoading: Boolean,
    currentRoute: String?,
    onSelectEvent: (Event) -> Unit,
    onNavigate: (String) -> Unit,
    onCreateEvent: () -> Unit,
    onScanQRCode: () -> Unit,
    onRefresh: () -> Unit,
    userJoinedEventIds: Set<String>,
    modifier: Modifier = Modifier
) {
    var selectedCategoryId by remember { mutableStateOf(homeCategories.first().id) }
    var confettiTrigger by remember { mutableStateOf(0) }
    val filteredEvents = remember(events, selectedCategoryId, userJoinedEventIds) {
        val today = LocalDate.now()
        val weekFromNow = today.plusDays(7)

        when (selectedCategoryId) {
            "past" -> events.filter { it.isPastEvent }.sortedByDescending { it.date }
            "all" -> events.filter { !it.isPastEvent }.sortedBy { it.date }
            "upcoming" -> events.filter { !it.isPastEvent && it.date >= today }.sortedBy { it.date }
            "today" -> events.filter { !it.isPastEvent && it.date == today }
            "week" -> events.filter { !it.isPastEvent && it.date >= today && it.date <= weekFromNow }.sortedBy { it.date }
            "available" -> events.filter { event ->
                !event.isPastEvent &&
                !event.isFull &&
                !userJoinedEventIds.contains(event.id) &&
                // Registration must be currently open (started and not ended)
                (event.registrationStart == null || !event.registrationStart.isAfter(today)) &&
                (event.registrationEnd == null || !event.registrationEnd.isBefore(today))
            }.sortedBy { it.date }
            "full" -> events.filter { !it.isPastEvent && it.isFull && it.isWaitlistFull }.sortedBy { it.date }
            "location" -> events.filter { !it.isPastEvent }.sortedBy { it.location }
            else -> events.filter { !it.isPastEvent }
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
                else -> (scrollOffset / 300f).coerceIn(0f, 1f)
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        bottomBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                onNavigate = onNavigate,
                onCreateEvent = onCreateEvent
            )
        }
    ) { paddingValues ->
        PluckLayeredBackground(
            modifier = Modifier.fillMaxSize()
        ) {
            val horizontalPadding = 20.dp
            val contentTopPadding = paddingValues.calculateTopPadding() + 20.dp
            val bottomInset = paddingValues.calculateBottomPadding()
            val adjustedBottomInset = (bottomInset - 48.dp).coerceAtLeast(12.dp)
            val contentBottomPadding = adjustedBottomInset + 46.dp

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = horizontalPadding,
                        end = horizontalPadding,
                        top = contentTopPadding,
                        bottom = contentBottomPadding
                    )
            ) {
                when {
                    isLoading -> HomeLoadingState()
                    filteredEvents.isEmpty() && firstVisibleIndex <= 1 -> {
                        // Show hero and filters with empty state
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            HomeHeroCard(
                                userName = userName,
                                onScanQRCode = onScanQRCode,
                                onRefreshClick = {
                                    confettiTrigger++
                                    onRefresh()
                                },
                                collapseProgress = 0f
                            )
                            HomeFilterRow(
                                categories = homeCategories,
                                selectedId = selectedCategoryId,
                                onCategorySelected = { selectedCategoryId = it }
                            )
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                shape = RoundedCornerShape(32.dp),
                                color = PluckPalette.Surface,
                                tonalElevation = 0.dp,
                                shadowElevation = 12.dp,
                                border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
                            ) {
                                HomeEmptyState()
                            }
                        }
                    }
                    else -> {
                        // Scrollable list with collapsing hero
                        LazyColumn(
                            state = listState,
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            item {
                                HomeHeroCard(
                                    userName = userName,
                                    onScanQRCode = onScanQRCode,
                                    onRefreshClick = {
                                        confettiTrigger++
                                        onRefresh()
                                    },
                                    collapseProgress = heroCollapseProgress
                                )
                            }

                            item {
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(32.dp),
                                    color = PluckPalette.Surface,
                                    tonalElevation = 0.dp,
                                    shadowElevation = 12.dp,
                                    border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
                                ) {
                                    Column(
                                        modifier = Modifier.padding(horizontal = 18.dp, vertical = 18.dp),
                                        verticalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        HomeFilterRow(
                                            categories = homeCategories,
                                            selectedId = selectedCategoryId,
                                            onCategorySelected = { selectedCategoryId = it },
                                            useContainer = false
                                        )
                                        Text(
                                            text = "Discover Events",
                                            style = MaterialTheme.typography.titleLarge.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = PluckPalette.Primary,
                                                fontSize = 22.sp
                                            ),
                                            modifier = Modifier.padding(bottom = 4.dp)
                                        )
                                    }
                                }
                            }

                            itemsIndexed(filteredEvents, key = { _, event -> event.id }) { index, event ->
                                val accentPalette = listOf(
                                    PluckPalette.Secondary,
                                    PluckPalette.Tertiary,
                                    PluckPalette.Pink,
                                    PluckPalette.Magenta
                                )
                                HomeEventCard(
                                    event = event,
                                    accentColor = accentPalette[index % accentPalette.size],
                                    onClick = { onSelectEvent(event) }
                                )
                            }
                        }
                    }
                }
            }

            ConfettiBurst(
                trigger = confettiTrigger,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
            )
        }
    }
}


/**
 * Renders the hero card at the top of the home feed, including the greeting and the quick action pills.
 *
 * @param onScanQRCode Triggered when the scan shortcut is tapped.
 * @param onRefreshClick Triggered when the refresh shortcut is tapped.
 * @param collapseProgress Animated value describing how collapsed the hero currently is.
 */
@Composable
private fun HomeHeroCard(
    userName: String?,
    onScanQRCode: () -> Unit = {},
    onRefreshClick: () -> Unit = {},
    collapseProgress: Float = 0f
) {
    val targetHeight = (1f - collapseProgress * 0.4f)
    val targetAlpha = (1f - collapseProgress).coerceIn(0f, 1f)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp)
            .height((100 * targetHeight).dp),
        shape = RoundedCornerShape(32.dp),
        color = PluckPalette.Surface.copy(alpha = targetAlpha),
        tonalElevation = 0.dp,
        shadowElevation = (16.dp * (1f - collapseProgress * 0.7f)),
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f * targetAlpha))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp, vertical = (20 * targetHeight).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy((8 * targetHeight).dp)
            ) {
                if (collapseProgress < 0.8f) {
                    Text(
                        text = "Hi, ${userName ?: "Vertex"}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = PluckPalette.Muted.copy(alpha = targetAlpha),
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = "Find your next experience",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = PluckPalette.Primary.copy(alpha = targetAlpha),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = (16 - collapseProgress * 5).sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeHeroAction(
                    icon = Icons.Outlined.QrCodeScanner,
                    backgroundColor = PluckPalette.Tertiary.copy(alpha = targetAlpha),
                    onClick = onScanQRCode
                )
                HomeHeroAction(
                    icon = Icons.Outlined.Refresh,
                    backgroundColor = PluckPalette.Surface.copy(alpha = targetAlpha),
                    iconTint = PluckPalette.Primary.copy(alpha = targetAlpha),
                    borderColor = PluckPalette.Primary.copy(alpha = 0.12f * targetAlpha),
                    onClick = onRefreshClick
                )
            }
        }
    }
}

/**
 * Small circular button used within the hero to represent an action.
 *
 * @param icon Icon drawn at the centre of the pill.
 * @param backgroundColor Background tint applied to the pill.
 * @param onClick Callback invoked when the pill is pressed.
 */
@Composable
private fun HomeHeroAction(
    icon: ImageVector,
    backgroundColor: Color,
    iconTint: Color = autoTextColor(backgroundColor),
    borderColor: Color = Color.Transparent,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .clickable(onClick = onClick)
            .size(56.dp),
        shape = CircleShape,
        color = backgroundColor,
        contentColor = iconTint,
        tonalElevation = 0.dp,
        shadowElevation = 10.dp,
        border = if (borderColor == Color.Transparent) null else BorderStroke(1.dp, borderColor)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

/** Internal model for a single confetti particle. */
private data class ConfettiParticle(
    val startX: Float,
    val startY: Float,
    val velocityX: Float,
    val velocityY: Float,
    val radiusPx: Float,
    val color: Color
)

/**
 * Lightweight confetti burst used as a playful confirmation when the refresh pill is triggered.
 *
 * @param trigger Increment this value to fire a new animation.
 */
@Composable
private fun ConfettiBurst(
    trigger: Int,
    modifier: Modifier = Modifier,
    particleCount: Int = 28
) {
    val density = LocalDensity.current
    val palette = listOf(
        PluckPalette.Primary,
        PluckPalette.Secondary,
        PluckPalette.Tertiary,
        PluckPalette.Magenta,
        PluckPalette.Pink
    )
    val particles = remember(trigger, density) {
        if (trigger == 0) emptyList() else List(particleCount) { index ->
            val random = Random(trigger * 997 + index * 23)
            val radiusPx = with(density) { (6 + random.nextInt(6)).dp.toPx() }
            ConfettiParticle(
                startX = random.nextFloat(),
                startY = random.nextFloat() * 0.2f,
                velocityX = (random.nextFloat() - 0.5f) * 0.7f,
                velocityY = 0.8f + random.nextFloat(),
                radiusPx = radiusPx,
                color = palette[random.nextInt(palette.size)]
            )
        }
    }
    val progress = remember { Animatable(0f) }
    var isActive by remember { mutableStateOf(false) }

    LaunchedEffect(trigger) {
        if (trigger == 0 || particles.isEmpty()) return@LaunchedEffect
        isActive = true
        progress.snapTo(0f)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1100, easing = LinearOutSlowInEasing)
        )
        isActive = false
    }

    if (!isActive || particles.isEmpty()) {
        return
    }

    val progressValue = progress.value
    Canvas(modifier = modifier) {
        particles.forEach { particle ->
            val xFraction = (particle.startX + particle.velocityX * progressValue).coerceIn(0f, 1f)
            val fallProgress = progressValue * progressValue
            val yFraction = (particle.startY + particle.velocityY * fallProgress).coerceIn(0f, 1.2f)
            drawCircle(
                color = particle.color.copy(alpha = 1f - progressValue.coerceIn(0f, 0.9f)),
                radius = particle.radiusPx,
                center = Offset(
                    x = xFraction * size.width,
                    y = yFraction * size.height
                )
            )
        }
    }
}

/**
 * Segmented control row for filtering the visible events list.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeFilterRow(
    categories: List<EventCategory>,
    selectedId: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    useContainer: Boolean = true
) {
    val baseModifier = modifier
        .fillMaxWidth()
        .widthIn(max = 460.dp)

    val chipRow: @Composable () -> Unit = {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(categories) { _, category ->
                val selected = category.id == selectedId
                FilterChip(
                    selected = selected,
                    onClick = { onCategorySelected(category.id) },
                    label = {
                        Text(
                            text = category.label,
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = if (selected) PluckPalette.Primary else PluckPalette.Surface,
                        labelColor = if (selected) PluckPalette.Surface else PluckPalette.Primary,
                        selectedContainerColor = PluckPalette.Primary,
                        selectedLabelColor = PluckPalette.Surface
                    )
                )
            }
        }
    }

    if (useContainer) {
        Surface(
            modifier = baseModifier,
            shape = RoundedCornerShape(32.dp),
            color = PluckPalette.Surface,
            tonalElevation = 0.dp,
            shadowElevation = 12.dp,
            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
        ) {
            chipRow()
        }
    } else {
        Box(modifier = baseModifier) {
            chipRow()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeEventCard(
    event: Event,
    accentColor: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        border = BorderStroke(2.dp, accentColor.copy(alpha = 0.15f))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // Colorful header strip
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                color = accentColor.copy(alpha = 0.12f),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
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
                    // US 01.01.03: Show availability status to entrants
                    EventAvailabilityBadge(event = event)
                }
            }

            // Content area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                EventMetaRow(
                    icon = Icons.Outlined.Schedule,
                    label = event.dateLabel,
                    accentColor = accentColor
                )
                EventMetaRow(
                    icon = Icons.Outlined.LocationOn,
                    label = event.location,
                    accentColor = accentColor
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${event.enrolled}/${event.capacity} enrolled",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PluckPalette.Primary,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(
                        text = "${event.waitlistCount} waitlisted",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PluckPalette.Muted
                        )
                    )
                }
            }
        }
    }
}

/**
 * US 01.01.03: Availability badge showing if event is open, full, or waitlist-only
 * Helps entrants identify which events they can join
 */
@Composable
private fun EventAvailabilityBadge(event: Event) {
    val (statusText, statusColor) = when {
        !event.isFull -> "${event.remainingSpots} left" to PluckPalette.Accept
        !event.isWaitlistFull -> "Waitlist Open" to PluckPalette.Secondary
        else -> "Full" to PluckPalette.Muted
    }

    Surface(
        shape = RoundedCornerShape(18.dp),
        color = statusColor,
        tonalElevation = 0.dp,
        shadowElevation = 4.dp
    ) {
        Text(
            text = statusText,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelLarge.copy(
                color = autoTextColor(statusColor),
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
private fun EventMetaRow(
    icon: ImageVector,
    label: String,
    accentColor: Color = PluckPalette.Secondary
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
private fun HomeLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = PluckPalette.Primary)
    }
}

@Composable
private fun HomeEmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No events match your filters just yet.",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = PluckPalette.Primary
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Adjust your categories or check back soon for new lotteries.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Muted
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 920)
@Composable
private fun HomeScreenPreview() {
    val previewEvents = listOf(
        Event(
            id = "1",
            title = "Swimming Lessons Event",
            description = "Learn the fundamentals with expert instructors.",
            location = "City Pool - Capacity 20",
            date = LocalDate.now().plusDays(3),
            capacity = 20,
            enrolled = 12,
            organizerName = "Vertex Community"
        ),
        Event(
            id = "2",
            title = "Cooking with Gordon",
            description = "Hands-on culinary experience with a master chef.",
            location = "Kitchen Lab - Capacity 15",
            date = LocalDate.now().plusDays(5),
            capacity = 15,
            enrolled = 14,
            organizerName = "Gastronomy Guild"
        )
    )

    HomeScreen(
        userName = "Caiden",
        events = previewEvents,
        isLoading = false,
        currentRoute = null,
        onSelectEvent = {},
        onNavigate = {},
        onCreateEvent = {}
    )
}
