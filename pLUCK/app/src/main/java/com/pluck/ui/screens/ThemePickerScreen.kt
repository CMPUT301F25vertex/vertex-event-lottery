/**
 * ThemePickerScreen.kt
 *
 * Purpose: UI screen for selecting and previewing different color themes.
 * Allows users to choose from predefined themes or create custom ones.
 *
 * Features:
 * - Display all available themes
 * - Live theme preview
 * - Theme selection and persistence
 * - Custom theme creation (future enhancement)
 *
 * Design Pattern: Stateful composable with theme preview cards
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.theme.CustomTheme

/**
 * Theme picker screen that displays all available themes.
 *
 * @param currentThemeId The ID of the currently selected theme
 * @param themes List of available themes to display
 * @param customTheme Optional custom theme slot to display/apply
 * @param onThemeSelected Callback when a theme is selected
 * @param onCreateOrEditCustomTheme Callback to open the custom theme builder
 * @param onResetToDefault Callback to reset to default theme
 * @param onBack Callback to navigate back
 * @param modifier Optional modifier for the screen
 */
@Composable
fun ThemePickerScreen(
    currentThemeId: String,
    themes: List<CustomTheme>,
    customTheme: CustomTheme?,
    onThemeSelected: (String) -> Unit,
    onCreateOrEditCustomTheme: () -> Unit,
    onResetToDefault: () -> Unit = {},
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ThemePickerHeader(
                onBack = onBack,
                onResetToDefault = onResetToDefault,
                showReset = currentThemeId != "blue"
            )

            // Theme list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(themes) { theme ->
                    ThemeCard(
                        theme = theme,
                        isSelected = theme.id == currentThemeId,
                        onClick = { onThemeSelected(theme.id) }
                    )
                }
                item {
                    CustomThemeCard(
                        customTheme = customTheme,
                        isSelected = currentThemeId == "custom",
                        onApply = { onThemeSelected("custom") },
                        onCreateOrEdit = onCreateOrEditCustomTheme
                    )
                }
            }
        }
    }
}

/**
 * Header section for the theme picker screen.
 */
@Composable
private fun ThemePickerHeader(
    onResetToDefault: () -> Unit = {},
    showReset: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Choose Your Theme",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black,
                        color = PluckPalette.Primary,
                        fontSize = 28.sp
                    )
                )
                Text(
                    text = "Select a color scheme that matches your style",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted
                    )
                )
            }
        }

        if (showReset) {
            androidx.compose.material3.OutlinedButton(
                onClick = onResetToDefault,
                shape = RoundedCornerShape(16.dp),
                colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                    contentColor = PluckPalette.Primary
                ),
                border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.3f))
            ) {
                Text(
                    text = "Reset to Default (Ocean Blue)",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

/**
 * Individual theme card showing theme preview and selection state.
 *
 * @param theme The theme to display
 * @param isSelected Whether this theme is currently selected
 * @param onClick Callback when the card is clicked
 */
@Composable
private fun ThemeCard(
    theme: CustomTheme,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        tonalElevation = if (isSelected) 4.dp else 0.dp,
        shadowElevation = if (isSelected) 12.dp else 8.dp,
        border = if (isSelected) {
            BorderStroke(2.dp, PluckPalette.Primary)
        } else {
            BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.1f))
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Theme info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = theme.name,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = PluckPalette.Primary
                        )
                    )
                    if (isSelected) {
                        Surface(
                            shape = CircleShape,
                            color = PluckPalette.Primary
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(4.dp)
                            )
                        }
                    }
                }

                // Color preview row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ColorPreviewCircle(
                        color = Color(theme.lightPrimary),
                        label = "Primary"
                    )
                    ColorPreviewCircle(
                        color = Color(theme.lightSecondary),
                        label = "Secondary"
                    )
                    ColorPreviewCircle(
                        color = Color(theme.lightTertiary),
                        label = "Tertiary"
                    )
                    ColorPreviewCircle(
                        color = Color(theme.lightBackground),
                        label = "Background"
                    )
                }
            }
        }
    }
}

/**
 * Small circular color preview.
 *
 * @param color The color to display
 * @param label Accessibility label for the color
 */
@Composable
private fun ColorPreviewCircle(
    color: Color,
    label: String
) {
    Surface(
        modifier = Modifier.size(40.dp),
        shape = CircleShape,
        color = color,
        border = BorderStroke(2.dp, PluckPalette.Primary.copy(alpha = 0.2f))
    ) {}
}
