package com.pluck.ui.components

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.QrCode2
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pluck.DEBUG
import com.pluck.ui.util.QRCodeGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * QRCodeDisplay.kt
 *
 * Purpose: Composable components for displaying and downloading QR codes
 *
 * Features:
 * - Display QR code in a full-screen dialog with event information
 * - Download QR code to device gallery (Pictures/pLUCK_QRCodes/)
 * - Generate high-quality QR codes (800x800 pixels)
 * - Loading state while QR code is being generated
 *
 * Design Pattern: Reusable UI components with coroutine-based async operations
 *
 * Dependencies:
 * - QRCodeGenerator utility for encoding event data
 * - MediaStore API for saving to device gallery
 */

/**
 * Full-screen dialog displaying an event's QR code with download capability.
 *
 * Generates QR code asynchronously on composition. Shows loading indicator
 * while generating, then displays the QR code with download and close buttons.
 *
 * @param eventId Unique identifier for the event
 * @param eventTitle Display name of the event (shown in dialog header)
 * @param onDismiss Callback when user closes the dialog
 * @param modifier Optional modifier for the dialog surface
 */
@Composable
fun QRCodeDialog(
    eventId: String,
    eventTitle: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isDownloading by remember { mutableStateOf(false) }

    // Generate high-resolution QR code (800x800) on background thread
    LaunchedEffect(eventId) {
        withContext(Dispatchers.Default) {
            val qrData = QRCodeGenerator.generateEventQRData(eventId)
            qrBitmap = QRCodeGenerator.generateQRCode(qrData, size = 800)
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(28.dp),
            color = PluckPalette.Surface,
            shadowElevation = 24.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .widthIn(max = 420.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Event QR Code",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = PluckPalette.Primary
                    )
                )

                Text(
                    text = eventTitle,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted
                    ),
                    textAlign = TextAlign.Center
                )

                // QR Code Display
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    shape = RoundedCornerShape(16.dp),
                    color = androidx.compose.ui.graphics.Color.White,
                    border = BorderStroke(2.dp, PluckPalette.Primary.copy(alpha = 0.2f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        qrBitmap?.let { bitmap ->
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "QR Code for $eventTitle",
                                modifier = Modifier.fillMaxSize()
                            )
                        } ?: run {
                            CircularProgressIndicator(
                                modifier = Modifier.size(48.dp),
                                color = PluckPalette.Primary
                            )
                        }
                    }
                }

                Text(
                    text = "Scan this QR code to view event details",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PluckPalette.Muted
                    ),
                    textAlign = TextAlign.Center
                )

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            qrBitmap?.let { bitmap ->
                                scope.launch {
                                    isDownloading = true
                                    // Save to Pictures/pLUCK_QRCodes/ using MediaStore API
                                    val success = saveQRCodeToGallery(context, bitmap, eventTitle)
                                    isDownloading = false
                                    if (success) {
                                        if (DEBUG) Toast.makeText(
                                            context,
                                            "QR Code saved to gallery",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        if (DEBUG) Toast.makeText(
                                            context,
                                            "Failed to save QR Code",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        enabled = qrBitmap != null && !isDownloading
                    ) {
                        if (isDownloading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                Icons.Outlined.Download,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Download")
                    }

                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PluckPalette.Primary
                        )
                    ) {
                        Text("Close", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

/**
 * Button that opens the QR code dialog when clicked.
 *
 * Manages dialog visibility state internally. Typically used in event
 * detail screens to allow users to view and download event QR codes.
 *
 * @param eventId Unique identifier for the event
 * @param eventTitle Display name of the event
 * @param modifier Optional modifier for the button
 */
@Composable
fun ShowQRCodeButton(
    eventId: String,
    eventTitle: String,
    modifier: Modifier = Modifier
) {
    var showQRDialog by remember { mutableStateOf(false) }

    RoundIconButton(
        onClick = { showQRDialog = true },
        imageVector = Icons.Outlined.QrCode2,
        contentDescription = "View QR Code"
    )

    if (showQRDialog) {
        QRCodeDialog(
            eventId = eventId,
            eventTitle = eventTitle,
            onDismiss = { showQRDialog = false }
        )
    }
}

/**
 * Saves QR code bitmap to device gallery using MediaStore API.
 *
 * Creates a PNG file in Pictures/pLUCK_QRCodes/ with a timestamped filename.
 * Uses MediaStore API for scoped storage compatibility (Android 10+).
 *
 * @param context Android context for content resolver access
 * @param bitmap QR code bitmap to save
 * @param eventTitle Event name (used in filename, sanitized)
 * @return True if save succeeded, false otherwise
 */
private suspend fun saveQRCodeToGallery(
    context: Context,
    bitmap: Bitmap,
    eventTitle: String
): Boolean = withContext(Dispatchers.IO) {
    try {
        // Create timestamped filename with sanitized event title
        val filename = "QRCode_${eventTitle.replace(" ", "_")}_${System.currentTimeMillis()}.png"

        // Configure MediaStore values for scoped storage
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, filename)
            put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/pLUCK_QRCodes")
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        uri?.let {
            // Write bitmap to output stream with maximum quality
            resolver.openOutputStream(it)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }
            true
        } ?: false
    } catch (e: Exception) {
        Log.e("QRCodeDisplay", "Failed to save QR code to media store", e)
        false
    }
}
