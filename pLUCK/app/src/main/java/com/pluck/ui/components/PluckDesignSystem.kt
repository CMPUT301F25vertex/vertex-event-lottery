/**
 * PluckDesignSystem.kt
 *
 * Purpose: Core design system components and utilities for the pLUCK application.
 * Provides theme-aware color palettes, gradients, and decorative backgrounds.
 *
 * Components:
 * - PluckPalette: Theme-reactive color palette accessor
 * - PluckDesignSystem: Gradient and design utilities
 * - PluckLayeredBackground: Decorative background with floating shapes
 * - PluckAccentCircle: Accent circle component for decorative purposes
 *
 * Design Pattern: Centralized design system with composable theme-aware utilities
 *
 * Usage:
 * ```kotlin
 * // Access theme colors
 * PluckPalette.Primary
 *
 * // Use layered background
 * PluckLayeredBackground {
 *     // Content
 * }
 *
 * // Use gradient
 * PluckDesignSystem.heroGradient()
 * ```
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pluck.ui.theme.PluckThemeColors

/**
 * Theme-aware palette helper that exposes the current color values.
 * Automatically adapts to the current theme (light/dark mode).
 *
 * Example usage:
 * ```kotlin
 * Text(
 *     text = "Hello",
 *     color = PluckPalette.Primary
 * )
 * ```
 */
object PluckPalette {
    private val palette
        @Composable get() = PluckThemeColors.current

    /** Main background color for screens */
    val Background: Color
        @Composable get() = palette.background

    /** Surface color for cards and elevated components */
    val Surface: Color
        @Composable get() = palette.surface

    /** Primary brand color - used for main actions and text */
    val Primary: Color
        @Composable get() = palette.primary

    /** Muted color for secondary text and disabled states */
    val Muted: Color
        @Composable get() = palette.muted

    /** Secondary brand color - used for highlights and accents */
    val Secondary: Color
        @Composable get() = palette.secondary

    /** Tertiary brand color - used for additional variety */
    val Tertiary: Color
        @Composable get() = palette.tertiary

    /** Positive/success action color (green) */
    val Accept: Color
        @Composable get() = palette.accept

    /** Negative/error action color (red) */
    val Decline: Color
        @Composable get() = palette.decline

    /** Pink accent color from purple palette */
    val Pink: Color
        @Composable get() = palette.pink

    /** Magenta accent color from purple palette */
    val Magenta: Color
        @Composable get() = palette.magenta
}

/**
 * Collection of design system utilities for gradients and special effects.
 */
object PluckDesignSystem {
    /**
     * Creates a linear gradient from secondary to primary colors.
     * Used for hero cards, large headers, and featured content.
     *
     * @return A Brush with linear gradient from secondary to primary
     */
    @Composable
    fun heroGradient(): Brush {
        val colors = PluckThemeColors.current
        return Brush.linearGradient(
            colors = listOf(colors.secondary, colors.primary),
            start = Offset.Zero,
            end = Offset(600f, 600f)
        )
    }
}

/**
 * Decorative layered background with colorful offset blobs and bursts.
 * Creates a vibrant, playful atmosphere with multiple floating shapes in theme colors.
 *
 * Features:
 * - Top left: Large rounded rectangle blob in secondary color
 * - Top right: Medium rounded rectangle blob in tertiary color
 * - Bottom center: Large rounded rectangle blob in primary color
 * - Pink and magenta circular accents scattered throughout
 * - Additional small purple and mint circular accents
 *
 * All colors automatically adapt to the current theme (light/dark mode).
 *
 * @param modifier Modifier to apply to the background container
 * @param topLeftSize Size of the top-left decorative blob (default: 220.dp)
 * @param topRightSize Size of the top-right decorative blob (default: 160.dp)
 * @param bottomSize Size of the bottom decorative blob (default: 240.dp)
 * @param content The main content to display over the background
 *
 * Example usage:
 * ```kotlin
 * PluckLayeredBackground {
 *     Text("Content on top of decorative background")
 * }
 * ```
 */
@Composable
fun PluckLayeredBackground(
    modifier: Modifier = Modifier,
    topLeftSize: Dp = 220.dp,
    topRightSize: Dp = 160.dp,
    bottomSize: Dp = 240.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val colors = PluckThemeColors.current

    Box(
        modifier = modifier
            .background(colors.background)
    ) {
        // Top left purple blob
        Surface(
            modifier = Modifier
                .size(topLeftSize)
                .offset(x = (-topLeftSize * 0.35f), y = (-topLeftSize * 0.24f)),
            shape = RoundedCornerShape(52.dp),
            color = colors.secondary.copy(alpha = 0.24f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {}

        // Top right mint blob
        Surface(
            modifier = Modifier
                .size(topRightSize)
                .align(Alignment.TopEnd)
                .offset(x = topRightSize * 0.28f, y = (-topRightSize * 0.2f)),
            shape = RoundedCornerShape(48.dp),
            color = colors.tertiary.copy(alpha = 0.22f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {}

        // Bottom center blob
        Surface(
            modifier = Modifier
                .size(bottomSize)
                .align(Alignment.BottomCenter)
                .offset(y = bottomSize * 0.24f),
            shape = RoundedCornerShape(56.dp),
            color = colors.primary.copy(alpha = 0.05f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {}

        // Pink burst - middle right
        Surface(
            modifier = Modifier
                .size(140.dp)
                .align(Alignment.CenterEnd)
                .offset(x = 50.dp, y = (-40).dp),
            shape = CircleShape,
            color = colors.pink.copy(alpha = 0.18f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {}

        // Magenta burst - middle left
        Surface(
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterStart)
                .offset(x = (-40).dp, y = 80.dp),
            shape = CircleShape,
            color = colors.magenta.copy(alpha = 0.16f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {}

        // Small purple accent - top center
        Surface(
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.TopCenter)
                .offset(x = 60.dp, y = 140.dp),
            shape = CircleShape,
            color = colors.secondary.copy(alpha = 0.2f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {}

        // Small mint accent - bottom left
        Surface(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomStart)
                .offset(x = 40.dp, y = (-80).dp),
            shape = CircleShape,
            color = colors.tertiary.copy(alpha = 0.2f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {}

        content()
    }
}

/**
 * Creates a circular accent shape for decorative purposes.
 * Useful for adding visual interest behind buttons, icons, or other floating elements.
 *
 * @param modifier Modifier to apply to the circle (typically includes size and position)
 * @param color Optional custom color; defaults to semi-transparent primary color from theme
 *
 * Example usage:
 * ```kotlin
 * Box {
 *     PluckAccentCircle(
 *         modifier = Modifier.size(100.dp),
 *         color = PluckPalette.Secondary.copy(alpha = 0.2f)
 *     )
 *     Icon(...) // Your content on top
 * }
 * ```
 */
@Composable
fun PluckAccentCircle(
    modifier: Modifier = Modifier,
    color: Color? = null
) {
    val palette = PluckThemeColors.current
    val resolvedColor = color ?: palette.primary.copy(alpha = 0.08f)

    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = resolvedColor,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {}
}
