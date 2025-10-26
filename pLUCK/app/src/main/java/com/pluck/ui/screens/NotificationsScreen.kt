package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Autorenew
import androidx.compose.material.icons.outlined.Celebration
import androidx.compose.material.icons.outlined.HourglassBottom
import androidx.compose.material.icons.outlined.MoodBad
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.model.NotificationButtons
import com.pluck.ui.model.NotificationCategory
import com.pluck.ui.model.NotificationFilter
import com.pluck.ui.model.NotificationItem
import com.pluck.ui.model.NotificationStatus
import com.pluck.ui.model.filterBy
import com.pluck.ui.model.previewNotifications

/**
 * NotificationsScreen.kt
 *
 * Purpose: Presents the notification centre using the updated jumbled-card design system.
 *
 * Design Pattern: Stateless composable scaffold with state hoisted and passed through parameters.
 *
 * Outstanding Issues: None.
 */
@Composable
fun NotificationsScreen(
    notifications: List<NotificationItem> = previewNotifications(),
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    processingNotificationIds: Set<String> = emptySet(),
    onEventDetails: (NotificationItem) -> Unit = {},
    onAccept: (NotificationItem) -> Unit = {},
    onDecline: (NotificationItem) -> Unit = {}
) {
    var filter by rememberSaveable { mutableStateOf(NotificationFilter.UNREAD) }
    val unreadCount = notifications.count { it.status == NotificationStatus.UNREAD }
    val filteredItems = remember(notifications, filter) { notifications.filterBy(filter) }

    NotificationsContent(
        modifier = modifier,
        unreadCount = unreadCount,
        selectedFilter = filter,
        onFilterSelected = { filter = it },
        notifications = filteredItems,
        isLoading = isLoading,
        processingNotificationIds = processingNotificationIds,
        onEventDetails = onEventDetails,
        onAccept = onAccept,
        onDecline = onDecline
    )
}

/**
 * High level layout container for the notifications experience.
 */
@Composable
private fun NotificationsContent(
    modifier: Modifier,
    unreadCount: Int,
    selectedFilter: NotificationFilter,
    onFilterSelected: (NotificationFilter) -> Unit,
    notifications: List<NotificationItem>,
    isLoading: Boolean,
    processingNotificationIds: Set<String>,
    onEventDetails: (NotificationItem) -> Unit,
    onAccept: (NotificationItem) -> Unit,
    onDecline: (NotificationItem) -> Unit
) {
    PluckLayeredBackground(
        modifier = modifier
            .fillMaxSize()
            .testTag(NotificationsTestTags.Screen)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            NotificationsHero(
                unreadCount = unreadCount,
                selectedFilter = selectedFilter,
                onFilterSelected = onFilterSelected
            )
            Box(modifier = Modifier.fillMaxSize()) {
                NotificationsFeedPanel(
                    notifications = notifications,
                    processingNotificationIds = processingNotificationIds,
                    onEventDetails = onEventDetails,
                    onAccept = onAccept,
                    onDecline = onDecline
                )
                if (isLoading) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

/**
 * Hero section housing the title, action icons, and filter chips.
 */
@Composable
private fun NotificationsHero(
    unreadCount: Int,
    selectedFilter: NotificationFilter,
    onFilterSelected: (NotificationFilter) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f),
            shape = RoundedCornerShape(32.dp),
            color = PluckPalette.Surface,
            tonalElevation = 0.dp,
            shadowElevation = 16.dp,
            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                NotificationsHeaderRow(unreadCount = unreadCount)
                NotificationFilterRow(
                    unreadCount = unreadCount,
                    selected = selectedFilter,
                    onSelected = onFilterSelected
                )
            }
        }
    }
}

/**
 * Header row inside the hero card showing the title and floating icons.
 */
@Composable
private fun NotificationsHeaderRow(unreadCount: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Black,
                    color = PluckPalette.Primary,
                    fontSize = 28.sp
                )
            )
            Text(
                text = if (unreadCount > 0) "$unreadCount new updates" else "You're in the loop",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PluckPalette.Muted
                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FloatingIconButton(
                icon = Icons.Outlined.Person,
                backgroundColor = PluckPalette.Secondary,
                contentColor = Color.White
            )
            FloatingIconButton(
                icon = Icons.Outlined.Notifications,
                backgroundColor = PluckPalette.Surface,
                contentColor = PluckPalette.Primary,
                borderColor = PluckPalette.Primary.copy(alpha = 0.08f)
            )
        }
    }
}

/**
 * Small utility component that renders a floating circular icon.
 */
@Composable
private fun FloatingIconButton(
    icon: ImageVector,
    backgroundColor: Color,
    contentColor: Color,
    borderColor: Color = Color.Transparent
) {
    Surface(
        modifier = Modifier.size(56.dp),
        shape = CircleShape,
        color = backgroundColor,
        contentColor = contentColor,
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

/**
 * Segmented control row with the Unread and Read filters.
 */
@Composable
private fun NotificationFilterRow(
    unreadCount: Int,
    selected: NotificationFilter,
    onSelected: (NotificationFilter) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        NotificationFilterChip(
            label = "Unread",
            badgeCount = unreadCount,
            selected = selected == NotificationFilter.UNREAD,
            onClick = { onSelected(NotificationFilter.UNREAD) },
            modifier = Modifier.testTag(NotificationsTestTags.TabUnread)
        )
        NotificationFilterChip(
            label = "Read",
            badgeCount = null,
            selected = selected == NotificationFilter.READ,
            onClick = { onSelected(NotificationFilter.READ) },
            modifier = Modifier.testTag(NotificationsTestTags.TabRead)
        )
    }
}

/**
 * Individual filter chip styled to match the new design system.
 */
@Composable
private fun NotificationFilterChip(
    label: String,
    badgeCount: Int?,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val background = if (selected) PluckPalette.Primary else PluckPalette.Surface
    val contentColor = if (selected) PluckPalette.Surface else PluckPalette.Primary
    Surface(
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(28.dp),
        color = background,
        contentColor = contentColor,
        tonalElevation = if (selected) 2.dp else 0.dp,
        shadowElevation = if (selected) 12.dp else 4.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = if (selected) 1f else 0.08f)),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            if (badgeCount != null && badgeCount > 0) {
                NotificationBadge(count = badgeCount, selected = selected)
            }
        }
    }
}

/**
 * Numeric badge shown inside the unread filter chip.
 */
@Composable
private fun NotificationBadge(count: Int, selected: Boolean) {
    Surface(
        shape = CircleShape,
        color = if (selected) PluckPalette.Surface else PluckPalette.Primary.copy(alpha = 0.1f),
        contentColor = if (selected) PluckPalette.Primary else PluckPalette.Primary,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        border = if (selected) BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.16f)) else null
    ) {
        Text(
            text = count.toString(),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = if (selected) PluckPalette.Primary else PluckPalette.Primary
        )
    }
}

/**
 * Sheet containing the lazy list of notification cards.
 */
@Composable
private fun NotificationsFeedPanel(
    notifications: List<NotificationItem>,
    processingNotificationIds: Set<String>,
    onEventDetails: (NotificationItem) -> Unit,
    onAccept: (NotificationItem) -> Unit,
    onDecline: (NotificationItem) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 8.dp
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            itemsIndexed(notifications, key = { _, item -> item.id }) { index, item ->
                NotificationCard(
                    index = index,
                    item = item,
                    isProcessing = processingNotificationIds.contains(item.id),
                    onEventDetails = { onEventDetails(item) },
                    onAccept = { onAccept(item) },
                    onDecline = { onDecline(item) }
                )
            }
        }
    }
}

/**
 * Stylised notification card displaying event state and CTAs.
 */
@Composable
private fun NotificationCard(
    index: Int,
    item: NotificationItem,
    isProcessing: Boolean,
    onEventDetails: () -> Unit,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = cardOffsetFor(index))
            .testTag("${NotificationsTestTags.CardPrefix}${item.id}")
    ) {
        NotificationCardBackdrop(accentColor = item.accentColor)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f),
            shape = RoundedCornerShape(28.dp),
            color = PluckPalette.Surface,
            tonalElevation = 0.dp,
            shadowElevation = 14.dp,
            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.06f))
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NotificationCardHeader(item = item)
                NotificationCardBody(item = item)
                ActionRow(
                    buttons = item.callToActionButtons,
                    isProcessing = isProcessing,
                    onEventDetails = onEventDetails,
                    onAccept = onAccept,
                    onDecline = onDecline
                )
            }
        }
    }
}

/**
 * Backdrop shapes that peek around each card, giving the jumbled depth effect.
 */
@Composable
private fun BoxScope.NotificationCardBackdrop(accentColor: Color) {
    Surface(
        modifier = Modifier
            .align(Alignment.TopStart)
            .offset(x = (-18).dp, y = (-18).dp)
            .width(120.dp)
            .height(72.dp),
        shape = RoundedCornerShape(28.dp),
        color = accentColor.copy(alpha = 0.28f),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {}
    Surface(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .offset(x = 18.dp, y = 28.dp)
            .size(70.dp),
        shape = CircleShape,
        color = PluckPalette.Primary.copy(alpha = 0.08f),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {}
}

/**
 * Header content inside a notification card (icon, title, status indicator).
 */
@Composable
private fun NotificationCardHeader(item: NotificationItem) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NotificationIcon(
            category = item.category,
            accentColor = item.accentColor
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = PluckPalette.Primary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                NotificationStatusIndicator(
                    status = item.status,
                    accentColor = item.accentColor
                )
            }
            Text(
                text = item.subtitle,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PluckPalette.Primary.copy(alpha = 0.72f)
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Status indicator shown on the right side of each card header.
 */
@Composable
private fun NotificationStatusIndicator(status: NotificationStatus, accentColor: Color) {
    if (status == NotificationStatus.UNREAD) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = accentColor,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {
            Text(
                text = "New",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }
    } else {
        Surface(
            modifier = Modifier.size(10.dp),
            shape = CircleShape,
            color = accentColor.copy(alpha = 0.35f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {}
    }
}

/**
 * Body copy area of the notification card.
 */
@Composable
private fun NotificationCardBody(item: NotificationItem) {
    Text(
        text = item.detail,
        style = MaterialTheme.typography.bodySmall.copy(
            color = PluckPalette.Muted
        )
    )
}

/**
 * Action row showing the relevant call-to-action buttons.
 */
@Composable
private fun ActionRow(
    buttons: NotificationButtons,
    isProcessing: Boolean,
    onEventDetails: () -> Unit,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    if (!buttons.showEventDetails && !buttons.showAccept && !buttons.showDecline) {
        return
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (buttons.showEventDetails) {
            NotificationActionButton(
                label = "Event Details",
                backgroundColor = PluckPalette.Primary,
                contentColor = PluckPalette.Surface,
                onClick = onEventDetails
            )
        }
        if (buttons.showAccept) {
            NotificationActionButton(
                label = "Accept",
                backgroundColor = PluckPalette.Accept,
                contentColor = PluckPalette.Surface,
                enabled = !isProcessing,
                onClick = onAccept
            )
        }
        if (buttons.showDecline) {
            NotificationActionButton(
                label = "Decline",
                backgroundColor = PluckPalette.Decline,
                contentColor = PluckPalette.Surface,
                enabled = !isProcessing,
                onClick = onDecline
            )
        }
    }
    if (isProcessing) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                strokeWidth = 2.dp
            )
        }
    }
}

/**
 * Reusable filled button used inside the action row.
 */
@Composable
private fun RowScope.NotificationActionButton(
    label: String,
    backgroundColor: Color,
    contentColor: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.weight(1f),
        enabled = enabled,
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

/**
 * Icon container displayed on the left side of each notification card.
 */
@Composable
private fun NotificationIcon(category: NotificationCategory, accentColor: Color) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = accentColor.copy(alpha = 0.18f),
        contentColor = accentColor,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        border = BorderStroke(1.dp, accentColor.copy(alpha = 0.28f))
    ) {
        Box(
            modifier = Modifier.size(56.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = category.icon(),
                contentDescription = null,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

/**
 * Maps notification categories to expressive icons.
 */
private fun NotificationCategory.icon(): ImageVector = when (this) {
    NotificationCategory.SELECTION -> Icons.Outlined.Celebration
    NotificationCategory.DEADLINE -> Icons.Outlined.HourglassBottom
    NotificationCategory.WAITLIST -> Icons.Outlined.Schedule
    NotificationCategory.NOT_SELECTED -> Icons.Outlined.MoodBad
    NotificationCategory.REPLACEMENT -> Icons.Outlined.Autorenew
}

/**
 * Determines the horizontal offset used to create the staggered card layout.
 */
private fun cardOffsetFor(index: Int): Dp = when (index % 3) {
    0 -> (-8).dp
    1 -> 0.dp
    else -> 8.dp
}

/**
 * Test tags exposed for Compose UI tests.
 */
object NotificationsTestTags {
    const val Screen = "notifications-screen"
    const val TabUnread = "notifications-tab-unread"
    const val TabRead = "notifications-tab-read"
    const val CardPrefix = "notifications-card-"
}

@Preview(showBackground = true, widthDp = 420, heightDp = 920)
@Composable
private fun NotificationsScreenPreview() {
    NotificationsScreen()
}

