package com.pluck.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

class ConnectionTest(val testValue: Int)

suspend fun checkFirebaseConnection(): Boolean {
    var result = true
    val collection = FirebaseFirestore.getInstance().collection("connectionTest")

    collection.document().set(ConnectionTest(4)).addOnFailureListener({
        result = false
    }).await()

    for (doc in collection.whereEqualTo("testValue", 4).get().await().documents) {
        doc.reference.delete().await()
    }

    return result
}
