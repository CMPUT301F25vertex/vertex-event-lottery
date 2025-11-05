package com.pluck.data

import com.google.firebase.firestore.FirebaseFirestore
import com.pluck.data.repository.EventRepository
import com.pluck.ui.model.Event
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.LocalDate

class EventRepositoryTest {
    val date = LocalDate.now()
    var testEvent = Event(
        id = "",
        title = "TestEvent",
        description = "Test Event Desc",
        location = "",
        date = date,
        capacity = 87,
        enrolled = 0,
        organizerName = ""
    )

    val collection = FirebaseFirestore.getInstance().collection("events")

    @Test
    fun createEventTest() = runTest {
        val repo = EventRepository()
        async {
            repo.createEvent(testEvent, "")
        }.await()

        val q1 = collection.whereEqualTo("title", "TestEvent").get().await()

        assert(q1.documents.size == 1)

        // Clean up
        q1.documents[0].reference.delete().await()
    }
}
