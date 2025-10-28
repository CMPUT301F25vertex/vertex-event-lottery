package com.pluck.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.pluck.data.firebase.FirebaseUser
import com.pluck.data.firebase.UserRole
import kotlinx.coroutines.tasks.await

/**
 * Repository for user profile CRUD operations with Firebase Firestore
 *
 * Handles all user-related database operations including:
 * - Reading/querying user profiles
 * - Updating user details
 * - Deleting user profiles
 * - Managing user roles (entrant, organizer, admin)
 */
class UserRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val usersCollection = firestore.collection("entrants")

    /**
     * Get all users in the system
     *
     * @return Result with list of users or error
     */
    suspend fun getAllUsers(): Result<List<FirebaseUser>> {
        return try {
            val snapshot = usersCollection
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            val users = snapshot.documents.mapNotNull { doc ->
                doc.toObject(FirebaseUser::class.java)
            }
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get users with a specific role
     *
     * @param role The role to filter by
     * @return Result with list of users or error
     */
    suspend fun getUsersByRole(role: UserRole): Result<List<FirebaseUser>> {
        return try {
            val snapshot = usersCollection
                .whereEqualTo("role", role.name)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            val users = snapshot.documents.mapNotNull { doc ->
                doc.toObject(FirebaseUser::class.java)
            }
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get a single user by ID
     *
     * @param userId The user ID
     * @return Result with the user or error
     */
    suspend fun getUser(userId: String): Result<FirebaseUser> {
        return try {
            val snapshot = usersCollection.document(userId).get().await()
            val user = snapshot.toObject(FirebaseUser::class.java)

            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("User not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Delete a user profile (US 03.02.01)
     *
     * @param userId The user ID to delete
     * @return Result with success or error
     */
    suspend fun deleteUser(userId: String): Result<Unit> {
        return try {
            usersCollection.document(userId).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Remove organizer role from a user (US 03.07.01)
     *
     * @param userId The user ID
     * @return Result with success or error
     */
    suspend fun removeOrganizerRole(userId: String): Result<Unit> {
        return try {
            usersCollection.document(userId)
                .update("role", UserRole.ENTRANT.name)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get all organizers in the system (US 03.07.01)
     *
     * @return Result with list of organizer users or error
     */
    suspend fun getAllOrganizers(): Result<List<FirebaseUser>> {
        return getUsersByRole(UserRole.ORGANIZER)
    }
}
