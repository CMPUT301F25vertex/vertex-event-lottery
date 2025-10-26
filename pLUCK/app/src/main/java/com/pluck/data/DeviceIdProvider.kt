package com.pluck.data

import android.content.Context
import android.provider.Settings
import java.util.UUID

class DeviceIdProvider(private val context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

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
