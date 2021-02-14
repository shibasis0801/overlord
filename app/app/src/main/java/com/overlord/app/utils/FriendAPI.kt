package com.overlord.app.utils

import com.overlord.app.mech.apibuilder.API

data class Message(val content: String)
object FriendAPI: API("192.168.0.118") {
    suspend fun getEchoMessage(message: String) =
        GET<Message>("/echo/android/$message") {
            headers = hashMapOf(
                "Accept" to "application/json",
                "User-Agent" to "OverlordAndroid"
            )
        }

    suspend fun postEchoMessage(message: Message) =
        POST<Message, Message>("/echo/android", message) {
            headers = hashMapOf(
                "Accept" to "application/json",
                "User-Agent" to "OverlordAndroid"
            )
        }
}
