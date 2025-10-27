package com.pluck.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.content.Context
import android.content.Intent
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.data.repository.UserEventHistory
import com.pluck.data.repository.WaitlistRepository
import com.pluck.data.repository.CsvExportRepository
import com.pluck.ui.model.Event
import com.pluck.ui.screens.WaitlistEntry
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * ViewModel for managing waitlist data and operations
 *
 * Provides reactive state for:
 * - Joining/leaving waitlist
 * - Accepting/declining invitations
 * - Running lottery draws
 * - Viewing waitlist and chosen entries
 * - Exporting entrant data to CSV (US 02.06.05)
 * - User waitlist status tracking
 */
class WaitlistViewModel(
    private val waitlistRepository: WaitlistRepository = WaitlistRepository(),
    private val csvExportRepository: CsvExportRepository = CsvExportRepository()
) : ViewModel() {

    private val _waitlistEntries = MutableStateFlow<List<WaitlistEntry>>(emptyList())
    val waitlistEntries: StateFlow<List<WaitlistEntry>> = _waitlistEntries.asStateFlow()

    private val _chosenEntries = MutableStateFlow<List<WaitlistEntry>>(emptyList())
    val chosenEntries: StateFlow<List<WaitlistEntry>> = _chosenEntries.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _userWaitlistEntryId = MutableStateFlow<String?>(null)
    val userWaitlistEntryId: StateFlow<String?> = _userWaitlistEntryId.asStateFlow()

    private val _userWaitlistStatus = MutableStateFlow<WaitlistStatus?>(null)
    val userWaitlistStatus: StateFlow<WaitlistStatus?> = _userWaitlistStatus.asStateFlow()

    private val _userEventHistory = MutableStateFlow<List<UserEventHistory>>(emptyList())
    val userEventHistory: StateFlow<List<UserEventHistory>> = _userEventHistory.asStateFlow()

    private var waitlistJob: Job? = null
    private var chosenJob: Job? = null

    /**
     * Observe real-time waitlist updates for an event
     */
    fun observeWaitlist(eventId: String, currentUserId: String = "") {
        if (eventId.isBlank()) return
        waitlistJob?.cancel()
        _waitlistEntries.value = emptyList()
        waitlistJob = viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            waitlistRepository.observeWaitlist(eventId, currentUserId)
                .catch { throwable ->
                    _error.value = throwable.message ?: "Failed to load waitlist"
                    _isLoading.value = false
                }
                .collect { entries ->
                    _waitlistEntries.value = entries
                    updateStatusFromEntries(entries, WaitlistStatus.WAITING)
                    _isLoading.value = false
                }
        }
    }

    /**
     * Observe real-time chosen entries for an event
     */
    fun observeChosenEntries(eventId: String, currentUserId: String = "") {
        if (eventId.isBlank()) return
        chosenJob?.cancel()
        _chosenEntries.value = emptyList()
        chosenJob = viewModelScope.launch {
            waitlistRepository.observeChosenEntries(eventId, currentUserId)
                .catch { throwable ->
                    _error.value = throwable.message ?: "Failed to load selected entrants"
                }
                .collect { entries ->
                    _chosenEntries.value = entries
                    updateStatusFromEntries(entries, WaitlistStatus.SELECTED)
                }
        }
    }

    /**
     * Load waitlist entries for an event
     */
    fun loadWaitlist(eventId: String, currentUserId: String = "") {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            waitlistRepository.getWaitlistEntries(eventId, currentUserId)
                .onSuccess { entries ->
                    _waitlistEntries.value = entries
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to load waitlist"
                }

            _isLoading.value = false
        }
    }

    /**
     * Load chosen entries for an event
     */
    fun loadChosenEntries(eventId: String, currentUserId: String = "") {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            waitlistRepository.getChosenEntries(eventId, currentUserId)
                .onSuccess { entries ->
                    _chosenEntries.value = entries
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to load chosen entries"
                }

            _isLoading.value = false
        }
    }

    /**
     * Join an event's waitlist
     */
    fun joinWaitlist(
        eventId: String,
        userId: String,
        userName: String,
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            waitlistRepository.joinWaitlist(eventId, userId, userName)
                .onSuccess { entryId ->
                    _userWaitlistEntryId.value = entryId
                    _userWaitlistStatus.value = WaitlistStatus.WAITING
                    runCatching { refreshMembership(eventId, userId, entryId) }
                    onSuccess()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to join waitlist"
                }

            _isLoading.value = false
        }
    }

    /**
     * Sign up directly for an event when seats are available
     */
    fun signUpForEvent(
        eventId: String,
        userId: String,
        userName: String,
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            waitlistRepository.signUpDirect(eventId, userId, userName)
                .onSuccess { entryId ->
                    _userWaitlistEntryId.value = entryId
                    _userWaitlistStatus.value = WaitlistStatus.ACCEPTED
                    runCatching {
                        refreshMembership(
                            eventId = eventId,
                            userId = userId,
                            fallbackEntryId = entryId,
                            overrideStatus = WaitlistStatus.ACCEPTED
                        )
                    }
                    onSuccess()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to sign up for event"
                }

            _isLoading.value = false
        }
    }

    /**
     * Leave waitlist
     */
    fun leaveWaitlist(waitlistEntryId: String, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            waitlistRepository.leaveWaitlist(waitlistEntryId)
                .onSuccess {
                    _userWaitlistEntryId.value = null
                    _userWaitlistStatus.value = null
                    onSuccess()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to leave waitlist"
                }

            _isLoading.value = false
        }
    }

    /**
     * Accept lottery invitation
     */
    fun acceptInvitation(waitlistEntryId: String, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            waitlistRepository.acceptInvitation(waitlistEntryId)
                .onSuccess {
                    _userWaitlistStatus.value = WaitlistStatus.ACCEPTED
                    onSuccess()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to accept invitation"
                }

            _isLoading.value = false
        }
    }

    /**
     * Decline lottery invitation
     */
    fun declineInvitation(
        waitlistEntryId: String,
        event: Event? = null,
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            waitlistRepository.declineInvitation(
                waitlistEntryId = waitlistEntryId,
                drawReplacement = true,
                event = event
            )
                .onSuccess {
                    _userWaitlistEntryId.value = null
                    _userWaitlistStatus.value = null
                    onSuccess()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to decline invitation"
                }

            _isLoading.value = false
        }
    }

    private suspend fun refreshMembership(
        eventId: String,
        userId: String,
        fallbackEntryId: String? = null,
        overrideStatus: WaitlistStatus? = null
    ) {
        val membershipResult = waitlistRepository.getUserWaitlistMembership(eventId, userId)
        membershipResult
            .onSuccess { membership ->
                _userWaitlistEntryId.value = membership?.entryId ?: fallbackEntryId
                _userWaitlistStatus.value = overrideStatus ?: membership?.status
            }
            .onFailure {
                if (fallbackEntryId == null) {
                    _userWaitlistEntryId.value = null
                    _userWaitlistStatus.value = null
                }
            }
    }

    private fun updateStatusFromEntries(entries: List<WaitlistEntry>, status: WaitlistStatus) {
        val currentEntry = entries.firstOrNull { it.isCurrentUser }
        if (currentEntry != null) {
            _userWaitlistEntryId.value = currentEntry.id
            _userWaitlistStatus.value = status
        } else if (_userWaitlistStatus.value == status) {
            _userWaitlistEntryId.value = null
            _userWaitlistStatus.value = null
        }
    }

    /**
     * Run lottery to select winners and notify them
     */
    fun runLottery(eventId: String, numberOfWinners: Int, event: Event? = null, onSuccess: (List<String>) -> Unit = {}) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            waitlistRepository.runLottery(eventId, numberOfWinners, event)
                .onSuccess { selectedIds ->
                    onSuccess(selectedIds)
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to run lottery"
                }

            _isLoading.value = false
        }
    }

    /**
     * Notify non-chosen entrants after lottery (US 01.04.02)
     */
    fun notifyNonChosenEntrants(event: Event) {
        viewModelScope.launch {
            waitlistRepository.notifyNonChosenEntrants(event.id, event)
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to notify non-chosen entrants"
                }
        }
    }

    /**
     * Export chosen entrants to CSV file for sharing (US 02.06.05)
     *
     * @param context Android context for file operations
     * @param event Event associated with the entrants
     * @param entrants List of waitlist entries to export
     * @param onSuccess Callback invoked with shareable Intent on success
     */
    fun exportChosenEntrantsToCSV(
        context: Context,
        event: Event,
        entrants: List<WaitlistEntry>,
        onSuccess: (Intent) -> Unit
    ) {
        viewModelScope.launch {
            csvExportRepository.exportAllEntrants(context, event, entrants)
                .onSuccess { intent ->
                    onSuccess(intent)
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to export CSV"
                }
        }
    }

    /**
     * Check if user is on waitlist
     */
    fun checkUserWaitlistStatus(eventId: String, userId: String) {
        viewModelScope.launch {
            waitlistRepository.getUserWaitlistMembership(eventId, userId)
                .onSuccess { membership ->
                    _userWaitlistEntryId.value = membership?.entryId
                    _userWaitlistStatus.value = membership?.status
                }
                .onFailure { exception ->
                    _error.value = exception.message
                }
        }
    }

    /**
     * Update all waitlist entries to reflect the latest entrant display name.
     */
    fun refreshEntrantDisplayName(userId: String, displayName: String) {
        val trimmedName = displayName.trim()
        if (userId.isBlank() || trimmedName.isBlank()) return

        viewModelScope.launch {
            waitlistRepository.updateEntrantName(userId, trimmedName)
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to refresh waitlist names"
                }

            _waitlistEntries.value = _waitlistEntries.value.map { entry ->
                if (entry.userId == userId) entry.copy(userName = trimmedName) else entry
            }
            _chosenEntries.value = _chosenEntries.value.map { entry ->
                if (entry.userId == userId) entry.copy(userName = trimmedName) else entry
            }
        }
    }

    /**
     * Clear error message
     */
    fun clearError() {
        _error.value = null
    }

    fun resetUserWaitlistEntry() {
        _userWaitlistEntryId.value = null
        _userWaitlistStatus.value = null
        _userEventHistory.value = emptyList()
    }

    fun loadUserEventHistory(userId: String) {
        viewModelScope.launch {
            waitlistRepository.getUserEventHistory(userId)
                .onSuccess { history -> _userEventHistory.value = history }
                .onFailure { exception -> _error.value = exception.message }
        }
    }
}
