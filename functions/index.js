/**
 * Firebase Cloud Functions entrypoint for pLUCK notification sending.
 *
 * HTTP function `sendNotification`:
 * - Accepts a JSON body with target user IDs plus optional event/organizer metadata
 * - Writes Firestore `notifications` documents for each recipient (so they
 *   appear in the in-app notification centre)
 * - Looks up each user's `fcmToken` in the `entrants` collection
 * - Sends Firebase Cloud Messaging (FCM) data messages to all resolved tokens
 *
 * Deploy from the repo root with:
 *   firebase deploy --only functions:sendNotification
 */

const functions = require("firebase-functions");
const admin = require("firebase-admin");

// Initialize Admin SDK only once
if (!admin.apps.length) {
  admin.initializeApp();
}

/**
 * HTTP endpoint used by the Android app.
 * Configured to allow unauthenticated access since the app doesn't send auth tokens.
 *
 * Expected JSON body:
 * {
 *   userIds: string[],              // Firestore entrant document IDs (device IDs)
 *   title: string,
 *   body: string,
 *   eventId?: string,
 *   organizerId?: string
 * }
 */
exports.sendNotification = functions.https.onRequest(async (req, res) => {
  // Set CORS headers to allow requests from any origin
  res.set('Access-Control-Allow-Origin', '*');
  res.set('Access-Control-Allow-Methods', 'POST');
  res.set('Access-Control-Allow-Headers', 'Content-Type');

  // Handle preflight requests
  if (req.method === 'OPTIONS') {
    res.status(204).send('');
    return;
  }

  if (req.method !== "POST") {
    res.status(405).send("Method Not Allowed");
    return;
  }

  const data = typeof req.body === "object" && req.body != null ? req.body : {};

  const userIds = Array.isArray(data.userIds) ? data.userIds : [];
  const title = typeof data.title === "string" ? data.title.trim() : "";
  const body = typeof data.body === "string" ? data.body.trim() : "";
  const eventId =
    typeof data.eventId === "string" ? data.eventId.trim() : "";
  const organizerIdOverride =
    typeof data.organizerId === "string" ? data.organizerId.trim() : "";

  if (!userIds.length || !title || !body) {
    res.status(400).json({
      error: "userIds (non-empty array), title, and body are required.",
    });
    return;
  }

  const db = admin.firestore();
  const notificationsCollection = db.collection("notifications");
  const entrantsCollection = db.collection("entrants");

  const tokens = [];
  const batch = db.batch();

  for (const rawUserId of userIds) {
    const userId =
      typeof rawUserId === "string" ? rawUserId.trim() : String(rawUserId || "");
    if (!userId) continue;

    const userSnap = await entrantsCollection.doc(userId).get();
    if (!userSnap.exists) {
      continue;
    }

    const userData = userSnap.data() || {};
    const token = userData.fcmToken;
    if (typeof token === "string" && token.trim()) {
      tokens.push(token.trim());
    }

    const docRef = notificationsCollection.doc();
    const payload = {
      id: docRef.id,
      userId,
      organizerId: organizerIdOverride || userData.organizerId || "",
      eventId: eventId,
      waitlistEntryId: "",
      title,
      subtitle: "",
      detail: body,
      // Align with NotificationCategory.ORGANIZER_UPDATE
      category: "ORGANIZER_UPDATE",
      // Align with NotificationStatus.UNREAD
      status: "UNREAD",
      inviteContact: null,
      inviteType: null,
      // Simple broadcast: no accept/decline actions
      allowAccept: false,
      allowDecline: false,
      allowEventDetails: !!eventId,
      actionTaken: null,
      createdAt: admin.firestore.FieldValue.serverTimestamp(),
      updatedAt: admin.firestore.FieldValue.serverTimestamp(),
    };

    batch.set(docRef, payload, { merge: true });
  }

  await batch.commit();

  let fcmSuccessCount = 0;
  let fcmFailureCount = 0;

  if (tokens.length) {
    const messaging = admin.messaging();

    // Send to each token individually since sendMulticast might not be available
    const sendPromises = tokens.map(async (token) => {
      try {
        await messaging.send({
          token,
          data: {
            title,
            body,
            eventId: eventId || "",
          },
          android: {
            priority: "high",
          },
        });
        fcmSuccessCount++;
      } catch (error) {
        console.error(`Failed to send to token ${token.substring(0, 10)}...`, error);
        fcmFailureCount++;
      }
    });

    await Promise.all(sendPromises);
  }

  res.status(200).json({
    success: true,
    createdNotifications: userIds.length,
    targetedTokens: tokens.length,
    fcmSuccessCount,
    fcmFailureCount,
  });
});
