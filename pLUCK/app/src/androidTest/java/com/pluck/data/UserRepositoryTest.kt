package com.pluck.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.pluck.data.firebase.FirebaseEvent
import com.pluck.data.firebase.FirebaseUser
import com.pluck.data.firebase.UserRole
import com.pluck.data.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.text.ifEmpty

class UserRepositoryTest {
    val collection = FirebaseFirestore.getInstance().collection("entrants")

    val testUser = FirebaseUser(
        displayName = "TEST_USER"
    )

    val nullUser = FirebaseUser(
        displayName = "NULL_USER"
    )

    suspend fun clean() {
        for (doc in collection.whereEqualTo("displayName", "TEST_USER").get().await().documents) {
            doc.reference.delete().await()
        }
        for (doc in collection.whereEqualTo("displayName", "NULL_USER").get().await().documents) {
            doc.reference.delete().await()
        }
    }

    suspend fun addUser(user: FirebaseUser): String {
        val docRef = collection.document()
        docRef.set(user).await()

        return docRef.id
    }

    @Test
    fun getAllUsersTest() = runTest {
        async { clean() }.await()

        val repo = UserRepository()
        val start = repo.getAllUsers().getOrElse { List(0, { nullUser }) }.size

        addUser(testUser)

        assert(repo.getAllUsers().getOrElse { List(0, { nullUser }) }.size == start + 1)

        async { clean() }.await()
    }

    @Test
    fun getUsersByRoleTest() = runTest {
        async { clean() }.await()

        val repo = UserRepository()
        val start = repo.getUsersByRole(UserRole.ENTRANT).getOrElse { List(0, { nullUser }) }.size
        val startA = repo.getUsersByRole(UserRole.ADMIN).getOrElse { List(0, { nullUser }) }.size
        val startO = repo.getUsersByRole(UserRole.ORGANIZER).getOrElse { List(0, { nullUser }) }.size

        addUser(testUser)

        assert(repo.getUsersByRole(UserRole.ENTRANT).getOrElse { List(0, { nullUser }) }.size == start + 1)

        addUser(FirebaseUser(role = UserRole.ADMIN, displayName = "TEST_USER"))

        assert(repo.getUsersByRole(UserRole.ENTRANT).getOrElse { List(0, { nullUser }) }.size == start + 1)

        addUser(FirebaseUser(role = UserRole.ORGANIZER, displayName = "TEST_USER"))

        assert(repo.getUsersByRole(UserRole.ENTRANT).getOrElse { List(0, { nullUser }) }.size == start + 1)

        assert(repo.getUsersByRole(UserRole.ORGANIZER).getOrElse { List(0, { nullUser }) }.size == startO + 1)
        assert(repo.getUsersByRole(UserRole.ADMIN).getOrElse { List(0, { nullUser }) }.size == startA + 1)

        async { clean() }.await()
    }

    @Test
    fun getUserTest() = runTest {
        async { clean() }.await()
        val repo = UserRepository()

        val newTestUser = FirebaseUser(displayName = "TEST_USER", id = "TEST_ID")

        val userId = addUser(newTestUser)

        assert(repo.getUser(userId).getOrElse { nullUser }.id == userId)

        async { clean() }.await()
    }

    @Test
    fun deleteUserTest() = runTest {
        async { clean() }.await()
        val repo = UserRepository()

        val userId = addUser(testUser)

        assert(repo.getUser(userId).getOrElse { nullUser }.id == userId)

        repo.deleteUser(userId)

        assert(repo.getUser(userId).getOrElse { nullUser }.id != userId)

        async { clean() }.await()
    }

    @Test
    fun removeOrganizerRoleTest() = runTest {
        async { clean() }.await()
        val repo = UserRepository()

        val organizer = FirebaseUser(displayName = "TEST_USER", role = UserRole.ORGANIZER)

        val userId = addUser(organizer)

        assert(repo.getUser(userId).getOrElse { nullUser }.id == userId)
        assert(repo.getUser(userId).getOrElse { nullUser }.role == UserRole.ORGANIZER)

        assert(repo.removeOrganizerRole(userId).isSuccess)

        assert(repo.getUser(userId).getOrElse { nullUser }.id == userId)
        assert(repo.getUser(userId).getOrElse { nullUser }.role != UserRole.ORGANIZER)

        async { clean() }.await()
    }
}
