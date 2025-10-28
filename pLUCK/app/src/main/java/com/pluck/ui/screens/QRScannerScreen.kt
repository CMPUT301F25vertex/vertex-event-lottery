/**
 * QRScannerScreen.kt
 *
 * Purpose: Advanced QR code scanner with camera and image upload capabilities.
 * Provides live camera scanning, gallery image upload, and manual event ID entry for accessing events.
 * Uses ML Kit for QR code detection and CameraX for camera integration.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.util.QRCodeGenerator
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * QRScannerScreen.kt
 *
 * Purpose: Advanced QR code scanner with camera and image upload
 *
 * Features:
 * - Live camera preview with QR code scanning using ML Kit
 * - Upload image from gallery and process for QR codes
 * - Manual event ID entry for direct navigation
 * - Runtime camera permission handling with user-friendly UI
 *
 * Design Pattern: Stateful composable with CameraX integration
 *
 * Dependencies:
 * - CameraX for camera preview and image analysis
 * - ML Kit Barcode Scanning for QR code detection
 * - Accompanist Permissions for runtime permission requests
 */

/**
 * QR code scanner screen with multiple input methods.
 *
 * Provides three ways to scan/enter event QR codes:
 * 1. Live camera scanning using ML Kit
 * 2. Upload image from device gallery
 * 3. Manual event ID entry
 *
 * @param onBack Callback when user navigates back
 * @param onEventScanned Callback with event ID when QR code is successfully scanned
 * @param modifier Optional modifier for the screen
 */
@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun QRScannerScreen(
    onBack: () -> Unit = {},
    onEventScanned: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    // Camera permission state for requesting/checking camera access
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    // Current scan mode (Camera, Upload, or Manual)
    var scanMode by remember { mutableStateOf(ScanMode.CAMERA) }
    var scannedCode by remember { mutableStateOf<String?>(null) }
    var manualEventId by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    // Image picker launcher for gallery selection
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            // Process selected image to extract QR code
            processImageUri(context, it) { eventId ->
                if (eventId != null) {
                    onEventScanned(eventId)
                } else {
                    showError = true
                }
            }
        }
    }

    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = PluckPalette.Primary
                    )
                }
                Text(
                    text = "Scan QR Code",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black,
                        color = PluckPalette.Primary,
                        fontSize = 24.sp
                    )
                )
                Spacer(modifier = Modifier.size(48.dp))
            }

            // Mode Selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = scanMode == ScanMode.CAMERA,
                    onClick = { scanMode = ScanMode.CAMERA },
                    label = { Text("Camera") },
                    leadingIcon = {
                        Icon(Icons.Outlined.CameraAlt, contentDescription = null, modifier = Modifier.size(18.dp))
                    },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = scanMode == ScanMode.UPLOAD,
                    onClick = { scanMode = ScanMode.UPLOAD },
                    label = { Text("Upload") },
                    leadingIcon = {
                        Icon(Icons.Outlined.Upload, contentDescription = null, modifier = Modifier.size(18.dp))
                    },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = scanMode == ScanMode.MANUAL,
                    onClick = { scanMode = ScanMode.MANUAL },
                    label = { Text("Manual") },
                    leadingIcon = {
                        Icon(Icons.Outlined.Edit, contentDescription = null, modifier = Modifier.size(18.dp))
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            // Content based on mode
            when (scanMode) {
                ScanMode.CAMERA -> {
                    if (cameraPermissionState.status.isGranted) {
                        CameraPreview(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            onQRCodeScanned = { code ->
                                scannedCode = code
                                QRCodeGenerator.parseEventId(code)?.let { eventId ->
                                    onEventScanned(eventId)
                                }
                            }
                        )
                    } else {
                        CameraPermissionRequest(
                            onRequestPermission = { cameraPermissionState.launchPermissionRequest() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                    }
                }
                ScanMode.UPLOAD -> {
                    UploadImageSection(
                        onUploadClick = { imagePicker.launch("image/*") },
                        showError = showError,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
                ScanMode.MANUAL -> {
                    ManualEntrySection(
                        eventId = manualEventId,
                        onEventIdChange = {
                            manualEventId = it
                            showError = false
                        },
                        onSubmit = {
                            if (manualEventId.isNotBlank()) {
                                onEventScanned(manualEventId.trim())
                            } else {
                                showError = true
                            }
                        },
                        showError = showError,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
            }

            // Instructions
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = PluckPalette.Secondary.copy(alpha = 0.1f),
                border = BorderStroke(1.dp, PluckPalette.Secondary.copy(alpha = 0.2f))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "How to use:",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = PluckPalette.Primary
                        )
                    )
                    Text(
                        text = when (scanMode) {
                            ScanMode.CAMERA -> "Point your camera at an event QR code to scan automatically"
                            ScanMode.UPLOAD -> "Select an image containing a QR code from your gallery"
                            ScanMode.MANUAL -> "Enter the event ID shown on the QR code or event poster"
                        },
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Muted
                        )
                    )
                }
            }
        }
    }
}

/**
 * Camera preview composable with real-time QR code scanning.
 *
 * Sets up CameraX with preview and image analysis use cases.
 * Processes each frame with ML Kit to detect QR codes.
 * Implements debouncing to prevent multiple scans of the same code.
 *
 * @param modifier Modifier for the preview surface
 * @param onQRCodeScanned Callback invoked when a QR code is detected
 */
@Composable
private fun CameraPreview(
    modifier: Modifier = Modifier,
    onQRCodeScanned: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val executor = remember { Executors.newSingleThreadExecutor() }
    // Track last scan time to debounce rapid scanning (prevent duplicate scans)
    var lastScannedTime by remember { mutableStateOf(0L) }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        border = BorderStroke(2.dp, PluckPalette.Primary.copy(alpha = 0.3f))
    ) {
        AndroidView(
            factory = { ctx ->
                val previewView = PreviewView(ctx)
                val cameraProvider = cameraProviderFuture.get()

                // Set up camera preview use case
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                // Set up image analysis use case for QR code detection
                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(executor) { imageProxy ->
                            // Process each camera frame with ML Kit barcode scanner
                            processImageProxy(imageProxy) { code ->
                                // Debounce: Only scan once every 2 seconds to prevent duplicates
                                val currentTime = System.currentTimeMillis()
                                if (currentTime - lastScannedTime > 2000) {
                                    lastScannedTime = currentTime
                                    onQRCodeScanned(code)
                                }
                            }
                        }
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    // Unbind previous use cases before binding new ones
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalyzer
                    )
                } catch (exc: Exception) {
                    Log.e("QRScannerScreen", "Failed to bind camera use cases", exc)
                }

                previewView
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * UI shown when camera permission is not granted.
 *
 * Displays an informative message and button to request camera access.
 *
 * @param onRequestPermission Callback to launch permission request
 * @param modifier Modifier for the container surface
 */
@Composable
private fun CameraPermissionRequest(
    onRequestPermission: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        border = BorderStroke(2.dp, PluckPalette.Primary.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.CameraAlt,
                contentDescription = null,
                tint = PluckPalette.Primary,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Camera Permission Required",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We need access to your camera to scan QR codes",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PluckPalette.Muted
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onRequestPermission,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PluckPalette.Primary)
            ) {
                Text("Grant Permission")
            }
        }
    }
}

/**
 * Upload mode UI for selecting images from device gallery.
 *
 * Allows users to choose an image containing a QR code to be processed.
 * Shows error message if no QR code is found in the selected image.
 *
 * @param onUploadClick Callback to launch image picker
 * @param showError Whether to show "no QR code found" error message
 * @param modifier Modifier for the container surface
 */
@Composable
private fun UploadImageSection(
    onUploadClick: () -> Unit,
    showError: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        border = BorderStroke(2.dp, PluckPalette.Primary.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Upload,
                contentDescription = null,
                tint = PluckPalette.Primary,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Upload QR Code Image",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Select an image from your gallery containing a QR code",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PluckPalette.Muted
                ),
                textAlign = TextAlign.Center
            )
            if (showError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "No QR code found in the selected image",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PluckPalette.Decline
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onUploadClick,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PluckPalette.Primary)
            ) {
                Icon(Icons.Outlined.Image, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Choose Image")
            }
        }
    }
}

/**
 * Manual entry mode UI for typing event IDs directly.
 *
 * Provides a text field for users to enter event IDs when QR scanning isn't possible.
 * Validates input and shows error for empty submissions.
 *
 * @param eventId Current text field value
 * @param onEventIdChange Callback when text field value changes
 * @param onSubmit Callback when user clicks "View Event" button
 * @param showError Whether to show validation error message
 * @param modifier Modifier for the container surface
 */
@Composable
private fun ManualEntrySection(
    eventId: String,
    onEventIdChange: (String) -> Unit,
    onSubmit: () -> Unit,
    showError: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        border = BorderStroke(2.dp, PluckPalette.Primary.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = null,
                tint = PluckPalette.Primary,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Enter Event ID",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Type the event ID manually",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PluckPalette.Muted
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = eventId,
                onValueChange = onEventIdChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Event ID") },
                placeholder = { Text("e.g., 12345") },
                singleLine = true,
                isError = showError,
                supportingText = if (showError) {
                    { Text("Please enter a valid event ID") }
                } else null
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PluckPalette.Primary)
            ) {
                Text("View Event", fontWeight = FontWeight.Bold)
            }
        }
    }
}

/**
 * Scan mode options for QR scanner screen.
 */
private enum class ScanMode {
    /** Live camera scanning with ML Kit */
    CAMERA,
    /** Upload image from device gallery */
    UPLOAD,
    /** Manual event ID text entry */
    MANUAL
}

/**
 * Processes a camera frame to detect QR codes using ML Kit.
 *
 * Converts CameraX ImageProxy to ML Kit InputImage and scans for barcodes.
 * Ensures the image proxy is closed after processing to prevent memory leaks.
 *
 * @param imageProxy Camera frame from CameraX image analysis
 * @param onQRCodeScanned Callback invoked with raw QR code data when detected
 */
@androidx.annotation.OptIn(ExperimentalGetImage::class)
private fun processImageProxy(
    imageProxy: ImageProxy,
    onQRCodeScanned: (String) -> Unit
) {
    val mediaImage = imageProxy.image
    if (mediaImage != null) {
        // Convert camera image to ML Kit format with correct rotation
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        val scanner = BarcodeScanning.getClient()

        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                // Process all detected barcodes
                for (barcode in barcodes) {
                    if (barcode.valueType == Barcode.TYPE_TEXT || barcode.valueType == Barcode.TYPE_URL) {
                        barcode.rawValue?.let { onQRCodeScanned(it) }
                    }
                }
            }
            .addOnCompleteListener {
                // Always close image proxy to release memory
                imageProxy.close()
            }
    } else {
        imageProxy.close()
    }
}

/**
 * Processes an image URI from gallery to extract QR code data.
 *
 * Loads bitmap from URI, processes with ML Kit barcode scanner,
 * and extracts event ID from QR code data.
 *
 * @param context Android context for content resolver access
 * @param uri URI of the selected image from gallery
 * @param onResult Callback with event ID (null if no valid QR code found)
 */
private fun processImageUri(
    context: Context,
    uri: Uri,
    onResult: (String?) -> Unit
) {
    try {
        // Load bitmap from URI
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()

        // Convert to ML Kit format and scan for QR codes
        val image = InputImage.fromBitmap(bitmap, 0)
        val scanner = BarcodeScanning.getClient()

        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                // Extract event ID from first detected QR code
                val qrCode = barcodes.firstOrNull()
                val eventId = qrCode?.rawValue?.let { QRCodeGenerator.parseEventId(it) }
                onResult(eventId)
            }
            .addOnFailureListener {
                onResult(null)
            }
    } catch (e: Exception) {
        Log.e("QRScannerScreen", "Failed to process image for QR codes", e)
        onResult(null)
    }
}
