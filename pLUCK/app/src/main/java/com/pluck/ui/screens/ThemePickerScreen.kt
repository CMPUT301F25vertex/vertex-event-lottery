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
 * - Custom theme slot with builder entry point
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
import com.pluck.ui.components.AutoHidingBarScroller
import com.pluck.ui.components.BackButton
import com.pluck.ui.components.ComposableItem
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
    val listElements = mutableListOf<ComposableItem>()

    listElements.add(ComposableItem {
        ThemePickerHeader(
            onBack = onBack
        )
    })

    if (currentThemeId != "blue") {
        listElements.add(ComposableItem {
            OutlinedButton(
                onClick = onResetToDefault,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = PluckPalette.Primary
                ),
                border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Reset to Default (Ocean Blue)",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        })
    }

    for (theme in themes) {
        listElements.add(ComposableItem {
            ThemeCard(
                theme = theme,
                isSelected = theme.id == currentThemeId,
                onClick = { onThemeSelected(theme.id) }
            )
        })
    }

    listElements.add(ComposableItem {
        CustomThemeCard(
            customTheme = customTheme,
            isSelected = currentThemeId == "custom",
            onApply = { onThemeSelected("custom") },
            onCreateOrEdit = onCreateOrEditCustomTheme
        )
    })

    AutoHidingBarScroller(
        listElements = listElements
    )
}

/**
 * Header section for the theme picker screen.
 */
@Composable
private fun ThemePickerHeader(
    onBack: () -> Unit = {}
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
            BackButton(
                onBack = onBack
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
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

@Composable
private fun CustomThemeCard(
    customTheme: CustomTheme?,
    isSelected: Boolean,
    onApply: () -> Unit,
    onCreateOrEdit: () -> Unit
) {
    val hasCustomTheme = customTheme != null
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = hasCustomTheme, onClick = {
                if (hasCustomTheme) onApply()
            }),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        tonalElevation = if (isSelected) 6.dp else 2.dp,
        shadowElevation = if (isSelected) 12.dp else 6.dp,
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) PluckPalette.Primary else PluckPalette.Primary.copy(alpha = 0.12f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Custom Theme",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = PluckPalette.Primary
                    )
                )
                if (hasCustomTheme && isSelected) {
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

            if (customTheme != null) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ColorPreviewCircle(color = Color(customTheme.lightPrimary), label = "Primary")
                    ColorPreviewCircle(color = Color(customTheme.lightSecondary), label = "Secondary")
                    ColorPreviewCircle(color = Color(customTheme.lightBackground), label = "Background")
                }

                Text(
                    text = "Apply your saved palette or tweak it anytime.",
                    style = MaterialTheme.typography.bodyMedium.copy(color = PluckPalette.Muted)
                )
            } else {
                Text(
                    text = "Design your own palette for both light and dark modes.",
                    style = MaterialTheme.typography.bodyMedium.copy(color = PluckPalette.Muted)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onApply,
                    enabled = hasCustomTheme,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Text(if (hasCustomTheme) "Apply" else "Create Theme")
                }
                OutlinedButton(
                    onClick = onCreateOrEdit,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Text(if (hasCustomTheme) "Edit" else "Start Designing")
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
    @Suppress("UNUSED_PARAMETER") label: String
) {
    Surface(
        modifier = Modifier.size(40.dp),
        shape = CircleShape,
        color = color,
        border = BorderStroke(2.dp, PluckPalette.Primary.copy(alpha = 0.2f))
    ) {}
}
