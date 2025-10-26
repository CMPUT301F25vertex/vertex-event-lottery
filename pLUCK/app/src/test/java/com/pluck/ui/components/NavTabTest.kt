/**
 * NavTabTest.kt
 *
 * Purpose: Unit tests for the NavTab data class and NavTabs object.
 * Verifies navigation tab structure, properties, and configuration.
 *
 * Test Coverage:
 * - NavTab data class properties
 * - NavTabs object configuration
 * - Tab list completeness and order
 */
package com.pluck.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Test class for NavTab and NavTabs.
 */
class NavTabTest {

    /**
     * Test that NavTab data class correctly stores all properties.
     */
    @Test
    fun navTab_storesPropertiesCorrectly() {
        val tab = NavTab(
            id = "test_id",
            label = "Test Label",
            icon = Icons.Default.Home,
            route = "test_route"
        )

        assertEquals("test_id", tab.id)
        assertEquals("Test Label", tab.label)
        assertEquals(Icons.Default.Home, tab.icon)
        assertEquals("test_route", tab.route)
    }

    /**
     * Test that all NavTabs are properly configured.
     */
    @Test
    fun navTabs_allTabsConfigured() {
        assertNotNull("Home tab should be configured", NavTabs.Home)
        assertNotNull("Notifications tab should be configured", NavTabs.Notifications)
        assertNotNull("Settings tab should be configured", NavTabs.Settings)
        assertNotNull("Profile tab should be configured", NavTabs.Profile)
    }

    /**
     * Test that Home tab has correct configuration.
     */
    @Test
    fun navTabs_homeTabConfiguration() {
        val home = NavTabs.Home
        assertEquals("home", home.id)
        assertEquals("Home", home.label)
        assertEquals("event_list", home.route)
    }

    /**
     * Test that Notifications tab has correct configuration.
     */
    @Test
    fun navTabs_notificationsTabConfiguration() {
        val notifications = NavTabs.Notifications
        assertEquals("notifications", notifications.id)
        assertEquals("Notifications", notifications.label)
        assertEquals("notifications", notifications.route)
    }

    /**
     * Test that Settings tab has correct configuration.
     */
    @Test
    fun navTabs_settingsTabConfiguration() {
        val settings = NavTabs.Settings
        assertEquals("settings", settings.id)
        assertEquals("Settings", settings.label)
        assertEquals("settings", settings.route)
    }

    /**
     * Test that Profile tab has correct configuration.
     */
    @Test
    fun navTabs_profileTabConfiguration() {
        val profile = NavTabs.Profile
        assertEquals("profile", profile.id)
        assertEquals("Profile", profile.label)
        assertEquals("profile", profile.route)
    }

    /**
     * Test that all tabs list contains exactly 5 tabs.
     */
    @Test
    fun navTabs_allListContainsFiveTabs() {
        assertEquals("Should have 4 tabs", 4, NavTabs.all.size)
    }

    /**
     * Test that all tabs are in the correct order.
     */
    @Test
    fun navTabs_allListInCorrectOrder() {
        val tabs = NavTabs.all
        assertEquals("First tab should be Home", NavTabs.Home, tabs[0])
        assertEquals("Second tab should be Notifications", NavTabs.Notifications, tabs[1])
        assertEquals("Third tab should be Settings", NavTabs.Settings, tabs[2])
        assertEquals("Fourth tab should be Profile", NavTabs.Profile, tabs[3])
    }

    /**
     * Test that all tab IDs are unique.
     */
    @Test
    fun navTabs_allIdsAreUnique() {
        val ids = NavTabs.all.map { it.id }
        val uniqueIds = ids.toSet()
        assertEquals(
            "All tab IDs should be unique",
            ids.size,
            uniqueIds.size
        )
    }

    /**
     * Test that all tab labels are non-empty.
     */
    @Test
    fun navTabs_allLabelsAreNonEmpty() {
        NavTabs.all.forEach { tab ->
            assertTrue(
                "Tab ${tab.id} should have non-empty label",
                tab.label.isNotBlank()
            )
        }
    }

    /**
     * Test that all tab routes are non-empty.
     */
    @Test
    fun navTabs_allRoutesAreNonEmpty() {
        NavTabs.all.forEach { tab ->
            assertTrue(
                "Tab ${tab.id} should have non-empty route",
                tab.route.isNotBlank()
            )
        }
    }

    /**
     * Test that NavTab equality works correctly (data class).
     */
    @Test
    fun navTab_equalityWorks() {
        val tab1 = NavTab("id1", "Label", Icons.Default.Home, "route1")
        val tab2 = NavTab("id1", "Label", Icons.Default.Home, "route1")
        val tab3 = NavTab("id2", "Label", Icons.Default.Home, "route1")

        assertEquals("Same properties should be equal", tab1, tab2)
        assertTrue("Different IDs should not be equal", tab1 != tab3)
    }

    /**
     * Test that NavTab copy function works correctly.
     */
    @Test
    fun navTab_copyWorks() {
        val original = NavTab("id", "Label", Icons.Default.Home, "route")
        val copied = original.copy(label = "New Label")

        assertEquals("ID should remain same", original.id, copied.id)
        assertEquals("Label should be updated", "New Label", copied.label)
        assertEquals("Icon should remain same", original.icon, copied.icon)
        assertEquals("Route should remain same", original.route, copied.route)
    }
}
