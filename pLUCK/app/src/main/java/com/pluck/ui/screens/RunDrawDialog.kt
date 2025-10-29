/**
 * RunDrawDialog.kt
 *
 * Purpose: Dialog for running lottery draws with wave tracking.
 * Organizers can specify draw size and see wave information.
 * Supports running multiple draws until event fills up.
 *
 * Design Pattern: Jetpack Compose Dialog Component
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Casino
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.theme.autoTextColor

/**
 * Dialog for running a lottery draw with wave tracking
 *
 * @param eventTitle The name of the event
 * @param waitingCount Number of users on waitlist with WAITING status
 * @param capacity Total event capacity
 * @param enrolled Current enrolled count
 * @param currentWave The current wave number (starts at 1)
 * @param samplingCount Default draw size from event settings
 * @param onConfirm Callback when draw is confirmed, returns the draw size
 * @param onDismiss Callback when dialog is dismissed
 */
@Composable
fun RunDrawDialog(
    eventTitle: String,
    waitingCount: Int,
    capacity: Int,
    enrolled: Int,
    currentWave: Int,
    samplingCount: Int,
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    var drawSizeInput by remember { mutableStateOf(samplingCount.toString()) }
    val availableSpots = (capacity - enrolled).coerceAtLeast(0)
    val maxDrawSize = minOf(availableSpots, waitingCount)

    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Outlined.Casino,
                contentDescription = null,
                tint = PluckPalette.Secondary,
                modifier = Modifier.size(48.dp)
            )
        },
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Run Lottery Draw",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = eventTitle,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted
                    )
                )
            }
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Wave info
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = PluckPalette.Secondary.copy(alpha = 0.12f),
                    border = BorderStroke(1.dp, PluckPalette.Secondary.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = null,
                            tint = PluckPalette.Secondary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Wave $currentWave",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = PluckPalette.Secondary
                            )
                        )
                    }
                }

                // Stats
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DrawStatRow("Waiting on waitlist:", "$waitingCount")
                    DrawStatRow("Available spots:", "$availableSpots")
                    DrawStatRow("Current enrollment:", "$enrolled/$capacity")
                }

                // Draw size input
                OutlinedTextField(
                    value = drawSizeInput,
                    onValueChange = { drawSizeInput = it },
                    label = { Text("Draw Size") },
                    placeholder = { Text("How many to draw?") },
                    supportingText = { Text("Max: $maxDrawSize") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PluckPalette.Secondary,
                        focusedLabelColor = PluckPalette.Secondary,
                        cursorColor = PluckPalette.Secondary
                    )
                )

                // Info text
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = PluckPalette.Accept.copy(alpha = 0.08f),
                    border = BorderStroke(1.dp, PluckPalette.Accept.copy(alpha = 0.2f))
                ) {
                    Text(
                        text = "Selected users will receive invitations. They must accept before being enrolled.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Muted
                        ),
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val drawSize = drawSizeInput.toIntOrNull() ?: 0
                    if (drawSize > 0 && drawSize <= maxDrawSize) {
                        onConfirm(drawSize)
                    }
                },
                enabled = (drawSizeInput.toIntOrNull() ?: 0) in 1..maxDrawSize,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PluckPalette.Secondary,
                    contentColor = autoTextColor(PluckPalette.Secondary)
                )
            ) {
                Text("Run Draw")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = PluckPalette.Muted)
            }
        }
    )
}

@Composable
private fun DrawStatRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Muted
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = PluckPalette.Primary
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RunDrawDialogPreview() {
    RunDrawDialog(
        eventTitle = "Tech Conference 2024",
        waitingCount = 50,
        capacity = 100,
        enrolled = 75,
        currentWave = 2,
        samplingCount = 10,
        onConfirm = {},
        onDismiss = {}
    )
}
