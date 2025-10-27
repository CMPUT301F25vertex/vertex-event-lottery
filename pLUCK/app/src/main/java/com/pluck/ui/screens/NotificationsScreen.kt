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
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.model.NotificationButtons
import com.pluck.ui.model.NotificationCategory
import com.pluck.ui.model.NotificationFilter
import com.pluck.ui.model.NotificationItem
import com.pluck.ui.model.NotificationStatus
import com.pluck.ui.model.filterBy
import com.pluck.ui.model.previewNotifications
import com.pluck.ui.theme.autoTextColor

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
    onDecline: (NotificationItem) -> Unit = {},
    onProfileClick: () -> Unit = {}
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
        onDecline = onDecline,
        onProfileClick = onProfileClick
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
    onDecline: (NotificationItem) -> Unit,
    onProfileClick: () -> Unit
) {
    val listState = rememberLazyListState()
    val heroCollapseProgress by remember {
        derivedStateOf {
            when {
                listState.firstVisibleItemIndex > 0 -> 1f
                else -> (listState.firstVisibleItemScrollOffset / 250f).coerceIn(0f, 1f)
            }
        }
    }

    PluckLayeredBackground(
        modifier = modifier
            .fillMaxSize()
            .testTag(NotificationsTestTags.Screen)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item { Spacer(modifier = Modifier.height(12.dp)) }
                item {
                    NotificationsHero(
                        unreadCount = unreadCount,
                        selectedFilter = selectedFilter,
                        onFilterSelected = onFilterSelected,
                        collapseProgress = heroCollapseProgress,
                        onProfileClick = onProfileClick
                    )
                }
                if (notifications.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Nothing to review right now.",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = PluckPalette.Primary
                                )
                            )
                            Text(
                                text = "We'll let you know when something changes.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = PluckPalette.Muted
                                ),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                } else {
                    itemsIndexed(notifications, key = { _, item -> item.id }) { index, item ->
                        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
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

            if (isLoading) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
    }
}

/**
 * Hero section housing the title, action icons, and filter chips.
 */
@Composable
/**
 * Displays the notification hero card, including summary text, action pills, and the filter chips.
 */
private fun NotificationsHero(
    unreadCount: Int,
    selectedFilter: NotificationFilter,
    onFilterSelected: (NotificationFilter) -> Unit,
    collapseProgress: Float,
    onProfileClick: () -> Unit
) {
    val targetHeightFactor = (1f - collapseProgress * 0.4f)
    val targetAlpha = (1f - collapseProgress).coerceIn(0f, 1f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height((150 * targetHeightFactor).dp)
                .zIndex(1f),
            shape = RoundedCornerShape(32.dp),
            color = PluckPalette.Surface.copy(alpha = targetAlpha),
            tonalElevation = 0.dp,
            shadowElevation = (16.dp * (1f - collapseProgress * 0.7f)),
            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f * targetAlpha))
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 22.dp,
                    vertical = (20 * targetHeightFactor).dp
                ),
                verticalArrangement = Arrangement.spacedBy((18 * targetHeightFactor).dp)
            ) {
                NotificationsHeaderRow(
                    unreadCount = unreadCount,
                    collapseProgress = collapseProgress,
                    targetAlpha = targetAlpha,
                    onProfileClick = onProfileClick
                )
                if (collapseProgress < 0.9f) {
                    NotificationFilterRow(
                        unreadCount = unreadCount,
                        selected = selectedFilter,
                        onSelected = onFilterSelected,
                        alpha = targetAlpha
                    )
                }
            }
        }
    }
}

/**
 * Header row inside the hero card showing the title and floating icons.
 */
@Composable
private fun NotificationsHeaderRow(
    unreadCount: Int,
    collapseProgress: Float,
    targetAlpha: Float,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy((8 * (1f - collapseProgress * 0.5f)).dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Black,
                    color = PluckPalette.Primary.copy(alpha = targetAlpha),
                    fontSize = (28 - collapseProgress * 8).sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (collapseProgress < 0.8f) {
                Text(
                    text = if (unreadCount > 0) "$unreadCount new updates" else "You're in the loop",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted.copy(alpha = targetAlpha)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        if (collapseProgress < 0.7f) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                FloatingIconButton(
                    icon = Icons.Outlined.Notifications,
                    backgroundColor = PluckPalette.Surface.copy(alpha = targetAlpha),
                    contentColor = PluckPalette.Primary.copy(alpha = targetAlpha),
                    borderColor = PluckPalette.Primary.copy(alpha = 0.08f * targetAlpha)
                )
                FloatingIconButton(
                    icon = Icons.Outlined.Person,
                    backgroundColor = PluckPalette.Secondary.copy(alpha = targetAlpha),
                    contentColor = autoTextColor(PluckPalette.Secondary).copy(alpha = targetAlpha),
                    onClick = onProfileClick
                )
            }
        }
    }
}

/**
 * Small utility component that renders a floating circular icon.
 */
@OptIn(ExperimentalMaterial3Api::class)
/** Rounded quick-action button used inside the hero card. */
@Composable
private fun FloatingIconButton(
    icon: ImageVector,
    backgroundColor: Color,
    contentColor: Color,
    borderColor: Color = Color.Transparent,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.size(56.dp),
        shape = CircleShape,
        color = backgroundColor,
        contentColor = contentColor,
        tonalElevation = 0.dp,
        shadowElevation = 10.dp,
        border = if (borderColor == Color.Transparent) null else BorderStroke(1.dp, borderColor),
        onClick = onClick
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
/** Filter chip row for toggling between unread and read notifications. */
private fun NotificationFilterRow(
    unreadCount: Int,
    selected: NotificationFilter,
    onSelected: (NotificationFilter) -> Unit,
    alpha: Float = 1f
) {
    val surfaceVariant = MaterialTheme.colorScheme.surfaceVariant
    val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant
    val primary = MaterialTheme.colorScheme.primary
    val onPrimary = MaterialTheme.colorScheme.onPrimary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(alpha),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FilterChip(
            selected = selected == NotificationFilter.UNREAD,
            onClick = { onSelected(NotificationFilter.UNREAD) },
            modifier = Modifier
                .testTag(NotificationsTestTags.TabUnread)
                .semantics { text = AnnotatedString("Unread") },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Unread",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    if (unreadCount > 0) {
                        Text(
                            text = " (${unreadCount})",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            },
            colors = FilterChipDefaults.filterChipColors(
                containerColor = surfaceVariant,
                labelColor = onSurfaceVariant,
                selectedContainerColor = primary,
                selectedLabelColor = onPrimary
            )
        )

        FilterChip(
            selected = selected == NotificationFilter.READ,
            onClick = { onSelected(NotificationFilter.READ) },
            modifier = Modifier
                .testTag(NotificationsTestTags.TabRead)
                .semantics { text = AnnotatedString("Read") },
            label = {
                Text(
                    text = "Read",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                containerColor = surfaceVariant,
                labelColor = onSurfaceVariant,
                selectedContainerColor = primary,
                selectedLabelColor = onPrimary
            )
        )
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
                    color = autoTextColor(accentColor)
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
                contentColor = autoTextColor(PluckPalette.Primary),
                enabled = !isProcessing,
                onClick = onEventDetails
            )
        }
        if (buttons.showAccept) {
            NotificationActionButton(
                label = "Accept",
                backgroundColor = PluckPalette.Accept,
                contentColor = autoTextColor(PluckPalette.Accept),
                enabled = !isProcessing,
                onClick = onAccept
            )
        }
        if (buttons.showDecline) {
            NotificationActionButton(
                label = "Decline",
                backgroundColor = PluckPalette.Decline,
                contentColor = autoTextColor(PluckPalette.Decline),
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
