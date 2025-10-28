package com.pluck.data.cloudinary

import android.content.Context
import com.cloudinary.android.MediaManager
import com.pluck.R

/**
 * CloudinaryConfig.kt
 *
 * Purpose: Manages Cloudinary SDK initialization and configuration for image upload services.
 * Provides singleton access to MediaManager for uploading user profile pictures and event posters
 * to Cloudinary CDN. Uses unsigned upload preset for client-side uploads without exposing API secrets.
 *
 * Design Pattern: Singleton configuration pattern with lazy initialization.
 * Outstanding Issues: None.
 */

/**
 * Cloudinary configuration singleton for managing image uploads.
 * Handles SDK initialization and provides upload configuration constants.
 */
object CloudinaryConfig {

    /**
     * Cloudinary cloud name for the pLUCK application.
     */
    private const val DEFAULT_CLOUD_NAME = "pluck"

    /**
     * Unsigned upload preset for client-side uploads.
     * This preset must be configured in the Cloudinary dashboard to allow unsigned uploads.
     */
    private const val DEFAULT_UPLOAD_PRESET = "pluck_unsigned"

    /**
     * Default flag indicating whether unsigned uploads can override the public ID.
     */
    private const val DEFAULT_ALLOW_PUBLIC_ID_OVERRIDE = false

    /**
     * Cached application context for resource lookups.
     */
    private var appContext: Context? = null

    /**
     * Cloud name resolved from resources (with fallback to default constant).
     */
    val cloudName: String
        get() = resolveStringResource(
            resId = R.string.cloudinary_cloud_name,
            fallback = DEFAULT_CLOUD_NAME
        )

    /**
     * Upload preset resolved from resources (with fallback to default constant).
     */
    val uploadPreset: String
        get() = resolveStringResource(
            resId = R.string.cloudinary_upload_preset,
            fallback = DEFAULT_UPLOAD_PRESET
        )

    /**
     * Optional API key resolved from resources. Not required for unsigned uploads but supported for
     * future signed operations when the developer provides it via local.properties.
     */
    val apiKey: String?
        get() = resolveStringResource(
            resId = R.string.cloudinary_api_key,
            fallback = ""
        ).trim().takeIf { it.isNotEmpty() }

    /**
     * Whether the unsigned preset allows overriding the public ID (configurable via resources).
     */
    val allowPublicIdOverride: Boolean
        get() = appContext
            ?.resources
            ?.getBoolean(R.bool.cloudinary_allow_public_id_override)
            ?: DEFAULT_ALLOW_PUBLIC_ID_OVERRIDE

    /**
     * Folder path for user profile images in Cloudinary storage.
     */
    const val PROFILE_IMAGES_FOLDER = "pluck/profiles"

    /**
     * Folder path for event poster images in Cloudinary storage.
     */
    const val EVENT_POSTERS_FOLDER = "pluck/events"

    /**
     * Flag to track if MediaManager has been initialized.
     */
    private var isInitialized = false

    /**
     * Initializes the Cloudinary MediaManager with application configuration.
     * This method should be called once during application startup or before first upload.
     * Subsequent calls are safely ignored.
     *
     * @param context Application context for MediaManager initialization.
     */
    @Synchronized
    fun initialize(context: Context) {
        val applicationContext = context.applicationContext
        appContext = applicationContext

        val targetCloudName = cloudName
        if (targetCloudName.isBlank()) {
            android.util.Log.e(TAG, "Cloudinary cloud name is not configured; skip initialization")
            return
        }

        if (isInitialized) return

        try {
            // Initialize MediaManager with cloud name
            val config = mutableMapOf("cloud_name" to targetCloudName, "secure" to "true")
            apiKey?.let { config["api_key"] = it }
            MediaManager.init(applicationContext, config)
            isInitialized = true
        } catch (e: IllegalStateException) {
            // MediaManager.init() throws if already initialized; safe to mark as initialized
            android.util.Log.i(TAG, "MediaManager already initialized, skipping re-init")
            isInitialized = true
        } catch (e: Exception) {
            android.util.Log.e(TAG, "MediaManager initialization failed", e)
            throw e
        }
    }

    /**
     * Checks if the MediaManager has been initialized.
     * @return True if initialized, false otherwise.
     */
    fun isInitialized(): Boolean = isInitialized

    /**
     * Generates a public ID for an uploaded image based on folder and identifier.
     * Public IDs are used to organize and retrieve images from Cloudinary.
     *
     * @param folder The folder path (use PROFILE_IMAGES_FOLDER or EVENT_POSTERS_FOLDER constants).
     * @param identifier Unique identifier for the image (e.g., userId or eventId).
     * @return Formatted public ID string for Cloudinary (used as a hint; the final ID may differ if
     * unsigned uploads do not permit overriding the public ID).
     */
    fun generatePublicId(folder: String, identifier: String): String {
        return "$folder/$identifier"
    }

    /**
     * Generates a optimized URL for displaying images with transformations.
     * Applies quality optimization and format conversion for web delivery.
     *
     * @param publicId The public ID of the image in Cloudinary.
     * @param width Optional width for responsive images (null for original size).
     * @param height Optional height for responsive images (null for original size).
     * @return Optimized image URL with transformations.
     */
    fun getOptimizedUrl(publicId: String, width: Int? = null, height: Int? = null): String {
        val baseUrl = "https://res.cloudinary.com/${cloudName}/image/upload"

        val transformations = buildList {
            add("q_auto") // Automatic quality
            add("f_auto") // Automatic format (WebP, AVIF when supported)
            if (width != null && height != null) {
                add("w_$width,h_$height,c_fill") // Fill mode with crop
            } else if (width != null) {
                add("w_$width") // Width only
            } else if (height != null) {
                add("h_$height") // Height only
            }
        }.joinToString(",")

        return if (transformations.isNotEmpty()) {
            "$baseUrl/$transformations/$publicId"
        } else {
            "$baseUrl/$publicId"
        }
    }

    private fun resolveStringResource(resId: Int, fallback: String): String {
        val context = appContext
        if (context != null) {
            val resolved = context.getString(resId)
            if (!resolved.isNullOrBlank()) {
                return resolved
            }
        }
        return fallback
    }

    private const val TAG = "CloudinaryConfig"
}
