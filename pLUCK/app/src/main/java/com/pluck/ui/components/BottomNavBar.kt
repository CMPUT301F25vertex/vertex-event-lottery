/**
 * BottomNavBar.kt
 *
 * Purpose: Provides a reusable bottom navigation bar with center FAB for event creation.
 *
 * Design Pattern: Composable UI Component with central action button
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

/**
 * Data class representing a navigation tab in the bottom navigation bar.
 */
data class NavTab(
    val id: String,
    val label: String,
    val icon: ImageVector,
    val route: String
)

/**
 * Object containing all available navigation tabs for the application.
 */
object NavTabs {
    val Home = NavTab(
        id = "home",
        label = "Home",
        icon = Icons.Default.Home,
        route = "event_list"
    )

    val Notifications = NavTab(
        id = "notifications",
        label = "Notifications",
        icon = Icons.Default.Notifications,
        route = "notifications"
    )

    val Settings = NavTab(
        id = "settings",
        label = "Settings",
        icon = Icons.Default.Settings,
        route = "settings"
    )

    val Profile = NavTab(
        id = "profile",
        label = "Profile",
        icon = Icons.Default.Person,
        route = "profile"
    )

    // Only 4 tabs now, plus button takes center position
    val all = listOf(Home, Notifications, Settings, Profile)
}

/**
 * Bottom navigation bar with center floating action button for creating events.
 *
 * @param currentRoute The currently active route
 * @param onNavigate Callback for navigation
 * @param onCreateEvent Callback for the center create button
 * @param modifier Optional Modifier
 */
@Composable
fun BottomNavBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    onCreateEvent: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Navigation bar
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = PluckPalette.Surface,
            contentColor = PluckPalette.Primary
        ) {
            // First 2 tabs (Home, Notifications)
            NavTabs.all.take(2).forEach { tab ->
                val selected = currentRoute == tab.route

                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = tab.label
                        )
                    },
                    label = { Text(tab.label) },
                    selected = selected,
                    onClick = { onNavigate(tab.route) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PluckPalette.Primary,
                        selectedTextColor = PluckPalette.Primary,
                        unselectedIconColor = PluckPalette.Muted,
                        unselectedTextColor = PluckPalette.Muted,
                        indicatorColor = PluckPalette.Tertiary.copy(alpha = 0.3f)
                    )
                )
            }

            // Empty spacer for center FAB
            NavigationBarItem(
                icon = { },
                label = { },
                selected = false,
                onClick = { },
                enabled = false,
                colors = NavigationBarItemDefaults.colors(
                    disabledIconColor = Color.Transparent,
                    disabledTextColor = Color.Transparent
                )
            )

            // Last 2 tabs (Settings, Profile)
            NavTabs.all.drop(2).forEach { tab ->
                val selected = currentRoute == tab.route

                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = tab.label
                        )
                    },
                    label = { Text(tab.label) },
                    selected = selected,
                    onClick = { onNavigate(tab.route) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = PluckPalette.Primary,
                        selectedTextColor = PluckPalette.Primary,
                        unselectedIconColor = PluckPalette.Muted,
                        unselectedTextColor = PluckPalette.Muted,
                        indicatorColor = PluckPalette.Tertiary.copy(alpha = 0.3f)
                    )
                )
            }
        }

        // Center floating action button
        FloatingActionButton(
            onClick = onCreateEvent,
            modifier = Modifier
                .offset(y = (-28).dp)
                .size(64.dp)
                .zIndex(1f),
            containerColor = PluckPalette.Secondary,
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 8.dp,
                pressedElevation = 12.dp
            ),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Create Event",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
