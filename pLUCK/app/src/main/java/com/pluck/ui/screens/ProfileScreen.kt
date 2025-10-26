package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.foundation.interaction.MutableInteractionSource
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
    userPhone: String?,
    deviceId: String?,
    isLoading: Boolean,
    isUpdatingProfile: Boolean = false,
    updateMessage: String? = null,
    updateError: String? = null,
    onUpdateProfile: (displayName: String, email: String?, phone: String?) -> Unit = { _, _, _ -> },
    onSignOut: () -> Unit,
    onDeleteAccount: () -> Unit,
    onMyEvents: () -> Unit = {},
    onOrganizerDashboard: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var editableName by remember(userName) { mutableStateOf(userName) }
    var editableEmail by remember(userEmail) { mutableStateOf(userEmail.orEmpty()) }
    var editablePhone by remember(userPhone) { mutableStateOf(userPhone.orEmpty()) }
    var showEditSection by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.TopCenter
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
                        .verticalScroll(scrollState)
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

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        color = PluckPalette.Surface,
                        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Personal Information",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = PluckPalette.Primary
                                    )
                                )
                                TextButton(
                                    onClick = {
                                        showEditSection = !showEditSection
                                        editableName = userName
                                        editableEmail = userEmail.orEmpty()
                                    },
                                    enabled = !isLoading && !isUpdatingProfile
                                ) {
                                    Text(if (showEditSection) "Cancel" else "Edit")
                                }
                            }

                            if (updateMessage != null) {
                                Text(
                                    text = updateMessage,
                                    color = PluckPalette.Accept,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            if (updateError != null) {
                                Text(
                                    text = updateError,
                                    color = PluckPalette.Decline,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            if (showEditSection) {
                                ProfileInputField(
                                    label = "Display Name",
                                    value = editableName,
                                    onValueChange = { editableName = it }
                                )
                                ProfileInputField(
                                    label = "Email",
                                    value = editableEmail,
                                    onValueChange = { editableEmail = it }
                                )
                                ProfileInputField(
                                    label = "Phone (optional)",
                                    value = editablePhone,
                                    onValueChange = { editablePhone = it }
                                )
                                TextButton(
                                    onClick = {
                                        val finalEmail = editableEmail.trim().takeIf { it.isNotBlank() }
                                        val finalPhone = editablePhone.trim().takeIf { it.isNotBlank() }
                                        onUpdateProfile(editableName.trim(), finalEmail, finalPhone)
                                    },
                                    enabled = editableName.trim().isNotBlank() && !isUpdatingProfile
                                ) {
                                    Text(if (isUpdatingProfile) "Saving..." else "Save Changes")
                                }
                            } else {
                                Text(
                                    text = "Keep your name and contact information up to date so organizers can reach you.",
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
                            containerColor = PluckPalette.Secondary.copy(alpha = 0.12f),
                            contentColor = PluckPalette.Secondary,
                            onClick = onMyEvents
                        )
                        ProfileActionButton(
                            text = "Organizer Dashboard",
                            description = "Create and manage your events.",
                            enabled = !isLoading,
                            containerColor = PluckPalette.Tertiary.copy(alpha = 0.12f),
                            contentColor = PluckPalette.Tertiary,
                            onClick = onOrganizerDashboard
                        )
                        ProfileActionButton(
                            text = "Sign Out",
                            description = "Return to the welcome screen and clear this session.",
                            enabled = !isLoading,
                            containerColor = PluckPalette.Primary.copy(alpha = 0.08f),
                            contentColor = PluckPalette.Primary,
                            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.2f)),
                            onClick = onSignOut
                        )
                        ProfileDestructiveActionButton(
                            text = "Delete Account",
                            description = "Permanently remove your profile and history.",
                            enabled = !isLoading,
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
                Button(
                    onClick = {
                        showDeleteDialog = false
                        onDeleteAccount()
                    },
                    modifier = Modifier.widthIn(min = 160.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PluckPalette.Decline,
                        contentColor = PluckPalette.Surface
                    )
                ) {
                    Text("Delete Account")
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
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    border: BorderStroke? = null,
    onClick: () -> Unit
) {
    val accentBorder = border ?: BorderStroke(1.dp, contentColor.copy(alpha = 0.25f))

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 96.dp),
        shape = RoundedCornerShape(28.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 8.dp,
        border = accentBorder,
        enabled = enabled,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(containerColor)
                .padding(horizontal = 24.dp, vertical = 18.dp),
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
                    color = PluckPalette.Muted
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileDestructiveActionButton(
    text: String,
    description: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 96.dp),
        shape = RoundedCornerShape(20.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 8.dp,
        border = BorderStroke(1.dp, PluckPalette.Decline.copy(alpha = 0.4f)),
        enabled = enabled,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(PluckPalette.Decline.copy(alpha = 0.08f))
                .padding(horizontal = 24.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(44.dp),
                    shape = CircleShape,
                    color = PluckPalette.Decline.copy(alpha = 0.2f),
                    contentColor = PluckPalette.Decline,
                    tonalElevation = 0.dp,
                    shadowElevation = 0.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.DeleteForever,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = PluckPalette.Decline
                    )
                )
            }
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = PluckPalette.Decline.copy(alpha = 0.85f)
                )
            )
        }
    }
}

@Composable
private fun ProfileInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    )
}

@Preview(showBackground = true, widthDp = 420, heightDp = 820)
@Composable
private fun ProfilePreview() {
    ProfileScreen(
        userName = "Caiden Weiss",
        userEmail = "caidenweiss@gmail.com",
        userPhone = "780-123-4567",
        deviceId = "38400000-8cf0-11bd-b23e-10b96e40000d",
        isLoading = false,
        isUpdatingProfile = false,
        updateMessage = null,
        updateError = null,
        onUpdateProfile = { _, _, _ -> },
        onSignOut = {},
        onDeleteAccount = {}
    )
}



