package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette

@Composable
fun ProfileScreen(
    userName: String,
    userEmail: String?,
    deviceId: String?,
    isLoading: Boolean,
    onSignOut: () -> Unit,
    onDeleteAccount: () -> Unit,
    onMyEvents: () -> Unit = {},
    onOrganizerDashboard: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 460.dp),
                shape = RoundedCornerShape(36.dp),
                color = PluckPalette.Surface,
                tonalElevation = 0.dp,
                shadowElevation = 16.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp, vertical = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = PluckPalette.Primary,
                            fontWeight = FontWeight.Black
                        )
                    )

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(28.dp),
                        color = PluckPalette.Primary.copy(alpha = 0.04f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 18.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = userName,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PluckPalette.Primary
                                )
                            )
                            if (!userEmail.isNullOrBlank()) {
                                Text(
                                    text = userEmail,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = PluckPalette.Muted
                                    )
                                )
                            }
                            if (!deviceId.isNullOrBlank()) {
                                Text(
                                    text = "Device ID: $deviceId",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = PluckPalette.Muted
                                    )
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ProfileActionButton(
                            text = "My Events",
                            description = "View your joined and created events.",
                            enabled = !isLoading,
                            backgroundColor = PluckPalette.Secondary,
                            contentColor = Color.White,
                            border = null,
                            onClick = onMyEvents
                        )
                        ProfileActionButton(
                            text = "Organizer Dashboard",
                            description = "Create and manage your events.",
                            enabled = !isLoading,
                            backgroundColor = PluckPalette.Tertiary,
                            contentColor = Color.White,
                            border = null,
                            onClick = onOrganizerDashboard
                        )
                        ProfileActionButton(
                            text = "Sign Out",
                            description = "Return to the welcome screen and clear this session.",
                            enabled = !isLoading,
                            backgroundColor = PluckPalette.Primary,
                            contentColor = Color.White,
                            border = null,
                            onClick = onSignOut
                        )
                        ProfileActionButton(
                            text = "Delete Account",
                            description = "Permanently remove your profile and history.",
                            enabled = !isLoading,
                            backgroundColor = PluckPalette.Surface,
                            contentColor = PluckPalette.Decline,
                            border = BorderStroke(1.dp, PluckPalette.Decline.copy(alpha = 0.5f)),
                            onClick = { showDeleteDialog = true }
                        )
                    }

                    Text(
                        text = "Need help? Contact support@pluck.app",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Muted
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    text = "Delete account?",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = PluckPalette.Primary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            },
            text = {
                Text(
                    text = "This will permanently delete your profile, history, and device linkage. This action cannot be undone.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Primary
                    )
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        onDeleteAccount()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = PluckPalette.Decline
                    )
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileActionButton(
    text: String,
    description: String,
    enabled: Boolean,
    backgroundColor: Color,
    contentColor: Color,
    border: BorderStroke?,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp),
        shape = RoundedCornerShape(28.dp),
        color = backgroundColor,
        contentColor = contentColor,
        tonalElevation = 0.dp,
        shadowElevation = 10.dp,
        border = border,
        onClick = onClick,
        enabled = enabled
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = contentColor
                )
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = if (contentColor == Color.White) Color.White.copy(alpha = 0.8f) else PluckPalette.Muted
                )
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 820)
@Composable
private fun ProfilePreview() {
    ProfileScreen(
        userName = "Caiden Weiss",
        userEmail = "caidenweiss@gmail.com",
        deviceId = "38400000-8cf0-11bd-b23e-10b96e40000d",
        isLoading = false,
        onSignOut = {},
        onDeleteAccount = {}
    )
}



