/**
 * CreateEventScreen.kt
 *
 * Purpose: Event creation screen for organizers to set up new lottery events.
 * Organizers can configure event details, capacity, registration deadlines, and event posters.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.theme.autoTextColor
import com.pluck.data.repository.CloudinaryUploadRepository
import com.pluck.data.repository.CloudinaryUploadResult
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
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
/**
 * Request object for creating a new event.
 *
 * @property requiresGeolocation Whether entrants must provide location when joining (US 02.02.03)
 */
data class CreateEventRequest(
    val title: String,
    val description: String,
    val location: String,
    val eventDate: LocalDate,
    val eventTime: LocalTime,
    val capacity: String,
    val posterUrl: String?,
    val registrationStartDate: LocalDate,
    val registrationStartTime: LocalTime,
    val registrationEndDate: LocalDate,
    val registrationEndTime: LocalTime,
    val waitlistLimit: String,
    val samplingCount: String,
    val requiresGeolocation: Boolean = false
)

object CreateEventTestTags {
    const val RegistrationOpensDate = "reg_open_date"
    const val RegistrationOpensTime = "reg_open_time"
    const val RegistrationClosesDate = "reg_open_date"
    const val RegistrationClosesTime = "reg_open_time"
    const val WaitlistLength = "waitlist_length"
}

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
    var eventDate by remember { mutableStateOf<LocalDate?>(null) }
    var eventTime by remember { mutableStateOf<LocalTime?>(null) }
    var capacity by remember { mutableStateOf("") }
    var registrationStartDate by remember { mutableStateOf<LocalDate?>(null) }
    var registrationStartTime by remember { mutableStateOf<LocalTime?>(null) }
    var registrationEndDate by remember { mutableStateOf<LocalDate?>(null) }
    var registrationEndTime by remember { mutableStateOf<LocalTime?>(null) }
    var waitlistLimit by remember { mutableStateOf("") }
    var samplingCount by remember { mutableStateOf("") }
    // Geolocation toggle - when enabled, location is MANDATORY for joining
    var requiresGeolocation by remember { mutableStateOf(true) }

    var posterUrl by remember { mutableStateOf<String?>(null) }
    var posterUrlInput by remember { mutableStateOf("") }
    var posterUploadInProgress by remember { mutableStateOf(false) }
    var posterUploadError by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val dateFormatter = remember { DateTimeFormatter.ofPattern("MMM d, yyyy") }
    val timeFormatter = remember { DateTimeFormatter.ofPattern("h:mm a") }
    val uploadRepository = remember { CloudinaryUploadRepository(context) }
    val coroutineScope = rememberCoroutineScope()

    /**
     * Image picker launcher for event poster selection.
     *
     * When an image is selected, it is automatically uploaded to Cloudinary.
     * The upload process is handled asynchronously with proper error handling
     * and user feedback via state variables (posterUploadInProgress, posterUploadError).
     */
    val posterPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        // Validate that an image was actually selected
        if (uri == null) {
            posterUploadError = "No image selected. Pick an image or paste a direct URL."
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
         * The upload is performed with updateFirestore = false because
         * we only need the URL at this stage - the full event will be
         * created later when the form is submitted.
         */
        coroutineScope.launch {
            try {
                // Generate unique identifier for this poster
                val eventId = UUID.randomUUID().toString()

                // Upload to Cloudinary and await the result
                val result = uploadRepository.uploadEventPoster(
                    imageUri = uri,
                    eventId = eventId,
                    updateFirestore = false  // Don't update Firestore yet - just get the URL
                )

                // Handle upload result
                when (result) {
                    is CloudinaryUploadResult.Success -> {
                        // Successfully uploaded - store the Cloudinary URL
                        posterUrl = result.url
                        posterUrlInput = result.url
                        posterUploadError = null
                        android.util.Log.d("CreateEventScreen", "Poster uploaded successfully: ${result.url}")
                    }
                    is CloudinaryUploadResult.Error -> {
                        // Upload failed - show user-friendly error message
                        posterUploadError = "Failed to upload poster: ${result.message}. Please try again or paste a direct image URL."
                        posterUrl = null
                        android.util.Log.e("CreateEventScreen", "Failed to upload poster: ${result.message}")
                    }
                }
            } catch (t: Throwable) {
                // Catch any unexpected exceptions
                android.util.Log.e("CreateEventScreen", "Unexpected error during poster upload", t)
                posterUploadError = "Failed to upload poster: ${t.message ?: "unknown error"}. Paste a direct image URL below."
                posterUrl = null
            } finally {
                // Always reset upload progress state
                posterUploadInProgress = false
            }
        }
    }

    val isFormValid = title.isNotBlank() &&
            description.isNotBlank() &&
            location.isNotBlank() &&
            eventDate != null &&
            eventTime != null &&
            capacity.isNotBlank() &&
            registrationStartDate != null &&
            registrationStartTime != null &&
            registrationEndDate != null &&
            registrationEndTime != null &&
            samplingCount.isNotBlank() &&
            !posterUploadInProgress  // Wait for Cloudinary upload to complete before allowing submission

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
                        canUploadPoster = true,  // Cloudinary is always available
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
                        placeholder = "e.g., Swimming Lessons",
                        isRequired = true
                    )

                    CreateEventFormField(
                        value = description,
                        onValueChange = { description = it },
                        label = "Description",
                        icon = Icons.Outlined.Description,
                        placeholder = "Tell attendees what to expect...",
                        maxLines = 4,
                        isRequired = true
                    )

                    CreateEventFormField(
                        value = location,
                        onValueChange = { location = it },
                        label = "Location",
                        icon = Icons.Outlined.LocationOn,
                        placeholder = "e.g., City Pool, 123 Main St",
                        isRequired = true
                    )

                    Text(
                        text = "Event Schedule",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PluckPalette.Primary
                        )
                    )

                    CreateEventPickerField(
                        label = "Event Date",
                        value = eventDate?.format(dateFormatter),
                        placeholder = "Select date",
                        icon = Icons.Outlined.CalendarMonth,
                        onClick = {
                            val initialDate = eventDate ?: LocalDate.now()
                            DatePickerDialog(
                                context,
                                { _, year, month, dayOfMonth ->
                                    eventDate = LocalDate.of(year, month + 1, dayOfMonth)
                                    if (eventTime == null) {
                                        eventTime = LocalTime.of(9, 0)
                                    }
                                },
                                initialDate.year,
                                initialDate.monthValue - 1,
                                initialDate.dayOfMonth
                            ).show()
                        },
                        isRequired = true
                    )

                    CreateEventPickerField(
                        label = "Event Start Time",
                        value = eventTime?.format(timeFormatter),
                        placeholder = "Select time",
                        icon = Icons.Outlined.Schedule,
                        onClick = {
                            val initialTime = eventTime ?: LocalTime.of(9, 0)
                            TimePickerDialog(
                                context,
                                { _, hourOfDay, minute ->
                                    eventTime = LocalTime.of(hourOfDay, minute)
                                },
                                initialTime.hour,
                                initialTime.minute,
                                false
                            ).show()
                        },
                        isRequired = true
                    )

                    CreateEventFormField(
                        value = capacity,
                        onValueChange = { capacity = it },
                        label = "Capacity",
                        icon = Icons.Outlined.People,
                        placeholder = "e.g., 20",
                        keyboardType = KeyboardType.Number,
                        isRequired = true
                    )

                    Text(
                        text = "Registration Window",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PluckPalette.Primary
                        )
                    )

                    CreateEventPickerField(
                        label = "Registration Opens (Date)",
                        value = registrationStartDate?.format(dateFormatter),
                        placeholder = "Select date",
                        icon = Icons.Outlined.CalendarMonth,
                        onClick = {
                            val baseDate = registrationStartDate ?: eventDate ?: LocalDate.now()
                            DatePickerDialog(
                                context,
                                { _, year, month, dayOfMonth ->
                                    registrationStartDate = LocalDate.of(year, month + 1, dayOfMonth)
                                    if (registrationStartTime == null) {
                                        registrationStartTime = LocalTime.of(8, 0)
                                    }
                                },
                                baseDate.year,
                                baseDate.monthValue - 1,
                                baseDate.dayOfMonth
                            ).show()
                        },
                        isRequired = true
                    )

                    CreateEventPickerField(
                        label = "Registration Opens (Time)",
                        value = registrationStartTime?.format(timeFormatter),
                        placeholder = "Select time",
                        icon = Icons.Outlined.Schedule,
                        onClick = {
                            val initialTime = registrationStartTime ?: LocalTime.of(8, 0)
                            TimePickerDialog(
                                context,
                                { _, hourOfDay, minute ->
                                    registrationStartTime = LocalTime.of(hourOfDay, minute)
                                },
                                initialTime.hour,
                                initialTime.minute,
                                false
                            ).show()
                        },
                        isRequired = true
                    )

                    CreateEventPickerField(
                        label = "Registration Closes (Date)",
                        value = registrationEndDate?.format(dateFormatter),
                        placeholder = "Select date",
                        icon = Icons.Outlined.CalendarMonth,
                        onClick = {
                            val baseDate = registrationEndDate
                                ?: registrationStartDate
                                ?: eventDate
                                ?: LocalDate.now()
                            DatePickerDialog(
                                context,
                                { _, year, month, dayOfMonth ->
                                    registrationEndDate = LocalDate.of(year, month + 1, dayOfMonth)
                                    if (registrationEndTime == null) {
                                        registrationEndTime = LocalTime.of(18, 0)
                                    }
                                },
                                baseDate.year,
                                baseDate.monthValue - 1,
                                baseDate.dayOfMonth
                            ).show()
                        },
                        isRequired = true
                    )

                    CreateEventPickerField(
                        label = "Registration Closes (Time)",
                        value = registrationEndTime?.format(timeFormatter),
                        placeholder = "Select time",
                        icon = Icons.Outlined.Schedule,
                        onClick = {
                            val initialTime = registrationEndTime ?: LocalTime.of(18, 0)
                            TimePickerDialog(
                                context,
                                { _, hourOfDay, minute ->
                                    registrationEndTime = LocalTime.of(hourOfDay, minute)
                                },
                                initialTime.hour,
                                initialTime.minute,
                                false
                            ).show()
                        },
                        isRequired = true
                    )

                    CreateEventFormField(
                        value = waitlistLimit,
                        onValueChange = { waitlistLimit = it },
                        label = "Waitlist Limit",
                        icon = Icons.Outlined.Groups,
                        placeholder = "e.g., 40",
                        keyboardType = KeyboardType.Number,
                        isRequired = false,
                        modifier = Modifier.testTag(CreateEventTestTags.WaitlistLength)
                    )

                    CreateEventFormField(
                        value = samplingCount,
                        onValueChange = { samplingCount = it },
                        label = "Sampling Count",
                        icon = Icons.Outlined.People,
                        placeholder = "Entrants per lottery draw",
                        keyboardType = KeyboardType.Number,
                        isRequired = true
                    )

                    // Geolocation toggle - when enabled, location becomes MANDATORY (US 02.02.03)
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        color = PluckPalette.Secondary.copy(alpha = 0.08f),
                        border = BorderStroke(1.dp, PluckPalette.Secondary.copy(alpha = 0.2f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Require Geolocation",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = PluckPalette.Primary
                                    )
                                )
                                Text(
                                    text = "When enabled, entrants MUST share their location to join. They cannot join without location access.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = PluckPalette.Muted
                                    )
                                )
                            }
                            Switch(
                                checked = requiresGeolocation,
                                onCheckedChange = { requiresGeolocation = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = PluckPalette.Surface,
                                    checkedTrackColor = PluckPalette.Secondary,
                                    uncheckedThumbColor = PluckPalette.Surface,
                                    uncheckedTrackColor = PluckPalette.Muted.copy(alpha = 0.3f)
                                )
                            )
                        }
                    }

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

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "*",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = PluckPalette.Decline
                            )
                        )

                        Text(
                            text = "Indicates Required Fields.",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = PluckPalette.Primary
                            )
                        )
                    }

                    // Create button
                    Button(
                        onClick = {
                            if (isFormValid && !isLoading) {
                                onCreateEvent(
                                    CreateEventRequest(
                                        title = title,
                                        description = description,
                                        location = location,
                                        eventDate = eventDate!!,
                                        eventTime = eventTime!!,
                                        capacity = capacity,
                                        posterUrl = posterUrl,
                                        registrationStartDate = registrationStartDate!!,
                                        registrationStartTime = registrationStartTime!!,
                                        registrationEndDate = registrationEndDate!!,
                                        registrationEndTime = registrationEndTime!!,
                                        waitlistLimit = waitlistLimit,
                                        samplingCount = samplingCount,
                                        requiresGeolocation = requiresGeolocation
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
            text = "Recommended ratio 4:3, JPEG or PNG. Uploaded to Cloudinary CDN.",
            style = MaterialTheme.typography.bodySmall.copy(
                color = PluckPalette.Muted
            )
        )
        if (!canUploadPoster) {
            Text(
                text = "Poster uploads unavailable. Paste a direct URL instead.",
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
    keyboardType: KeyboardType = KeyboardType.Text,
    isRequired: Boolean = false,
    modifier: Modifier = Modifier
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
            if (isRequired) {
                Text(
                    text = "*",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = PluckPalette.Decline
                    )
                )
            }
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth().then(modifier),
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
@Composable
private fun CreateEventPickerField(
    label: String,
    value: String?,
    placeholder: String,
    icon: ImageVector,
    onClick: () -> Unit,
    isRequired: Boolean
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = PluckPalette.Primary
                )
            )
            if (isRequired) {
                Text(
                    text = "*",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = PluckPalette.Decline
                    )
                )
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = PluckPalette.Surface,
            tonalElevation = 0.dp,
            shadowElevation = 6.dp,
            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.06f)),
            onClick = onClick
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = PluckPalette.Primary.copy(alpha = 0.12f),
                    contentColor = PluckPalette.Primary
                ) {
                    Box(
                        modifier = Modifier.size(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Text(
                    text = value ?: placeholder,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = if (value != null) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (value != null) PluckPalette.Primary else PluckPalette.Muted
                    )
                )
            }
        }
    }
}
