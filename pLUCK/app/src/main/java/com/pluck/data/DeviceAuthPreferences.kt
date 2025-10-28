package com.pluck.data

import android.content.Context

/**
 * DeviceAuthPreferences.kt
 *
 * Purpose: Manages user authentication preferences stored in SharedPreferences.
 * Controls whether the user should be automatically logged in on app launch, supporting
 * a seamless user experience for returning users.
 *
 * Design Pattern: Preference Manager pattern for persistent storage.
 * Outstanding Issues: None.
 */

/**
 * Manages authentication-related user preferences.
 * Stores settings in SharedPreferences for persistence across app sessions.
 *
 * @property context Application context for accessing shared preferences.
 */
class DeviceAuthPreferences(context: Context) {
    private val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /**
     * Checks if auto-login is enabled for this device.
     * @return True if the user should be automatically logged in, false otherwise.
     */
    fun isAutoLoginEnabled(): Boolean = prefs.getBoolean(KEY_AUTO_LOGIN, false)

    /**
     * Enables or disables auto-login for this device.
     * @param enabled True to enable auto-login, false to disable.
     */
    fun setAutoLoginEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_AUTO_LOGIN, enabled).apply()
    }

    companion object {
        private const val PREFS_NAME = "pluck_device_auth"
        private const val KEY_AUTO_LOGIN = "auto_login_enabled"
    }
}
