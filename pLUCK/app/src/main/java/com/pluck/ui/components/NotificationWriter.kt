package com.pluck.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.pluck.notifications.SendNotification
import com.pluck.ui.theme.autoTextColor

@Composable
fun NotificationWriter(
    users: List<String>,
    eventId: String? = null,
    organizerId: String? = null,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var notifTitle by remember { mutableStateOf("") }
    var notifBody by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = null,
                tint = PluckPalette.Secondary,
                modifier = Modifier.size(48.dp)
            )
        },
        title = {
            Text(
                text = "Write Notification",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = notifTitle,
                    onValueChange = { notifTitle = it },
                    label = { Text("Notification Title") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PluckPalette.Secondary,
                        focusedLabelColor = PluckPalette.Secondary,
                        cursorColor = PluckPalette.Secondary
                    )
                )

                OutlinedTextField(
                    value = notifBody,
                    onValueChange = { notifBody = it },
                    label = { Text("Notification Body") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PluckPalette.Secondary,
                        focusedLabelColor = PluckPalette.Secondary,
                        cursorColor = PluckPalette.Secondary
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    SendNotification(
                        users = users,
                        body = notifBody,
                        title = notifTitle,
                        eventId = eventId,
                        organizerId = organizerId
                    )
                    onConfirm()
                },
                enabled = notifBody != "" && notifTitle != "",
                colors = ButtonDefaults.buttonColors(
                    containerColor = PluckPalette.Secondary,
                    contentColor = autoTextColor(PluckPalette.Secondary)
                )
            ) {
                Text("Send")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = PluckPalette.Muted)
            }
        }
    )

}
