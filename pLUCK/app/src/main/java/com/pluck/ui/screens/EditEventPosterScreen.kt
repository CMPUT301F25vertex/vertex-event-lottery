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
import com.google.firebase.storage.FirebaseStorage
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

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
    var posterUrl by remember { mutableStateOf(currentPosterUrl) }
    var manualPosterUrl by remember { mutableStateOf(currentPosterUrl.orEmpty()) }
    var posterUploadInProgress by remember { mutableStateOf(false) }
    var posterUploadError by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val storage = remember { runCatching { FirebaseStorage.getInstance() }.getOrNull() }
    val coroutineScope = rememberCoroutineScope()

    val posterPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri == null) {
            posterUploadError = "No image selected."
            return@rememberLauncherForActivityResult
        }
        if (storage == null) {
            posterUploadError = "Firebase Storage not configured. Please use a direct image URL instead."
            return@rememberLauncherForActivityResult
        }

        coroutineScope.launch {
            posterUploadInProgress = true
            posterUploadError = null
            var inputStream: java.io.InputStream? = null
            try {
                // Take persistable URI permission for the selected file
                try {
                    context.contentResolver.takePersistableUriPermission(
                        uri,
                        android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                } catch (e: SecurityException) {
                    // Permission not available, continue anyway as PickVisualMedia should handle this
                }

                val contentResolver = context.contentResolver
                inputStream = contentResolver.openInputStream(uri)
                if (inputStream == null) {
                    posterUploadError = "Cannot read selected image. Please try a different image or use a direct URL."
                    posterUploadInProgress = false
                    return@launch
                }
                val byteArray = inputStream.readBytes()
                inputStream.close()
                inputStream = null

                if (byteArray.isEmpty()) {
                    posterUploadError = "Selected image is empty. Please try a different image."
                    posterUploadInProgress = false
                    return@launch
                }

                val fileName = "event_posters/${UUID.randomUUID()}.jpg"
                val reference = storage.reference.child(fileName)

                val uploadTask = reference.putBytes(byteArray)
                uploadTask.await()

                val downloadUrl = reference.downloadUrl.await().toString()

                posterUrl = downloadUrl
                manualPosterUrl = downloadUrl
                posterUploadError = null
            } catch (t: Throwable) {
                android.util.Log.e("EditEventPosterScreen", "Failed to upload poster", t)
                val errorMsg = when {
                    t is java.io.FileNotFoundException ->
                        "Cannot access the selected image. Please try a different image or use a direct URL."
                    t is SecurityException ->
                        "Permission denied to read the image. Please try a different image or use a direct URL."
                    t.message?.contains("does not exist", ignoreCase = true) == true ||
                    t.message?.contains("No such file", ignoreCase = true) == true ||
                    t.message?.contains("not found", ignoreCase = true) == true ||
                    t.message?.contains("404", ignoreCase = true) == true ->
                        "Firebase Storage is not configured for this project.\n\n" +
                        "Please paste a direct image URL below instead.\n\n" +
                        "You can upload images to:\n• Imgur (https://imgur.com)\n• PostImages (https://postimages.org)\n• ImgBB (https://imgbb.com)"
                    t.message?.contains("permission", ignoreCase = true) == true ||
                    t.message?.contains("PERMISSION_DENIED", ignoreCase = true) == true ->
                        "Firebase Storage permission denied.\n\n" +
                        "Please paste a direct image URL below instead."
                    t.message?.contains("network", ignoreCase = true) == true ->
                        "Network error. Check your internet connection and try again."
                    else -> "Upload failed: ${t.message ?: "Unknown error"}\n\n" +
                        "Please paste a direct image URL below instead."
                }
                posterUploadError = errorMsg
            } finally {
                // Ensure stream is closed
                inputStream?.close()
                posterUploadInProgress = false
            }
        }
    }

    LaunchedEffect(Unit) {
        posterUploadError = null
    }

    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .align(Alignment.TopCenter),
                shape = RoundedCornerShape(36.dp),
                color = PluckPalette.Surface,
                tonalElevation = 0.dp,
                shadowElevation = 16.dp,
                border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp, vertical = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    PosterEditorHeader(
                        eventTitle = eventTitle,
                        onBack = onBack
                    )

                    PosterPreview(
                        posterUrl = posterUrl,
                        isUploading = posterUploadInProgress,
                        canUploadPoster = storage != null,
                        onSelectPoster = {
                            posterPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                        onRemovePoster = {
                            posterUrl = null
                            manualPosterUrl = ""
                        }
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Use a direct image link if uploads are disabled. A 4:3 JPEG or PNG under 2 MB looks best.",
                            style = MaterialTheme.typography.bodySmall.copy(color = PluckPalette.Muted)
                        )
                        OutlinedTextField(
                            value = manualPosterUrl,
                            onValueChange = { value ->
                                manualPosterUrl = value
                                posterUrl = value.trim().takeIf { it.isNotBlank() }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Poster URL") },
                            placeholder = { Text("https://example.com/poster.jpg") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PluckPalette.Secondary,
                                unfocusedBorderColor = PluckPalette.Primary.copy(alpha = 0.12f),
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = PluckPalette.Primary.copy(alpha = 0.02f)
                            )
                        )
                    }

                    posterUploadError?.let { error ->
                        ErrorCallout(message = error)
                    }

                    if (errorMessage != null) {
                        ErrorCallout(
                            message = errorMessage,
                            onDismiss = onClearError
                        )
                    }

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
                }
            }
        }
    }
}

@Composable
private fun PosterEditorHeader(
    eventTitle: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            color = PluckPalette.Primary.copy(alpha = 0.08f),
            contentColor = PluckPalette.Primary,
            tonalElevation = 0.dp,
            shadowElevation = 4.dp,
            onClick = onBack
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = PluckPalette.Primary
                )
            }
        }
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
                Text("Upload New")
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
                text = "Uploads unavailable in this environment. Paste a direct URL instead.",
                style = MaterialTheme.typography.bodySmall.copy(color = PluckPalette.Decline)
            )
        }
    }
}

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
