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

import android.util.Log
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
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
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.zIndex
import com.pluck.ui.components.PluckAccentCircle
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.ui.components.BottomNavBar
import com.pluck.ui.components.ComposableItem
import com.pluck.ui.components.DashboardType
import com.pluck.ui.components.EventBrowser
import com.pluck.ui.components.EventFilter
import com.pluck.ui.components.FullWidthLazyScroll
import com.pluck.ui.components.RoundIconButton
import com.pluck.ui.model.EntrantProfile
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
    user: EntrantProfile?, // TODO make this not null
    isLoading: Boolean = false,
    onEventClick: (Event) -> Unit = {},
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier,
    bottomBar: @Composable () -> Unit
) {
    val filters = mutableListOf<EventFilter>()

    val userId = user?.deviceId.orEmpty()

    val today = LocalDate.now()

    val joinedCondition = { event: Event -> true }
    val upcomingCondition = { event: Event -> event.date >= today && joinedCondition(event) }
    val pastCondition = { event: Event -> event.date < today && joinedCondition(event) }
    val createdCondition = { event: Event -> event.organizerId == userId }

    filters.add(EventFilter(
        label = "All",
        condition = { event ->
            upcomingCondition(event) || pastCondition(event) || joinedCondition(event) || createdCondition(event)
        }
    ))

    filters.add(EventFilter(
        label = "Upcoming",
        condition = { event -> upcomingCondition(event) }
    ))

    filters.add(EventFilter(
        label = "Past",
        condition = { event -> pastCondition(event) }
    ))

    filters.add(EventFilter(
        label = "Joined",
        condition = { event -> joinedCondition(event) }
    ))

    filters.add(EventFilter(
        label = "Created",
        condition = { event -> createdCondition(event) }
    ))

    EventBrowser(
        bottomNavBar = {
            bottomBar()
        },
        overviewHero = {
            MyEventsHero()
        },
        events = events,
        onSelectEvent = onEventClick,
        filters = filters,
        eventCardBonus = { event ->
            if (event.organizerId == userId) {
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
    )
}

@Composable
private fun MyEventsHero() {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
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
                        text = "My Events",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Black,
                            color = PluckPalette.Primary,
                            fontSize = 28.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                RoundIconButton(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "Calendar"
                )
            }
        }
    }
}

/*
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
*/
