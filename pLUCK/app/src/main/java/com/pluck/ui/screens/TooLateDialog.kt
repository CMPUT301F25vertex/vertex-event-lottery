/**
 * TooLateDialog.kt
 *
 * Purpose: Dialog shown when a user accepts an invitation but the event is already full.
 * User is returned to the waiting list.
 *
 * Design Pattern: Jetpack Compose Dialog Component
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.theme.autoTextColor

/**
 * Dialog shown when user tries to accept but event is full
 *
 * @param eventTitle The name of the event that's full
 * @param onDismiss Callback when dialog is dismissed
 */
@Composable
fun TooLateDialog(
    eventTitle: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Outlined.Schedule,
                contentDescription = null,
                tint = PluckPalette.Secondary,
                modifier = Modifier.size(56.dp)
            )
        },
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Too Late!",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black
                    ),
                    textAlign = TextAlign.Center
                )
            }
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Unfortunately, $eventTitle has reached full capacity while you were deciding.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "You've been returned to the waiting list and may be selected in a future draw if spots become available.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted
                    ),
                    textAlign = TextAlign.Center
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PluckPalette.Secondary,
                    contentColor = autoTextColor(PluckPalette.Secondary)
                )
            ) {
                Text("Got it")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TooLateDialogPreview() {
    TooLateDialog(
        eventTitle = "Tech Conference 2024",
        onDismiss = {}
    )
}
