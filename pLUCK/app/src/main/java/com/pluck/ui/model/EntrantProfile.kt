package com.pluck.ui.model

import java.time.Instant

data class EntrantProfile(
    val deviceId: String,
    val displayName: String,
    val email: String?,
    val phoneNumber: String?,
    val firebaseUid: String,
    val createdAt: Instant?,
    val updatedAt: Instant?
)
