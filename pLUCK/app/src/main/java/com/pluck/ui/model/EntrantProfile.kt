package com.pluck.ui.model

import java.time.Instant

/**
 * EntrantProfile.kt
 *
 * Purpose: Immutable domain model representing a user's profile within the pLUCK system.
 * Serves as the primary identity object for entrants joining events, managing waitlists, and
 * receiving notifications. Uses device-based authentication to provide a frictionless signup flow.
 *
 * Design Pattern: Domain Model (MVVM architecture).
 * Outstanding Issues: None.
 */

/**
 * Represents an entrant's profile in the pLUCK system.
 *
 * @property deviceId Unique device identifier used as the primary key for authentication.
 * @property displayName User-facing name shown throughout the app and to organizers.
 * @property email Optional email address for contact and notifications.
 * @property phoneNumber Optional phone number for contact and notifications.
 * @property profileImageUrl Optional Cloudinary image URL for the entrant's profile photo.
 * @property firebaseUid Firebase Authentication UID (anonymous auth linked to device).
 * @property createdAt Timestamp when the profile was first created.
 * @property updatedAt Timestamp of the most recent profile modification.
 */
data class EntrantProfile(
    val deviceId: String,
    val displayName: String,
    val email: String?,
    val phoneNumber: String?,
    val profileImageUrl: String?,
    val firebaseUid: String,
    val createdAt: Instant?,
    val updatedAt: Instant?
)
