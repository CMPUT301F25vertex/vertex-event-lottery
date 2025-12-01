/**
 * MyEventsScreen.kt
 *
 * Purpose: Personalized event dashboard showing user's joined events and waitlist status.
 * Entrants can track their registrations and manage their event participations.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.zIndex
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pluck.ui.components.PluckAccentCircle
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.data.repository.UserEventHistory
import com.pluck.ui.components.BottomNavBar
import com.pluck.ui.components.ComposableItem
import com.pluck.ui.components.Dashboard
import com.pluck.ui.components.EventBrowser
import com.pluck.ui.components.EventFilter
import com.pluck.ui.components.FullWidthLazyScroll
import com.pluck.ui.components.RoundIconButton
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

@Composable
fun MyEventsScreen(
    events: List<Event> = emptyList(),
    userId: String,
    isLoading: Boolean = false,
    onEventClick: (Event) -> Unit = {},
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier,
    historyByEventId: Map<String, UserEventHistory>,
    dashboards: List<Dashboard>,
    onNavigate: (String) -> Unit,
    currentRoute: String?,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    notificationCount: Int
) {
    val filters = mutableListOf<EventFilter>()

    val today = LocalDate.now()

    filters.add(EventFilter(
        label = "All",
        condition = { event -> true }
    ))

    filters.add(EventFilter(
        label = "Upcoming",
        condition = { event -> event.date >= today }
    ))

    filters.add(EventFilter(
        label = "Past",
        condition = { event -> event.date < today }
    ))

    filters.add(EventFilter(
        label = "Joined",
        condition = { event -> event.organizerId != userId && historyByEventId[event.id] != null }
    ))

    filters.add(EventFilter(
        label = "Created",
        condition = { event -> event.organizerId == userId }
    ))

    EventBrowser(
        bottomNavBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                onNavigate = onNavigate,
                dashboards = dashboards,
                notificationCount = notificationCount
            )
        },
        overviewHero = {
            MyEventsHero(
                eventCount = events.size
            )
        },
        events = events,
        onSelectEvent = onEventClick,
        filters = filters,
        currentUserId = userId,
        onRefresh = onRefresh,
        isRefreshing = isRefreshing
    )
}

@Composable
private fun MyEventsHero(
    eventCount: Int
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp)
            .zIndex(1f),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface,
        shadowElevation = 18.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Event History",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Black,
                            color = PluckPalette.Primary,
                            fontSize = 28.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = if (eventCount > 0) "$eventCount events in your calendar" else "No events yet",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PluckPalette.Secondary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                RoundIconButton(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "Calender"
                )
            }
        }
    }
}
