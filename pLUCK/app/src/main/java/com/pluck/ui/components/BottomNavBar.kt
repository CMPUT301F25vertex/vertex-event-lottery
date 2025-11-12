/**
 * BottomNavBar.kt
 *
 * Purpose: Bottom navigation bar component with floating action button for event creation
 *
 * Design Pattern: Composable UI component with Material Design 3 navigation patterns
 *
 * Features:
 * - 4 navigation tabs: Home, Notifications, Settings, Profile
 * - Center-aligned floating action button for event creation
 * - Active tab highlighting with tinted background
 * - Theme-aware colors via PluckPalette
 * - Elevated surface with shadow
 * - Icon + label layout for each tab
 *
 * Layout:
 * - Left: Home, Notifications
 * - Center: FAB (Create Event)
 * - Right: Settings, Profile
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.pluck.ui.theme.autoTextColor

/**
 * Represents a single navigation tab in the bottom bar
 *
 * @property id Unique identifier for the tab
 * @property label Display text shown under the icon
 * @property icon Material icon to display
 * @property route Navigation route associated with this tab
 */
data class NavTab(
    val id: String,
    val label: String,
    val icon: ImageVector,
    val route: String
)

/**
 * Predefined navigation tabs for the bottom navigation bar
 */
object NavTabs {
    val Home = NavTab("home", "Home", Icons.Default.Home, "event_list")
    val Notifications = NavTab("notifications", "Notifications", Icons.Default.Notifications, "notifications")
    val Settings = NavTab("settings", "Settings", Icons.Default.Settings, "settings")
    val Profile = NavTab("profile", "Profile", Icons.Default.Person, "profile")
    val all = listOf(Home, Notifications, Settings, Profile)
}

enum class DashboardType {
    Entrant,
    Organizer,
    Admin
}

data class Dashboard(
    val type: DashboardType,
    val onClick: () -> Unit
)

/**
 * Bottom navigation bar with centered floating action button
 *
 * Displays 4 navigation tabs (Home, Notifications, Settings, Profile) split around
 * a floating action button for creating events. Active tab is highlighted with theme colors.
 * The FAB is only visible for users with organizer role.
 *
 * @param currentRoute Current navigation route to determine active tab
 * @param onNavigate Callback when a tab is clicked, receives route string
 * @param onCreateEvent Callback when the FAB is clicked
 * @param showCreateButton Whether to show the create event FAB (only for organizers)
 * @param modifier Optional modifier for the container
 */
@Composable
fun BottomNavBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    dashboards: List<Dashboard>,
    currentDashboard: DashboardType? = null,
    modifier: Modifier = Modifier
) {
    val items = mutableListOf<@Composable () -> Unit>()

    NavTabs.all.take(2).forEach { tab ->
        items.add({
            NavItem(
                tab = tab,
                selected = currentRoute == tab.route,
                onClick = { onNavigate(tab.route) }
            )
        })
    }

    items.add({ DashboardSelector(dashboards, currentDashboard) })

    NavTabs.all.drop(2).forEach { tab ->
        items.add({
            NavItem(
                tab = tab,
                selected = currentRoute == tab.route,
                onClick = { onNavigate(tab.route) }
            )
        })
    }

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    )
    {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = PluckPalette.Surface,
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (item in items) {
                    item()
                }
            }
        }
    }
}

@Composable
fun DashboardSelector(
    dashboards: List<Dashboard>,
    currentDashboard: DashboardType?
) {
    var showMenu by remember { mutableStateOf(false) }

    if (dashboards.size < 2) return

    Box {
        RoundIconButton(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Event",
            circleColor = PluckPalette.Secondary,
            iconColor = autoTextColor(PluckPalette.Secondary),
            onClick = { showMenu = true }
        )

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = {
                showMenu = false
            }
        ) {
            for (dashboard in dashboards) {
                if (dashboard.type == currentDashboard) continue

                DropdownMenuItem(
                    text = { Text(dashboard.type.name) },
                    onClick = {
                        dashboard.onClick()
                    }
                )
            }
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun NavItem(
    tab: NavTab,
    selected: Boolean,
    onClick: () -> Unit
) {
    val contentColor = if (selected) PluckPalette.Primary else PluckPalette.Muted
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (selected) PluckPalette.Primary.copy(alpha = 0.12f) else Color.Transparent,
        contentColor = contentColor,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Icon(
                imageVector = tab.icon,
                contentDescription = tab.label,
                tint = contentColor,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
