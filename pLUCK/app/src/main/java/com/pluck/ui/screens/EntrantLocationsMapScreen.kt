/**
 * EntrantLocationsMapScreen.kt
 *
 * Purpose: Geographic visualization of entrant locations on a map for organizers.
 * Displays where waitlist entrants joined from to help organizers understand participant distribution.
 * Implements US 02.02.02 for viewing entrant locations on a map.
 *
 * Design Pattern: Jetpack Compose Screen (MVVM)
 *
 * Outstanding Issues: None
 */
package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.model.Event

@Composable
fun EntrantLocationsMapScreen(
    event: Event,
    entrants: List<WaitlistEntry>,
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    // Filter entrants who have location data
    val entrantsWithLocation = entrants.filter { it.latitude != null && it.longitude != null }

    // Calculate the center point of all locations
    val centerPosition = if (entrantsWithLocation.isNotEmpty()) {
        val avgLat = entrantsWithLocation.mapNotNull { it.latitude }.average()
        val avgLng = entrantsWithLocation.mapNotNull { it.longitude }.average()
        LatLng(avgLat, avgLng)
    } else {
        // Default to event location if available, otherwise a default location
        LatLng(event.latitude ?: 53.5461, event.longitude ?: -113.4938) // Default to Edmonton
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(centerPosition, 10f)
    }

    PluckLayeredBackground(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                color = PluckPalette.Surface,
                shadowElevation = 12.dp,
                border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Back",
                            tint = PluckPalette.Primary
                        )
                    }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text(
                            text = "Entrant Locations",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = PluckPalette.Primary
                            )
                        )
                        Text(
                            text = event.title,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = PluckPalette.Muted
                            )
                        )
                    }
                    Text(
                        text = "${entrantsWithLocation.size} locations",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PluckPalette.Secondary
                        )
                    )
                }
            }

            // Map container
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(28.dp),
                color = PluckPalette.Surface,
                shadowElevation = 12.dp,
                border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
            ) {
                if (entrantsWithLocation.isEmpty()) {
                    // Empty state
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "No Location Data",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PluckPalette.Primary
                                )
                            )
                            Text(
                                text = "Entrants haven't shared their locations yet",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = PluckPalette.Muted
                                )
                            )
                        }
                    }
                } else {
                    // Google Map with markers
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState
                    ) {
                        // Add a marker for each entrant location
                        entrantsWithLocation.forEach { entrant ->
                            entrant.latitude?.let { lat ->
                                entrant.longitude?.let { lng ->
                                    Marker(
                                        state = MarkerState(position = LatLng(lat, lng)),
                                        title = entrant.userName,
                                        snippet = "Position: ${entrant.position}"
                                    )
                                }
                            }
                        }

                        // Add event location marker if available
                        event.latitude?.let { lat ->
                            event.longitude?.let { lng ->
                                Marker(
                                    state = MarkerState(position = LatLng(lat, lng)),
                                    title = event.title,
                                    snippet = "Event Location"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
