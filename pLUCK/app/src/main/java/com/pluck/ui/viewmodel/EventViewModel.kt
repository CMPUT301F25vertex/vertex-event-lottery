package com.pluck.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pluck.data.repository.EventRepository
import com.pluck.ui.model.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * ViewModel for managing event data and state
 *
 * Provides reactive state for:
 * - List of events
 * - Loading states
 * - Error handling
 * - Real-time updates
 */
class EventViewModel(
    private val eventRepository: EventRepository = EventRepository()
) : ViewModel() {

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    private val _selectedEvent = MutableStateFlow<Event?>(null)
    val selectedEvent: StateFlow<Event?> = _selectedEvent.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private var eventsJob: Job? = null
    private var selectedEventJob: Job? = null

    init {
        // Start observing events on initialization
        observeEvents()
    }

    /**
     * Observe real-time event updates
     */
    private fun observeEvents() {
        eventsJob?.cancel()
        eventsJob = viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            eventRepository.observeEvents()
                .catch { throwable ->
                    _error.value = throwable.message ?: "Failed to load events"
                    _isLoading.value = false
                }
                .collect { eventList ->
                    _events.value = eventList
                    _isLoading.value = false
                }
        }
    }

    /**
     * Load all active events
     */
    fun loadEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            eventRepository.getAllEvents()
                .onSuccess { eventList ->
                    _events.value = eventList
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to load events"
                }

            _isLoading.value = false
        }
    }

    /**
     * Load events by organizer
     */
    fun loadEventsByOrganizer(organizerId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            eventRepository.getEventsByOrganizer(organizerId)
                .onSuccess { eventList ->
                    _events.value = eventList
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to load organizer events"
                }

            _isLoading.value = false
        }
    }

    /**
     * Load available events (not full)
     */
    fun loadAvailableEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            eventRepository.getAvailableEvents()
                .onSuccess { eventList ->
                    _events.value = eventList
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to load available events"
                }

            _isLoading.value = false
        }
    }

    /**
     * Load a specific event by ID
     */
    fun loadEvent(eventId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            eventRepository.getEvent(eventId)
                .onSuccess { event ->
                    _selectedEvent.value = event
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to load event"
                }

            _isLoading.value = false
        }
    }

    /**
     * Observe a specific event for real-time updates
     */
    fun observeEvent(eventId: String) {
        if (eventId.isBlank()) return
        selectedEventJob?.cancel()
        selectedEventJob = viewModelScope.launch {
            eventRepository.observeEvent(eventId)
                .catch { throwable ->
                    _error.value = throwable.message ?: "Failed to observe event"
                }
                .collect { event ->
                    _selectedEvent.value = event
                }
        }
    }

    /**
     * Create a new event
     */
    fun createEvent(event: Event, organizerId: String, onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            eventRepository.createEvent(event, organizerId)
                .onSuccess { eventId ->
                    onSuccess(eventId)
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to create event"
                }

            _isLoading.value = false
        }
    }

    /**
     * Update an event
     */
    fun updateEvent(eventId: String, updates: Map<String, Any>, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            eventRepository.updateEvent(eventId, updates)
                .onSuccess {
                    onSuccess()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to update event"
                }

            _isLoading.value = false
        }
    }

    /**
     * Delete an event
     */
    fun deleteEvent(eventId: String, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            eventRepository.deleteEvent(eventId)
                .onSuccess {
                    onSuccess()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to delete event"
                }

            _isLoading.value = false
        }
    }

    /**
     * Run lottery draw for an event
     * Updates the event draw status to COMPLETED after successful draw
     */
    fun runDraw(
        event: Event,
        waitlistViewModel: WaitlistViewModel,
        drawSize: Int? = null,
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            // Run the lottery
            waitlistViewModel.runLottery(
                eventId = event.id,
                numberOfWinners = drawSize ?: event.samplingCount,
                event = event,
                onSuccess = { selectedIds ->
                    // Update event draw status to COMPLETED and notify non-chosen entrants
                    viewModelScope.launch {
                        eventRepository.updateEvent(
                            eventId = event.id,
                            updates = mapOf(
                                "drawStatus" to "COMPLETED",
                                "updatedAt" to com.google.firebase.firestore.FieldValue.serverTimestamp()
                            )
                        ).onSuccess {
                            // FIX: Notify non-chosen entrants (US 01.04.02)
                            viewModelScope.launch {
                                waitlistViewModel.notifyNonChosenEntrants(event)
                            }
                            _isLoading.value = false
                            onSuccess()
                        }.onFailure { exception ->
                            _isLoading.value = false
                            _error.value = exception.message ?: "Failed to update draw status"
                        }
                    }
                }
            )
        }
    }

    /**
     * Clear error message
     */
    fun clearError() {
        _error.value = null
    }
}
