package com.pluck.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
        modifier = modifier
            .fillMaxWidth()
            .background(PluckPalette.Surface),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = PluckPalette.Surface,
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            tonalElevation = 6.dp,
            shadowElevation = 12.dp,
            border = BorderStroke(0.dp, Color.Transparent)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
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

                Spacer(modifier = Modifier.size(80.dp))

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
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

        FloatingActionButton(
            onClick = onCreateEvent,
            modifier = Modifier
                .offset(y = (-28).dp)
                .size(64.dp)
                .zIndex(1f),
            containerColor = PluckPalette.Secondary,
            contentColor = autoTextColor(PluckPalette.Secondary),
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


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun NavItem(
    tab: NavTab,
    selected: Boolean,
    onClick: () -> Unit
) {
    val contentColor = if (selected) PluckPalette.Primary else PluckPalette.Muted
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = if (selected) PluckPalette.Primary.copy(alpha = 0.14f) else Color.Transparent,
        contentColor = contentColor,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = tab.icon,
                contentDescription = tab.label,
                tint = contentColor,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = tab.label,
                fontSize = 11.sp,
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
