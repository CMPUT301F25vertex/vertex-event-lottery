package com.pluck.data

import android.content.Context

class DeviceAuthPreferences(context: Context) {
    private val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun isAutoLoginEnabled(): Boolean = prefs.getBoolean(KEY_AUTO_LOGIN, false)

    fun setAutoLoginEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_AUTO_LOGIN, enabled).apply()
    }

    companion object {
        private const val PREFS_NAME = "pluck_device_auth"
        private const val KEY_AUTO_LOGIN = "auto_login_enabled"
    }
}
