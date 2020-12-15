package com.overlord.chat

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

/*
Create a WhatsApp like Chat using Firestore and Storage
Then use this SDK inside the app to implement it.

Direct Chat
Group Chat
Firebase Storage image upload and share
WebRTC video/audio call

 */
class FireChat {
    @Inject lateinit var firestore: FirebaseFirestore
    @Inject lateinit var storageReference: StorageReference
    
    fun test() {

    }
}
