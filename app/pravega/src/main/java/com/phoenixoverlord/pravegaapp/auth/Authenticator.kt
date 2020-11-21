package com.phoenixoverlord.pravegaapp.auth

import com.phoenixoverlord.pravegaapp.cloud.firebase.extensions.Firebase.auth

fun authenticateAnonymously(onComplete: (Boolean) -> Unit) {
    val user = auth.currentUser
    if (user == null) {
        auth.signInAnonymously()
            .addOnCompleteListener { onComplete(it.isSuccessful) }
    }
}