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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

object CreateAccountTestTags {
    const val DisplayNameField = "create_account_display_name"
    const val EmailField = "create_account_email"
    const val PhoneField = "create_account_phone"
    const val AutoLoginToggle = "create_account_auto_login_toggle"
}

@Composable
fun CreateAccountScreen(
    deviceId: String?,
    isLoading: Boolean,
    errorMessage: String?,
    autoLoginEnabled: Boolean,
    onAutoLoginToggle: (Boolean) -> Unit,
    onCreateAccount: (displayName: String, email: String?, phoneNumber: String?) -> Unit,
    modifier: Modifier = Modifier
) {
    var displayName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
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
                        .padding(horizontal = 28.dp, vertical = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "pLUCK",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = PluckPalette.Primary,
                            fontWeight = FontWeight.Black
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Create Your Account",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PluckPalette.Primary
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Set up your profile to join exclusive lotteries and receive real-time selections.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PluckPalette.Muted
                        ),
                        textAlign = TextAlign.Center
                    )

                    OutlinedTextField(
                        value = displayName,
                        onValueChange = { displayName = it },
                        label = { Text("Display Name") },
                        singleLine = true,
                        enabled = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(CreateAccountTestTags.DisplayNameField),
                        shape = RoundedCornerShape(18.dp)
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email (Optional)") },
                        singleLine = true,
                        enabled = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(CreateAccountTestTags.EmailField),
                        shape = RoundedCornerShape(18.dp)
                    )

                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("Phone Number (Optional)") },
                        singleLine = true,
                        enabled = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(CreateAccountTestTags.PhoneField),
                        shape = RoundedCornerShape(18.dp)
                    )

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
                                .padding(horizontal = 20.dp, vertical = 14.dp),
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
                        text = "Sign in automatically next time you launch the app.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Muted
                        )
                    )
                }
                Switch(
                    checked = autoLoginEnabled,
                    onCheckedChange = onAutoLoginToggle,
                    modifier = Modifier.testTag(CreateAccountTestTags.AutoLoginToggle),
                    enabled = !isLoading,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = PluckPalette.Primary
                    )
                            )
                        }
                    }

                    if (errorMessage != null) {
                        Text(
                            text = errorMessage,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = PluckPalette.Decline,
                                fontWeight = FontWeight.Medium
                            ),
                            textAlign = TextAlign.Center
                        )
                    }

                    Button(
                        onClick = {
                            val finalEmail = email.trim().takeIf { it.isNotBlank() }
                            val finalPhoneNumber = phoneNumber.trim().takeIf { it.isNotBlank() }
                            val finalName = displayName.trim().ifBlank {
                                "Entrant ${deviceId?.takeLast(4) ?: ""}"
                            }
                            onCreateAccount(finalName, finalEmail, finalPhoneNumber)
                        },
                        enabled = !isLoading && displayName.trim().isNotBlank(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(28.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PluckPalette.Primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = if (isLoading) "Creating Account..." else "Create Account",
                            modifier = Modifier.padding(vertical = 10.dp),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "We use your device ID to secure one entrant per device.",
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
private fun CreateAccountPreview() {
    CreateAccountScreen(
        deviceId = "38400000-8cf0-11bd-b23e-10b96e40000d",
        isLoading = false,
        errorMessage = null,
        autoLoginEnabled = true,
        onAutoLoginToggle = {},
        onCreateAccount = { _, _, _ -> }
    )
}
