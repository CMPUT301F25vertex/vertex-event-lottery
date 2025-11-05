package com.pluck.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.pluck.data.repository.EventRepository
import com.pluck.ui.model.Event
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class EventRepositoryTest {
    val date = LocalDate.now()
    var testEvent = Event(
        id = "",
        title = "TEST_EVENT",
        description = "Test Event Desc",
        location = "",
        date = date,
        capacity = 87,
        enrolled = 0,
        organizerName = "PERSON 1",
        organizerId = "ORGANIZER1",
        waitlistCount = 32,
        waitlistCapacity = 45
    )

    var testEvent2 = Event(
        id = "",
        title = "TEST_EVENT2",
        description = "Test Event Desc2",
        location = "",
        date = date,
        capacity = 5,
        enrolled = 5,
        organizerName = "PERSON 2",
        organizerId = "ORGANIZER2",
        waitlistCount = 0,
        waitlistCapacity = 0
    )

    var nullEvent = Event(
        id = "",
        title = "",
        description = "",
        location = "",
        date = date,
        capacity = -1,
        enrolled = -1,
        organizerName = "",
        organizerId = "",
        waitlistCount = -1,
        waitlistCapacity = -1
    )

    val collection = FirebaseFirestore.getInstance().collection("events")

    suspend fun clean() {
        for (doc in collection.whereEqualTo("title", "TEST_EVENT").get().await().documents) {
            doc.reference.delete().await()
        }
        for (doc in collection.whereEqualTo("title", "TEST_EVENT2").get().await().documents) {
            doc.reference.delete().await()
        }
        for (doc in collection.whereEqualTo("title", "").get().await().documents) {
            doc.reference.delete().await()
        }
    }

    @Test
    fun createEventTest() = runTest {
        val repo = EventRepository()
        async {
            repo.createEvent(testEvent, "")
        }.await()

        val q1 = collection.whereEqualTo("title", "TEST_EVENT").get().await()

        assert(q1.documents.size == 1)

        // Clean up
        q1.documents[0].reference.delete().await()
    }

    @Test
    fun getEventTest() = runTest {
        val repo = EventRepository()
        async {
            repo.createEvent(testEvent, "")
        }.await()

        assert(repo.getEvent(testEvent.id).getOrElse { nullEvent }.title == "TEST_EVENT")
        assert(repo.getEvent(nullEvent.id).getOrElse { nullEvent }.title == "")

        // Clean up
        collection.whereEqualTo("title", "TEST_EVENT").get().await().documents[0].reference.delete().await()
    }

    @Test
    fun getAllEventsTest() = runTest {
        val repo = EventRepository()

        val start = repo.getAllEvents().getOrElse { List(0, { nullEvent } ) }.size

        async {
            repo.createEvent(testEvent, "")
            repo.createEvent(testEvent2, "")
        }.await()

        assert(repo.getAllEvents().getOrElse { List(0, { nullEvent } ) }.size == start + 2)

        // Clean up
        collection.whereEqualTo("title", "TEST_EVENT").get().await().documents[0].reference.delete().await()
        collection.whereEqualTo("title", "TEST_EVENT2").get().await().documents[0].reference.delete().await()
    }

    @Test
    fun updateEventTest() = runTest {
        val repo = EventRepository()

        var out = ""
        async {
            out = repo.createEvent(testEvent, "").getOrElse({ "" })
        }.await()

        assert(out != "")

        val updateMap = "capacity" to 39

        assert(repo.getEvent(out).getOrElse { nullEvent }.capacity == 87)

        repo.updateEvent(out, mapOf(updateMap))

        assert(repo.getEvent(out).getOrElse { nullEvent }.capacity == 39)

        async { clean() }.await()
    }

    @Test
    fun deactivateEventsByOrganizerTest() = runTest {
        val repo = EventRepository()

        var out = ""
        async {
            out = repo.createEvent(testEvent, "").getOrElse({ "" })
        }.await()

        assert(out != "")

        assert(repo.getEvent(out).getOrElse { nullEvent }.title != nullEvent.title)

        repo.deactivateEventsByOrganizer(testEvent.organizerId)

        assert(repo.getEvent(out).getOrElse { nullEvent }.title == nullEvent.title)

        async { clean() }.await()
    }

    @Test
    fun enrolledModificationTest() = runTest {
        val repo = EventRepository()

        var out = ""
        async {
            out = repo.createEvent(testEvent, "").getOrElse({ "" })
        }.await()

        assert(out != "")

        assert(repo.getEvent(out).getOrElse { nullEvent }.enrolled == 0)

        repo.incrementEnrolled(out)

        assert(repo.getEvent(out).getOrElse { nullEvent }.enrolled == 1)

        repo.incrementEnrolled(out)

        assert(repo.getEvent(out).getOrElse { nullEvent }.enrolled == 2)

        repo.decrementEnrolled(out)

        assert(repo.getEvent(out).getOrElse { nullEvent }.enrolled == 1)

        repo.decrementEnrolled(out)

        assert(repo.getEvent(out).getOrElse { nullEvent }.enrolled == 0)

        repo.decrementEnrolled(out)

        assert(repo.getEvent(out).getOrElse { nullEvent }.enrolled == 0)

        async { clean() }.await()
    }

    @Test
    fun updateWaitlistCountTest() = runTest {
        val repo = EventRepository()

        var out = ""
        async {
            out = repo.createEvent(testEvent, "").getOrElse({ "" })
        }.await()

        assert(out != "")

        assert(repo.getEvent(out).getOrElse { nullEvent }.waitlistCount == 32)

        repo.updateWaitlistCount(out, 45)

        assert(repo.getEvent(out).getOrElse { nullEvent }.waitlistCount == 45)

        repo.updateWaitlistCount(out, 1)

        assert(repo.getEvent(out).getOrElse { nullEvent }.waitlistCount == 1)

        async { clean() }.await()
    }

    @Test
    fun resetAttendanceTest() = runTest {
        val repo = EventRepository()

        var out = ""
        async {
            out = repo.createEvent(testEvent, "").getOrElse({ "" })
        }.await()

        assert(out != "")

        assert(repo.getEvent(out).getOrElse { nullEvent }.waitlistCount == 32)
        assert(repo.getEvent(out).getOrElse { nullEvent }.enrolled == 0)

        repo.resetAttendance(out)

        assert(repo.getEvent(out).getOrElse { nullEvent }.waitlistCount == 0)
        assert(repo.getEvent(out).getOrElse { nullEvent }.enrolled == 0)

        async { clean() }.await()
    }
}
