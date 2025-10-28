package com.pluck.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.pluck.data.firebase.FirebaseUser
import com.pluck.data.repository.EventRepository
import com.pluck.data.repository.NotificationRepository
import com.pluck.data.repository.UserRepository
import com.pluck.ui.model.Event
import com.pluck.ui.model.NotificationItem
import com.pluck.ui.screens.AdminStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

/**
 * ViewModel for admin dashboard operations.
 * Manages platform-wide data browsing and moderation actions.
 *
 * Handles:
 * - Loading all events, users, organizers, images, and notifications
 * - Deleting events, profiles, and images
 * - Revoking organizer privileges
 * - Computing admin statistics
 */
class AdminViewModel(
    private val eventRepository: EventRepository = EventRepository(),
    private val userRepository: UserRepository = UserRepository(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
) : ViewModel() {

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    private val _users = MutableStateFlow<List<FirebaseUser>>(emptyList())
    val users: StateFlow<List<FirebaseUser>> = _users.asStateFlow()

    private val _organizers = MutableStateFlow<List<FirebaseUser>>(emptyList())
    val organizers: StateFlow<List<FirebaseUser>> = _organizers.asStateFlow()

    private val _images = MutableStateFlow<List<ImageMetadata>>(emptyList())
    val images: StateFlow<List<ImageMetadata>> = _images.asStateFlow()

    private val _notifications = MutableStateFlow<List<NotificationLog>>(emptyList())
    val notifications: StateFlow<List<NotificationLog>> = _notifications.asStateFlow()

    private val _stats = MutableStateFlow(AdminStats())
    val stats: StateFlow<AdminStats> = _stats.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadAllData()
    }

    /** Loads all admin data on initialization */
    private fun loadAllData() {
        loadAllEvents()
        loadAllUsers()
        loadAllOrganizers()
        loadAllImages()
        loadNotificationLogs()
    }

    /** Loads all events from Firestore for browsing (US 03.04.01) */
    fun loadAllEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            eventRepository.getAllEvents()
                .onSuccess { eventsList ->
                    _events.value = eventsList
                    updateStats()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to load events"
                }

            _isLoading.value = false
        }
    }

    /** Loads all user profiles for browsing (US 03.05.01) */
    fun loadAllUsers() {
        viewModelScope.launch {
            userRepository.getAllUsers()
                .onSuccess { usersList ->
                    _users.value = usersList
                    updateStats()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to load users"
                }
        }
    }

    /** Loads all users with organizer role (US 03.07.01) */
    fun loadAllOrganizers() {
        viewModelScope.launch {
            userRepository.getAllOrganizers()
                .onSuccess { organizersList ->
                    _organizers.value = organizersList
                    updateStats()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to load organizers"
                }
        }
    }

    /** Loads all uploaded images from Firebase Storage (US 03.06.01) */
    fun loadAllImages() {
        viewModelScope.launch {
            try {
                val eventImagesRef = storage.reference.child("event_images")
                val listResult = eventImagesRef.listAll().await()

                // Map storage items to metadata objects
                val imagesList = listResult.items.map { storageRef ->
                    val metadata = storageRef.metadata.await()
                    ImageMetadata(
                        id = storageRef.name,
                        name = storageRef.name,
                        url = storageRef.downloadUrl.await().toString(),
                        path = storageRef.path,
                        sizeBytes = metadata.sizeBytes,
                        contentType = metadata.contentType ?: "image/*",
                        uploadedAt = metadata.creationTimeMillis
                    )
                }

                _images.value = imagesList
                updateStats()
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load images"
            }
        }
    }

    /** Loads notification history from Firestore (US 03.08.01) */
    fun loadNotificationLogs() {
        viewModelScope.launch {
            try {
                val snapshot = firestore.collection("notifications")
                    .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                    .limit(100) // Only load the 100 most recent notifications
                    .get()
                    .await()

                // Parse each document into a notification log entry
                val logs = snapshot.documents.mapNotNull { doc ->
                    try {
                        NotificationLog(
                            id = doc.id,
                            title = doc.getString("title") ?: "",
                            subtitle = doc.getString("subtitle") ?: "",
                            userId = doc.getString("userId") ?: "",
                            eventId = doc.getString("eventId") ?: "",
                            category = doc.getString("category") ?: "",
                            status = doc.getString("status") ?: "SENT",
                            createdAt = doc.getLong("createdAtMillis") ?: 0L
                        )
                    } catch (e: Exception) {
                        null // Skip malformed entries
                    }
                }

                _notifications.value = logs
                updateStats()
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load notifications"
            }
        }
    }

    /** Deletes an event from the system (US 03.01.01) */
    fun removeEvent(eventId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            eventRepository.deleteEvent(eventId)
                .onSuccess {
                    loadAllEvents()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to remove event"
                    _isLoading.value = false
                }
        }
    }

    /** Deletes a user profile from the system (US 03.02.01) */
    fun removeProfile(profileId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            userRepository.deleteUser(profileId)
                .onSuccess {
                    loadAllUsers()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to remove profile"
                    _isLoading.value = false
                }
        }
    }

    /** Deletes an image from Firebase Storage (US 03.03.01) */
    fun removeImage(imageId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                // Find the image metadata
                val image = _images.value.find { it.id == imageId }
                if (image != null) {
                    // Delete from Firebase Storage using the URL
                    storage.getReferenceFromUrl(image.url).delete().await()
                    loadAllImages()
                } else {
                    _error.value = "Image not found"
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to remove image"
                _isLoading.value = false
            }
        }
    }

    /** Revokes organizer privileges from a user (US 03.07.01) */
    fun removeOrganizer(organizerId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            userRepository.removeOrganizerRole(organizerId)
                .onSuccess {
                    loadAllOrganizers()
                    loadAllUsers()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to remove organizer"
                    _isLoading.value = false
                }
        }
    }

    /** Recalculates admin statistics based on current data */
    private fun updateStats() {
        _stats.value = AdminStats(
            totalEvents = _events.value.size,
            totalUsers = _users.value.size,
            totalImages = _images.value.size,
            totalOrganizers = _organizers.value.size,
            totalNotifications = _notifications.value.size
        )
    }
}

/**
 * Metadata for an uploaded image in Firebase Storage.
 *
 * @property id Unique identifier (filename)
 * @property name Display name of the image
 * @property url Download URL from Firebase Storage
 * @property path Full path in Firebase Storage
 * @property sizeBytes File size in bytes
 * @property contentType MIME type (e.g., "image/jpeg")
 * @property uploadedAt Timestamp when the image was uploaded
 */
data class ImageMetadata(
    val id: String,
    val name: String,
    val url: String,
    val path: String,
    val sizeBytes: Long,
    val contentType: String,
    val uploadedAt: Long
)

/**
 * Log entry for a notification sent by the system.
 *
 * @property id Unique notification ID
 * @property title Main notification text
 * @property subtitle Secondary text
 * @property userId Recipient user ID
 * @property eventId Associated event ID
 * @property category Notification category (e.g., "SELECTION", "REJECTION")
 * @property status Delivery status (e.g., "SENT")
 * @property createdAt Timestamp when notification was created
 */
data class NotificationLog(
    val id: String,
    val title: String,
    val subtitle: String,
    val userId: String,
    val eventId: String,
    val category: String,
    val status: String,
    val createdAt: Long
)
