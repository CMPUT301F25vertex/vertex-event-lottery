/**
 * HomeScreen.kt
 *
 * Purpose: Main landing screen displaying browsable events and lottery system.
 * Users can browse available events, view event details, and join waitlists.
 * Organizers can navigate to event creation and management features.
 * Implements event discovery with search, filtering by category/date, and availability status.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.outlined.Search
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalDensity
import coil3.compose.AsyncImage
import com.pluck.ui.components.AutoHidingBarScroller
import com.pluck.ui.components.BottomNavBar
import com.pluck.ui.components.ComposableItem
import com.pluck.ui.components.ConfettiBurst
import com.pluck.ui.components.Dashboard
import com.pluck.ui.components.DashboardType
import com.pluck.ui.components.EventBrowser
import com.pluck.ui.components.EventFilter
import com.pluck.ui.components.FullWidthLazyScroll
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.components.RoundIconButton
import com.pluck.ui.model.Event
import com.pluck.ui.theme.autoTextColor
import java.time.LocalDate
import kotlin.random.Random

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
    currentUserId: String,
    isLoading: Boolean,
    currentRoute: String?,
    dashboards: List<Dashboard>,
    onSelectEvent: (Event) -> Unit,
    onNavigate: (String) -> Unit,
    onScanQRCode: (() -> Unit)? = null,
    onRefreshEvents: (() -> Unit)? = null,
    isRefreshing: Boolean,
    userJoinedEventIds: Set<String> = emptySet(),
    notificationCount: Int
) {
    HomeScreenContent(
        userName = userName,
        events = events,
        isLoading = isLoading,
        currentRoute = currentRoute,
        onSelectEvent = onSelectEvent,
        onNavigate = onNavigate,
        onScanQRCode = onScanQRCode ?: {},
        onRefresh = onRefreshEvents ?: {},
        isRefreshing = isRefreshing,
        userJoinedEventIds = userJoinedEventIds,
        dashboards = dashboards,
        currentUserId = currentUserId,
        notificationCount = notificationCount
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
    currentUserId: String,
    isLoading: Boolean,
    currentRoute: String?,
    dashboards: List<Dashboard>,
    onSelectEvent: (Event) -> Unit,
    onNavigate: (String) -> Unit,
    onScanQRCode: () -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    userJoinedEventIds: Set<String>,
    notificationCount: Int
) {
    val filters = mutableListOf<EventFilter>()

    val today = LocalDate.now()
    val weekFromNow = today.plusDays(7)

    filters.add(EventFilter(
        label = "All",
        condition = { event -> true }
    ))

    filters.add(EventFilter(
        label = "Upcoming",
        condition = { event -> event.date >= today}
    ))

    filters.add(EventFilter(
        label = "Today",
        condition = { event -> event.date == today }
    ))

    filters.add(EventFilter(
        label = "This Week",
        condition = { event -> event.date >= today && event.date <= weekFromNow }
    ))

    filters.add(EventFilter(
        label = "Available",
        condition = { event -> !event.isPastEvent && !event.isFull && !userJoinedEventIds.contains(event.id) && event.isRegistrationOpen }
    ))

    filters.add(EventFilter(
        label = "Full",
        condition = { event -> !event.isPastEvent && event.isFull && event.isWaitlistFull }
    ))

    filters.add(EventFilter(
        label = "Past",
        condition = { event -> event.date < today }
    ))

    EventBrowser(
        bottomNavBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                onNavigate = onNavigate,
                dashboards = dashboards,
                currentDashboard = DashboardType.Entrant,
                notificationCount = notificationCount
            )
        },
        overviewHero = {
            HomeHeroCard(
                userName = userName,
                onScanQRCode = onScanQRCode
            )
        },
        events = events,
        onSelectEvent = onSelectEvent,
        filters = filters,
        onRefresh = onRefresh,
        isRefreshing = isRefreshing,
        currentUserId = currentUserId
    )
}


/**
 * Renders the hero card at the top of the home feed, including the greeting and the quick action pills.
 *
 * @param onScanQRCode Triggered when the scan shortcut is tapped.
 * @param onRefreshClick Triggered when the refresh shortcut is tapped.
 */
@Composable
private fun HomeHeroCard(
    userName: String?,
    onScanQRCode: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Hi, ${userName ?: "Vertex"}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = PluckPalette.Muted,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "Find your next experience",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = PluckPalette.Primary,
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            RoundIconButton(
                imageVector = Icons.Outlined.QrCodeScanner,
                contentDescription = "QR Code Scanner",
                onClick = onScanQRCode
            )
        }
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
        dashboards = emptyList(),
        isRefreshing = false,
        currentUserId = "",
        notificationCount = 0
    )
}
