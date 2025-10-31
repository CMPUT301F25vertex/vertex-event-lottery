package com.pluck.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.google.firebase.firestore.FirebaseFirestore
import com.pluck.data.cloudinary.CloudinaryConfig
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * CloudinaryUploadRepository.kt
 *
 * Purpose: Repository for handling image uploads to Cloudinary CDN and storing resulting URLs
 * in Firebase Firestore. Provides a complete pipeline for user profile pictures and event poster
 * uploads with proper error handling and progress tracking.
 *
 * Features:
 * - Upload images to Cloudinary using unsigned upload preset
 * - Store Cloudinary URLs in Firebase Firestore
 * - Support for profile images and event posters
 * - Coroutine-based async upload with proper cancellation support
 * - Automatic folder organization (profiles/, events/)
 * - Error handling and retry logic
 *
 * Design Pattern: Repository pattern for cloud storage operations.
 * Outstanding Issues: None.
 */

/**
 * Sealed class representing the result of an image upload operation.
 */
sealed class CloudinaryUploadResult {
    /**
     * Upload succeeded with the resulting Cloudinary URL.
     * @property url The public HTTPS URL of the uploaded image.
     * @property publicId The Cloudinary public ID for transformation operations.
     */
    data class Success(val url: String, val publicId: String) : CloudinaryUploadResult()

    /**
     * Upload failed with an error message.
     * @property message Human-readable error description.
     * @property throwable Optional exception that caused the failure.
     */
    data class Error(val message: String, val throwable: Throwable? = null) : CloudinaryUploadResult()
}

/**
 * Repository for managing image uploads to Cloudinary and URL storage in Firestore.
 * Handles the complete pipeline from local image selection to cloud storage and database persistence.
 *
 * @property context Application context for MediaManager initialization.
 * @property firestore Firestore instance for storing image URLs.
 */
class CloudinaryUploadRepository(
    private val context: Context,
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    init {
        // Ensure Cloudinary is initialized
        CloudinaryConfig.initialize(context)
    }

    /**
     * Uploads a user profile image to Cloudinary and updates Firestore with the URL.
     * The image is stored in the "pluck/profiles" folder with the userId as the public ID.
     *
     * @param imageUri Local URI of the selected image (from gallery or camera).
     * @param userId Unique user ID (typically device ID) for organizing the image.
     * @param updateFirestore Whether to automatically update the user's profileImageUrl in Firestore.
     * @return [CloudinaryUploadResult] indicating success or failure.
     */
    suspend fun uploadProfileImage(
        imageUri: Uri,
        userId: String,
        updateFirestore: Boolean = true
    ): CloudinaryUploadResult {
        if (userId.isBlank()) {
            return CloudinaryUploadResult.Error("User ID is required for profile upload")
        }

        val publicId = CloudinaryConfig.generatePublicId(
            CloudinaryConfig.PROFILE_IMAGES_FOLDER,
            userId
        )

        val uploadResult = uploadImage(imageUri, publicId)

        // Update Firestore with the new profile image URL if requested
        if (uploadResult is CloudinaryUploadResult.Success && updateFirestore) {
            try {
                firestore.collection("entrants")
                    .document(userId)
                    .update("profileImageUrl", uploadResult.url)
                Log.d(TAG, "Profile image URL updated in Firestore for user: $userId")
            } catch (e: Exception) {
                Log.w(TAG, "Failed to update profile image URL in Firestore", e)
                // Don't fail the upload if Firestore update fails
            }
        }

        return uploadResult
    }

    /**
     * Uploads an event poster image to Cloudinary and updates Firestore with the URL.
     * The image is stored in the "pluck/events" folder with the eventId as the public ID.
     *
     * @param imageUri Local URI of the selected poster image.
     * @param eventId Unique event ID for organizing the poster.
     * @param updateFirestore Whether to automatically update the event's imageUrl in Firestore.
     * @return [CloudinaryUploadResult] indicating success or failure.
     */
    suspend fun uploadEventPoster(
        imageUri: Uri,
        eventId: String,
        updateFirestore: Boolean = true
    ): CloudinaryUploadResult {
        if (eventId.isBlank()) {
            return CloudinaryUploadResult.Error("Event ID is required for poster upload")
        }

        val publicId = CloudinaryConfig.generatePublicId(
            CloudinaryConfig.EVENT_POSTERS_FOLDER,
            eventId
        )

        val uploadResult = uploadImage(imageUri, publicId)

        // Update Firestore with the new poster URL if requested
        if (uploadResult is CloudinaryUploadResult.Success && updateFirestore) {
            try {
                firestore.collection("events")
                    .document(eventId)
                    .update("imageUrl", uploadResult.url)
                Log.d(TAG, "Event poster URL updated in Firestore for event: $eventId")
            } catch (e: Exception) {
                Log.w(TAG, "Failed to update event poster URL in Firestore", e)
                // Don't fail the upload if Firestore update fails
            }
        }

        return uploadResult
    }

    /**
     * Core image upload method that handles the actual Cloudinary upload using unsigned preset.
     * Uses Kotlin coroutines with suspendCancellableCoroutine for proper async/await support.
     *
     * @param imageUri Local URI of the image to upload.
     * @param publicId Cloudinary public ID for the uploaded image (includes folder path).
     * @return [CloudinaryUploadResult] with the upload result.
     */
    private suspend fun uploadImage(
        imageUri: Uri,
        publicId: String
    ): CloudinaryUploadResult = suspendCancellableCoroutine { continuation ->
        try {
            Log.d(TAG, "Starting upload: $publicId from URI: $imageUri")

            val uploadPreset = CloudinaryConfig.uploadPreset
            if (uploadPreset.isBlank()) {
                if (continuation.isActive) {
                    continuation.resume(
                        CloudinaryUploadResult.Error(
                            "Cloudinary upload preset is not configured"
                        )
                    )
                }
                return@suspendCancellableCoroutine
            }

            val folder = publicId.substringBeforeLast('/', missingDelimiterValue = "")
            val leafId = publicId.substringAfterLast('/', missingDelimiterValue = "")

            // Perform upload using MediaManager
            val uploadRequest = MediaManager.get()
                .upload(imageUri)
                .unsigned(uploadPreset)

            if (folder.isNotBlank()) {
                uploadRequest.option("folder", folder)
            }

            if (CloudinaryConfig.allowPublicIdOverride && leafId.isNotBlank()) {
                uploadRequest.option("public_id", leafId)
            } else if (!CloudinaryConfig.allowPublicIdOverride && leafId.isNotBlank()) {
                Log.i(
                    TAG,
                    "Public ID override disabled by configuration; Cloudinary will assign the ID automatically"
                )
            }

            val requestId = uploadRequest
                .callback(object : UploadCallback {
                    override fun onStart(requestId: String) {
                        Log.d(TAG, "Upload started: $requestId")
                    }

                    override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {
                        val progress = (bytes.toDouble() / totalBytes.toDouble() * 100).toInt()
                        Log.d(TAG, "Upload progress: $progress% ($bytes / $totalBytes bytes)")
                    }

                    override fun onSuccess(requestId: String, resultData: Map<*, *>) {
                        Log.d(TAG, "Upload successful: $requestId")
                        Log.d(TAG, "Result data: $resultData")

                        // Extract secure_url from result
                        val secureUrl = resultData["secure_url"] as? String
                        val uploadedPublicId = resultData["public_id"] as? String

                        if (secureUrl != null && uploadedPublicId != null) {
                            if (continuation.isActive) {
                                continuation.resume(
                                    CloudinaryUploadResult.Success(
                                        url = secureUrl,
                                        publicId = uploadedPublicId
                                    )
                                )
                            }
                        } else {
                            if (continuation.isActive) {
                                continuation.resume(
                                    CloudinaryUploadResult.Error(
                                        "Upload succeeded but no URL returned in response"
                                    )
                                )
                            }
                        }
                    }

                    override fun onError(requestId: String, error: ErrorInfo) {
                        Log.e(TAG, "Upload failed: ${error.description} (code: ${error.code})")
                        if (continuation.isActive) {
                            continuation.resume(
                                CloudinaryUploadResult.Error(
                                    message = error.description ?: "Upload failed with unknown error",
                                    throwable = null
                                )
                            )
                        }
                    }

                    override fun onReschedule(requestId: String, error: ErrorInfo) {
                        Log.w(TAG, "Upload rescheduled: ${error.description}")
                    }
                })
                .dispatch()

            // Handle cancellation
            continuation.invokeOnCancellation {
                Log.d(TAG, "Upload cancelled: $requestId")
                MediaManager.get().cancelRequest(requestId)
            }

        } catch (e: Exception) {
            Log.e(TAG, "Upload initialization failed", e)
            if (continuation.isActive) {
                continuation.resumeWithException(e)
            }
        }
    }

    /**
     * Generates an optimized URL for an existing Cloudinary image.
     * Useful for creating responsive image variants without re-uploading.
     *
     * @param publicId The public ID of the image in Cloudinary.
     * @param width Optional width for responsive display.
     * @param height Optional height for responsive display.
     * @return Optimized image URL with transformations applied.
     */
    fun getOptimizedImageUrl(publicId: String, width: Int? = null, height: Int? = null): String {
        return CloudinaryConfig.getOptimizedUrl(publicId, width, height)
    }

    companion object {
        private const val TAG = "CloudinaryUploadRepo"
    }
}
