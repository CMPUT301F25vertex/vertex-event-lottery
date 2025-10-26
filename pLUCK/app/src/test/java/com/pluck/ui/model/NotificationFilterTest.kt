package com.pluck.ui.model

import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * NotificationFilterTest.kt
 *
 * Purpose: Verifies filtering behaviour for notifications.
 *
 * Design Pattern: Pure function unit tests.
 *
 * Outstanding Issues: None
 */
class NotificationFilterTest {

    private val notifications = listOf(
        NotificationItem(
            id = "one",
            title = "Unread",
            subtitle = "",
            detail = "",
            category = NotificationCategory.SELECTION,
            status = NotificationStatus.UNREAD,
            accentColor = Color.Black
        ),
        NotificationItem(
            id = "two",
            title = "Read",
            subtitle = "",
            detail = "",
            category = NotificationCategory.DEADLINE,
            status = NotificationStatus.READ,
            accentColor = Color.Black
        ),
        NotificationItem(
            id = "three",
            title = "Unread 2",
            subtitle = "",
            detail = "",
            category = NotificationCategory.WAITLIST,
            status = NotificationStatus.UNREAD,
            accentColor = Color.Black
        )
    )

    @Test
    fun `filter unread returns only unread items`() {
        val filtered = notifications.filterBy(NotificationFilter.UNREAD)

        assertEquals(listOf("one", "three"), filtered.map { it.id })
    }

    @Test
    fun `filter read returns only read items`() {
        val filtered = notifications.filterBy(NotificationFilter.READ)

        assertEquals(listOf("two"), filtered.map { it.id })
    }

    @Test
    fun `filter maintains original order`() {
        val filtered = notifications.filterBy(NotificationFilter.UNREAD)

        assertEquals(listOf("Unread", "Unread 2"), filtered.map { it.title })
    }
}
