package com.pluck.ui.model

import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NotificationFilterTest {

    private val unreadSelection = NotificationItem(
        id = "unread-selection",
        title = "You've been selected!",
        subtitle = "Community Yoga",
        detail = "Confirm by Friday at noon.",
        category = NotificationCategory.SELECTION,
        status = NotificationStatus.UNREAD,
        accentColor = Color(0xFFFF4081),
        callToActionButtons = NotificationButtons(showAccept = true, showDecline = true, showEventDetails = true)
    )

    private val readDigest = NotificationItem(
        id = "read-digest",
        title = "Lottery results posted",
        subtitle = "Cooking with Gordon",
        detail = "Thanks for entering! Check back next week.",
        category = NotificationCategory.NOT_SELECTED,
        status = NotificationStatus.READ,
        accentColor = Color(0xFF444444),
        callToActionButtons = NotificationButtons(showAccept = false, showDecline = false, showEventDetails = true)
    )

    private val unreadWaitlist = NotificationItem(
        id = "unread-waitlist",
        title = "You're #2 on the waitlist",
        subtitle = "Street Photography Walk",
        detail = "We'll notify you if a spot opens.",
        category = NotificationCategory.WAITLIST,
        status = NotificationStatus.UNREAD
    )

    private val sample = listOf(unreadSelection, readDigest, unreadWaitlist)

    @Test
    fun `unread filter returns only unread notifications and preserves order`() {
        val filtered = sample.filterBy(NotificationFilter.UNREAD)

        assertEquals(listOf(unreadSelection, unreadWaitlist), filtered)
        assertTrue(filtered.all { it.status == NotificationStatus.UNREAD })
    }

    @Test
    fun `read filter emits only read notifications`() {
        val filtered = sample.filterBy(NotificationFilter.READ)

        assertEquals(listOf(readDigest), filtered)
        assertTrue(filtered.all { it.status == NotificationStatus.READ })
    }

    @Test
    fun `filter ignores auxiliary metadata when matching`() {
        val enriched = unreadSelection.copy(
            callToActionButtons = NotificationButtons(showAccept = false, showDecline = false, showEventDetails = true),
            isAccepted = true,
            eventId = "event-123"
        )

        val filtered = listOf(enriched).filterBy(NotificationFilter.UNREAD)

        assertEquals(listOf(enriched), filtered)
    }
}

