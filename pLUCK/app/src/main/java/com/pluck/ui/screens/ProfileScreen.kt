/**
 * ProfileScreen.kt
 *
 * Purpose: User profile management screen displaying and editing personal information.
 * Users can update their display name, email, phone, and view their account details.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pluck.data.repository.CloudinaryUploadRepository
import com.pluck.data.repository.CloudinaryUploadResult
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import kotlinx.coroutines.launch

/**
 * Displays the profile management screen allowing entrants to review and update their information.
 *
 * @param userName Current display name for the entrant.
 * @param userEmail Optional email address tied to the profile.
 * @param userPhone Optional phone number tied to the profile.
 * @param profileImageUrl Optional Cloudinary URL for the entrant's profile photo.
 * @param deviceId Unique device identifier used as the Cloudinary public ID.
 * @param isLoading Whether the overall profile data is loading.
 * @param isUpdatingProfile Whether profile details are currently being saved.
 * @param isAdmin Whether the current device has admin privileges.
 * @param updateMessage Optional success banner message for profile operations.
 * @param updateError Optional error banner message for profile operations.
 * @param onProfileImageUploadStarted Callback invoked when the user selects a photo and upload begins.
 * @param onProfileImageUploaded Callback invoked when a Cloudinary upload succeeds with the secure URL.
 * @param onProfileImageUploadFailed Callback invoked when the upload fails with a human-readable message.
 * @param onUpdateProfile Invoked when profile text fields are saved.
 * @param onSignOut Invoked when the user taps the sign-out action.
 * @param onDeleteAccount Invoked when the user confirms account deletion.
 * @param onMyEvents Navigates to the "My Events" screen.
 * @param onOrganizerDashboard Navigates to the organizer dashboard.
 * @param onAdminDashboard Navigates to the admin dashboard (if available).
 * @param onRegisterAdmin Invoked to open the admin registration dialog.
 * @param modifier Optional modifier for styling.
 */
@Composable
fun ProfileScreen(
    userName: String,
    userEmail: String?,
    userPhone: String?,
    profileImageUrl: String?,
    deviceId: String?,
    isLoading: Boolean,
    isUpdatingProfile: Boolean = false,
    isAdmin: Boolean = false,
    isOrganizer: Boolean = false,
    isOrganizerBanned: Boolean = false,
    hasOutstandingAppeal: Boolean = false,
    updateMessage: String? = null,
    updateError: String? = null,
    onProfileImageUploadStarted: () -> Unit = {},
    onProfileImageUploaded: (String) -> Unit = {},
    onProfileImageUploadFailed: (String) -> Unit = {},
    onUpdateProfile: (displayName: String, email: String?, phone: String?) -> Unit = { _, _, _ -> },
    onSignOut: () -> Unit,
    onDeleteAccount: () -> Unit,
    onMyEvents: () -> Unit = {},
    onOrganizerDashboard: () -> Unit = {},
    onAdminDashboard: () -> Unit = {},
    onRegisterAdmin: () -> Unit = {},
    onBecomeOrganizer: () -> Unit = {},
    onDowngradeFromOrganizer: () -> Unit = {},
    onSubmitAppeal: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val uploadRepository = remember { CloudinaryUploadRepository(context) }
    val imageRequest = remember { PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly) }
    var profileImageUploading by remember { mutableStateOf(false) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri == null) return@rememberLauncherForActivityResult
        if (deviceId.isNullOrBlank()) {
            onProfileImageUploadFailed("Cannot upload profile photo because the device ID is unavailable.")
            return@rememberLauncherForActivityResult
        }

        onProfileImageUploadStarted()
        profileImageUploading = true

        scope.launch {
            val result = runCatching {
                uploadRepository.uploadProfileImage(uri, deviceId)
            }.getOrElse { throwable ->
                Log.e(PROFILE_SCREEN_TAG, "Profile photo upload failed", throwable)
                val message = throwable.message ?: throwable.localizedMessage ?: "Failed to upload profile photo."
                onProfileImageUploadFailed(message)
                profileImageUploading = false
                return@launch
            }

            when (result) {
                is CloudinaryUploadResult.Success -> onProfileImageUploaded(result.url)
                is CloudinaryUploadResult.Error -> {
                    val message = result.message.ifBlank { "Failed to upload profile photo." }
                    onProfileImageUploadFailed(message)
                }
            }

            profileImageUploading = false
        }
    }

    val onChangePhoto = on@{
        if (isLoading || profileImageUploading) return@on
        if (deviceId.isNullOrBlank()) {
            onProfileImageUploadFailed("Cannot upload profile photo because the device ID is unavailable.")
            return@on
        }
        imagePickerLauncher.launch(imageRequest)
    }

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showBecomeOrganizerDialog by remember { mutableStateOf(false) }
    var showDowngradeDialog by remember { mutableStateOf(false) }
    var showAppealDialog by remember { mutableStateOf(false) }
    var appealMessage by remember { mutableStateOf("") }
    var editableName by remember(userName) { mutableStateOf(userName) }
    var editableEmail by remember(userEmail) { mutableStateOf(userEmail.orEmpty()) }
    var editablePhone by remember(userPhone) { mutableStateOf(userPhone.orEmpty()) }
    var showEditSection by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp),
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
                        .verticalScroll(scrollState)
                        .padding(horizontal = 28.dp, vertical = 32.dp)
                        .testTag(ProfileScreenTestTags.ScrollContainer),
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

                    ProfileAvatarSection(
                        userName = userName,
                        profileImageUrl = profileImageUrl,
                        isUploading = profileImageUploading,
                        onChangePhoto = onChangePhoto
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
                        if (isOrganizer) {
                            ProfileActionButton(
                                text = "Organizer Dashboard",
                                description = "Create and manage your events.",
                                enabled = !isLoading,
                                containerColor = PluckPalette.Tertiary.copy(alpha = 0.12f),
                                contentColor = PluckPalette.Tertiary,
                                onClick = onOrganizerDashboard
                            )
                            ProfileActionButton(
                                text = "Downgrade to User",
                                description = "Remove organizer status. All your events will be deleted.",
                                enabled = !isLoading,
                                containerColor = PluckPalette.Muted.copy(alpha = 0.12f),
                                contentColor = PluckPalette.Muted,
                                onClick = { showDowngradeDialog = true }
                            )
                        } else if (!isOrganizerBanned) {
                            ProfileActionButton(
                                text = "Become an Organizer",
                                description = "Create and manage events for the community.",
                                enabled = !isLoading,
                                containerColor = PluckPalette.Tertiary.copy(alpha = 0.12f),
                                contentColor = PluckPalette.Tertiary,
                                onClick = { showBecomeOrganizerDialog = true }
                            )
                        } else if (hasOutstandingAppeal) {
                            ProfileActionButton(
                                text = "Appeal Pending",
                                description = "Your organizer access appeal is being reviewed by admins.",
                                enabled = false,
                                containerColor = PluckPalette.Muted.copy(alpha = 0.12f),
                                contentColor = PluckPalette.Muted,
                                onClick = {}
                            )
                        } else {
                            ProfileActionButton(
                                text = "Submit Appeal",
                                description = "You were removed from organizer access. Submit an appeal to admins.",
                                enabled = !isLoading,
                                containerColor = PluckPalette.Accept.copy(alpha = 0.12f),
                                contentColor = PluckPalette.Accept,
                                onClick = { showAppealDialog = true }
                            )
                        }
                        if (isAdmin) {
                            ProfileActionButton(
                                text = "Admin Dashboard",
                                description = "Manage platform content and users.",
                                enabled = !isLoading,
                                containerColor = PluckPalette.Decline.copy(alpha = 0.12f),
                                contentColor = PluckPalette.Decline,
                                onClick = onAdminDashboard
                            )
                        } else {
                            ProfileActionButton(
                                text = "Register as Admin",
                                description = "Unlock the admin console with a secure password.",
                                enabled = !isLoading,
                                containerColor = PluckPalette.Primary.copy(alpha = 0.12f),
                                contentColor = PluckPalette.Primary,
                                onClick = onRegisterAdmin
                            )
                        }
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
                            onClick = { showDeleteDialog = true },
                            modifier = Modifier.testTag(ProfileScreenTestTags.DeleteAccountButton)
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
                    modifier = Modifier
                        .widthIn(min = 160.dp)
                        .testTag(ProfileScreenTestTags.DeleteConfirmButton),
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

    // Become Organizer Confirmation Dialog
    if (showBecomeOrganizerDialog) {
        AlertDialog(
            onDismissRequest = { showBecomeOrganizerDialog = false },
            title = {
                Text(
                    text = "Become an Organizer?",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = PluckPalette.Primary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            },
            text = {
                Text(
                    text = "As an organizer, you'll be able to create and manage events. You can downgrade back to a regular user at any time.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Primary
                    )
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showBecomeOrganizerDialog = false
                        onBecomeOrganizer()
                    },
                    modifier = Modifier.widthIn(min = 160.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PluckPalette.Tertiary,
                        contentColor = PluckPalette.Surface
                    )
                ) {
                    Text("Become Organizer")
                }
            },
            dismissButton = {
                TextButton(onClick = { showBecomeOrganizerDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Downgrade Confirmation Dialog
    if (showDowngradeDialog) {
        AlertDialog(
            onDismissRequest = { showDowngradeDialog = false },
            title = {
                Text(
                    text = "Downgrade to User?",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = PluckPalette.Primary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            },
            text = {
                Text(
                    text = "This will remove your organizer status and permanently delete all events you've created. This action cannot be undone. You can become an organizer again later.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Primary
                    )
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDowngradeDialog = false
                        onDowngradeFromOrganizer()
                    },
                    modifier = Modifier.widthIn(min = 160.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PluckPalette.Decline,
                        contentColor = PluckPalette.Surface
                    )
                ) {
                    Text("Downgrade")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDowngradeDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Appeal Submission Dialog
    if (showAppealDialog) {
        AlertDialog(
            onDismissRequest = { showAppealDialog = false },
            title = {
                Text(
                    text = "Submit Appeal",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = PluckPalette.Primary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Explain why you should be reinstated as an organizer. Your appeal will be reviewed by administrators.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PluckPalette.Primary
                        )
                    )
                    OutlinedTextField(
                        value = appealMessage,
                        onValueChange = { appealMessage = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Appeal Message") },
                        placeholder = { Text("Enter your appeal message...") },
                        minLines = 4,
                        maxLines = 8
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (appealMessage.isNotBlank()) {
                            showAppealDialog = false
                            onSubmitAppeal(appealMessage)
                            appealMessage = ""
                        }
                    },
                    modifier = Modifier.widthIn(min = 160.dp),
                    shape = RoundedCornerShape(18.dp),
                    enabled = appealMessage.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PluckPalette.Accept,
                        contentColor = PluckPalette.Surface
                    )
                ) {
                    Text("Submit Appeal")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showAppealDialog = false
                    appealMessage = ""
                }) {
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
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileAvatarSection(
    /**
     * User's display name used for the textual fallback when no image is available.
     */
    userName: String,
    /**
     * Cloudinary URL for the current profile photo.
     */
    profileImageUrl: String?,
    /**
     * Whether an image upload is in progress to display the loading indicator.
     */
    isUploading: Boolean,
    /**
     * Callback invoked when the user taps the avatar or change photo action.
     */
    onChangePhoto: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier.size(140.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = CircleShape,
                color = PluckPalette.Primary.copy(alpha = 0.08f),
                tonalElevation = 0.dp,
                shadowElevation = 10.dp,
                onClick = onChangePhoto,
                enabled = !isUploading
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (!profileImageUrl.isNullOrBlank()) {
                        AsyncImage(
                            model = profileImageUrl,
                            contentDescription = "Profile photo",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text(
                            text = userName.trim()
                                .takeIf { it.isNotEmpty() }
                                ?.first()
                                ?.uppercaseChar()
                                ?.toString()
                                ?: "?",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                color = PluckPalette.Primary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    if (isUploading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(PluckPalette.Primary.copy(alpha = 0.45f)),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = PluckPalette.Surface)
                        }
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .padding(end = 4.dp, bottom = 4.dp)
                    .size(40.dp),
                shape = CircleShape,
                color = PluckPalette.Primary,
                shadowElevation = 8.dp,
                tonalElevation = 0.dp,
                onClick = onChangePhoto,
                enabled = !isUploading
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.PhotoCamera,
                        contentDescription = "Change profile photo",
                        tint = PluckPalette.Surface
                    )
                }
            }
        }

        TextButton(
            onClick = onChangePhoto,
            enabled = !isUploading
        ) {
            Text(if (isUploading) "Uploading…" else "Change Photo")
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

object ProfileScreenTestTags {
    const val ScrollContainer = "profile_scroll"
    const val DeleteAccountButton = "profile_delete_account_button"
    const val DeleteConfirmButton = "profile_delete_confirm"
}

@Preview(showBackground = true, widthDp = 420, heightDp = 820)
@Composable
private fun ProfilePreview() {
    ProfileScreen(
        userName = "Caiden Weiss",
        userEmail = "caidenweiss@gmail.com",
        userPhone = "780-123-4567",
        profileImageUrl = null,
        deviceId = "38400000-8cf0-11bd-b23e-10b96e40000d",
        isLoading = false,
        isUpdatingProfile = false,
        isAdmin = true,
        isOrganizer = false,
        isOrganizerBanned = false,
        hasOutstandingAppeal = false,
        updateMessage = null,
        updateError = null,
        onUpdateProfile = { _, _, _ -> },
        onSignOut = {},
        onDeleteAccount = {},
        onAdminDashboard = {},
        onRegisterAdmin = {},
        onBecomeOrganizer = {},
        onDowngradeFromOrganizer = {},
        onSubmitAppeal = {}
    )
}

private const val PROFILE_SCREEN_TAG = "ProfileScreen"
