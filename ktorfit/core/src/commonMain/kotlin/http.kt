package com.overlord.core


import com.overlord.annotations.BaseURL
import com.overlord.annotations.GET
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

//@file:JsExport
//@file:OptIn(kotlin.js.ExperimentalJsExport::class)


@JsExport
fun helloFromKotlin(): String {
    return "Hello from Kotlin"
}

object Config {
    const val baseURL = "http://localhost:8080/echo"
    fun getPath(client: String, message: String): String {
        return "$baseURL/$client/$message"
    }
}


data class Message(val content: String)

expect class Http() {
    fun get(message: String)
}


@JsExport
fun getClient() = Http()

@BaseURL("http://localhost:8000")
interface ServerAPI {
    @GET("/echo/common/hello")
    fun getHelloWorld()
}

