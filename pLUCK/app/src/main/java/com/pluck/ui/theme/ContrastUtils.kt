package com.pluck.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * ContrastUtils.kt
 *
 * Purpose: Provides WCAG-compliant contrast calculations for accessible text colors
 *
 * Design Pattern: Utility functions for color accessibility
 */

/**
 * Calculate the relative luminance of a color according to WCAG
 * https://www.w3.org/TR/WCAG20/#relativeluminancedef
 */
fun Color.relativeLuminance(): Double {
    val r = red.toSrgb()
    val g = green.toSrgb()
    val b = blue.toSrgb()

    return 0.2126 * r + 0.7152 * g + 0.0722 * b
}

/**
 * Convert sRGB color component to linear RGB
 */
private fun Float.toSrgb(): Double {
    return if (this <= 0.03928) {
        this / 12.92
    } else {
        ((this + 0.055) / 1.055).pow(2.4)
    }.toDouble()
}

/**
 * Calculate the contrast ratio between two colors
 * https://www.w3.org/TR/WCAG20/#contrast-ratiodef
 *
 * @return Contrast ratio (1:1 to 21:1)
 */
fun contrastRatio(color1: Color, color2: Color): Double {
    val lum1 = color1.relativeLuminance()
    val lum2 = color2.relativeLuminance()

    val lighter = maxOf(lum1, lum2)
    val darker = minOf(lum1, lum2)

    return (lighter + 0.05) / (darker + 0.05)
}

/**
 * Automatically determine the best text color (white or black) for a background
 * based on WCAG contrast requirements
 *
 * @param backgroundColor The background color to contrast against
 * @return White or black color, whichever has better contrast
 */
fun autoTextColor(backgroundColor: Color): Color {
    val bgLuminance = backgroundColor.relativeLuminance()

    val white = Color.White
    val black = Color.Black

    val contrastWithWhite = contrastRatio(white, backgroundColor)
    val contrastWithBlack = contrastRatio(black, backgroundColor)

    return if (contrastWithWhite > contrastWithBlack) white else black
}

/**
 * Get a themed text color that is tinted toward an accent color
 * while maintaining readability
 *
 * @param backgroundColor The background to contrast against
 * @param accentColor Theme color to tint toward
 * @param tintStrength How much to tint (0.0 to 1.0), default 0.15
 * @return A tinted text color with good contrast
 */
fun themedTextColor(
    backgroundColor: Color,
    accentColor: Color,
    tintStrength: Float = 0.15f
): Color {
    val baseColor = autoTextColor(backgroundColor)

    // If the base is white, use lighter tint
    // If the base is black, use stronger tint
    val actualTintStrength = if (baseColor == Color.White) {
        tintStrength
    } else {
        tintStrength * 1.5f
    }

    return mixColors(baseColor, accentColor, actualTintStrength.coerceIn(0f, 1f))
}

/**
 * Mix two colors together
 *
 * @param color1 First color
 * @param color2 Second color
 * @param weight How much of color2 to use (0.0 to 1.0)
 * @return Blended color
 */
fun mixColors(color1: Color, color2: Color, weight: Float): Color {
    val w = weight.coerceIn(0f, 1f)

    return Color(
        red = color1.red + (color2.red - color1.red) * w,
        green = color1.green + (color2.green - color1.green) * w,
        blue = color1.blue + (color2.blue - color1.blue) * w,
        alpha = color1.alpha + (color2.alpha - color1.alpha) * w
    )
}

/**
 * Ensure a text color meets minimum contrast ratio against background
 * by adjusting brightness aggressively
 *
 * @param backgroundColor Background color
 * @param textColor Initial text color
 * @param minRatio Minimum contrast ratio (default 7.0 for WCAG AAA - high contrast)
 * @param maxIterations Maximum adjustment iterations
 * @return Adjusted text color meeting contrast requirements
 */
fun ensureContrast(
    backgroundColor: Color,
    textColor: Color,
    minRatio: Double = 7.0,
    maxIterations: Int = 30
): Color {
    var adjusted = textColor
    var ratio = contrastRatio(backgroundColor, adjusted)

    // If already meets contrast, return as-is
    if (ratio >= minRatio) return adjusted

    var iterations = 0
    val bgLuminance = backgroundColor.luminance()

    // Determine if we should lighten or darken based on background
    val shouldDarken = bgLuminance > 0.5  // Light background needs dark text

    // Be MUCH more aggressive - adjust by 15% per iteration for light backgrounds
    // For light backgrounds (like cyan/white), we need to darken significantly
    val adjustStep = if (shouldDarken) -0.15f else 0.15f

    while (ratio < minRatio && iterations < maxIterations) {
        adjusted = adjustBrightness(adjusted, adjustStep)
        ratio = contrastRatio(backgroundColor, adjusted)
        iterations++
    }

    // If still not enough contrast, force to pure black or white
    if (ratio < minRatio) {
        adjusted = if (shouldDarken) Color.Black else Color.White
    }

    return adjusted
}

/**
 * Adjust the brightness of a color
 *
 * @param color Original color
 * @param amount Amount to adjust (-1.0 to 1.0). Positive = lighter, negative = darker
 * @return Adjusted color
 */
fun adjustBrightness(color: Color, amount: Float): Color {
    val factor = 1f + amount

    return Color(
        red = (color.red * factor).coerceIn(0f, 1f),
        green = (color.green * factor).coerceIn(0f, 1f),
        blue = (color.blue * factor).coerceIn(0f, 1f),
        alpha = color.alpha
    )
}

/**
 * Check if a text color is readable on a background
 *
 * @param backgroundColor Background color
 * @param textColor Text color
 * @param level WCAG conformance level (AA = 4.5, AAA = 7.0)
 * @return true if contrast is sufficient
 */
fun isReadable(
    backgroundColor: Color,
    textColor: Color,
    level: WCAGLevel = WCAGLevel.AA
): Boolean {
    return contrastRatio(backgroundColor, textColor) >= level.ratio
}

enum class WCAGLevel(val ratio: Double) {
    AA(4.5),        // Normal text
    AA_LARGE(3.0),  // Large text (18pt+ or 14pt+ bold)
    AAA(7.0),       // Enhanced contrast
    AAA_LARGE(4.5)  // Large text enhanced
}

/**
 * Get a surface color with good contrast for the given background
 */
fun surfaceColorFor(backgroundColor: Color, isDark: Boolean): Color {
    return if (isDark) {
        // Dark mode: slightly lighter surface
        adjustBrightness(backgroundColor, 0.1f)
    } else {
        // Light mode: slightly darker or lighter based on luminance
        if (backgroundColor.luminance() > 0.5) {
            adjustBrightness(backgroundColor, -0.05f)
        } else {
            adjustBrightness(backgroundColor, 0.1f)
        }
    }
}

/**
 * Get a muted/secondary text color with reduced emphasis
 */
fun mutedTextColor(backgroundColor: Color, primaryTextColor: Color): Color {
    // Mix the primary text color with the background to create muted effect
    return mixColors(primaryTextColor, backgroundColor, 0.4f)
        .let { ensureContrast(backgroundColor, it, minRatio = 3.0) }
}
