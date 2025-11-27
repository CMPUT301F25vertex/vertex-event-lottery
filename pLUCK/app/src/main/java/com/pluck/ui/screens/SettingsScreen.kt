/**
 * SettingsScreen.kt
 *
 * Purpose: Application settings and preferences screen for configuring app behavior.
 * Users can manage notifications, theme preferences, and other application settings.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.pluck.data.NotificationPreferences
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pluck.ui.components.ComposableItem
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.components.SquircleScrollableLazyList

/**
 * SettingsScreen.kt
 *
 * Purpose: User settings and preferences management following the pLUCK design language.
 *
 * Design Pattern: Stateful screen with organized settings sections.
 *
 * Outstanding Issues: None.
 */
/**
 * Backwards-compatible overload retained for instrumentation tests compiled against
 * the previous two-parameter SettingsScreen signature.
 */
@Composable
fun SettingsScreen(
    darkModeEnabled: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    SettingsScreen(
        darkModeEnabled = darkModeEnabled,
        onDarkModeChange = onDarkModeChange,
        onNavigateToThemePicker = {},
        modifier = modifier
    )
}

@Composable
fun SettingsScreen(
    darkModeEnabled: Boolean = false,
    onDarkModeChange: (Boolean) -> Unit = {},
    onNavigateToThemePicker: () -> Unit = {},
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val notificationPrefs = remember { NotificationPreferences(context) }

    var notificationsEnabled by remember { mutableStateOf(notificationPrefs.areAllNotificationsEnabled()) }
    var emailNotifications by remember { mutableStateOf(notificationPrefs.areEmailNotificationsEnabled()) }
    var pushNotifications by remember { mutableStateOf(notificationPrefs.arePushNotificationsEnabled()) }

    // Load preferences on first composition
    LaunchedEffect(Unit) {
        notificationsEnabled = notificationPrefs.areAllNotificationsEnabled()
        emailNotifications = notificationPrefs.areEmailNotificationsEnabled()
        pushNotifications = notificationPrefs.arePushNotificationsEnabled()
    }

    val listElements = mutableListOf<ComposableItem>()

    listElements.add(ComposableItem {
        SettingsHeader()
    })

    listElements.add(ComposableItem {
        SettingsSection(
            title = "Notifications",
            icon = Icons.Outlined.Notifications,
            iconColor = PluckPalette.Secondary
        )
        {
            Spacer(modifier = Modifier.size(4.dp))
            SettingsToggleItem(
                label = "In App Notifications",
                description = "Enable or disable in app notifications",
                checked = notificationsEnabled,
                onCheckedChange = {
                    notificationsEnabled = it
                    notificationPrefs.setAllNotificationsEnabled(it)
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
            SettingsToggleItem(
                label = "Push Notifications",
                description = "Receive push notifications for updates",
                checked = pushNotifications,
                onCheckedChange = {
                    pushNotifications = it
                    notificationPrefs.setPushNotificationsEnabled(it)
                },
                enabled = notificationsEnabled
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
    })

    listElements.add(ComposableItem {
        SettingsSection(
            title = "Appearance",
            icon = Icons.Outlined.Palette,
            iconColor = PluckPalette.Tertiary
        )
        {
            SettingsActionItem(
                label = "Color Theme",
                description = "Choose your preferred color scheme",
                onClick = onNavigateToThemePicker
            )
            SettingsToggleItem(
                label = "Dark Mode",
                description = "Switch between light and dark theme",
                checked = darkModeEnabled,
                onCheckedChange = onDarkModeChange,
                enabled = true
            )
        }
    })

    PluckLayeredBackground(
        modifier = modifier.fillMaxSize()
    )
    {
        SquircleScrollableLazyList(
            listElements = listElements
        )
    }
}

@Composable
private fun SettingsHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Black,
                color = PluckPalette.Primary,
                fontSize = 28.sp
            )
        )
        Text(
            text = "Manage your preferences and account",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Muted
            )
        )
    }
}

@Composable
private fun SettingsSection(
    title: String,
    icon: ImageVector,
    iconColor: Color,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = iconColor.copy(alpha = 0.16f),
                contentColor = iconColor
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            color = PluckPalette.Primary.copy(alpha = 0.02f),
            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.06f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
private fun SettingsToggleItem(
    label: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable(
                enabled = enabled,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                if (enabled) onCheckedChange(!checked)
            },
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = if (enabled) PluckPalette.Primary else PluckPalette.Muted
                    )
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PluckPalette.Muted
                    )
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = PluckPalette.Surface,
                    checkedTrackColor = PluckPalette.Accept,
                    uncheckedThumbColor = PluckPalette.Surface,
                    uncheckedTrackColor = PluckPalette.Muted.copy(alpha = 0.3f),
                    disabledCheckedThumbColor = PluckPalette.Muted,
                    disabledCheckedTrackColor = PluckPalette.Muted.copy(alpha = 0.2f),
                    disabledUncheckedThumbColor = PluckPalette.Muted,
                    disabledUncheckedTrackColor = PluckPalette.Muted.copy(alpha = 0.1f)
                )
            )
        }
    }
}

@Composable
private fun SettingsActionItem(
    label: String,
    description: String,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            },
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = PluckPalette.Primary
                    )
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PluckPalette.Muted
                    )
                )
            }
            Surface(
                modifier = Modifier.size(32.dp),
                shape = CircleShape,
                color = PluckPalette.Primary.copy(alpha = 0.08f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.ChevronRight,
                        contentDescription = null,
                        tint = PluckPalette.Primary
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 420, heightDp = 920)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}

