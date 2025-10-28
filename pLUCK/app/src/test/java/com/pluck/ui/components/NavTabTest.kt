package com.pluck.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NavTabTest {

    private val expectedOrder = listOf(
        NavTabs.Home,
        NavTabs.Notifications,
        NavTabs.Settings,
        NavTabs.Profile
    )

    @Test
    fun `bottom navigation exposes tabs in design order`() {
        assertEquals(expectedOrder, NavTabs.all)
    }

    @Test
    fun `nav tab routes remain wired to navigation graph`() {
        val routesById = NavTabs.all.associate { it.id to it.route }

        assertEquals(
            mapOf(
                "home" to "event_list",
                "notifications" to "notifications",
                "settings" to "settings",
                "profile" to "profile"
            ),
            routesById
        )
    }

    @Test
    fun `material icons mirror the visual spec`() {
        assertEquals(Icons.Default.Home, NavTabs.Home.icon)
        assertEquals(Icons.Default.Notifications, NavTabs.Notifications.icon)
        assertEquals(Icons.Default.Settings, NavTabs.Settings.icon)
        assertEquals(Icons.Default.Person, NavTabs.Profile.icon)
    }

    @Test
    fun `labels are user facing and non blank`() {
        NavTabs.all.forEach { tab ->
            assertTrue("Expected label for ${tab.id} to be non blank", tab.label.isNotBlank())
            assertTrue(
                "Expected label for ${tab.id} to start uppercase",
                tab.label.first().isUpperCase()
            )
        }
    }

    @Test
    fun `copy keeps navigation identity`() {
        val renamed = NavTabs.Home.copy(label = "Start")

        assertEquals("home", renamed.id)
        assertEquals(NavTabs.Home.route, renamed.route)
        assertEquals(Icons.Default.Home, renamed.icon)
        assertEquals("Start", renamed.label)
        assertNotEquals(NavTabs.Home, renamed)
    }
}

