/**
 * CustomThemeCreatorScreen.kt
 *
 * Purpose: Full-screen experience for creating bespoke color themes.
 * Presents granular color controls, manual hex entry, presets, and live previews.
 *
 * Features:
 * - Edit light & dark palettes independently
 * - Manual hex input + preset swatches
 * - Live preview of both modes
 * - Reset to Ocean Blue defaults
 *
 * Design Pattern: Stateful composable with dialog-driven color pickers
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.theme.CustomTheme

/**
 * Custom theme creator entry point.
 *
 * @param initialTheme Starting point for the editor (current selection or saved custom palette)
 * @param onSave Callback invoked when user saves the custom theme
 * @param onBack Callback when user dismisses the screen
 * @param modifier Optional modifier for screen styling
 */
@Composable
fun CustomThemeCreatorScreen(
    initialTheme: CustomTheme? = null,
    onSave: (CustomTheme) -> Unit,
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val seedTheme = initialTheme ?: CustomTheme.Blue

    var lightBackground by remember(initialTheme) { mutableStateOf(seedTheme.lightBackground.toComposeColor()) }
    var lightPrimary by remember(initialTheme) { mutableStateOf(seedTheme.lightPrimary.toComposeColor()) }
    var lightSecondary by remember(initialTheme) { mutableStateOf(seedTheme.lightSecondary.toComposeColor()) }
    var darkBackground by remember(initialTheme) { mutableStateOf(seedTheme.darkBackground.toComposeColor()) }
    var darkPrimary by remember(initialTheme) { mutableStateOf(seedTheme.darkPrimary.toComposeColor()) }
    var darkSecondary by remember(initialTheme) { mutableStateOf(seedTheme.darkSecondary.toComposeColor()) }

    var showColorPicker by remember { mutableStateOf(false) }
    var editingColor by remember { mutableStateOf<ColorField?>(null) }

    LaunchedEffect(initialTheme) {
        // Ensure manual edits start from the selected theme snapshot
        lightBackground = seedTheme.lightBackground.toComposeColor()
        lightPrimary = seedTheme.lightPrimary.toComposeColor()
        lightSecondary = seedTheme.lightSecondary.toComposeColor()
        darkBackground = seedTheme.darkBackground.toComposeColor()
        darkPrimary = seedTheme.darkPrimary.toComposeColor()
        darkSecondary = seedTheme.darkSecondary.toComposeColor()
    }

    val resetToDefault = {
        lightBackground = DefaultLightBackground
        lightPrimary = DefaultLightPrimary
        lightSecondary = DefaultLightSecondary
        darkBackground = DefaultDarkBackground
        darkPrimary = DefaultDarkPrimary
        darkSecondary = DefaultDarkSecondary
    }

    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Header(onBack = onBack)

            ThemePreviewCard(
                lightBackground = lightBackground,
                lightPrimary = lightPrimary,
                darkBackground = darkBackground,
                darkPrimary = darkPrimary
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                ThemeColorSection(
                    title = "Light Mode",
                    description = "Controls surfaces and action colors when light mode is active.",
                    colors = listOf(
                        ColorItem("Background", lightBackground) {
                            editingColor = ColorField.LIGHT_BACKGROUND
                            showColorPicker = true
                        },
                        ColorItem("Primary", lightPrimary) {
                            editingColor = ColorField.LIGHT_PRIMARY
                            showColorPicker = true
                        },
                        ColorItem("Secondary", lightSecondary) {
                            editingColor = ColorField.LIGHT_SECONDARY
                            showColorPicker = true
                        }
                    )
                )

                ThemeColorSection(
                    title = "Dark Mode",
                    description = "Tune the deep surfaces and highlight colors for dark mode.",
                    colors = listOf(
                        ColorItem("Background", darkBackground) {
                            editingColor = ColorField.DARK_BACKGROUND
                            showColorPicker = true
                        },
                        ColorItem("Primary", darkPrimary) {
                            editingColor = ColorField.DARK_PRIMARY
                            showColorPicker = true
                        },
                        ColorItem("Secondary", darkSecondary) {
                            editingColor = ColorField.DARK_SECONDARY
                            showColorPicker = true
                        }
                    )
                )
            }

            OutlinedButton(
                onClick = resetToDefault,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.3f))
            ) {
                Text(
                    text = "Reset to Ocean Blue Defaults",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            Button(
                onClick = {
                    val customTheme = CustomTheme(
                        id = "custom",
                        name = "Custom Theme",
                        lightBackground = lightBackground.toThemeLong(),
                        lightSurface = lightBackground.blendToward(Color.White, 0.15f).toThemeLong(),
                        lightPrimary = lightPrimary.toThemeLong(),
                        lightMuted = lightPrimary.blendToward(lightBackground, 0.35f).toThemeLong(),
                        lightSecondary = lightSecondary.toThemeLong(),
                        lightTertiary = lightSecondary.blendToward(lightPrimary, 0.4f).toThemeLong(),
                        darkBackground = darkBackground.toThemeLong(),
                        darkSurface = darkBackground.blendToward(Color.White, 0.12f).toThemeLong(),
                        darkPrimary = darkPrimary.toThemeLong(),
                        darkMuted = darkPrimary.blendToward(darkBackground, 0.25f).toThemeLong(),
                        darkSecondary = darkSecondary.toThemeLong(),
                        darkTertiary = darkSecondary.blendToward(darkPrimary, 0.35f).toThemeLong(),
                        isCustom = true
                    )
                    onSave(customTheme)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PluckPalette.Primary)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = PluckPalette.Surface
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Save Custom Theme",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }

    if (showColorPicker && editingColor != null) {
        SimpleColorPickerDialog(
            currentColor = when (editingColor) {
                ColorField.LIGHT_BACKGROUND -> lightBackground
                ColorField.LIGHT_PRIMARY -> lightPrimary
                ColorField.LIGHT_SECONDARY -> lightSecondary
                ColorField.DARK_BACKGROUND -> darkBackground
                ColorField.DARK_PRIMARY -> darkPrimary
                ColorField.DARK_SECONDARY -> darkSecondary
                else -> Color.White
            },
            onColorSelected = { picked ->
                when (editingColor) {
                    ColorField.LIGHT_BACKGROUND -> lightBackground = picked
                    ColorField.LIGHT_PRIMARY -> lightPrimary = picked
                    ColorField.LIGHT_SECONDARY -> lightSecondary = picked
                    ColorField.DARK_BACKGROUND -> darkBackground = picked
                    ColorField.DARK_PRIMARY -> darkPrimary = picked
                    ColorField.DARK_SECONDARY -> darkSecondary = picked
                    else -> Unit
                }
                showColorPicker = false
                editingColor = null
            },
            onDismiss = {
                showColorPicker = false
                editingColor = null
            }
        )
    }
}

@Composable
private fun Header(onBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = PluckPalette.Primary
            )
        }
        Text(
            text = "Custom Theme Builder",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Black,
                color = PluckPalette.Primary,
                fontSize = 24.sp
            )
        )
        Spacer(modifier = Modifier.size(48.dp))
    }
}

@Composable
private fun ThemePreviewCard(
    lightBackground: Color,
    lightPrimary: Color,
    darkBackground: Color,
    darkPrimary: Color
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        shape = RoundedCornerShape(28.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 10.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Live Preview",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = PluckPalette.Primary
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PreviewTile(
                    modifier = Modifier.weight(1f),
                    label = "Light",
                    background = lightBackground,
                    accent = lightPrimary,
                    contentColor = Color.Black.copy(alpha = 0.7f)
                )
                PreviewTile(
                    modifier = Modifier.weight(1f),
                    label = "Dark",
                    background = darkBackground,
                    accent = darkPrimary,
                    contentColor = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}

@Composable
private fun PreviewTile(
    modifier: Modifier = Modifier,
    label: String,
    background: Color,
    accent: Color,
    contentColor: Color
) {
    Surface(
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(20.dp),
        color = background,
        tonalElevation = 0.dp,
        shadowElevation = 2.dp,
        border = BorderStroke(1.dp, contentColor.copy(alpha = 0.08f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge.copy(
                    color = contentColor,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = accent,
                tonalElevation = 0.dp,
                shadowElevation = 0.dp
            ) {
                Text(
                    text = "Primary Action",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (label == "Light") Color.White else Color.Black
                    )
                )
            }
        }
    }
}

@Composable
private fun ThemeColorSection(
    title: String,
    description: String,
    colors: List<ColorItem>
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 480.dp),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        shadowElevation = 6.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.06f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = PluckPalette.Primary
                    )
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(color = PluckPalette.Muted)
                )
            }

            colors.forEach { item ->
                ColorPickerRow(
                    label = item.label,
                    color = item.color,
                    onClick = item.onClick
                )
            }
        }
    }
}

@Composable
private fun ColorPickerRow(
    label: String,
    color: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        color = PluckPalette.Background.copy(alpha = 0.35f),
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        color = PluckPalette.Primary
                    )
                )
                Text(
                    text = color.toHexString(),
                    style = MaterialTheme.typography.labelMedium.copy(color = PluckPalette.Muted)
                )
            }
            Surface(
                modifier = Modifier.size(44.dp),
                shape = CircleShape,
                color = color,
                border = BorderStroke(2.dp, PluckPalette.Primary.copy(alpha = 0.25f))
            ) {}
        }
    }
}

/**
 * Dialog that provides preset swatches plus manual hex entry.
 */
@Composable
private fun SimpleColorPickerDialog(
    currentColor: Color,
    onColorSelected: (Color) -> Unit,
    onDismiss: () -> Unit
) {
    var hexValue by remember(currentColor) {
        mutableStateOf(currentColor.toHexString(includeHash = false))
    }
    var previewColor by remember(currentColor) { mutableStateOf(currentColor) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(28.dp),
            color = PluckPalette.Surface,
            shadowElevation = 24.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .widthIn(max = 420.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Pick a Color",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = PluckPalette.Primary
                    )
                )

                val presets = listOf(
                    // Blues
                    Color(0xFF3B80AB), Color(0xFF216A97), Color(0xFF0D47A1),
                    // Purples
                    Color(0xFF7E0297), Color(0xFFA43AB0), Color(0xFF6A1B9A),
                    // Greens
                    Color(0xFF2E7D32), Color(0xFF388E3C), Color(0xFF1B5E20),
                    // Oranges
                    Color(0xFFE65100), Color(0xFFFF6F00), Color(0xFFBF360C),
                    // Reds
                    Color(0xFFC62828), Color(0xFFD32F2F), Color(0xFFB71C1C),
                    // Greys / Neutrals
                    Color(0xFFEBF1F5), Color(0xFFFFFFFF), Color(0xFF04090B),
                    Color(0xFF0D1419), Color(0xFF1A111C), Color(0xFF2E2E2E)
                )

                OutlinedTextField(
                    value = hexValue,
                    onValueChange = { value ->
                        val sanitized = value.uppercase().filter { it in HEX_CHARS }.take(8)
                        hexValue = sanitized
                        parseHexColor(sanitized)?.let { previewColor = it }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = { Text("Hex") },
                    prefix = { Text("#") },
                    supportingText = {
                        Text(text = if (hexValue.length == 6 || hexValue.length == 8) " " else "6 or 8 digits")
                    }
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = previewColor,
                    border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.2f))
                ) {}

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    presets.chunked(3).forEach { row ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            row.forEach { color ->
                                Surface(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                        .clickable {
                                            previewColor = color
                                            hexValue = color.toHexString(includeHash = false)
                                        },
                                    shape = RoundedCornerShape(12.dp),
                                    color = color,
                                    border = if (color == previewColor) {
                                        BorderStroke(3.dp, PluckPalette.Primary)
                                    } else {
                                        BorderStroke(1.dp, PluckPalette.Muted.copy(alpha = 0.3f))
                                    }
                                ) {}
                            }
                        }
                    }
                }

                val canApply = hexValue.length == 6 || hexValue.length == 8

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text("Cancel", fontWeight = FontWeight.SemiBold)
                    }

                    Button(
                        onClick = {
                            if (canApply) {
                                onColorSelected(previewColor)
                            }
                        },
                        enabled = canApply,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text("Select", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

private enum class ColorField {
    LIGHT_BACKGROUND,
    LIGHT_PRIMARY,
    LIGHT_SECONDARY,
    DARK_BACKGROUND,
    DARK_PRIMARY,
    DARK_SECONDARY
}

private data class ColorItem(
    val label: String,
    val color: Color,
    val onClick: () -> Unit
)

private fun Color.blendToward(target: Color, amount: Float): Color =
    lerp(this, target, amount.coerceIn(0f, 1f))

private fun Color.toHexString(includeHash: Boolean = true): String {
    val value = toArgb() and 0xFFFFFF
    val hex = String.format("%06X", value)
    return if (includeHash) "#$hex" else hex
}

private fun Color.toThemeLong(): Long = toArgb().toLong() and 0xFFFFFFFF

private fun Long.toComposeColor(): Color = Color(this.toInt())

private fun parseHexColor(value: String): Color? {
    if (value.length != 6 && value.length != 8) return null
    val parsed = value.toLongOrNull(16) ?: return null
    val argb = if (value.length == 6) parsed or 0xFF000000 else parsed
    return Color(argb.toInt())
}

private val HEX_CHARS = "0123456789ABCDEF"

private val DefaultLightBackground = CustomTheme.Blue.lightBackground.toComposeColor()
private val DefaultLightPrimary = CustomTheme.Blue.lightPrimary.toComposeColor()
private val DefaultLightSecondary = CustomTheme.Blue.lightSecondary.toComposeColor()
private val DefaultDarkBackground = CustomTheme.Blue.darkBackground.toComposeColor()
private val DefaultDarkPrimary = CustomTheme.Blue.darkPrimary.toComposeColor()
private val DefaultDarkSecondary = CustomTheme.Blue.darkSecondary.toComposeColor()
