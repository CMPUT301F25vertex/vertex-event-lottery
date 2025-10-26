/**
 * PluckTheme.kt
 *
 * Purpose: Complete theme system with light and dark mode support for the pLUCK application.
 * Provides a comprehensive color palette based on purple (#7E0297) theme with both light and dark variants.
 *
 * Design Pattern: Material Design 3 theming with custom color schemes
 *
 * Color Palette:
 * - Light Mode: Features a soft purple background (#D6C1D6) with deep purple primary (#7E0297)
 * - Dark Mode: Features a very dark purple background (#1A111C) with lighter purple accents
 *
 * Usage:
 * ```kotlin
 * PluckTheme {
 *     // Your composable content
 * }
 * ```
 *
 * Accessing Colors:
 * ```kotlin
 * PluckPalette.Primary  // Reactive to current theme
 * PluckThemeColors.current.primary  // Alternative access
 * ```
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Light mode color palette
 * Based on blue theme with high contrast
 */
object LightPluckPalette {
    val Background = Color(0xFFEBF1F5)  // Light blue-gray background
    val Surface = Color(0xFFFFFFFF)     // White surface
    val Primary = Color(0xFF3B80AB)     // Primary blue
    val Muted = Color(0xFF225677)       // Muted dark blue
    val Secondary = Color(0xFF216A97)   // Secondary accent blue
    val Tertiary = Color(0xFF5BA3D0)    // Tertiary lighter blue
    val Accept = Color(0xFF10CB6B)      // Success green
    val Decline = Color(0xFFE74C3C)     // Error red
    val Pink = Color(0xFF6DB3E8)        // Light blue accent
    val Magenta = Color(0xFF2E5F7E)     // Deep blue
}

/**
 * Dark mode color palette
 * Based on blue theme with high contrast
 */
object DarkPluckPalette {
    val Background = Color(0xFF04090B)  // Very dark blue-black background
    val Surface = Color(0xFF0D1419)     // Dark blue surface
    val Primary = Color(0xFFEBF1F5)     // Light blue-gray for text
    val Muted = Color(0xFF5BA3D0)       // Muted light blue
    val Secondary = Color(0xFF3B80AB)   // Secondary blue
    val Tertiary = Color(0xFF216A97)    // Tertiary blue
    val Accept = Color(0xFF1EDB7C)      // Success green
    val Decline = Color(0xFFE74C3C)     // Error red
    val Pink = Color(0xFF6DB3E8)        // Light blue accent
    val Magenta = Color(0xFF225677)     // Deep blue
}

/**
 * Theme colors accessible via LocalPluckColors
 */
data class PluckColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val muted: Color,
    val secondary: Color,
    val tertiary: Color,
    val accept: Color,
    val decline: Color,
    val pink: Color,
    val magenta: Color,
    val isDark: Boolean
)

val LocalPluckColors = staticCompositionLocalOf {
    PluckColors(
        background = LightPluckPalette.Background,
        surface = LightPluckPalette.Surface,
        primary = LightPluckPalette.Primary,
        muted = LightPluckPalette.Muted,
        secondary = LightPluckPalette.Secondary,
        tertiary = LightPluckPalette.Tertiary,
        accept = LightPluckPalette.Accept,
        decline = LightPluckPalette.Decline,
        pink = LightPluckPalette.Pink,
        magenta = LightPluckPalette.Magenta,
        isDark = false
    )
}

private val LightColorScheme = lightColorScheme(
    primary = LightPluckPalette.Primary,
    secondary = LightPluckPalette.Secondary,
    tertiary = LightPluckPalette.Tertiary,
    background = LightPluckPalette.Background,
    surface = LightPluckPalette.Surface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = LightPluckPalette.Primary,
    onSurface = LightPluckPalette.Primary
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPluckPalette.Primary,
    secondary = DarkPluckPalette.Secondary,
    tertiary = DarkPluckPalette.Tertiary,
    background = DarkPluckPalette.Background,
    surface = DarkPluckPalette.Surface,
    onPrimary = DarkPluckPalette.Background,
    onSecondary = DarkPluckPalette.Background,
    onTertiary = DarkPluckPalette.Background,
    onBackground = DarkPluckPalette.Primary,
    onSurface = DarkPluckPalette.Primary
)

@Composable
fun PluckTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeId: String = "blue",
    content: @Composable () -> Unit
) {
    // Get the selected theme
    val customTheme = ThemeManager.getThemeById(themeId)

    // Convert to PluckColors based on dark mode
    val pluckColors = if (darkTheme) {
        customTheme.toDarkColors()
    } else {
        customTheme.toLightColors()
    }

    // Create Material color scheme from custom theme
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = pluckColors.primary,
            secondary = pluckColors.secondary,
            tertiary = pluckColors.tertiary,
            background = pluckColors.background,
            surface = pluckColors.surface,
            onPrimary = pluckColors.background,
            onSecondary = pluckColors.background,
            onTertiary = pluckColors.background,
            onBackground = pluckColors.primary,
            onSurface = pluckColors.primary
        )
    } else {
        lightColorScheme(
            primary = pluckColors.primary,
            secondary = pluckColors.secondary,
            tertiary = pluckColors.tertiary,
            background = pluckColors.background,
            surface = pluckColors.surface,
            onPrimary = Color.White,
            onSecondary = Color.White,
            onTertiary = Color.White,
            onBackground = pluckColors.primary,
            onSurface = pluckColors.primary
        )
    }

    CompositionLocalProvider(LocalPluckColors provides pluckColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

/**
 * Easy access to theme colors
 */
object PluckThemeColors {
    val current: PluckColors
        @Composable
        get() = LocalPluckColors.current
}
