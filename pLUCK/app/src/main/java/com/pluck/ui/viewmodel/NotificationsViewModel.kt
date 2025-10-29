package com.pluck.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pluck.data.repository.AcceptNotificationResult
import com.pluck.data.repository.NotificationRepository
import com.pluck.ui.model.EntrantProfile
import com.pluck.ui.model.InviteContactType
import com.pluck.ui.model.NotificationItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * ViewModel for managing notification data and user interactions
 *
 * Design Pattern: MVVM with reactive state management
 *
 * Provides reactive state for:
 * - Real-time notification list (selection, rejection, waitlist updates, organizer messages)
 * - Notification actions (accept, decline, mark read)
 * - Event invitation sending (US 02.09.01)
 * - Per-notification loading states
 * - Navigation to event details after accepting invitations
 *
 * State Management:
 * - Observes notifications in real-time via Flow from NotificationRepository
 * - Tracks individual notification processing states to prevent duplicate actions
 * - Manages invitation sending feedback (success/error messages)
 * - Handles navigation requests when user accepts invitation
 *
 * Outstanding Issues: None
 */
class NotificationsViewModel(
    private val notificationRepository: NotificationRepository = NotificationRepository()
) : ViewModel() {

    private val _notifications = MutableStateFlow<List<NotificationItem>>(emptyList())
    val notifications: StateFlow<List<NotificationItem>> = _notifications.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _processingNotificationIds = MutableStateFlow<Set<String>>(emptySet())
    val processingNotificationIds: StateFlow<Set<String>> = _processingNotificationIds.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _inviteFeedback = MutableStateFlow<InviteFeedback?>(null)
    val inviteFeedback: StateFlow<InviteFeedback?> = _inviteFeedback.asStateFlow()

    private val _isInviteInProgress = MutableStateFlow(false)
    val isInviteInProgress: StateFlow<Boolean> = _isInviteInProgress.asStateFlow()

    private val _navigateToEventDetails = MutableStateFlow<String?>(null)
    val navigateToEventDetails: StateFlow<String?> = _navigateToEventDetails.asStateFlow()

    private var observeJob: Job? = null

    fun observeNotifications(profile: EntrantProfile?) {
        observeJob?.cancel()
        if (profile == null) {
            _notifications.value = emptyList()
            _isLoading.value = false
            return
        }
        observeJob = viewModelScope.launch {
            _isLoading.value = true
            try {
                notificationRepository.observeNotifications(profile)
                    .catch {
                        _error.value = it.message
                        _isLoading.value = false
                    }
                    .collect { list ->
                        _notifications.value = list
                        if (_isLoading.value) {
                            _isLoading.value = false
                        }
                    }
            } finally {
                if (_isLoading.value) {
                    _isLoading.value = false
                }
            }
        }
    }

    fun acceptNotification(
        notification: NotificationItem,
        profile: EntrantProfile?,
        onCompleted: (String?) -> Unit = {}
    ) {
        if (profile == null) {
            _error.value = "Profile required to accept invitations."
            return
        }
        viewModelScope.launch {
            toggleProcessing(notification.id, true)
            val result = notificationRepository.acceptNotification(notification, profile)
            result.onSuccess { outcome ->
                if (outcome is AcceptNotificationResult.AlreadyParticipating && notification.eventId.isNotBlank()) {
                    _navigateToEventDetails.value = notification.eventId
                }
                onCompleted(notification.eventId.takeIf { it.isNotBlank() })
            }.onFailure {
                _error.value = it.message ?: "Failed to accept invite."
            }
            toggleProcessing(notification.id, false)
        }
    }

    fun declineNotification(
        notification: NotificationItem,
        onCompleted: (String?) -> Unit = {}
    ) {
        viewModelScope.launch {
            toggleProcessing(notification.id, true)
            val result = notificationRepository.declineNotification(notification)
            result.onSuccess {
                onCompleted(notification.eventId.takeIf { it.isNotBlank() })
            }.onFailure {
                _error.value = it.message ?: "Failed to decline invite."
            }
            toggleProcessing(notification.id, false)
        }
    }

    fun clearAllNotifications(profile: EntrantProfile?) {
        if (profile == null) {
            _error.value = "Profile required to clear notifications."
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            notificationRepository.deleteNotificationsForUser(profile.deviceId)
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to clear notifications."
                }
            _processingNotificationIds.value = emptySet()
            _isLoading.value = false
        }
    }

    fun markRead(notificationId: String) {
        viewModelScope.launch {
            notificationRepository.markRead(notificationId)
        }
    }

    fun sendInvite(eventId: String, organizerId: String, contact: String, type: InviteContactType) {
        viewModelScope.launch {
            _isInviteInProgress.value = true
            _inviteFeedback.value = null
            val result = notificationRepository.sendInvite(eventId, organizerId, contact, type)
            _inviteFeedback.value = if (result.isSuccess) {
                InviteFeedback(message = "Invite sent to $contact.")
            } else {
                InviteFeedback(message = result.exceptionOrNull()?.message ?: "Failed to send invite.", isError = true)
            }
            _isInviteInProgress.value = false
        }
    }

    fun clearFeedback() {
        _inviteFeedback.value = null
    }

    fun clearError() {
        _error.value = null
    }

    fun clearNavigationRequest() {
        _navigateToEventDetails.value = null
    }

    private fun toggleProcessing(id: String, active: Boolean) {
        val current = _processingNotificationIds.value.toMutableSet()
        if (active) {
            current.add(id)
        } else {
            current.remove(id)
        }
        _processingNotificationIds.value = current
    }
}

data class InviteFeedback(
    val message: String,
    val isError: Boolean = false
)
