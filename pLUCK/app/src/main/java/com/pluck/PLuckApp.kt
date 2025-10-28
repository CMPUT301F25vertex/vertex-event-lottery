package com.pluck

import android.app.Application
import com.pluck.data.cloudinary.CloudinaryConfig

/**
 * PLuckApp.kt
 *
 * Purpose: Application class for the pLUCK lottery event management system. Serves as the entry
 * point for global application state and dependency injection setup. Initializes Cloudinary SDK
 * for image upload services and provides foundation for future application-wide initialization
 * such as crash reporting, analytics, or custom dependency injection frameworks.
 *
 * Design Pattern: Application Singleton pattern (Android lifecycle).
 * Outstanding Issues: None.
 */

/**
 * Custom Application class for the pLUCK app.
 * Extends Android's Application to provide application-wide lifecycle management.
 * Initializes Cloudinary MediaManager on app startup for image upload capabilities.
 */
class PLuckApp : Application() {

    /**
     * Called when the application is starting, before any activity, service, or receiver objects
     * have been created. Initializes third-party SDKs and application-wide services.
     */
    override fun onCreate() {
        super.onCreate()

        // Initialize Cloudinary for image uploads
        CloudinaryConfig.initialize(this)
    }
}
