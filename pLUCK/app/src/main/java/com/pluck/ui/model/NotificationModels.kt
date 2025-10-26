package com.pluck.ui.model

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * NotificationModels.kt
 *
 * Purpose: Data models and helpers for the notifications experience.
 *
 * Design Pattern: Immutable data + pure helper functions for easy testing.
 *
 * Outstanding Issues: None
 */

/**
 * High-level category used to select icons/colours for a notification card.
 */
enum class NotificationCategory {
    SELECTION,
    DEADLINE,
    WAITLIST,
    NOT_SELECTED,
    REPLACEMENT
}

/**
 * Notification read status. Used by the segmented control to filter the list.
 */
enum class NotificationStatus {
    UNREAD,
    READ
}

/**
 * Segmented control filter value.
 */
enum class NotificationFilter {
    UNREAD,
    READ
}

/**
 * Immutable notification item shown on the Notifications screen.
 *
 * @property id stable identifier used for test tags and diffing
 * @property title bold headline (e.g., "You were selected!")
 * @property subtitle supporting content with event name
 * @property detail final line of supporting text (usually deadlines)
 * @property category determines accent colour/icon
 * @property status read state used for filtering
 * @property accentColor explicit override for icon background
 * @property callToActionButtons actions shown on the right side
 * @property eventId associated event ID for navigation and actions
 * @property isAccepted whether the entrant has accepted the invitation
 * @property isDeclined whether the entrant has declined the invitation
 */
@Immutable
data class NotificationItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val detail: String,
    val category: NotificationCategory,
    val status: NotificationStatus,
    val accentColor: Color,
    val callToActionButtons: NotificationButtons = NotificationButtons(),
    val eventId: String = "",
    val isAccepted: Boolean = false,
    val isDeclined: Boolean = false
)

/**
 * Determines which call-to-action buttons are shown.
 */
@Immutable
data class NotificationButtons(
    val showEventDetails: Boolean = true,
    val showAccept: Boolean = true,
    val showDecline: Boolean = true
)

/**
 * Pure helper used by both UI and unit tests to filter notifications by status.
 */
fun Iterable<NotificationItem>.filterBy(filter: NotificationFilter): List<NotificationItem> = when (filter) {
    NotificationFilter.UNREAD -> filter { it.status == NotificationStatus.UNREAD }
    NotificationFilter.READ -> filter { it.status == NotificationStatus.READ }
}

/**
 * Sample notifications used by previews/tests when a repository is not yet wired up.
 */
@VisibleForTesting
fun previewNotifications(): List<NotificationItem> = listOf(
    NotificationItem(
        id = "selected-1",
        title = "You were selected!",
        subtitle = "Swimming Lessons • Draw Oct 12",
        detail = "Confirm by Oct 14 at 5pm.",
        category = NotificationCategory.SELECTION,
        status = NotificationStatus.UNREAD,
        accentColor = Color(0xFFFE2C55),
        eventId = "1"
    ),
    NotificationItem(
        id = "deadline-1",
        title = "Only 48 hours left to accept!",
        subtitle = "Cooking with Gordon • Drew Sep 12",
        detail = "Accept by Oct 9 at 12pm.",
        category = NotificationCategory.DEADLINE,
        status = NotificationStatus.UNREAD,
        accentColor = Color(0xFF2C2C2C),
        callToActionButtons = NotificationButtons(showDecline = true, showAccept = true, showEventDetails = true),
        eventId = "2"
    ),
    NotificationItem(
        id = "waitlist-1",
        title = "You're on the Waitlist!",
        subtitle = "Darts at the Clam • Draw Oct 29",
        detail = "Waitlist closes by Oct 28 at 11pm.",
        category = NotificationCategory.WAITLIST,
        status = NotificationStatus.UNREAD,
        accentColor = Color(0xFF9F15A2),
        callToActionButtons = NotificationButtons(showEventDetails = true, showAccept = false, showDecline = false),
        eventId = "3"
    ),
    NotificationItem(
        id = "not-selected-1",
        title = "Not selected this draw",
        subtitle = "Darts at the Clam • Draw Oct 29",
        detail = "Waitlist closes by Oct 28 at 11pm.",
        category = NotificationCategory.NOT_SELECTED,
        status = NotificationStatus.READ,
        accentColor = Color(0xFF2C2C2C),
        callToActionButtons = NotificationButtons(showEventDetails = true, showAccept = false, showDecline = false),
        eventId = "3"
    ),
    NotificationItem(
        id = "replacement-1",
        title = "Someone rejected their spot!",
        subtitle = "Accept before Oct 29 to replace them.",
        detail = "Waitlist closes by Oct 28 at 11pm.",
        category = NotificationCategory.REPLACEMENT,
        status = NotificationStatus.READ,
        accentColor = Color(0xFF9F15A2),
        eventId = "4"
    )
)
