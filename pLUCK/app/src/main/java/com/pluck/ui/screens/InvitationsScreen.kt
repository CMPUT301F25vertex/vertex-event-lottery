/**
 * InvitationsScreen.kt
 *
 * Purpose: Displays pending invitations for lottery-selected users.
 * Users can accept or decline their invitations from this screen.
 * Handles "too late" scenario when event fills before acceptance.
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pluck.data.firebase.EventInvitation
import com.pluck.data.firebase.InvitationStatus
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.theme.autoTextColor
import com.google.firebase.Timestamp
import com.pluck.ui.components.BackButton
import com.pluck.ui.components.RoundButton

data class InvitationWithEvent(
    val invitation: EventInvitation,
    val eventTitle: String,
    val eventDate: String,
    val eventLocation: String,
    val capacity: Int,
    val enrolled: Int
)

@Composable
fun InvitationsScreen(
    invitations: List<InvitationWithEvent> = emptyList(),
    isLoading: Boolean = false,
    onAcceptInvitation: (String) -> Unit = {},
    onDeclineInvitation: (String) -> Unit = {},
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    PluckLayeredBackground(
        modifier = modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BackButton(onBack = onBack)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                InvitationsHeader()

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(1f),
                    shape = RoundedCornerShape(36.dp),
                    color = PluckPalette.Surface,
                    tonalElevation = 0.dp,
                    shadowElevation = 12.dp,
                    border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
                ) {
                    when {
                        isLoading -> InvitationsLoadingState()
                        invitations.isEmpty() -> InvitationsEmptyState()
                        else -> InvitationsList(
                            invitations = invitations,
                            onAcceptInvitation = onAcceptInvitation,
                            onDeclineInvitation = onDeclineInvitation
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InvitationsHeader() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 18.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = PluckPalette.Secondary.copy(alpha = 0.16f),
                contentColor = PluckPalette.Secondary
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Your Invitations",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black,
                        color = PluckPalette.Primary,
                        fontSize = 24.sp
                    )
                )
                Text(
                    text = "Respond to your lottery selections",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted
                    )
                )
            }
        }
    }
}

@Composable
private fun InvitationsList(
    invitations: List<InvitationWithEvent>,
    onAcceptInvitation: (String) -> Unit,
    onDeclineInvitation: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Pending Invitations",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
        }

        items(invitations, key = { it.invitation.id }) { invitationWithEvent ->
            InvitationCard(
                invitationWithEvent = invitationWithEvent,
                onAccept = { onAcceptInvitation(invitationWithEvent.invitation.id) },
                onDecline = { onDeclineInvitation(invitationWithEvent.invitation.id) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InvitationCard(
    invitationWithEvent: InvitationWithEvent,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    var showAcceptDialog by remember { mutableStateOf(false) }
    var showDeclineDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 10.dp,
        border = BorderStroke(1.dp, PluckPalette.Secondary.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Event info
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = invitationWithEvent.eventTitle,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = PluckPalette.Primary
                    )
                )
                Text(
                    text = "${invitationWithEvent.eventDate} • ${invitationWithEvent.eventLocation}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted
                    )
                )

                // Capacity warning
                val spotsLeft = invitationWithEvent.capacity - invitationWithEvent.enrolled
                if (spotsLeft <= 5) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = PluckPalette.Secondary.copy(alpha = 0.12f),
                        border = BorderStroke(1.dp, PluckPalette.Secondary.copy(alpha = 0.3f))
                    ) {
                        Text(
                            text = "⚠️ Only $spotsLeft spot${if (spotsLeft != 1) "s" else ""} left - accept soon!",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = PluckPalette.Secondary
                            ),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            // Wave info
            Text(
                text = "Draw Wave ${invitationWithEvent.invitation.drawWave}",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = PluckPalette.Muted
                )
            )

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { showDeclineDialog = true },
                    modifier = Modifier.weight(1f).height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PluckPalette.Decline.copy(alpha = 0.12f),
                        contentColor = PluckPalette.Decline
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Decline",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Button(
                    onClick = { showAcceptDialog = true },
                    modifier = Modifier.weight(1f).height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PluckPalette.Accept,
                        contentColor = autoTextColor(PluckPalette.Accept)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Accept",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }

    // Accept confirmation dialog
    if (showAcceptDialog) {
        AlertDialog(
            onDismissRequest = { showAcceptDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = PluckPalette.Accept,
                    modifier = Modifier.size(48.dp)
                )
            },
            title = {
                Text(
                    text = "Accept Invitation?",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(
                    text = "You'll be enrolled in ${invitationWithEvent.eventTitle}. Make sure you can attend!",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showAcceptDialog = false
                        onAccept()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PluckPalette.Accept
                    )
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAcceptDialog = false }) {
                    Text("Cancel", color = PluckPalette.Muted)
                }
            }
        )
    }

    // Decline confirmation dialog
    if (showDeclineDialog) {
        AlertDialog(
            onDismissRequest = { showDeclineDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = null,
                    tint = PluckPalette.Decline,
                    modifier = Modifier.size(48.dp)
                )
            },
            title = {
                Text(
                    text = "Decline Invitation?",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(
                    text = "You'll be returned to the waiting list. Your spot will be given to another entrant.",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDeclineDialog = false
                        onDecline()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PluckPalette.Decline
                    )
                ) {
                    Text("Decline")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeclineDialog = false }) {
                    Text("Cancel", color = PluckPalette.Muted)
                }
            }
        )
    }
}

@Composable
private fun InvitationsLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = PluckPalette.Primary)
    }
}

@Composable
private fun InvitationsEmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = PluckPalette.Muted.copy(alpha = 0.12f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.HourglassEmpty,
                        contentDescription = null,
                        tint = PluckPalette.Muted,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Text(
                text = "No pending invitations",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = PluckPalette.Primary
                ),
                textAlign = TextAlign.Center
            )
            Text(
                text = "You'll see invitations here when you're selected in a lottery draw",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PluckPalette.Muted
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 920)
@Composable
private fun InvitationsScreenPreview() {
    val sampleInvitations = listOf(
        InvitationWithEvent(
            invitation = EventInvitation(
                id = "inv1",
                eventId = "event1",
                userId = "user1",
                userDisplayName = "John Doe",
                status = InvitationStatus.PENDING,
                sentAt = Timestamp.now(),
                drawWave = 1
            ),
            eventTitle = "Tech Conference 2024",
            eventDate = "Jan 15, 2024",
            eventLocation = "Convention Center",
            capacity = 50,
            enrolled = 47
        ),
        InvitationWithEvent(
            invitation = EventInvitation(
                id = "inv2",
                eventId = "event2",
                userId = "user1",
                userDisplayName = "John Doe",
                status = InvitationStatus.PENDING,
                sentAt = Timestamp.now(),
                drawWave = 2
            ),
            eventTitle = "Cooking Workshop",
            eventDate = "Jan 20, 2024",
            eventLocation = "Culinary School",
            capacity = 20,
            enrolled = 10
        )
    )

    InvitationsScreen(
        invitations = sampleInvitations
    )
}
