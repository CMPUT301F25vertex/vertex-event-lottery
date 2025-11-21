package com.pluck.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository = UserRepository(),
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

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
