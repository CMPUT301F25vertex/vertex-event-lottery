/**
 * Type.kt
 *
 * Purpose: Typography definitions for the pLUCK application
 *
 * Design Pattern: Material Design 3 typography system with custom scale
 *
 * Typography Scale:
 * - displayMedium: Large headers (28sp, Bold) - Used for screen titles
 * - titleLarge: Section headers (22sp, SemiBold) - Used for card titles
 * - titleMedium: Subsection headers (18sp, Medium) - Used for list items
 * - bodyLarge: Primary content text (16sp, Normal) - Used for main content
 * - bodyMedium: Secondary content text (14sp, Normal) - Used for descriptions
 * - labelLarge: Button text (14sp, SemiBold) - Used for action buttons
 *
 * Font Family: System SansSerif for maximum compatibility and readability
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Material Design 3 Typography configuration for the pLUCK application
 *
 * Defines the type scale used throughout the app with consistent sizing,
 * weights, and spacing.
 */
val PLuckTypography = Typography(
    displayMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
)
