package com.pluck.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.pluck.data.firebase.FirebaseEvent
import com.pluck.ui.model.Event
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

/**
 * Repository for event CRUD operations with Firebase Firestore
 *
 * Handles all event-related database operations including:
 * - Creating new events
 * - Reading/querying events
 * - Updating event details
 * - Deleting events
 * - Real-time event updates
 */
class EventRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val eventsCollection = firestore.collection("events")

    /**
     * Create a new event
     *
     * @param event The event to create
     * @param organizerId ID of the user creating the event
     * @return Result with the created event ID or error
     */
    suspend fun createEvent(event: Event, organizerId: String): Result<String> {
        return try {
            val firebaseEvent = FirebaseEvent.fromEvent(event, organizerId)
            val docRef = eventsCollection.document()
            val eventWithId = firebaseEvent.copy(
                id = docRef.id,
                qrCodeData = event.qrCodeData.ifEmpty { docRef.id }
            )

            docRef.set(eventWithId).await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get a single event by ID
     *
     * @param eventId The event ID
     * @return Result with the event or error
     */
    suspend fun getEvent(eventId: String): Result<Event> {
        return try {
            val snapshot = eventsCollection.document(eventId).get().await()
            val firebaseEvent = snapshot.toObject(FirebaseEvent::class.java)

            if (firebaseEvent != null) {
                Result.success(firebaseEvent.toEvent())
            } else {
                Result.failure(Exception("Event not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get all active events
     *
     * @return Result with list of events or error
     */
    suspend fun getAllEvents(): Result<List<Event>> {
        return try {
            val snapshot = eventsCollection
                .orderBy("dateTimestamp", Query.Direction.ASCENDING)
                .get()
                .await()

            val events = snapshot.documents.mapNotNull { doc ->
                doc.toObject(FirebaseEvent::class.java)
                    ?.takeIf { it.isActive }
                    ?.toEvent()
            }

            Result.success(events)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get events created by a specific organizer
     *
     * @param organizerId The organizer's user ID
     * @return Result with list of events or error
     */
    suspend fun getEventsByOrganizer(organizerId: String): Result<List<Event>> {
        return try {
            val snapshot = eventsCollection
                .whereEqualTo("organizerId", organizerId)
                .orderBy("dateTimestamp", Query.Direction.DESCENDING)
                .get()
                .await()

            val events = snapshot.documents.mapNotNull { doc ->
                doc.toObject(FirebaseEvent::class.java)
                    ?.takeIf { it.isActive }
                    ?.toEvent()
            }

            Result.success(events)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get available events (not full, accepting entrants)
     *
     * @return Result with list of available events or error
     */
    suspend fun getAvailableEvents(): Result<List<Event>> {
        return try {
            val snapshot = eventsCollection
                .orderBy("dateTimestamp", Query.Direction.ASCENDING)
                .get()
                .await()

            val events = snapshot.documents.mapNotNull { doc ->
                doc.toObject(FirebaseEvent::class.java)
                    ?.takeIf { it.isActive }
                    ?.toEvent()
            }.filter { event ->
                // Filter for events that aren't full or have waitlist space
                !event.isFull || !event.isWaitlistFull
            }

            Result.success(events)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Update an existing event
     *
     * @param eventId The event ID to update
     * @param updates Map of field names to new values
     * @return Result indicating success or error
     */
    suspend fun updateEvent(eventId: String, updates: Map<String, Any>): Result<Unit> {
        return try {
            eventsCollection.document(eventId).update(updates).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Marks all events owned by the organiser as inactive and returns their identifiers.
     */
    suspend fun deactivateEventsByOrganizer(organizerId: String): Result<List<String>> {
        if (organizerId.isBlank()) return Result.success(emptyList())

        return try {
            val snapshot = eventsCollection
                .whereEqualTo("organizerId", organizerId)
                .get()
                .await()

            if (snapshot.isEmpty) {
                return Result.success(emptyList())
            }

            val batch = firestore.batch()
            val eventIds = mutableListOf<String>()
            snapshot.documents.forEach { doc ->
                eventIds.add(doc.id)
                batch.update(
                    doc.reference,
                    mapOf(
                        "isActive" to false,
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                )
            }
            batch.commit().await()
            Result.success(eventIds)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Delete an event (soft delete by setting isActive = false)
     *
     * @param eventId The event ID to delete
     * @return Result indicating success or error
     */
    suspend fun deleteEvent(eventId: String): Result<Unit> {
        return try {
            eventsCollection.document(eventId)
                .update("isActive", false)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Observe real-time updates for all events
     *
     * @return Flow of event lists that updates in real-time
     */
    fun observeEvents(): Flow<List<Event>> = callbackFlow {
        val listener = eventsCollection
            .orderBy("dateTimestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val events = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(FirebaseEvent::class.java)
                            ?.takeIf { it.isActive }
                            ?.toEvent()
                    }
                    trySend(events)
                }
            }

        awaitClose { listener.remove() }
    }

    /**
     * Observe real-time updates for a single event
     *
     * @param eventId The event ID to observe
     * @return Flow of event that updates in real-time
     */
    fun observeEvent(eventId: String): Flow<Event?> = callbackFlow {
        val listener = eventsCollection.document(eventId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val event = snapshot.toObject(FirebaseEvent::class.java)?.toEvent()
                    trySend(event)
                }
            }

        awaitClose { listener.remove() }
    }

    /**
     * Increment enrolled count when someone joins an event
     *
     * @param eventId The event ID
     * @return Result indicating success or error
     */
    suspend fun incrementEnrolled(eventId: String): Result<Unit> {
        return try {
            firestore.runTransaction { transaction ->
                val eventRef = eventsCollection.document(eventId)
                val snapshot = transaction.get(eventRef)
                val currentEnrolled = snapshot.getLong("enrolled") ?: 0
                val capacity = snapshot.getLong("capacity") ?: 0

                if (currentEnrolled >= capacity) {
                    throw IllegalStateException("Event is full")
                }

                transaction.update(eventRef, "enrolled", currentEnrolled + 1)
            }.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Decrement enrolled count when someone leaves an event
     *
     * @param eventId The event ID
     * @return Result indicating success or error
     */
    suspend fun decrementEnrolled(eventId: String): Result<Unit> {
        return try {
            firestore.runTransaction { transaction ->
                val eventRef = eventsCollection.document(eventId)
                val snapshot = transaction.get(eventRef)
                val currentEnrolled = snapshot.getLong("enrolled") ?: 0

                if (currentEnrolled > 0) {
                    transaction.update(eventRef, "enrolled", currentEnrolled - 1)
                }
            }.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Update waitlist count
     *
     * @param eventId The event ID
     * @param count New waitlist count
     * @return Result indicating success or error
     */
    suspend fun updateWaitlistCount(eventId: String, count: Int): Result<Unit> {
        return try {
            eventsCollection.document(eventId)
                .update("waitlistCount", count)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Resets attendance counters (enrolled and waitlist) for an event.
     */
    suspend fun resetAttendance(eventId: String): Result<Unit> {
        return try {
            eventsCollection.document(eventId)
                .update(
                    mapOf(
                        "enrolled" to 0,
                        "waitlistCount" to 0,
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                )
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
