/**
 * ThemePreferences.kt
 *
 * Purpose: Manages user theme preferences (light/dark mode and color theme)
 *
 * Design Pattern: SharedPreferences wrapper for theme settings
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.theme

import android.content.Context
import android.content.SharedPreferences

class ThemePreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_DARK_MODE = "dark_mode_enabled"
        private const val KEY_THEME_ID = "selected_theme_id"
        private const val DEFAULT_THEME_ID = "blue"

        private const val KEY_CUSTOM_THEME_ENABLED = "custom_theme_enabled"
        private const val KEY_CUSTOM_THEME_ID = "custom_theme_id"
        private const val KEY_CUSTOM_THEME_NAME = "custom_theme_name"
        private const val KEY_CUSTOM_LIGHT_BACKGROUND = "custom_light_background"
        private const val KEY_CUSTOM_LIGHT_SURFACE = "custom_light_surface"
        private const val KEY_CUSTOM_LIGHT_PRIMARY = "custom_light_primary"
        private const val KEY_CUSTOM_LIGHT_MUTED = "custom_light_muted"
        private const val KEY_CUSTOM_LIGHT_SECONDARY = "custom_light_secondary"
        private const val KEY_CUSTOM_LIGHT_TERTIARY = "custom_light_tertiary"
        private const val KEY_CUSTOM_DARK_BACKGROUND = "custom_dark_background"
        private const val KEY_CUSTOM_DARK_SURFACE = "custom_dark_surface"
        private const val KEY_CUSTOM_DARK_PRIMARY = "custom_dark_primary"
        private const val KEY_CUSTOM_DARK_MUTED = "custom_dark_muted"
        private const val KEY_CUSTOM_DARK_SECONDARY = "custom_dark_secondary"
        private const val KEY_CUSTOM_DARK_TERTIARY = "custom_dark_tertiary"
    }

    fun isDarkModeEnabled(): Boolean {
        return prefs.getBoolean(KEY_DARK_MODE, false)
    }

    fun setDarkModeEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_DARK_MODE, enabled).apply()
    }

    fun getSelectedThemeId(): String {
        return prefs.getString(KEY_THEME_ID, DEFAULT_THEME_ID) ?: DEFAULT_THEME_ID
    }

    fun setSelectedThemeId(themeId: String) {
        prefs.edit().putString(KEY_THEME_ID, themeId).apply()
    }

    fun resetToDefault() {
        prefs.edit()
            .putString(KEY_THEME_ID, DEFAULT_THEME_ID)
            .putBoolean(KEY_DARK_MODE, false)
            .apply()
    }

    fun saveCustomTheme(theme: CustomTheme) {
        prefs.edit()
            .putBoolean(KEY_CUSTOM_THEME_ENABLED, true)
            .putString(KEY_CUSTOM_THEME_ID, theme.id)
            .putString(KEY_CUSTOM_THEME_NAME, theme.name)
            .putLong(KEY_CUSTOM_LIGHT_BACKGROUND, theme.lightBackground)
            .putLong(KEY_CUSTOM_LIGHT_SURFACE, theme.lightSurface)
            .putLong(KEY_CUSTOM_LIGHT_PRIMARY, theme.lightPrimary)
            .putLong(KEY_CUSTOM_LIGHT_MUTED, theme.lightMuted)
            .putLong(KEY_CUSTOM_LIGHT_SECONDARY, theme.lightSecondary)
            .putLong(KEY_CUSTOM_LIGHT_TERTIARY, theme.lightTertiary)
            .putLong(KEY_CUSTOM_DARK_BACKGROUND, theme.darkBackground)
            .putLong(KEY_CUSTOM_DARK_SURFACE, theme.darkSurface)
            .putLong(KEY_CUSTOM_DARK_PRIMARY, theme.darkPrimary)
            .putLong(KEY_CUSTOM_DARK_MUTED, theme.darkMuted)
            .putLong(KEY_CUSTOM_DARK_SECONDARY, theme.darkSecondary)
            .putLong(KEY_CUSTOM_DARK_TERTIARY, theme.darkTertiary)
            .apply()
    }

    fun hasCustomTheme(): Boolean = prefs.getBoolean(KEY_CUSTOM_THEME_ENABLED, false)

    fun getCustomTheme(): CustomTheme? {
        if (!hasCustomTheme()) return null
        val fallback = CustomTheme.Blue
        return CustomTheme(
            id = prefs.getString(KEY_CUSTOM_THEME_ID, "custom") ?: "custom",
            name = prefs.getString(KEY_CUSTOM_THEME_NAME, "Custom Theme") ?: "Custom Theme",
            lightBackground = prefs.getLong(KEY_CUSTOM_LIGHT_BACKGROUND, fallback.lightBackground),
            lightSurface = prefs.getLong(KEY_CUSTOM_LIGHT_SURFACE, fallback.lightSurface),
            lightPrimary = prefs.getLong(KEY_CUSTOM_LIGHT_PRIMARY, fallback.lightPrimary),
            lightMuted = prefs.getLong(KEY_CUSTOM_LIGHT_MUTED, fallback.lightMuted),
            lightSecondary = prefs.getLong(KEY_CUSTOM_LIGHT_SECONDARY, fallback.lightSecondary),
            lightTertiary = prefs.getLong(KEY_CUSTOM_LIGHT_TERTIARY, fallback.lightTertiary),
            darkBackground = prefs.getLong(KEY_CUSTOM_DARK_BACKGROUND, fallback.darkBackground),
            darkSurface = prefs.getLong(KEY_CUSTOM_DARK_SURFACE, fallback.darkSurface),
            darkPrimary = prefs.getLong(KEY_CUSTOM_DARK_PRIMARY, fallback.darkPrimary),
            darkMuted = prefs.getLong(KEY_CUSTOM_DARK_MUTED, fallback.darkMuted),
            darkSecondary = prefs.getLong(KEY_CUSTOM_DARK_SECONDARY, fallback.darkSecondary),
            darkTertiary = prefs.getLong(KEY_CUSTOM_DARK_TERTIARY, fallback.darkTertiary),
            isCustom = true
        )
    }

    fun clearCustomTheme() {
        prefs.edit()
            .putBoolean(KEY_CUSTOM_THEME_ENABLED, false)
            .remove(KEY_CUSTOM_THEME_ID)
            .remove(KEY_CUSTOM_THEME_NAME)
            .remove(KEY_CUSTOM_LIGHT_BACKGROUND)
            .remove(KEY_CUSTOM_LIGHT_SURFACE)
            .remove(KEY_CUSTOM_LIGHT_PRIMARY)
            .remove(KEY_CUSTOM_LIGHT_MUTED)
            .remove(KEY_CUSTOM_LIGHT_SECONDARY)
            .remove(KEY_CUSTOM_LIGHT_TERTIARY)
            .remove(KEY_CUSTOM_DARK_BACKGROUND)
            .remove(KEY_CUSTOM_DARK_SURFACE)
            .remove(KEY_CUSTOM_DARK_PRIMARY)
            .remove(KEY_CUSTOM_DARK_MUTED)
            .remove(KEY_CUSTOM_DARK_SECONDARY)
            .remove(KEY_CUSTOM_DARK_TERTIARY)
            .apply()
    }
}
