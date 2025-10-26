package com.pluck.ui.screens

import android.net.Uri
import android.util.Log
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
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Groups
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.theme.autoTextColor
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.format.DateTimeFormatter
import java.util.UUID

/**
 * CreateEventScreen.kt
 *
 * Purpose: Allows organizers to create new lottery events with all necessary details.
 *
 * Design Pattern: Stateful screen with form validation following pLUCK design language.
 *
 * Outstanding Issues: None.
 */
data class CreateEventRequest(
    val title: String,
    val description: String,
    val location: String,
    val date: String,
    val capacity: String,
    val posterUrl: String?,
    val registrationStart: String,
    val registrationEnd: String,
    val waitlistLimit: String,
    val samplingCount: String
)

@Composable
fun CreateEventScreen(
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onCreateEvent: (CreateEventRequest) -> Unit = {},
    onCancel: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var capacity by remember { mutableStateOf("") }
    var registrationStart by remember { mutableStateOf("") }
    var registrationEnd by remember { mutableStateOf("") }
    var waitlistLimit by remember { mutableStateOf("") }
    var samplingCount by remember { mutableStateOf("") }

    var posterUrl by remember { mutableStateOf<String?>(null) }
    var posterUrlInput by remember { mutableStateOf("") }
    var posterUploadInProgress by remember { mutableStateOf(false) }
    var posterUploadError by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val storage = remember { runCatching { FirebaseStorage.getInstance() }.getOrNull() }
    val coroutineScope = rememberCoroutineScope()

    val posterPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        Log.d("CreateEvent_PosterUpload", "=== CREATE EVENT POSTER UPLOAD STARTED ===")
        Log.d("CreateEvent_PosterUpload", "URI received: $uri")

        if (uri == null || storage == null) {
            if (storage == null) {
                Log.e("CreateEvent_PosterUpload", "Firebase Storage is null - not configured")
                posterUploadError = "Poster uploads require Firebase Storage. Paste a direct image URL below."
            } else {
                Log.e("CreateEvent_PosterUpload", "URI is null - no image selected")
            }
            return@rememberLauncherForActivityResult
        }

        Log.d("CreateEvent_PosterUpload", "Storage is available, starting upload coroutine")
        posterUploadError = null
        posterUploadInProgress = true
        coroutineScope.launch {
            var inputStream: java.io.InputStream? = null
            try {
                val contentResolver = context.contentResolver
                Log.d("CreateEvent_PosterUpload", "Content resolver obtained: $contentResolver")

                // Read the file into a byte array to avoid URI permission issues
                Log.d("CreateEvent_PosterUpload", "Attempting to open input stream for URI: $uri")
                inputStream = contentResolver.openInputStream(uri)
                if (inputStream == null) {
                    Log.e("CreateEvent_PosterUpload", "Failed to open input stream - URI not accessible")
                    posterUploadError = "Cannot read selected image. Please try a different image or use a direct URL."
                    posterUploadInProgress = false
                    return@launch
                }
                Log.d("CreateEvent_PosterUpload", "Input stream opened successfully")

                // Copy the entire stream into a byte array buffer
                Log.d("CreateEvent_PosterUpload", "Reading bytes from input stream...")
                val byteArray = inputStream.readBytes()
                Log.d("CreateEvent_PosterUpload", "Bytes read successfully. Size: ${byteArray.size} bytes")
                inputStream.close()
                inputStream = null

                if (byteArray.isEmpty()) {
                    Log.e("CreateEvent_PosterUpload", "Byte array is empty - image has no data")
                    posterUploadError = "Selected image is empty. Please try a different image."
                    posterUploadInProgress = false
                    return@launch
                }

                // Upload the byte array to Firebase Storage
                val fileName = "event_posters/${UUID.randomUUID()}.jpg"
                Log.d("CreateEvent_PosterUpload", "Uploading to Firebase Storage path: $fileName")
                val ref = storage.reference.child(fileName)
                Log.d("CreateEvent_PosterUpload", "Storage reference created: ${ref.path}")

                Log.d("CreateEvent_PosterUpload", "Starting putBytes upload...")
                ref.putBytes(byteArray).await()
                Log.d("CreateEvent_PosterUpload", "Upload completed successfully!")

                Log.d("CreateEvent_PosterUpload", "Fetching download URL...")
                val downloadUrl = ref.downloadUrl.await().toString()
                Log.d("CreateEvent_PosterUpload", "Download URL obtained: $downloadUrl")

                posterUrl = downloadUrl
                posterUrlInput = downloadUrl
                Log.d("CreateEvent_PosterUpload", "=== CREATE EVENT POSTER UPLOAD SUCCESS ===")
            } catch (t: Throwable) {
                Log.e("CreateEvent_PosterUpload", "=== CREATE EVENT POSTER UPLOAD FAILED ===", t)
                Log.e("CreateEvent_PosterUpload", "Exception type: ${t.javaClass.simpleName}")
                Log.e("CreateEvent_PosterUpload", "Exception message: ${t.message}")
                Log.e("CreateEvent_PosterUpload", "Stack trace:", t)

                posterUploadError = when {
                    t.message?.contains("object", ignoreCase = true) == true &&
                        t.message?.contains("doesn't exist", ignoreCase = true) == true ->
                        "Upload failed because the storage object was not found. Double-check your Firebase Storage bucket, or paste a public image URL below."
                    else -> "Failed to upload poster: ${t.message ?: "unknown error"}. Paste a direct image URL below."
                }
                posterUrl = null
            } finally {
                inputStream?.close()
                posterUploadInProgress = false
                Log.d("CreateEvent_PosterUpload", "Upload process completed, posterUploadInProgress set to false")
            }
        }
    }

    val isFormValid = title.isNotBlank() &&
            description.isNotBlank() &&
            location.isNotBlank() &&
            date.isNotBlank() &&
            capacity.isNotBlank() &&
            registrationStart.isNotBlank() &&
            registrationEnd.isNotBlank() &&
            waitlistLimit.isNotBlank() &&
            samplingCount.isNotBlank() &&
            !posterUploadInProgress  // Poster URL is now optional since Firebase Storage isn't configured

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

                    PosterUploadSection(
                        posterUrl = posterUrl,
                        isUploading = posterUploadInProgress,
                        canUploadPoster = storage != null,
                        manualPosterUrl = posterUrlInput,
                        onManualPosterUrlChange = { value ->
                            posterUrlInput = value
                            posterUrl = value.trim().takeIf { it.isNotBlank() }
                        },
                        onSelectPoster = {
                            posterPicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        onRemovePoster = {
                            posterUrl = null
                            posterUrlInput = ""
                        }
                    )

                    if (posterUploadError != null) {
                        Text(
                            text = posterUploadError ?: "",
                            color = PluckPalette.Decline,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

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

                    CreateEventFormField(
                        value = registrationStart,
                        onValueChange = { registrationStart = it },
                        label = "Registration Opens",
                        icon = Icons.Outlined.Schedule,
                        placeholder = "YYYY-MM-DD"
                    )

                    CreateEventFormField(
                        value = registrationEnd,
                        onValueChange = { registrationEnd = it },
                        label = "Registration Closes",
                        icon = Icons.Outlined.Schedule,
                        placeholder = "YYYY-MM-DD"
                    )

                    CreateEventFormField(
                        value = waitlistLimit,
                        onValueChange = { waitlistLimit = it },
                        label = "Waitlist Limit",
                        icon = Icons.Outlined.Groups,
                        placeholder = "e.g., 40",
                        keyboardType = KeyboardType.Number
                    )

                    CreateEventFormField(
                        value = samplingCount,
                        onValueChange = { samplingCount = it },
                        label = "Sampling Count",
                        icon = Icons.Outlined.People,
                        placeholder = "Entrants per lottery draw",
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
                                onCreateEvent(
                                    CreateEventRequest(
                                        title = title,
                                        description = description,
                                        location = location,
                                        date = date,
                                        capacity = capacity,
                                        posterUrl = posterUrl,
                                        registrationStart = registrationStart,
                                        registrationEnd = registrationEnd,
                                        waitlistLimit = waitlistLimit,
                                        samplingCount = samplingCount
                                    )
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(28.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PluckPalette.Primary,
                            contentColor = autoTextColor(PluckPalette.Primary),
                            disabledContainerColor = PluckPalette.Muted.copy(alpha = 0.3f),
                            disabledContentColor = PluckPalette.Muted
                        ),
                        enabled = isFormValid && !isLoading,
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 16.dp)
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = autoTextColor(PluckPalette.Primary),
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
private fun PosterUploadSection(
    posterUrl: String?,
    isUploading: Boolean,
    canUploadPoster: Boolean,
    manualPosterUrl: String,
    onManualPosterUrlChange: (String) -> Unit,
    onSelectPoster: () -> Unit,
    onRemovePoster: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Event Poster",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = PluckPalette.Primary
            )
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "No poster selected yet.",
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
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
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
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = if (posterUrl == null) "Upload Poster" else "Replace Poster")
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

        OutlinedTextField(
            value = manualPosterUrl,
            onValueChange = onManualPosterUrlChange,
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

        Text(
            text = "Recommended ratio 4:3, JPEG or PNG up to 2MB.",
            style = MaterialTheme.typography.bodySmall.copy(
                color = PluckPalette.Muted
            )
        )
        if (!canUploadPoster) {
            Text(
                text = "Poster uploads unavailable in this environment. Paste a direct URL instead.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = PluckPalette.Decline
                )
            )
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
