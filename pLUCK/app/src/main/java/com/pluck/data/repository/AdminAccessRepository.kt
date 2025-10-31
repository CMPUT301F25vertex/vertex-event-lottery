package com.pluck.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

data class AdminConfig(
    val password: String,
    val adminDeviceIds: List<String>
)

/**
 * Repository responsible for managing admin access credentials and enrolment.
 *
 * Structure used in Firestore:
 *
 * adminConfig/default {
 *   password: "securePassword",
 *   admins: ["deviceId1", "deviceId2"],
 *   adminMetadata: {
 *     "deviceId1": { displayName: "Alice", addedAt: <timestamp> }
 *   }
 * }
 */
class AdminAccessRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val configRef = firestore.collection(CONFIG_COLLECTION).document(DEFAULT_DOCUMENT_ID)

    suspend fun isDeviceAdmin(deviceId: String): Result<Boolean> = withContext(Dispatchers.IO) {
        runCatching {
            val snapshot = configRef.get().await()
            val config = snapshot.toAdminConfig()
                ?: throw IllegalStateException(ADMIN_CONFIG_MISSING_MESSAGE)
            config.adminDeviceIds.contains(deviceId)
        }
    }

    suspend fun registerDevice(
        password: String,
        deviceId: String,
        displayName: String?
    ): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val snapshot = configRef.get().await()
            val config = snapshot.toAdminConfig()
                ?: throw IllegalStateException(ADMIN_CONFIG_MISSING_MESSAGE)

            if (config.password.isBlank()) {
                throw IllegalStateException("Admin password is not set. Configure it in Firestore.")
            }
            if (password != config.password) {
                throw IllegalArgumentException("Incorrect admin password.")
            }

            val updates = mutableMapOf<String, Any>(
                ADMINS_FIELD to FieldValue.arrayUnion(deviceId),
                "$ADMIN_METADATA_FIELD.$deviceId" to mapOf(
                    "deviceId" to deviceId,
                    "displayName" to (displayName?.takeIf { it.isNotBlank() } ?: "Unknown"),
                    "addedAt" to FieldValue.serverTimestamp()
                )
            )

            configRef.update(updates).await()
            Unit
        }
    }

    suspend fun removeDevice(deviceId: String): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val updates = mapOf(
                ADMINS_FIELD to FieldValue.arrayRemove(deviceId),
                "$ADMIN_METADATA_FIELD.$deviceId" to FieldValue.delete()
            )
            configRef.update(updates).await()
            Unit
        }
    }

    private fun com.google.firebase.firestore.DocumentSnapshot.toAdminConfig(): AdminConfig? {
        if (!exists()) return null
        val password = getString(ADMIN_PASSWORD_FIELD) ?: return null
        val admins = get(ADMINS_FIELD) as? List<*>
        val adminIds = admins?.filterIsInstance<String>().orEmpty()
        return AdminConfig(
            password = password,
            adminDeviceIds = adminIds
        )
    }

    companion object {
        private const val CONFIG_COLLECTION = "adminConfig"
        private const val DEFAULT_DOCUMENT_ID = "default"
        private const val ADMIN_PASSWORD_FIELD = "password"
        private const val ADMINS_FIELD = "admins"
        private const val ADMIN_METADATA_FIELD = "adminMetadata"
        private const val ADMIN_CONFIG_MISSING_MESSAGE =
            "Admin configuration not found. Please create adminConfig/default with a password in Firestore."
    }
}
