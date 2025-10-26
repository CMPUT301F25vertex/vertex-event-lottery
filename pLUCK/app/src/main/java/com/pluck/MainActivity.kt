package com.pluck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.pluck.navigation.PLuckNavHost
import com.pluck.ui.theme.PluckTheme
import com.pluck.ui.theme.ThemePreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val darkModeFlow = MutableStateFlow(false)
    private val selectedThemeIdFlow = MutableStateFlow("blue") // Default theme

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load preferences
        val prefs = ThemePreferences(this)
        lifecycleScope.launch {
            darkModeFlow.value = prefs.isDarkModeEnabled()
            selectedThemeIdFlow.value = prefs.getSelectedThemeId()
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
                        onThemeChange = { themeId ->
                            prefs.setSelectedThemeId(themeId)
                            selectedThemeIdFlow.value = themeId
                        }
                    )
                }
            }
        }
    }
}
