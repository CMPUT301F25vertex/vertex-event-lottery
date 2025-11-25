package com.pluck

import android.Manifest
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FieldValue
import com.google.firebase.messaging.FirebaseMessaging
import com.pluck.data.DeviceAuthenticator
import com.pluck.data.firebase.UserRole
import com.pluck.navigation.PLuckNavHost
import com.pluck.ui.theme.PluckTheme
import com.pluck.ui.theme.ThemeManager
import com.pluck.ui.theme.ThemePreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

/**
 * Main activity for the pLUCK lottery event management app.
 * Handles theme initialization, navigation setup, and user preference persistence.
 */
class MainActivity : ComponentActivity() {
    /** Tracks dark mode state for reactive theme updates */
    private val darkModeFlow = MutableStateFlow(false)

    /** Tracks the currently selected theme (default is blue) */
    private val selectedThemeIdFlow = MutableStateFlow("blue")

    private val requestNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                Log.w("Notifications", "POST_NOTIFICATIONS permission denied by user")
            }
        }

    /**
     * Sets up theme preferences and initializes the navigation host.
     * Loads saved user preferences and applies them to the UI.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermissionIfNeeded()

        // Load saved theme preferences
        val prefs = ThemePreferences(this)
        ThemeManager.setCustomTheme(prefs.getCustomTheme())
        lifecycleScope.launch {
            darkModeFlow.value = prefs.isDarkModeEnabled()
            val storedThemeId = prefs.getSelectedThemeId()
            ThemeManager.setActiveThemeId(storedThemeId)
            selectedThemeIdFlow.value = storedThemeId
        }

        setContent {
            val isDarkMode by darkModeFlow.collectAsState()
            val selectedThemeId by selectedThemeIdFlow.collectAsState()

            PluckTheme(
                darkTheme = isDarkMode,
                themeId = selectedThemeId
            ) {
                Surface(color = Color.Transparent, modifier = Modifier) {
                    PLuckNavHost(
                        onDarkModeChange = { enabled ->
                            prefs.setDarkModeEnabled(enabled)
                            darkModeFlow.value = enabled
                        },
                        currentThemeId = selectedThemeId,
                        onThemeChange = { themeId ->
                            prefs.setSelectedThemeId(themeId)
                             ThemeManager.setActiveThemeId(themeId)
                            selectedThemeIdFlow.value = themeId
                        },
                        isDarkMode = isDarkMode
                    )
                }
            }
        }
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

        val permissionStatus = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        )

        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}
