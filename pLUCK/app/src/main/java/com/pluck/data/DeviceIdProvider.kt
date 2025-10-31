package com.pluck.data

import android.content.Context
import android.provider.Settings
import java.util.UUID

/**
 * DeviceIdProvider.kt
 *
 * Purpose: Provides a stable, unique device identifier for authentication and user tracking.
 * Attempts to use the Android system's ANDROID_ID for consistency across app reinstalls when available,
 * falling back to a generated UUID stored in SharedPreferences for devices without a valid system ID.
 *
 * Design Pattern: Provider pattern for ID generation and persistence.
 * Outstanding Issues: None.
 */

/**
 * Generates and retrieves a stable device identifier.
 * Prioritizes Android's ANDROID_ID, then falls back to a persisted UUID.
 *
 * @property context Application context for accessing system settings and preferences.
 */
class DeviceIdProvider(private val context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /**
     * Retrieves the device ID, creating one if necessary.
     * Uses Android's ANDROID_ID if available, otherwise generates and persists a UUID.
     *
     * @return Stable unique identifier for this device.
     */
    fun getOrCreateId(): String {
        val systemId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        if (!systemId.isNullOrBlank() && systemId != PLACEHOLDER_ANDROID_ID) {
            return systemId
        }

        val stored = prefs.getString(KEY_GENERATED_ID, null)
        if (!stored.isNullOrBlank()) {
            return stored
        }

        val generated = UUID.randomUUID().toString()
        prefs.edit().putString(KEY_GENERATED_ID, generated).apply()
        return generated
    }

    companion object {
        private const val PREFS_NAME = "pluck_device_auth"
        private const val KEY_GENERATED_ID = "generated_device_id"
        private const val PLACEHOLDER_ANDROID_ID = "9774d56d682e549c" // legacy emulator value
    }
}
