package com.pluck.ui.model

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import kotlin.DeprecationLevel

enum class NotificationCategory {
    SELECTION,
    DEADLINE,
    WAITLIST,
    NOT_SELECTED,
    REPLACEMENT
}

enum class NotificationStatus {
    UNREAD,
    READ
}

enum class NotificationFilter {
    UNREAD,
    READ
}

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
    val isDeclined: Boolean = false
) {
    // Backwards-compatible constructor used by the prebuilt test APK.
    @Deprecated(
        message = "Binary compatibility shim for instrumentation APKs built before event metadata.",
        level = DeprecationLevel.HIDDEN
    )
    constructor(
        id: String,
        title: String,
        subtitle: String,
        detail: String,
        category: NotificationCategory,
        status: NotificationStatus,
        accentColor: Color,
        callToActionButtons: NotificationButtons
    ) : this(
        id = id,
        title = title,
        subtitle = subtitle,
        detail = detail,
        category = category,
        status = status,
        accentColor = accentColor,
        callToActionButtons = callToActionButtons
    )
}

@Immutable
data class NotificationButtons(
    val showEventDetails: Boolean = true,
    val showAccept: Boolean = true,
    val showDecline: Boolean = true
)

// Shared helper used by UI code and tests to apply the segmented filters.
fun Iterable<NotificationItem>.filterBy(filter: NotificationFilter): List<NotificationItem> = when (filter) {
    NotificationFilter.UNREAD -> filter { it.status == NotificationStatus.UNREAD }
    NotificationFilter.READ -> filter { it.status == NotificationStatus.READ }
}

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
        eventId = "1"
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
        eventId = "2"
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
        eventId = "3"
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

private fun NotificationCategory.defaultAccentColor(): Color = when (this) {
    NotificationCategory.SELECTION -> Color(0xFFFE2C55)
    NotificationCategory.DEADLINE -> Color(0xFF2C2C2C)
    NotificationCategory.WAITLIST -> Color(0xFF9F15A2)
    NotificationCategory.NOT_SELECTED -> Color(0xFF6C6C6C)
    NotificationCategory.REPLACEMENT -> Color(0xFF2CB67D)
}
