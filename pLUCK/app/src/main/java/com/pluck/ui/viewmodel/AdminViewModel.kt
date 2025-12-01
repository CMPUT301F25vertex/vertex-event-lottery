package com.pluck.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.pluck.data.firebase.FirebaseUser
import com.pluck.data.firebase.OrganizerAppeal
import com.pluck.data.repository.AppealRepository
import com.pluck.data.repository.EventRepository
import com.pluck.data.repository.NotificationRepository
import com.pluck.data.repository.UserRepository
import com.pluck.ui.model.Event
import com.pluck.ui.screens.AdminStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
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
    private val appealRepository: AppealRepository = AppealRepository(),
    private val notificationRepository: NotificationRepository = NotificationRepository(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
) : ViewModel() {
    companion object {
        private const val TAG = "AdminViewModel"
    }

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

    private val _appeals = MutableStateFlow<List<OrganizerAppeal>>(emptyList())
    val appeals: StateFlow<List<OrganizerAppeal>> = _appeals.asStateFlow()

    private val _stats = MutableStateFlow(AdminStats())
    val stats: StateFlow<AdminStats> = _stats.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        observeOrganizersRealtime()
        observeAppealsRealtime()
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

    private fun observeOrganizersRealtime() {
        viewModelScope.launch {
            userRepository.observeOrganizers()
                .catch { exception ->
                    _error.value = exception.message ?: "Failed to observe organizers"
                }
                .collect { organizersList ->
                    _organizers.value = organizersList
                    updateStats()
                }
        }
    }

    private fun observeAppealsRealtime() {
        viewModelScope.launch {
            appealRepository.getAllAppeals()
                .catch { exception ->
                    _error.value = exception.message ?: "Failed to observe appeals"
                }
                .collect { appealsList ->
                    _appeals.value = appealsList
                }
        }
    }

    /** Loads all uploaded images from Firebase Storage and linked event URLs (US 03.06.01) */
    fun loadAllImages() {
        viewModelScope.launch {
            try {
                val imagesByUrl = mutableMapOf<String, ImageMetadata>()

                // 1. Load Firebase Storage images (best-effort)
                listOf("event_images", "profile_images").forEach { folder ->
                    runCatching {
                        val folderRef = storage.reference.child(folder)
                        val listResult = folderRef.listAll().await()

                        listResult.items.forEach { storageRef ->
                            runCatching {
                                val metadata = storageRef.metadata.await()
                                val downloadUrl = storageRef.downloadUrl.await().toString()
                                val image = ImageMetadata(
                                    id = storageRef.name,
                                    name = storageRef.name,
                                    url = downloadUrl,
                                    path = storageRef.path,
                                    sizeBytes = metadata.sizeBytes,
                                    contentType = metadata.contentType ?: "image/*",
                                    uploadedAt = metadata.creationTimeMillis,
                                    source = ImageSource.STORAGE
                                )
                                imagesByUrl[downloadUrl] = image
                            }.onFailure { inner ->
                                Log.w(TAG, "Failed to load storage metadata for ${storageRef.path}", inner)
                            }
                        }
                    }.onFailure { storageError ->
                        Log.w(TAG, "Failed to list Firebase Storage images under '$folder'", storageError)
                    }
                }

                // 2. Include event poster links (external URLs or storage references saved on events)
                val eventDocs = runCatching {
                    firestore.collection("events").get().await().documents
                }.getOrElse {
                    Log.w(TAG, "Failed to fetch events for image catalog", it)
                    emptyList()
                }

                eventDocs.forEach { doc ->
                    val imageUrl = doc.getString("imageUrl").orEmpty()
                    if (imageUrl.isBlank()) return@forEach

                    val title = doc.getString("title").orEmpty()
                    val updatedAt = doc.getTimestamp("updatedAt")?.toDate()?.time
                        ?: doc.getTimestamp("createdAt")?.toDate()?.time
                        ?: 0L

                    val existing = imagesByUrl[imageUrl]
                    if (existing != null) {
                        imagesByUrl[imageUrl] = existing.copy(
                            name = if (existing.name.isNotBlank()) existing.name else "Event Poster: ${title.ifBlank { doc.id }}",
                            eventId = doc.id,
                            eventTitle = title.ifBlank { null },
                            uploadedAt = maxOf(existing.uploadedAt, updatedAt),
                            source = if (existing.source == ImageSource.STORAGE) ImageSource.STORAGE else ImageSource.EVENT_LINK
                        )
                    } else {
                        imagesByUrl[imageUrl] = ImageMetadata(
                            id = "event_link_${doc.id}",
                            name = "Event Poster: ${title.ifBlank { doc.id }}",
                            url = imageUrl,
                            path = "events/${doc.id}/imageUrl",
                            sizeBytes = 0,
                            contentType = "external/url",
                            uploadedAt = updatedAt,
                            eventId = doc.id,
                            eventTitle = title.ifBlank { null },
                            source = ImageSource.EVENT_LINK
                        )
                    }
                }

                // 3. Include profile photos stored via Cloudinary (URLs on entrant profiles)
                val entrantDocs = runCatching {
                    firestore.collection("entrants").get().await().documents
                }.getOrElse {
                    Log.w(TAG, "Failed to fetch entrants for image catalog", it)
                    emptyList()
                }

                entrantDocs.forEach { doc ->
                    val profileUrl = doc.getString("profileImageUrl").orEmpty()
                    if (profileUrl.isBlank()) return@forEach

                    val displayName = doc.getString("displayName").orEmpty()
                    val updatedAt = doc.getTimestamp("updatedAt")?.toDate()?.time
                        ?: doc.getTimestamp("createdAt")?.toDate()?.time
                        ?: 0L

                    val metadataName = "Profile Photo: ${displayName.ifBlank { doc.id }}"

                    val existing = imagesByUrl[profileUrl]
                    if (existing != null) {
                        imagesByUrl[profileUrl] = existing.copy(
                            name = if (existing.name.isNotBlank()) existing.name else metadataName,
                            uploadedAt = maxOf(existing.uploadedAt, updatedAt),
                            userId = doc.id,
                            userName = displayName.ifBlank { null },
                            source = ImageSource.PROFILE
                        )
                    } else {
                        imagesByUrl[profileUrl] = ImageMetadata(
                            id = "profile_${doc.id}",
                            name = metadataName,
                            url = profileUrl,
                            path = "entrants/${doc.id}/profileImageUrl",
                            sizeBytes = 0,
                            contentType = "profile/image",
                            uploadedAt = updatedAt,
                            eventId = null,
                            eventTitle = null,
                            userId = doc.id,
                            userName = displayName.ifBlank { null },
                            source = ImageSource.PROFILE
                        )
                    }
                }

                _images.value = imagesByUrl.values
                    .sortedWith(compareByDescending<ImageMetadata> { it.uploadedAt }
                        .thenBy { it.name })
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
                }

            _isLoading.value = false
        }
    }

    /** Deletes a user profile from the system (US 03.02.01) */
    fun removeProfile(profileId: String) {
        viewModelScope.launch {
            Log.e("TTAG","Starting removal")
            _isLoading.value = true
            _error.value = null

            userRepository.deleteUser(profileId)
                .onSuccess {
                    Log.e("TTAG","Starting Load")
                    loadAllUsers()
                    Log.e("TTAG","Done Load")
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to remove profile"
                }

            _isLoading.value = false
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
                    ?: throw IllegalStateException("Image not found")

                // Remove references from events that point to this image URL
                val referencingEvents = firestore.collection("events")
                    .whereEqualTo("imageUrl", image.url)
                    .get()
                    .await()

                referencingEvents.documents.forEach { doc ->
                    doc.reference.update("imageUrl", FieldValue.delete()).await()
                }

                val referencingEntrants = firestore.collection("entrants")
                    .whereEqualTo("profileImageUrl", image.url)
                    .get()
                    .await()

                referencingEntrants.documents.forEach { doc ->
                    doc.reference.update("profileImageUrl", FieldValue.delete()).await()
                }

                if (image.source == ImageSource.STORAGE) {
                    try {
                        storage.getReferenceFromUrl(image.url).delete().await()
                    } catch (storageError: IllegalArgumentException) {
                        // URL might not map to Firebase Storage; ignore in this case
                    }
                }

                loadAllImages()
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to remove image"
            } finally {
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
                    eventRepository.deactivateEventsByOrganizer(organizerId)
                        .onFailure { exception ->
                            _error.value = exception.message ?: "Organizer removed, but failed to close events."
                        }

                    notificationRepository.deleteNotificationsForOrganizer(organizerId)
                        .onFailure { exception ->
                            _error.value = exception.message ?: "Organizer removed, but failed to clean notifications."
                        }

                    loadAllEvents()
                    loadAllOrganizers()
                    loadAllUsers()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to remove organizer"
                }

            _isLoading.value = false
        }
    }

    fun approveAppeal(appealId: String, adminId: String, adminNotes: String?) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            appealRepository.approveAppeal(appealId, adminId, adminNotes)
                .onSuccess {
                    loadAllOrganizers()
                    loadAllUsers()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to approve appeal"
                }

            _isLoading.value = false
        }
    }

    fun rejectAppeal(appealId: String, adminId: String, adminNotes: String?) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            appealRepository.rejectAppeal(appealId, adminId, adminNotes)
                .onSuccess {
                    loadAllUsers()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Failed to reject appeal"
                }

            _isLoading.value = false
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
 * @property eventId Optional event ID that references this image
 * @property eventTitle Optional event title associated with the image
 * @property source Origin of the image (Firebase Storage or external link)
 */
data class ImageMetadata(
    val id: String,
    val name: String,
    val url: String,
    val path: String,
    val sizeBytes: Long,
    val contentType: String,
    val uploadedAt: Long,
    val eventId: String? = null,
    val eventTitle: String? = null,
    val userId: String? = null,
    val userName: String? = null,
    val source: ImageSource = ImageSource.STORAGE
)

enum class ImageSource {
    STORAGE,
    EVENT_LINK,
    PROFILE
}

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
