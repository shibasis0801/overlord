package com.overlord.app.utils

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class GETModifier {
    var headers: HashMap<String, String> = hashMapOf()
    var queryParams: HashMap<String, String> = hashMapOf()
}

class POSTModifier {
    var headers: HashMap<String, String> = hashMapOf()
}

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
val http = OkHttpClient()

fun <T> Moshi.inlineAdapter(type: Class<T>): JsonAdapter<T> = adapter(type)

abstract class API(baseURL: String) {
    val baseURL: String = baseURL.removeSuffix("/")

    fun makeCall(request: Request) = try {
        http.newCall(request).execute().run {
            if (!isSuccessful) {
                Log.e("API:makeCall", "Unexpected code $code")
                null
            }
            else {
                body!!.string()
            }
        }
    } catch (e: Error) {
        null
    }


    fun createURL(route: String, fn: (HttpUrl.Builder.() -> Unit) = {}): HttpUrl {
        val partial = HttpUrl.Builder()
            .scheme("http")
            .host(baseURL)
            .port(8080)
            .addEncodedPathSegments(route.removePrefix("/"))
        partial.fn()
        return partial.build().apply {
            Log.d("API:URL", toString())
        }
    }


    fun createRequest(url: HttpUrl, fn: Request.Builder.() -> Unit): Request {
        val partial = Request.Builder()
            .url(url)
        partial.fn()
        return partial.build()
    }


    suspend inline fun <reified T> GET(
        route: String,
        actions: (GETModifier.() -> Unit) = {}
    ): T? {
        val adapter = moshi.inlineAdapter(T::class.java)
        val getModifier = GETModifier()
        getModifier.actions()

        val url = createURL(route) {
            getModifier.queryParams.entries.forEach {
                addQueryParameter(it.key, it.value)
            }
        }

        val request = createRequest(url) {
                getModifier.headers.entries.forEach {
                    header(it.key, it.value)
                }
        }

        return withContext(Dispatchers.IO) {
            val response = makeCall(request)
            Log.d("API:makeCallResponse", response ?: "NULL RESPONSE")
            response?.run { adapter.fromJson(this) }
        }
    }
    val JSON: MediaType = "application/json; charset=utf-8".toMediaType()

    suspend inline fun <reified Request, reified Response> POST(
        route: String,
        body: Request,
        actions: (POSTModifier.() -> Unit) = {}
    ): Response? {
        val requestAdapter = moshi.inlineAdapter(Request::class.java)
        val responseAdapter = moshi.inlineAdapter(Response::class.java)

        val requestBody = requestAdapter.toJson(body).toRequestBody(JSON)

        val postModifier = POSTModifier()
        postModifier.actions()

        val url = createURL(route)

        val request = createRequest(url) {
            post(requestBody)
            postModifier.headers.entries.forEach {
                header(it.key, it.value)
            }
        }

        return withContext(Dispatchers.IO) {
            val response = makeCall(request)
            Log.d("API:makeCallResponse", response ?: "NULL RESPONSE")
            response?.run { responseAdapter.fromJson(this) }
        }
    }
}

data class Message(val content: String)

object FriendAPI: API("192.168.0.118") {
    suspend fun getEchoMessage(message: String) = GET<Message>("/echo/android/$message") {
        headers = hashMapOf(
            "Accept" to "application/json",
            "User-Agent" to "OverlordAndroid"
        )
    }

    suspend fun postEchoMessage(message: Message) = POST<Message, Message>("/echo/android", message) {
        headers = hashMapOf(
            "Accept" to "application/json",
            "User-Agent" to "OverlordAndroid"
        )
    }
}
