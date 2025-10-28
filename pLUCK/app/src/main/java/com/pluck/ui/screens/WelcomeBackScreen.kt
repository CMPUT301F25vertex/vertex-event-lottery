/**
 * WelcomeBackScreen.kt
 *
 * Purpose: Returning user welcome screen that greets users and offers auto-login configuration.
 * Provides a smooth re-entry experience for users with existing accounts.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.theme.autoTextColor

object WelcomeBackTestTags {
    const val AutoLoginToggle = "welcome_back_auto_login_toggle"
}

@Composable
fun WelcomeBackScreen(
    userName: String,
    deviceId: String?,
    isLoading: Boolean,
    autoLoginEnabled: Boolean,
    onAutoLoginToggle: (Boolean) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 440.dp),
                shape = RoundedCornerShape(36.dp),
                color = PluckPalette.Surface,
                tonalElevation = 0.dp,
                shadowElevation = 16.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 28.dp, vertical = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Welcome Back",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Black,
                            color = PluckPalette.Primary
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = PluckPalette.Secondary,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Your lotteries, notifications, and history are synced.\nTap continue to jump back in.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PluckPalette.Muted
                        ),
                        textAlign = TextAlign.Center
                    )

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        color = PluckPalette.Surface,
                        tonalElevation = 0.dp,
                        shadowElevation = 8.dp,
                        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Auto-login",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = PluckPalette.Primary
                                    )
                                )
                                Text(
                                    text = "Keep me signed in on this device.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = PluckPalette.Muted
                                    )
                                )
                            }
                            Switch(
                                checked = autoLoginEnabled,
                                onCheckedChange = onAutoLoginToggle,
                                modifier = Modifier.testTag(WelcomeBackTestTags.AutoLoginToggle),
                                enabled = !isLoading,
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = PluckPalette.Primary
                                )
                            )
                        }
                    }

                    if (!deviceId.isNullOrBlank()) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(24.dp),
                            color = PluckPalette.Primary.copy(alpha = 0.04f)
                        ) {
                            Text(
                                text = "Device ID: $deviceId",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = PluckPalette.Primary
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Button(
                        onClick = onContinue,
                        enabled = !isLoading,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(28.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PluckPalette.Primary,
                            contentColor = autoTextColor(PluckPalette.Primary)
                        )
                    ) {
                        Text(
                            text = if (isLoading) "Loading..." else "Continue",
                            modifier = Modifier.padding(vertical = 10.dp),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    Text(
                        text = "Not you? Sign out from the profile tab anytime.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Muted
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 820)
@Composable
private fun WelcomeBackPreview() {
    WelcomeBackScreen(
        userName = "Caiden Weiss",
        deviceId = "38400000-8cf0-11bd-b23e-10b96e40000d",
        isLoading = false,
        autoLoginEnabled = true,
        onAutoLoginToggle = {},
        onContinue = {}
    )
}
