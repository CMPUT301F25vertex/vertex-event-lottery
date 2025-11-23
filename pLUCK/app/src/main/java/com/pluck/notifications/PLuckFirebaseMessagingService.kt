package com.pluck.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Build
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.R
import com.google.firebase.messaging.RemoteMessage

class PLuckFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.e("FCM", "Refreshed token: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.e("FCM", "NOTIF Received")
        message.data.forEach { p0, p1 -> Log.e("FCM", "p1: $p0, p2: $p1") }
        showNotification("TITLE", "BODY")
    }

    private fun showNotification(title: String?, body: String?) {
        val channelId = "default_channel"
        val channelName = "Default Channel"

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(IconCompat.createWithData(ByteArray(128), 0, 128))
            .build()

        notificationManager.notify(0, notification)
    }
}
