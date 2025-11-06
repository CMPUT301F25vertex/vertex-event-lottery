package com.pluck.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.pluck.data.firebase.FirebaseUser
import com.pluck.data.firebase.WaitlistStatus
import com.pluck.data.repository.EventRepository
import com.pluck.data.repository.WaitlistRepository
import com.pluck.ui.model.Event
import com.pluck.ui.screens.WaitlistEntry
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import okhttp3.internal.wait
import org.junit.Test
import java.time.LocalDate

class WaitlistRepositoryTest {
    var testEvent = Event(
        id = "",
        title = "TEST_EVENT",
        description = "Test Event Desc",
        location = "",
        date = LocalDate.now(),
        capacity = 87,
        enrolled = 0,
        organizerName = "PERSON 1",
        organizerId = "ORGANIZER1",
        waitlistCount = 32,
        waitlistCapacity = 45
    )

    var nullEvent = Event(
        id = "",
        title = "",
        description = "",
        location = "",
        date = LocalDate.now(),
        capacity = -1,
        enrolled = -1,
        organizerName = "",
        organizerId = "",
        waitlistCount = -1,
        waitlistCapacity = -1
    )

    val testUser = FirebaseUser(
        displayName = "TEST_USER"
    )

    val nullUser = FirebaseUser(
        displayName = "NULL_USER"
    )

    suspend fun addUser(user: FirebaseUser): String {
        val docRef = FirebaseFirestore.getInstance().collection("entrants").document()
        docRef.set(user).await()

        return docRef.id
    }

    suspend fun addEvent(repo: EventRepository, event: Event): String {
        return repo.createEvent(event, "").getOrElse({ "" })
    }

    suspend fun clean() {
        for (doc in FirebaseFirestore.getInstance().collection("entrants").whereEqualTo("displayName", "TEST_USER").get().await().documents) {
            doc.reference.delete().await()
        }
        for (doc in FirebaseFirestore.getInstance().collection("entrants").whereEqualTo("displayName", "NULL_USER").get().await().documents) {
            doc.reference.delete().await()
        }

        for (doc in FirebaseFirestore.getInstance().collection("events").whereEqualTo("title", "TEST_EVENT").get().await().documents) {
            doc.reference.delete().await()
        }
        for (doc in FirebaseFirestore.getInstance().collection("events").whereEqualTo("title", "TEST_EVENT2").get().await().documents) {
            doc.reference.delete().await()
        }
        for (doc in FirebaseFirestore.getInstance().collection("events").whereEqualTo("title", "").get().await().documents) {
            doc.reference.delete().await()
        }

        for (doc in FirebaseFirestore.getInstance().collection("waitlist").whereEqualTo("userName", "TEST_USER").get().await().documents) {
            doc.reference.delete().await()
        }
        for (doc in FirebaseFirestore.getInstance().collection("waitlist").whereEqualTo("userName", "NULL_USER").get().await().documents) {
            doc.reference.delete().await()
        }
    }

    @Test
    fun joinWaitlistTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .get().await().documents.isEmpty())

        waitlistRepo.joinWaitlist(eventId, userId, testUser.displayName)

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .get().await().documents.size == 1)

        async { clean() }.await()
    }

    @Test
    fun signUpDirectTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .whereNotEqualTo("status", "ACCEPTED")
            .get().await().documents.isEmpty())

        waitlistRepo.signUpDirect(eventId, userId, testUser.displayName)

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .whereEqualTo("status", "ACCEPTED")
            .get().await().documents.size == 1)

        async { clean() }.await()
    }

    @Test
    fun leaveWaitlistTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .get().await().documents.isEmpty())

        val result = waitlistRepo.joinWaitlist(eventId, userId, testUser.displayName).getOrElse { "" }
        assert(result != "")

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .whereNotEqualTo("status", "CANCELLED")
            .get().await().documents.size == 1)

        waitlistRepo.leaveWaitlist(result)

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .whereNotEqualTo("status", "CANCELLED")
            .get().await().documents.isEmpty())

        async { clean() }.await()
    }

    @Test
    fun acceptInvitationTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .whereEqualTo("status", "ACCEPTED")
            .get().await().documents.isEmpty())

        val result = waitlistRepo.joinWaitlist(eventId, userId, testUser.displayName).getOrElse { "" }
        assert(result != "")

        waitlistRepo.acceptInvitation(result)

        assert(!FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .whereEqualTo("status", "ACCEPTED")
            .get().await().documents.isEmpty())

        async { clean() }.await()
    }

    @Test
    fun declineInvitationTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .whereEqualTo("status", "WAITING")
            .get().await().documents.isEmpty())

        val result = waitlistRepo.joinWaitlist(eventId, userId, testUser.displayName).getOrElse { "" }
        assert(result != "")

        waitlistRepo.declineInvitation(result)

        assert(!FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .whereEqualTo("status", "WAITING")
            .get().await().documents.isEmpty())

        async { clean() }.await()
    }

    @Test
    fun updateEntrantNameTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .whereEqualTo("userName", "NEW_NAME")
            .get().await().documents.isEmpty())

        waitlistRepo.joinWaitlist(eventId, userId, testUser.displayName)

        waitlistRepo.updateEntrantName(userId, "NEW_NAME")

        assert(!FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("userId", userId)
            .whereEqualTo("userName", "NEW_NAME")
            .get().await().documents.isEmpty())

        async { clean() }.await()
    }

    @Test
    fun purgeEntrantTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .get().await().documents.isEmpty())

        waitlistRepo.joinWaitlist(eventId, userId, testUser.displayName)

        assert(!FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .get().await().documents.isEmpty())

        waitlistRepo.purgeEntrant(userId)

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .get().await().documents.isEmpty())

        async { clean() }.await()
    }

    @Test
    fun purgeEventTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .get().await().documents.isEmpty())

        waitlistRepo.joinWaitlist(eventId, userId, testUser.displayName)

        assert(!FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .get().await().documents.isEmpty())

        waitlistRepo.purgeEvent(eventId)

        assert(FirebaseFirestore.getInstance().collection("waitlist")
            .whereEqualTo("eventId", eventId)
            .get().await().documents.isEmpty())

        async { clean() }.await()
    }

    @Test
    fun runLotteryTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        waitlistRepo.joinWaitlist(eventId, userId, testUser.displayName)

        waitlistRepo.runLottery(eventId, 1)

        assert(FirebaseFirestore.getInstance().collection("events")
            .whereEqualTo("eventId", eventId)
            .whereEqualTo("enrolled", 0)
            .get().await().documents.isEmpty())

        async { clean() }.await()
    }

    @Test
    fun getWaitlistEntriesTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        assert(waitlistRepo.getWaitlistEntries(eventId).getOrThrow().isEmpty())

        waitlistRepo.joinWaitlist(eventId, userId, testUser.displayName)

        assert(!waitlistRepo.getWaitlistEntries(eventId).getOrThrow().isEmpty())

        async { clean() }.await()
    }

    @Test
    fun getChosenEntriesTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        assert(waitlistRepo.getChosenEntries(eventId).getOrThrow().isEmpty())

        waitlistRepo.signUpDirect(eventId, userId, testUser.displayName)

        assert(!waitlistRepo.getChosenEntries(eventId).getOrThrow().isEmpty())

        async { clean() }.await()
    }

    @Test
    fun getUserEventHistoryTest() = runTest {
        async { clean() }.await()

        val waitlistRepo = WaitlistRepository()
        val eventRepo = EventRepository()

        val eventId = addEvent(eventRepo, testEvent)
        assert(eventId != "")

        val userId = addUser(testUser)
        assert(userId != "")

        assert(waitlistRepo.getUserEventHistory(userId).getOrThrow().isEmpty())

        waitlistRepo.joinWaitlist(eventId, userId, testUser.displayName)

        assert(!waitlistRepo.getUserEventHistory(userId).getOrThrow().isEmpty())

        async { clean() }.await()
    }
}
