package com.pluck.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pluck.ui.components.PluckLayeredBackground
import com.pluck.ui.components.PluckPalette
import com.pluck.ui.components.ShowQRCodeButton
import com.pluck.ui.model.Event
import com.pluck.ui.model.InviteContactType
import java.time.format.DateTimeFormatter
import java.time.LocalDate
import java.time.LocalTime
import coil.compose.AsyncImage

/**
 * EventDetailScreen.kt
 *
 * Purpose: Event detail view with meticulous centering and professional polish.
 *
 * Design Pattern: Stateless composable following pLUCK design language.
 *
 * Outstanding Issues: None.
 */
@Composable
fun EventDetailScreen(
    event: Event,
    isUserOnWaitlist: Boolean,
    isUserConfirmed: Boolean,
    onJoinEvent: (Event) -> Unit,
    onLeaveWaitlist: (Event) -> Unit = {},
    onViewWaitlist: (Event) -> Unit = {},
    onBack: () -> Unit = {},
    canEditPoster: Boolean = false,
    onEditPoster: (Event) -> Unit = {},
    canInviteEntrants: Boolean = false,
    inviteFeedbackMessage: String? = null,
    inviteFeedbackIsError: Boolean = false,
    inviteInProgress: Boolean = false,
    onInviteEntrant: (String, InviteContactType) -> Unit = { _, _ -> },
    onClearInviteFeedback: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var inviteContact by rememberSaveable(event.id) { mutableStateOf("") }
    var inviteType by rememberSaveable(event.id) { mutableStateOf(InviteContactType.EMAIL) }

    LaunchedEffect(inviteFeedbackMessage, inviteFeedbackIsError) {
        if (!inviteFeedbackIsError && !inviteFeedbackMessage.isNullOrBlank()) {
            inviteContact = ""
        }
    }

    PluckLayeredBackground(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            // Back button - positioned at top left
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 24.dp, top = 24.dp)
                    .size(56.dp)
                    .zIndex(10f),
                shape = CircleShape,
                color = PluckPalette.Surface,
                contentColor = PluckPalette.Primary,
                tonalElevation = 0.dp,
                shadowElevation = 12.dp,
                onClick = onBack
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Main content column - properly centered
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp, bottom = 240.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(36.dp)) // Space for back button

                EventDetailHeroCard(
                    event = event,
                    canEditPoster = canEditPoster,
                    onEditPoster = { onEditPoster(event) }
                )

                EventDetailInfoSection(event = event)

                EventDetailDescriptionSection(description = event.description)

                EventDetailReminder(remainingSpots = event.remainingSpots, waitlistCount = event.waitlistCount)

                EventLotteryInfoCard(event = event)

                if (canInviteEntrants) {
                    OrganizerInviteCard(
                        inviteContact = inviteContact,
                        onInviteContactChange = {
                            inviteContact = it
                            onClearInviteFeedback()
                        },
                        inviteType = inviteType,
                        onInviteTypeChange = { type ->
                            inviteType = type
                            onClearInviteFeedback()
                        },
                        onSendInvite = {
                            val trimmed = inviteContact.trim()
                            if (trimmed.isNotBlank()) {
                                onClearInviteFeedback()
                                onInviteEntrant(trimmed, inviteType)
                            }
                        },
                        isSending = inviteInProgress,
                        feedbackMessage = inviteFeedbackMessage,
                        isError = inviteFeedbackIsError
                    )
                }
            }

            // Bottom action section - fixed at bottom
            EventDetailBottomActions(
                event = event,
                isUserOnWaitlist = isUserOnWaitlist,
                isUserConfirmed = isUserConfirmed,
                onJoinEvent = onJoinEvent,
                onLeaveWaitlist = onLeaveWaitlist,
                onViewWaitlist = onViewWaitlist,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            )
        }
    }
}

/**
 * Hero card with event media placeholder
 */
@Composable
private fun EventDetailHeroCard(
    event: Event,
    canEditPoster: Boolean = false,
    onEditPoster: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp)
            .zIndex(1f),
        shape = RoundedCornerShape(36.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 18.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            val hasPoster = !event.posterUrl.isNullOrBlank()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
            ) {
                if (hasPoster) {
                    AsyncImage(
                        model = event.posterUrl,
                        contentDescription = "${event.title} poster",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(PluckPalette.Secondary)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 20.dp, end = 30.dp)
                            .size(80.dp)
                            .background(
                                color = PluckPalette.Tertiary.copy(alpha = 0.4f),
                                shape = CircleShape
                            )
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(bottom = 15.dp, start = 40.dp)
                            .size(60.dp)
                            .background(
                                color = Color.White.copy(alpha = 0.25f),
                                shape = CircleShape
                            )
                    )
                    Icon(
                        imageVector = Icons.Outlined.CalendarMonth,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(64.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.35f))
                            )
                        )
                )

                if (canEditPoster) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = PluckPalette.Surface.copy(alpha = 0.95f),
                            contentColor = PluckPalette.Primary,
                            tonalElevation = 0.dp,
                            shadowElevation = 8.dp,
                            border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.08f)),
                            onClick = onEditPoster
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = "Edit poster",
                                    modifier = Modifier.size(18.dp),
                                    tint = PluckPalette.Primary
                                )
                                Text(
                                    text = "Edit Poster",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = PluckPalette.Primary
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Event title and organizer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Black,
                        color = PluckPalette.Primary,
                        fontSize = 26.sp
                    ),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Hosted by ${event.organizerName}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = PluckPalette.Muted,
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

/**
 * Event information section with date, location, and capacity
 */
@Composable
private fun EventDetailInfoSection(
    event: Event,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        shape = RoundedCornerShape(28.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            EventInfoRow(
                icon = Icons.Outlined.Schedule,
                label = event.dateLabel,
                accentColor = PluckPalette.Secondary
            )
            EventInfoRow(
                icon = Icons.Outlined.LocationOn,
                label = event.location,
                accentColor = PluckPalette.Tertiary
            )
            EventInfoRow(
                icon = Icons.Outlined.PeopleOutline,
                label = "${event.enrolled}/${event.capacity} enrolled • ${event.remainingSpots} spots left",
                accentColor = PluckPalette.Pink
            )
        }
    }
}

/**
 * Single info row with icon and text
 */
@Composable
private fun EventInfoRow(
    icon: ImageVector,
    label: String,
    accentColor: Color = PluckPalette.Secondary
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(42.dp),
            shape = CircleShape,
            color = accentColor.copy(alpha = 0.15f),
            contentColor = accentColor,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.size(14.dp))
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = PluckPalette.Primary,
                fontWeight = FontWeight.Medium
                            )
                    )
                }

            }

/**
 * Description section
 */
@Composable
private fun EventDetailDescriptionSection(
    description: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        shape = RoundedCornerShape(28.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "About This Event",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = PluckPalette.Muted,
                    lineHeight = 22.sp
                )
            )
        }
    }
}

/**
 * Reminder callout with waitlist info
 */
@Composable
private fun EventDetailReminder(
    remainingSpots: Int,
    waitlistCount: Int = 0,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Lottery Notice
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = PluckPalette.Tertiary.copy(alpha = 0.12f),
            border = BorderStroke(1.5.dp, PluckPalette.Tertiary.copy(alpha = 0.25f)),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    color = PluckPalette.Tertiary.copy(alpha = 0.25f),
                    contentColor = PluckPalette.Tertiary
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarMonth,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Lottery Draw Notice",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = PluckPalette.Primary
                        )
                    )
                    Text(
                        text = "${remainingSpots.coerceAtLeast(0)} seats • ${waitlistCount} on waitlist",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = PluckPalette.Primary.copy(alpha = 0.75f),
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun EventLotteryInfoCard(
    event: Event,
    modifier: Modifier = Modifier
) {
    val dateFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
    fun formatDateTime(date: LocalDate?, time: LocalTime?): String? {
        if (date == null) return null
        val dateText = date.format(dateFormatter)
        val timeText = time?.format(timeFormatter)
        return timeText?.let { "$dateText • $it" } ?: dateText
    }

    val registrationStartText = formatDateTime(event.registrationStart, event.registrationStartTime)
    val registrationEndText = formatDateTime(event.registrationEnd, event.registrationEndTime)

    val registrationLabel = when {
        registrationStartText != null && registrationEndText != null ->
            "$registrationStartText – $registrationEndText"
        registrationStartText != null ->
            "Opens $registrationStartText"
        registrationEndText != null ->
            "Closes $registrationEndText"
        else -> "Registration open until the organizer closes the waitlist."
    }
    val samplingLabel = if (event.samplingCount > 0) {
        "Lottery randomly selects ${event.samplingCount} entrant${if (event.samplingCount == 1) "" else "s"} each run. Unselected entrants remain in queue order for the next draw."
    } else {
        "Lottery selects entrants at random when the organizer runs a draw."
    }
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        shape = RoundedCornerShape(28.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Lottery & Registration",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
            EventInfoRow(
                icon = Icons.Outlined.Schedule,
                label = registrationLabel,
                accentColor = PluckPalette.Secondary
            )
            EventInfoRow(
                icon = Icons.Outlined.PeopleOutline,
                label = samplingLabel,
                accentColor = PluckPalette.Tertiary
            )
            EventInfoRow(
                icon = Icons.Outlined.Groups,
                label = "Waitlist capped at ${event.waitlistCapacity} entrants.",
                accentColor = PluckPalette.Pink
            )
        }
    }
}

/**
 * Organizer-only card that allows direct invites by email or phone.
 */
@Composable
private fun OrganizerInviteCard(
    inviteContact: String,
    onInviteContactChange: (String) -> Unit,
    inviteType: InviteContactType,
    onInviteTypeChange: (InviteContactType) -> Unit,
    onSendInvite: () -> Unit,
    isSending: Boolean,
    feedbackMessage: String?,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        shape = RoundedCornerShape(28.dp),
        color = PluckPalette.Surface,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Invite Participants",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PluckPalette.Primary
                )
            )
            InviteTypeToggle(
                selected = inviteType,
                onSelect = onInviteTypeChange
            )
            OutlinedTextField(
                value = inviteContact,
                onValueChange = onInviteContactChange,
                label = {
                    Text(
                        if (inviteType == InviteContactType.EMAIL) "Email address" else "Phone number"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = if (inviteType == InviteContactType.EMAIL) {
                    KeyboardOptions(keyboardType = KeyboardType.Email)
                } else {
                    KeyboardOptions(keyboardType = KeyboardType.Phone)
                }
            )
            Button(
                onClick = onSendInvite,
                enabled = inviteContact.trim().isNotBlank() && !isSending,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PluckPalette.Primary,
                    contentColor = PluckPalette.Surface,
                    disabledContainerColor = PluckPalette.Primary.copy(alpha = 0.4f),
                    disabledContentColor = PluckPalette.Surface.copy(alpha = 0.8f)
                ),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                if (isSending) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(18.dp),
                        strokeWidth = 2.dp,
                        color = PluckPalette.Surface
                    )
                } else {
                    Text(
                        text = "Send Invite",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            if (!feedbackMessage.isNullOrBlank()) {
                Text(
                    text = feedbackMessage,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = if (isError) PluckPalette.Decline else PluckPalette.Accept
                    )
                )
            }
        }
    }
}

@Composable
private fun InviteTypeToggle(
    selected: InviteContactType,
    onSelect: (InviteContactType) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        InviteTypeChip(
            label = "Email",
            icon = Icons.Outlined.Email,
            selected = selected == InviteContactType.EMAIL,
            onClick = { onSelect(InviteContactType.EMAIL) }
        )
        InviteTypeChip(
            label = "Phone",
            icon = Icons.Outlined.Phone,
            selected = selected == InviteContactType.PHONE,
            onClick = { onSelect(InviteContactType.PHONE) }
        )
    }
}

@Composable
private fun RowScope.InviteTypeChip(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(24.dp),
        color = if (selected) PluckPalette.Primary else PluckPalette.Surface,
        contentColor = if (selected) PluckPalette.Surface else PluckPalette.Primary,
        tonalElevation = 0.dp,
        shadowElevation = if (selected) 8.dp else 2.dp,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = if (selected) 0.4f else 0.12f)),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

/**
 * Bottom action buttons - properly centered and fixed
 */
@Composable
private fun EventDetailBottomActions(
    event: Event,
    isUserOnWaitlist: Boolean,
    isUserConfirmed: Boolean,
    onJoinEvent: (Event) -> Unit,
    onLeaveWaitlist: (Event) -> Unit,
    onViewWaitlist: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val waitlistFull = event.isWaitlistFull
    val registrationClosed = !event.isRegistrationOpen
    val canJoinWaitlist = !isUserOnWaitlist && !isUserConfirmed && !waitlistFull && !registrationClosed

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 460.dp),
        shape = RoundedCornerShape(32.dp),
        shadowElevation = 20.dp,
        tonalElevation = 0.dp,
        color = PluckPalette.Surface,
        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.05f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when {
                isUserConfirmed -> {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        color = PluckPalette.Accept.copy(alpha = 0.15f),
                        border = BorderStroke(1.dp, PluckPalette.Accept.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = "You're confirmed for this event. Tap below if you need to release your spot.",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = PluckPalette.Accept,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
                isUserOnWaitlist -> {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        color = PluckPalette.Secondary.copy(alpha = 0.12f),
                        border = BorderStroke(1.dp, PluckPalette.Secondary.copy(alpha = 0.3f))
                    ) {
                        Text(
                            text = "You're already on this waitlist.",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = PluckPalette.Secondary,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
                registrationClosed -> {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        color = PluckPalette.Muted.copy(alpha = 0.12f),
                        border = BorderStroke(1.dp, PluckPalette.Muted.copy(alpha = 0.2f))
                    ) {
                        Text(
                            text = "Registration window has closed. Check back for future lotteries.",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = PluckPalette.Muted,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
                waitlistFull -> {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        color = PluckPalette.Muted.copy(alpha = 0.12f),
                        border = BorderStroke(1.dp, PluckPalette.Muted.copy(alpha = 0.2f))
                    ) {
                        Text(
                            text = "Waitlist is full. Check back after the next draw.",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = PluckPalette.Muted,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }

            // Hide join/leave buttons for past events
            if (!event.isPastEvent) {
                if (isUserConfirmed) {
                    Button(
                        onClick = { onLeaveWaitlist(event) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PluckPalette.Surface,
                            contentColor = PluckPalette.Primary
                        ),
                        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.4f)),
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text(
                            text = "Release My Spot",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                } else if (isUserOnWaitlist) {
                    Button(
                        onClick = { onLeaveWaitlist(event) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PluckPalette.Surface,
                            contentColor = PluckPalette.Primary
                        ),
                        border = BorderStroke(1.dp, PluckPalette.Primary.copy(alpha = 0.4f)),
                        contentPadding = PaddingValues(vertical = 14.dp)
                    ) {
                        Text(
                            text = "Leave Waitlist",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                } else {
                    Button(
                        onClick = { onJoinEvent(event) },
                        enabled = canJoinWaitlist,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PluckPalette.Primary,
                            contentColor = PluckPalette.Surface,
                            disabledContainerColor = PluckPalette.Primary.copy(alpha = 0.35f),
                            disabledContentColor = PluckPalette.Surface.copy(alpha = 0.8f)
                        ),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        val label = when {
                            waitlistFull -> "Waitlist Full"
                            else -> "Join Waitlist"
                        }
                        Text(
                            text = label,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            } else {
                // Show read-only message for past events
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    color = PluckPalette.Muted.copy(alpha = 0.1f),
                    border = BorderStroke(1.dp, PluckPalette.Muted.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = "This event has already occurred",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PluckPalette.Muted,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }

            Button(
                onClick = { onViewWaitlist(event) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PluckPalette.Secondary,
                    contentColor = PluckPalette.Surface
                ),
                border = BorderStroke(0.dp, Color.Transparent),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.PeopleOutline,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "View Waitlist",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            // QR Code Button
            ShowQRCodeButton(
                eventId = event.id,
                eventTitle = event.title,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 920)
@Composable
private fun EventDetailPreview() {
    EventDetailScreen(
        event = Event(
            id = "preview",
            title = "Swimming Lessons Event",
            description = "Join us for an exciting swimming lesson experience! Our expert instructors will guide you through fundamental techniques in a supportive environment. Perfect for beginners looking to build confidence in the water. This lottery-based registration ensures fair access for all community members.",
            location = "City Pool \u2022 123 Main Street",
            date = LocalDate.now().plusDays(4),
            capacity = 20,
            enrolled = 15,
            organizerName = "Vertex Community Center",
            posterUrl = null,
            registrationStart = LocalDate.now().minusDays(5),
            registrationEnd = LocalDate.now().plusDays(2),
            waitlistCapacity = 40,
            samplingCount = 5
        ),
        isUserOnWaitlist = false,
        isUserConfirmed = false,
        onJoinEvent = {},
        canInviteEntrants = true
    )
}
