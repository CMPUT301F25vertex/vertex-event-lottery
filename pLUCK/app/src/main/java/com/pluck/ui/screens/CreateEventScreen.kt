package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette

/**
 * CreateEventScreen.kt
 *
 * Purpose: Allows organizers to create new lottery events with all necessary details.
 *
 * Design Pattern: Stateful screen with form validation following pLUCK design language.
 *
 * Outstanding Issues: None.
 */
@Composable
fun CreateEventScreen(
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onCreateEvent: (
        title: String,
        description: String,
        location: String,
        date: String,
        capacity: String
    ) -> Unit = { _, _, _, _, _ -> },
    onCancel: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var capacity by remember { mutableStateOf("") }

    val isFormValid = title.isNotBlank() &&
            description.isNotBlank() &&
            location.isNotBlank() &&
            date.isNotBlank() &&
            capacity.isNotBlank()

    PluckLayeredBackground(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            // Close button
            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(56.dp)
                    .zIndex(2f),
                shape = CircleShape,
                color = PluckPalette.Surface,
                contentColor = PluckPalette.Primary,
                tonalElevation = 0.dp,
                shadowElevation = 12.dp,
                onClick = onCancel
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Main content card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 460.dp)
                    .align(Alignment.Center)
                    .zIndex(1f),
                shape = RoundedCornerShape(36.dp),
                color = PluckPalette.Surface,
                tonalElevation = 0.dp,
                shadowElevation = 18.dp,
                border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 28.dp, vertical = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Header
                    CreateEventHeader()

                    // Form fields
                    CreateEventFormField(
                        value = title,
                        onValueChange = { title = it },
                        label = "Event Title",
                        icon = Icons.Outlined.Event,
                        placeholder = "e.g., Swimming Lessons"
                    )

                    CreateEventFormField(
                        value = description,
                        onValueChange = { description = it },
                        label = "Description",
                        icon = Icons.Outlined.Description,
                        placeholder = "Tell attendees what to expect...",
                        maxLines = 4
                    )

                    CreateEventFormField(
                        value = location,
                        onValueChange = { location = it },
                        label = "Location",
                        icon = Icons.Outlined.LocationOn,
                        placeholder = "e.g., City Pool, 123 Main St"
                    )

                    CreateEventFormField(
                        value = date,
                        onValueChange = { date = it },
                        label = "Event Date",
                        icon = Icons.Outlined.CalendarMonth,
                        placeholder = "YYYY-MM-DD"
                    )

                    CreateEventFormField(
                        value = capacity,
                        onValueChange = { capacity = it },
                        label = "Capacity",
                        icon = Icons.Outlined.People,
                        placeholder = "e.g., 20",
                        keyboardType = KeyboardType.Number
                    )

                    // Error message
                    if (errorMessage != null) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            color = PluckPalette.Decline.copy(alpha = 0.12f),
                            border = BorderStroke(1.dp, PluckPalette.Decline.copy(alpha = 0.3f))
                        ) {
                            Text(
                                text = errorMessage,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = PluckPalette.Decline,
                                    fontWeight = FontWeight.Medium
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    // Info callout
                    CreateEventInfoCallout()

                    // Create button
                    Button(
                        onClick = {
                            if (isFormValid && !isLoading) {
                                onCreateEvent(title, description, location, date, capacity)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(28.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PluckPalette.Primary,
                            contentColor = Color.White,
                            disabledContainerColor = PluckPalette.Muted.copy(alpha = 0.3f),
                            disabledContentColor = PluckPalette.Muted
                        ),
                        enabled = isFormValid && !isLoading,
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 16.dp)
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Add,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = "Create Event",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateEventHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Create Event",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Black,
                color = PluckPalette.Primary,
                fontSize = 28.sp
            )
        )
        Text(
            text = "Set up a new lottery event for your community",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Muted
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateEventFormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    placeholder: String,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(32.dp),
                shape = CircleShape,
                color = PluckPalette.Secondary.copy(alpha = 0.12f),
                contentColor = PluckPalette.Secondary
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = PluckPalette.Primary
                )
            )
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted
                    )
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Primary
            ),
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PluckPalette.Secondary,
                unfocusedBorderColor = PluckPalette.Primary.copy(alpha = 0.12f),
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = PluckPalette.Primary.copy(alpha = 0.02f)
            ),
            maxLines = maxLines,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}

@Composable
private fun CreateEventInfoCallout() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Tertiary.copy(alpha = 0.12f),
        border = BorderStroke(1.dp, PluckPalette.Tertiary.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = PluckPalette.Tertiary.copy(alpha = 0.3f),
                contentColor = PluckPalette.Tertiary
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.Event,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Text(
                text = "Events use a lottery system. Attendees join the waitlist and are randomly selected when the draw closes.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = PluckPalette.Primary.copy(alpha = 0.8f)
                )
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 920)
@Composable
private fun CreateEventScreenPreview() {
    CreateEventScreen()
}
