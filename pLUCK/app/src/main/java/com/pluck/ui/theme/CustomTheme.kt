/**
 * CustomTheme.kt
 *
 * Purpose: Custom theme system allowing users to create and manage their own color schemes.
 * Provides predefined themes and support for user-created custom themes.
 *
 * Features:
 * - Predefined themes (Blue, Purple, Green, etc.)
 * - Custom theme creation with color picker
 * - Theme persistence across app sessions
 * - Real-time theme preview
 *
 * Design Pattern: Data class with serialization support for theme storage
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Represents a custom color theme for the application.
 * Contains all necessary colors for both light and dark modes.
 *
 * @property id Unique identifier for the theme
 * @property name Display name of the theme
 * @property lightBackground Background color for light mode
 * @property lightSurface Surface color for light mode
 * @property lightPrimary Primary color for light mode
 * @property lightMuted Muted color for light mode
 * @property lightSecondary Secondary color for light mode
 * @property lightTertiary Tertiary color for light mode
 * @property darkBackground Background color for dark mode
 * @property darkSurface Surface color for dark mode
 * @property darkPrimary Primary color for dark mode
 * @property darkMuted Muted color for dark mode
 * @property darkSecondary Secondary color for dark mode
 * @property darkTertiary Tertiary color for dark mode
 * @property isCustom Whether this is a user-created custom theme
 */
data class CustomTheme(
    val id: String,
    val name: String,
    val lightBackground: Long,
    val lightSurface: Long,
    val lightPrimary: Long,
    val lightMuted: Long,
    val lightSecondary: Long,
    val lightTertiary: Long,
    val darkBackground: Long,
    val darkSurface: Long,
    val darkPrimary: Long,
    val darkMuted: Long,
    val darkSecondary: Long,
    val darkTertiary: Long,
    val isCustom: Boolean = false
) {
    /**
     * Converts the theme to PluckColors for light mode.
     */
    fun toLightColors(): PluckColors {
        return PluckColors(
            background = Color(lightBackground),
            surface = Color(lightSurface),
            primary = Color(lightPrimary),
            muted = Color(lightMuted),
            secondary = Color(lightSecondary),
            tertiary = Color(lightTertiary),
            accept = Color(0xFF10CB6B),
            decline = Color(0xFFE74C3C),
            pink = Color(lightSecondary),
            magenta = Color(lightMuted),
            isDark = false
        )
    }

    /**
     * Converts the theme to PluckColors for dark mode.
     */
    fun toDarkColors(): PluckColors {
        return PluckColors(
            background = Color(darkBackground),
            surface = Color(darkSurface),
            primary = Color(darkPrimary),
            muted = Color(darkMuted),
            secondary = Color(darkSecondary),
            tertiary = Color(darkTertiary),
            accept = Color(0xFF1EDB7C),
            decline = Color(0xFFE74C3C),
            pink = Color(darkSecondary),
            magenta = Color(darkMuted),
            isDark = true
        )
    }

    companion object {
        /**
         * Predefined Blue theme (default)
         */
        val Blue = CustomTheme(
            id = "blue",
            name = "Ocean Blue",
            lightBackground = 0xFFEBF1F5,
            lightSurface = 0xFFFFFFFF,
            lightPrimary = 0xFF3B80AB,
            lightMuted = 0xFF225677,
            lightSecondary = 0xFF216A97,
            lightTertiary = 0xFF5BA3D0,
            darkBackground = 0xFF04090B,
            darkSurface = 0xFF0D1419,
            darkPrimary = 0xFFEBF1F5,
            darkMuted = 0xFF5BA3D0,
            darkSecondary = 0xFF3B80AB,
            darkTertiary = 0xFF216A97
        )

        /**
         * Predefined Purple theme
         */
        val Purple = CustomTheme(
            id = "purple",
            name = "Royal Purple",
            lightBackground = 0xFFD6C1D6,
            lightSurface = 0xFFFFFFFF,
            lightPrimary = 0xFF7E0297,
            lightMuted = 0xFF5A0070,
            lightSecondary = 0xFFA43AB0,
            lightTertiary = 0xFFB65CBD,
            darkBackground = 0xFF1A111C,
            darkSurface = 0xFF260F2B,
            darkPrimary = 0xFFB15AB8,
            darkMuted = 0xFF6E0583,
            darkSecondary = 0xFF9D38A8,
            darkTertiary = 0xFF851B95
        )

        /**
         * Predefined Green theme
         */
        val Green = CustomTheme(
            id = "green",
            name = "Forest Green",
            lightBackground = 0xFFE8F5E9,
            lightSurface = 0xFFFFFFFF,
            lightPrimary = 0xFF2E7D32,
            lightMuted = 0xFF1B5E20,
            lightSecondary = 0xFF388E3C,
            lightTertiary = 0xFF66BB6A,
            darkBackground = 0xFF0A1F0C,
            darkSurface = 0xFF1B3F1E,
            darkPrimary = 0xFFA5D6A7,
            darkMuted = 0xFF66BB6A,
            darkSecondary = 0xFF4CAF50,
            darkTertiary = 0xFF2E7D32
        )

        /**
         * Predefined Orange theme
         */
        val Orange = CustomTheme(
            id = "orange",
            name = "Sunset Orange",
            lightBackground = 0xFFFFF3E0,
            lightSurface = 0xFFFFFFFF,
            lightPrimary = 0xFFE65100,
            lightMuted = 0xFFBF360C,
            lightSecondary = 0xFFFF6F00,
            lightTertiary = 0xFFFF9800,
            darkBackground = 0xFF1A0F00,
            darkSurface = 0xFF2E1B00,
            darkPrimary = 0xFFFFCC80,
            darkMuted = 0xFFFFB74D,
            darkSecondary = 0xFFFF9800,
            darkTertiary = 0xFFF57C00
        )

        /**
         * Predefined Red theme
         */
        val Red = CustomTheme(
            id = "red",
            name = "Ruby Red",
            lightBackground = 0xFFFFEBEE,
            lightSurface = 0xFFFFFFFF,
            lightPrimary = 0xFFC62828,
            lightMuted = 0xFFB71C1C,
            lightSecondary = 0xFFD32F2F,
            lightTertiary = 0xFFE57373,
            darkBackground = 0xFF1A0A0A,
            darkSurface = 0xFF2E1414,
            darkPrimary = 0xFFEF9A9A,
            darkMuted = 0xFFE57373,
            darkSecondary = 0xFFEF5350,
            darkTertiary = 0xFFC62828
        )

        /**
         * List of all predefined themes
         */
        val predefinedThemes = listOf(Blue, Purple, Green, Orange, Red)
    }
}
