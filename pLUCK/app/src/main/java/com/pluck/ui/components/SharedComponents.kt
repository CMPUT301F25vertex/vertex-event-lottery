/**
 * SharedComponents.kt
 *
 * Purpose: Centralized library of reusable UI components used across multiple screens.
 * Extracts common patterns to reduce code duplication and improve maintainability.
 *
 * Design Pattern: Component Library
 *
 * Outstanding Issues: None
 *
 * Components Included:
 * - HeroCard: Header card with title, subtitle, and icon
 * - EmptyStateCard: Empty state display with icon, title, and description
 * - LoadingStateCard: Centered loading indicator
 * - StatusBadge: Status indicator with icon and label
 */
package com.pluck.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pluck.ui.theme.PluckThemeColors

/**
 * Reusable hero card component displayed at the top of screens.
 *
 * Features:
 * - Gradient background with design system colors
 * - Icon in circular container
 * - Title and optional subtitle
 * - Consistent styling across all screens
 *
 * @param title Primary heading text
 * @param subtitle Optional secondary text below title
 * @param icon Vector icon to display
 * @param iconTint Color for the icon (default: white)
 * @param modifier Optional Modifier for customization
 */
@Composable
fun HeroCard(
    title: String,
    subtitle: String? = null,
    icon: ImageVector,
    iconTint: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PluckDesignSystem.heroGradient())
                .padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Icon circle
                Box(
                    modifier = Modifier
                        .size(64.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Text content
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            if (subtitle != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
            }
        }
    }
}

/**
 * Reusable empty state component for screens with no content.
 *
 * Displays centered icon, title, and description when data is empty.
 * Provides consistent empty state UX across the application.
 *
 * @param icon Vector icon to display
 * @param title Primary message
 * @param description Secondary explanatory text
 * @param iconTint Color for the icon (default: onSurfaceVariant)
 * @param modifier Optional Modifier for customization
 */
@Composable
fun EmptyStateCard(
    icon: ImageVector,
    title: String,
    description: String,
    iconTint: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon in circular container
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Reusable loading state component.
 *
 * Displays centered circular progress indicator with consistent styling.
 *
 * @param message Optional loading message to display below indicator
 * @param modifier Optional Modifier for customization
 */
@Composable
fun LoadingStateCard(
    message: String? = null,
    modifier: Modifier = Modifier
) {
    val palette = PluckThemeColors.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = palette.secondary,
            strokeWidth = 4.dp,
            modifier = Modifier.size(48.dp)
        )

        if (message != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Reusable status badge component.
 *
 * Displays status with icon and label in a rounded badge.
 * Used for event status, notification types, etc.
 *
 * @param label Status text to display
 * @param icon Optional vector icon
 * @param backgroundColor Background color of the badge
 * @param textColor Text color (default: onPrimary)
 * @param modifier Optional Modifier for customization
 */
@Composable
fun StatusBadge(
    label: String,
    icon: ImageVector? = null,
    backgroundColor: Color,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = textColor,
            fontSize = 11.sp
        )
    }
}

/**
 * Constants for component styling consistency.
 */
object ComponentConstants {
    /** Standard hero card height */
    val HeroCardHeight: Dp = 120.dp

    /** Standard empty state padding */
    val EmptyStatePadding: Dp = 48.dp

    /** Standard loading indicator size */
    val LoadingIndicatorSize: Dp = 48.dp

    /** Standard badge corner radius */
    val BadgeCornerRadius: Dp = 16.dp

    /** Standard card corner radius */
    val CardCornerRadius: Dp = 28.dp

    /** Standard card elevation */
    val CardElevation: Dp = 2.dp
}
