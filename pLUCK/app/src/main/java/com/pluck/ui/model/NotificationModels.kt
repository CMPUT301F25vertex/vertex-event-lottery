package com.pluck.ui.model

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * NotificationModels.kt
 *
 * Purpose: Consolidates notification-related enums and models used across the experience centre
 * screens. Centralising the data classes keeps UI code declarative and makes test fixtures easy to
 * generate via [previewNotifications].
 *
 * Outstanding Issues: None.
 */
/**
 * Enumerates the categories surfaced inside the notification feed.
 */
enum class NotificationCategory {
    SELECTION,
    DEADLINE,
    WAITLIST,
    NOT_SELECTED,
    REPLACEMENT
}

/**
 * Determines whether a notification is surfaced under the unread or read tab.
 */
enum class NotificationStatus {
    UNREAD,
    READ
}

/**
 * Filter applied to the notification list when toggling tabs.
 */
enum class NotificationFilter {
    UNREAD,
    READ
}

/**
 * Identifies how invite metadata is delivered to downstream flows.
 */
enum class InviteContactType {
    EMAIL,
    PHONE
}

/**
 * Immutable notification payload powering the experience centre UI.
 *
 * @property id Stable identifier used for diffing and persistence.
 * @property title Primary announcement text.
 * @property subtitle Supporting context such as event name.
 * @property detail Rich detail body shown in the card content.
 * @property category Category driving iconography and accent hues.
 * @property status Read/unread state used for segmentation.
 * @property accentColor Optional override for category tinting.
 * @property callToActionButtons Flags that drive available CTA buttons.
 * @property eventId Optional event the notification references.
 * @property isAccepted Tracks whether the entrant already accepted their spot.
 * @property isDeclined Tracks whether the entrant declined their spot.
 * @property waitlistEntryId Identifier for waitlist-linked notifications.
 * @property inviteContact Optional invite target (email or phone).
 * @property inviteType Communication channel for invites.
 * @property createdAtMillis Epoch timestamp used for ordering.
 */
@Immutable
data class NotificationItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val detail: String,
    val category: NotificationCategory,
    val status: NotificationStatus,
    val accentColor: Color = category.defaultAccentColor(),
    val callToActionButtons: NotificationButtons = NotificationButtons(),
    val eventId: String = "",
    val isAccepted: Boolean = false,
    val isDeclined: Boolean = false,
    val waitlistEntryId: String? = null,
    val inviteContact: String? = null,
    val inviteType: InviteContactType? = null,
    val createdAtMillis: Long = 0L,
    /**
     * Raw category string as stored in Firestore. This allows mapping to
     * higher-level notification preference types (e.g., ORGANIZER_UPDATE,
     * CANCELLATION) without expanding the UI-facing [NotificationCategory] enum.
     */
    val rawCategory: String = ""
)

/**
 * Flags that determine which call-to-action buttons render inside a notification card.
 *
 * @property showEventDetails Whether to present an event details button.
 * @property showAccept Whether to present an accept button.
 * @property showDecline Whether to present a decline button.
 */
@Immutable
data class NotificationButtons(
    val showEventDetails: Boolean = true,
    val showAccept: Boolean = true,
    val showDecline: Boolean = true
)

/**
 * Filters the notification collection with the current tab selection.
 *
 * @receiver Iterable collection of [NotificationItem]s.
 * @param filter Target filter (unread or read).
 * @return List restricted to the requested filter while preserving order.
 */
fun Iterable<NotificationItem>.filterBy(filter: NotificationFilter): List<NotificationItem> = when (filter) {
    NotificationFilter.UNREAD -> filter { it.status == NotificationStatus.UNREAD }
    NotificationFilter.READ -> filter { it.status == NotificationStatus.READ }
}

/**
 * Provides deterministic notification fixtures used by previews and tests.
 */
@VisibleForTesting
fun previewNotifications(): List<NotificationItem> = listOf(
    NotificationItem(
        id = "selected-1",
        title = "You were selected!",
        subtitle = "Swimming Lessons - Draw Oct 12",
        detail = "Confirm by Oct 14 at 5pm.",
        category = NotificationCategory.SELECTION,
        status = NotificationStatus.UNREAD,
        accentColor = Color(0xFFFE2C55),
        eventId = "1",
        createdAtMillis = 0L
    ),
    NotificationItem(
        id = "deadline-1",
        title = "Only 48 hours left to accept!",
        subtitle = "Cooking with Gordon - Drew Sep 12",
        detail = "Accept by Oct 9 at 12pm.",
        category = NotificationCategory.DEADLINE,
        status = NotificationStatus.UNREAD,
        accentColor = Color(0xFF2C2C2C),
        callToActionButtons = NotificationButtons(showDecline = true, showAccept = true, showEventDetails = true),
        eventId = "2",
        createdAtMillis = 0L
    ),
    NotificationItem(
        id = "waitlist-1",
        title = "You're on the Waitlist!",
        subtitle = "Darts at the Clam - Draw Oct 29",
        detail = "Waitlist closes by Oct 28 at 11pm.",
        category = NotificationCategory.WAITLIST,
        status = NotificationStatus.UNREAD,
        accentColor = Color(0xFF9F15A2),
        callToActionButtons = NotificationButtons(showEventDetails = true, showAccept = false, showDecline = false),
        eventId = "3",
        createdAtMillis = 0L
    ),
    NotificationItem(
        id = "not-selected-1",
        title = "Not selected this draw",
        subtitle = "Darts at the Clam - Draw Oct 29",
        detail = "Waitlist closes by Oct 28 at 11pm.",
        category = NotificationCategory.NOT_SELECTED,
        status = NotificationStatus.READ,
        accentColor = Color(0xFF2C2C2C),
        callToActionButtons = NotificationButtons(showEventDetails = true, showAccept = false, showDecline = false),
        eventId = "3",
        createdAtMillis = 0L
    ),
    NotificationItem(
        id = "replacement-1",
        title = "Someone rejected their spot!",
        subtitle = "Accept before Oct 29 to replace them.",
        detail = "Waitlist closes by Oct 28 at 11pm.",
        category = NotificationCategory.REPLACEMENT,
        status = NotificationStatus.READ,
        accentColor = Color(0xFF9F15A2),
        eventId = "4",
        createdAtMillis = 0L
    )
)

private fun NotificationCategory.defaultAccentColor(): Color = when (this) {
    NotificationCategory.SELECTION -> Color(0xFFFE2C55)
    NotificationCategory.DEADLINE -> Color(0xFF2C2C2C)
    NotificationCategory.WAITLIST -> Color(0xFF9F15A2)
    NotificationCategory.NOT_SELECTED -> Color(0xFF6C6C6C)
    NotificationCategory.REPLACEMENT -> Color(0xFF2CB67D)
}
