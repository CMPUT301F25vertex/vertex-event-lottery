package com.pluck.data

import android.content.Context
import android.content.SharedPreferences

/**
 * NotificationPreferences.kt
 *
 * Purpose: Manages user notification preferences
 * Handles push notifications, email notifications, and notification opt-out settings
 */
class NotificationPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "notification_preferences",
        Context.MODE_PRIVATE
    )

    companion object {
        private const val KEY_ALL_NOTIFICATIONS = "all_notifications_enabled"
        private const val KEY_PUSH_NOTIFICATIONS = "push_notifications_enabled"
        private const val KEY_EMAIL_NOTIFICATIONS = "email_notifications_enabled"
        private const val KEY_SELECTION_NOTIFICATIONS = "selection_notifications_enabled"
        private const val KEY_REJECTION_NOTIFICATIONS = "rejection_notifications_enabled"
        private const val KEY_ORGANIZER_NOTIFICATIONS = "organizer_notifications_enabled"
    }

    /**
     * Check if all notifications are enabled
     */
    fun areAllNotificationsEnabled(): Boolean {
        return prefs.getBoolean(KEY_ALL_NOTIFICATIONS, true)
    }

    /**
     * Enable or disable all notifications
     */
    fun setAllNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_ALL_NOTIFICATIONS, enabled).apply()
    }

    /**
     * Check if push notifications are enabled
     */
    fun arePushNotificationsEnabled(): Boolean {
        return prefs.getBoolean(KEY_PUSH_NOTIFICATIONS, true)
    }

    /**
     * Enable or disable push notifications
     */
    fun setPushNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_PUSH_NOTIFICATIONS, enabled).apply()
    }

    /**
     * Check if email notifications are enabled
     */
    fun areEmailNotificationsEnabled(): Boolean {
        return prefs.getBoolean(KEY_EMAIL_NOTIFICATIONS, false)
    }

    /**
     * Enable or disable email notifications
     */
    fun setEmailNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_EMAIL_NOTIFICATIONS, enabled).apply()
    }

    /**
     * Check if selection (chosen) notifications are enabled
     */
    fun areSelectionNotificationsEnabled(): Boolean {
        return prefs.getBoolean(KEY_SELECTION_NOTIFICATIONS, true)
    }

    /**
     * Enable or disable selection notifications
     */
    fun setSelectionNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_SELECTION_NOTIFICATIONS, enabled).apply()
    }

    /**
     * Check if rejection (not chosen) notifications are enabled
     */
    fun areRejectionNotificationsEnabled(): Boolean {
        return prefs.getBoolean(KEY_REJECTION_NOTIFICATIONS, true)
    }

    /**
     * Enable or disable rejection notifications
     */
    fun setRejectionNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_REJECTION_NOTIFICATIONS, enabled).apply()
    }

    /**
     * Check if organizer notifications are enabled (updates from organizers)
     */
    fun areOrganizerNotificationsEnabled(): Boolean {
        return prefs.getBoolean(KEY_ORGANIZER_NOTIFICATIONS, true)
    }

    /**
     * Enable or disable organizer notifications
     */
    fun setOrganizerNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_ORGANIZER_NOTIFICATIONS, enabled).apply()
    }

    /**
     * Check if a specific notification type should be sent
     * Takes into account the master switch and specific notification type
     */
    fun shouldSendNotification(notificationType: NotificationType): Boolean {
        if (!areAllNotificationsEnabled()) return false

        return when (notificationType) {
            NotificationType.SELECTION -> areSelectionNotificationsEnabled()
            NotificationType.REJECTION -> areRejectionNotificationsEnabled()
            NotificationType.ORGANIZER_UPDATE -> areOrganizerNotificationsEnabled()
            NotificationType.CANCELLATION -> true // Always send cancellation notifications
            NotificationType.REMINDER -> areOrganizerNotificationsEnabled()
        }
    }
}

enum class NotificationType {
    SELECTION,      // US 01.04.01 - Chosen from waitlist
    REJECTION,      // US 01.04.02 - Not chosen from waitlist
    ORGANIZER_UPDATE, // US 02.07.01/02/03 - Updates from organizers
    CANCELLATION,   // US 02.07.03 - Event cancelled
    REMINDER        // General reminders
}
