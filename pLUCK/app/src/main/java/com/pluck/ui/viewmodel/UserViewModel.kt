package com.pluck.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pluck.data.firebase.FirebaseUser
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.data.repository.UserRepository
import com.pluck.ui.model.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository = UserRepository(),
) : ViewModel() {

    private val _users = MutableStateFlow<List<FirebaseUser>>(emptyList())
    val users: StateFlow<List<FirebaseUser>> = _users.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        // Start observing users on initialization
        observeUsers()
    }

    private var usersJob: Job? = null

    /**
     * Observe real-time user updates
     */
    private fun observeUsers() {
        usersJob?.cancel()
        usersJob = viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            userRepository.observeUsers()
                .catch { throwable ->
                    _error.value = throwable.message ?: "Failed to load users"
                    _isLoading.value = false
                }
                .collect { userList ->
                    _users.value = userList
                    _isLoading.value = false
                }
        }
    }

    /**
     * Set a users FCM Token
     */
    fun setFCMToken(
        userId: String,
        token: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            userRepository.setFCMToken(userId, token)
                .onSuccess {
                    // TODO
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to set FCM token"
                }

            _isLoading.value = false
        }
    }
}
