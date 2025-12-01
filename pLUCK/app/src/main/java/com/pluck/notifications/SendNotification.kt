package com.pluck.notifications

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

/**
 * Sends notifications to all users with user ids in the users list.
 *
 * This posts directly to the deployed HTTP Cloud Function `sendNotification` which is responsible for:
 * - Creating Firestore notification documents for each recipient
 * - Looking up their FCM tokens in the `entrants` collection
 * - Dispatching push notifications via Firebase Cloud Messaging
 *
 * @param users A list of user ids who should receive the notification.
 * @param body The body of the notification to send.
 * @param title The title of the notification to send.
 * @param eventId Optional event id this notification relates to.
 * @param organizerId Optional organizer id sending the notification.
 * @param skipNotifications If true, Cloud Function will send FCM only and skip creating Firestore notification docs.
 */
fun SendNotification(
    users: List<String>,
    body: String,
    title: String,
    eventId: String? = null,
    organizerId: String? = null,
    skipNotifications: Boolean = false
) {
    if (users.isEmpty() || title.isBlank() || body.isBlank()) {
        return
    }

    val endpoint =
        "https://us-central1-vertexlotto.cloudfunctions.net/sendNotification"

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val url = URL(endpoint)
            val connection = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                doOutput = true
                connectTimeout = 10_000
                readTimeout = 10_000
                setRequestProperty("Content-Type", "application/json; charset=utf-8")
            }

            val jsonBody = JSONObject().apply {
                put("userIds", JSONArray(users))
                put("title", title)
                put("body", body)
                eventId?.takeIf { it.isNotBlank() }?.let { put("eventId", it) }
                organizerId?.takeIf { it.isNotBlank() }?.let { put("organizerId", it) }
                if (skipNotifications) {
                    put("skipNotifications", true)
                }
            }

            connection.outputStream.use { os ->
                BufferedWriter(OutputStreamWriter(os, Charsets.UTF_8)).use { writer ->
                    writer.write(jsonBody.toString())
                }
            }

            val code = connection.responseCode
            if (code !in 200..299) {
                Log.e("FCM", "sendNotification HTTP error: $code")
            }

            connection.disconnect()
        } catch (t: Throwable) {
            Log.e("FCM", "Failed to send notification batch", t)
        }
    }
}
