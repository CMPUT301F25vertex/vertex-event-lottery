package com.pluck.ui.util

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

/**
 * QRCodeGenerator.kt
 *
 * Utility for generating and parsing QR codes for event sharing.
 *
 * Features:
 * - Generate QR codes from event data using ZXing library
 * - Customize QR code size and colors
 * - High error correction level (Level H) for better scanning
 * - Parse event IDs from scanned QR code data
 *
 * Design Pattern: Utility object with static methods
 *
 * QR Code Format: pluck://event/{eventId}
 */
object QRCodeGenerator {

    /**
     * Generates a QR code bitmap from the given data
     *
     * @param data The data to encode in the QR code (typically event ID or URL)
     * @param size The size of the QR code in pixels (width and height)
     * @param foregroundColor The color of the QR code modules (default: black)
     * @param backgroundColor The background color (default: white)
     * @return Bitmap containing the QR code, or null if generation fails
     */
    fun generateQRCode(
        data: String,
        size: Int = 512,
        foregroundColor: Int = Color.BLACK,
        backgroundColor: Int = Color.WHITE
    ): Bitmap? {
        return try {
            val hints = hashMapOf<EncodeHintType, Any>().apply {
                put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H)
                put(EncodeHintType.MARGIN, 1)
            }

            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size, hints)

            val width = bitMatrix.width
            val height = bitMatrix.height
            val pixels = IntArray(width * height)

            for (y in 0 until height) {
                val offset = y * width
                for (x in 0 until width) {
                    pixels[offset + x] = if (bitMatrix.get(x, y)) {
                        foregroundColor
                    } else {
                        backgroundColor
                    }
                }
            }

            Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
                setPixels(pixels, 0, width, 0, 0, width, height)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to generate QR code", e)
            null
        }
    }

    private const val TAG = "QRCodeGenerator"

    /**
     * Generates event-specific QR code data
     *
     * @param eventId The unique event identifier
     * @return Formatted string for QR code encoding
     */
    fun generateEventQRData(eventId: String): String {
        // Format: pluck://event/{eventId}
        return "pluck://event/$eventId"
    }

    /**
     * Parses event ID from QR code data
     *
     * @param qrData The scanned QR code data
     * @return Event ID if valid format, null otherwise
     */
    fun parseEventId(qrData: String): String? {
        return if (qrData.startsWith("pluck://event/")) {
            qrData.removePrefix("pluck://event/")
        } else {
            null
        }
    }
}
