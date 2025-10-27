package com.pluck.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pluck.ui.theme.autoTextColor

data class NavTab(
    val id: String,
    val label: String,
    val icon: ImageVector,
    val route: String
)

object NavTabs {
    val Home = NavTab("home", "Home", Icons.Default.Home, "event_list")
    val Notifications = NavTab("notifications", "Notifications", Icons.Default.Notifications, "notifications")
    val Settings = NavTab("settings", "Settings", Icons.Default.Settings, "settings")
    val Profile = NavTab("profile", "Profile", Icons.Default.Person, "profile")
    val all = listOf(Home, Notifications, Settings, Profile)
}

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
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = PluckPalette.Surface,
            shape = RoundedCornerShape(0.dp),
            tonalElevation = 0.dp,
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left side nav items
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavTabs.all.take(2).forEach { tab ->
                        NavItem(
                            tab = tab,
                            selected = currentRoute == tab.route,
                            onClick = { onNavigate(tab.route) }
                        )
                    }
                }

                // Center spacer for FAB
                Spacer(modifier = Modifier.size(72.dp))

                // Right side nav items
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavTabs.all.drop(2).forEach { tab ->
                        NavItem(
                            tab = tab,
                            selected = currentRoute == tab.route,
                            onClick = { onNavigate(tab.route) }
                        )
                    }
                }
            }
        }

        // Floating action button centered and elevated
        FloatingActionButton(
            onClick = onCreateEvent,
            modifier = Modifier
                .offset(y = (-24).dp)
                .size(56.dp)
                .zIndex(2f),
            containerColor = PluckPalette.Secondary,
            contentColor = autoTextColor(PluckPalette.Secondary),
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 6.dp,
                pressedElevation = 8.dp
            ),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Create Event",
                modifier = Modifier.size(28.dp)
            )
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
                modifier = Modifier.size(22.dp)
            )
            Text(
                text = tab.label,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                softWrap = false,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                color = contentColor
            )
        }
    }
}
