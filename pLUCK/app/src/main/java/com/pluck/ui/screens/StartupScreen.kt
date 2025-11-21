package com.pluck.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import kotlinx.coroutines.delay

@Composable
fun StartupScreen(
    hasFirebaseConnection: Boolean,
    onConnect: () -> Unit
) {
    var giveUpConnecting by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(5000)
        giveUpConnecting = true
    }

    if (hasFirebaseConnection) {
        onConnect()
    } else {
        PluckLayeredBackground(
            modifier = Modifier.fillMaxSize()
        ) {
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
                            .padding(horizontal = 28.dp, vertical = 32.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Welcome to:",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Black,
                                color = PluckPalette.Primary
                            ),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "pLuck",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                color = PluckPalette.Secondary,
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Connecting to the internet...",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = PluckPalette.Muted
                            ),
                            textAlign = TextAlign.Center
                        )
                        CircularProgressIndicator()

                        if (giveUpConnecting) {
                            Text(
                                text = "Failed to reach the database.\n Please check your internet connection and permissions.",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = PluckPalette.Decline
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}
