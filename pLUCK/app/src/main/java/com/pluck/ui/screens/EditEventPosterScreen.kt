/**
 * EditEventPosterScreen.kt
 *
 * Purpose: Event poster editor allowing organizers to customize event visual representation.
 * Provides image upload, cropping, and poster customization tools for event branding.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pluck.data.repository.CloudinaryUploadRepository
import com.pluck.data.repository.CloudinaryUploadResult
import com.pluck.ui.components.BackButton
import com.pluck.ui.components.ComposableItem
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.components.SquircleScrollableLazyList
import kotlinx.coroutines.launch
import java.util.UUID

/**
 * Screen for editing event posters.
 *
 * Allows organizers to update event posters by uploading a new image to Cloudinary
 * or providing a direct URL. Supports image preview, upload progress tracking,
 * and comprehensive error handling.
 *
 * @param eventTitle The title of the event being edited
 * @param currentPosterUrl The current poster URL (if any)
 * @param isSaving Whether the save operation is in progress
 * @param errorMessage Any error message from the parent component
 * @param onBack Callback to navigate back
 * @param onClearError Callback to clear error messages
 * @param onSavePoster Callback to save the new poster URL
 * @param modifier Modifier for this composable
 */
@Composable
fun EditEventPosterScreen(
    eventTitle: String,
    currentPosterUrl: String?,
    isSaving: Boolean,
    errorMessage: String?,
    onBack: () -> Unit,
    onClearError: () -> Unit,
    onSavePoster: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    // State for poster URL management
    var posterUrl by remember { mutableStateOf(currentPosterUrl) }
    var posterUploadInProgress by remember { mutableStateOf(false) }
    var posterUploadError by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val uploadRepository = remember { CloudinaryUploadRepository(context) }
    val coroutineScope = rememberCoroutineScope()

    /**
     * Image picker launcher for event poster selection.
     *
     * When an image is selected, it is automatically uploaded to Cloudinary.
     * The upload process updates Firestore directly since we're editing an existing event.
     * Progress and errors are tracked via state variables for user feedback.
     */
    val posterPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        // Validate that an image was actually selected
        if (uri == null) {
            posterUploadError = "No image selected."
            return@rememberLauncherForActivityResult
        }

        // Reset error state and show upload progress
        posterUploadError = null
        posterUploadInProgress = true

        /**
         * Upload the selected image to Cloudinary.
         *
         * Process:
         * 1. Generate a unique filename using UUID to avoid conflicts
         * 2. Call CloudinaryUploadRepository to upload the image
         * 3. Handle the result (success or error)
         * 4. Update UI state accordingly
         *
         * The upload is performed with updateFirestore = true because
         * we're editing an existing event and want Firestore to be updated
         * with the new poster URL immediately.
         */
        coroutineScope.launch {
            try {
                // Generate unique identifier for this poster
                val eventId = UUID.randomUUID().toString()

                // Upload to Cloudinary and await the result
                // updateFirestore = true ensures Firestore is updated with the new URL
                val result = uploadRepository.uploadEventPoster(
                    imageUri = uri,
                    eventId = eventId,
                    updateFirestore = true  // Update Firestore since we're editing an existing event
                )

                // Handle upload result
                when (result) {
                    is CloudinaryUploadResult.Success -> {
                        // Successfully uploaded - store the Cloudinary URL
                        posterUrl = result.url
                        posterUploadError = null
                        android.util.Log.d("EditEventPosterScreen", "Poster uploaded successfully: ${result.url}")
                    }
                    is CloudinaryUploadResult.Error -> {
                        // Upload failed - show user-friendly error message
                        posterUploadError = "Failed to upload poster: ${result.message}. Please try again"
                        posterUrl = null
                        android.util.Log.e("EditEventPosterScreen", "Failed to upload poster: ${result.message}")
                    }
                }
            } catch (t: Throwable) {
                // Catch any unexpected exceptions
                android.util.Log.e("EditEventPosterScreen", "Unexpected error during poster upload", t)
                posterUploadError = "Failed to upload poster: ${t.message ?: "unknown error"}."
                posterUrl = null
            } finally {
                // Always reset upload progress state
                posterUploadInProgress = false
            }
        }
    }

    LaunchedEffect(Unit) {
        posterUploadError = null
    }

    val listElements = mutableListOf<ComposableItem>()

    listElements.add(ComposableItem {
        PosterEditorHeader(
            eventTitle = eventTitle,
            onBack = onBack
        )
    })

    listElements.add(ComposableItem {
        PosterPreview(
            posterUrl = posterUrl,
            isUploading = posterUploadInProgress,
            canUploadPoster = true,  // Cloudinary is always available
            onSelectPoster = {
                posterPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            },
            onRemovePoster = {
                posterUrl = null
            }
        )
    })

    posterUploadError?.let { error ->
        listElements.add(ComposableItem {
            ErrorCallout(message = error)
        })
    }

    if (errorMessage != null) {
        listElements.add(ComposableItem {
            ErrorCallout(
                message = errorMessage,
                onDismiss = onClearError
            )
        })
    }

    listElements.add(ComposableItem {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextButton(
                onClick = onBack,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = PluckPalette.Muted
                )
            ) {
                Text("Cancel")
            }
            Button(
                onClick = {
                    val sanitizedPoster = posterUrl?.trim()?.takeIf { it.isNotBlank() }
                    val normalizedCurrent = currentPosterUrl?.trim()

                    if (sanitizedPoster == normalizedCurrent ||
                        (sanitizedPoster == null && normalizedCurrent.isNullOrEmpty())
                    ) {
                        onBack()
                    } else {
                        onSavePoster(sanitizedPoster)
                    }
                },
                modifier = Modifier.weight(1f),
                enabled = !posterUploadInProgress && !isSaving,
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PluckPalette.Primary,
                    contentColor = PluckPalette.Surface,
                    disabledContainerColor = PluckPalette.Primary.copy(alpha = 0.35f),
                    disabledContentColor = PluckPalette.Surface.copy(alpha = 0.8f)
                )
            ) {
                if (isSaving) {
                    CircularProgressIndicator(
                        color = PluckPalette.Surface,
                        modifier = Modifier
                            .size(18.dp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
                Text("Save Poster")
            }
        }
    })

    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        SquircleScrollableLazyList(
            listElements = listElements
        )
    }
}

/**
 * Header section for the poster editor screen.
 *
 * Displays a back button and title describing the poster editing task.
 *
 * @param eventTitle The title of the event being edited
 * @param onBack Callback to navigate back to the previous screen
 */
@Composable
private fun PosterEditorHeader(
    eventTitle: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BackButton(onBack = onBack)

        Text(
            text = "Update Poster",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Black,
                color = PluckPalette.Primary,
                fontSize = 28.sp
            )
        )
        Text(
            text = "Refresh the poster shown on \"$eventTitle\".",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Muted
            )
        )
    }
}

/**
 * Poster preview and upload controls.
 *
 * Displays the current poster image (or a placeholder if none is selected),
 * along with buttons to upload a new poster or remove the current one.
 * Shows upload progress when an upload is in progress.
 *
 * @param posterUrl The current poster URL (or null if none)
 * @param isUploading Whether a poster upload is currently in progress
 * @param canUploadPoster Whether poster uploads are available (always true for Cloudinary)
 * @param onSelectPoster Callback to launch the image picker
 * @param onRemovePoster Callback to remove the current poster
 */
@Composable
private fun PosterPreview(
    posterUrl: String?,
    isUploading: Boolean,
    canUploadPoster: Boolean,
    onSelectPoster: () -> Unit,
    onRemovePoster: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(24.dp),
            color = PluckPalette.Primary.copy(alpha = 0.04f),
            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.1f))
        ) {
            when {
                isUploading -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(color = PluckPalette.Primary)
                    }
                }
                posterUrl.isNullOrBlank() -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "No poster selected.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = PluckPalette.Muted
                            )
                        )
                    }
                }
                else -> {
                    AsyncImage(
                        model = posterUrl,
                        contentDescription = "Event poster preview",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onSelectPoster,
                enabled = canUploadPoster && !isUploading,
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PluckPalette.Secondary,
                    contentColor = PluckPalette.Surface,
                    disabledContainerColor = PluckPalette.Muted.copy(alpha = 0.2f),
                    disabledContentColor = PluckPalette.Surface.copy(alpha = 0.8f)
                ),
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Image,
                    contentDescription = "Select poster"
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text("Upload")
            }
            TextButton(
                onClick = onRemovePoster,
                enabled = !posterUrl.isNullOrBlank(),
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = PluckPalette.Decline
                )
            ) {
                Text("Remove Poster")
            }
        }

        if (!canUploadPoster) {
            Text(
                text = "Uploads unavailable in this environment.",
                style = MaterialTheme.typography.bodySmall.copy(color = PluckPalette.Decline)
            )
        }
    }
}

/**
 * Error message callout component.
 *
 * Displays an error message in a styled surface with optional dismiss button.
 * Used for showing upload errors or other validation issues.
 *
 * @param message The error message to display
 * @param onDismiss Optional callback to dismiss the error (shows dismiss button if provided)
 */
@Composable
private fun ErrorCallout(
    message: String,
    onDismiss: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = PluckPalette.Decline.copy(alpha = 0.12f),
        border = BorderStroke(1.dp, PluckPalette.Decline.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = PluckPalette.Decline,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )
            if (onDismiss != null) {
                TextButton(
                    onClick = onDismiss,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = PluckPalette.Decline
                    )
                ) {
                    Text("Dismiss")
                }
            }
        }
    }
}
