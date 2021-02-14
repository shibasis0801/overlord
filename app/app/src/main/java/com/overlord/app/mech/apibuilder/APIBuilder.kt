package com.overlord.app.mech.apibuilder

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class GETModifier(
    var headers: HashMap<String, String> = hashMapOf(),
    var queryParams: HashMap<String, String> = hashMapOf()
)
class POSTModifier {
    var headers: HashMap<String, String> = hashMapOf()
}

abstract class API(baseURL: String) {
    val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
    val baseURL: String = baseURL.removeSuffix("/")

    protected suspend inline fun <reified T> GET(
        route: String,
        actions: (GETModifier.() -> Unit) = {}
    ): T? {
        val adapter = moshi.inlineAdapter(T::class.java)
        val getModifier = GETModifier()
        getModifier.actions()

        val url = createURL(baseURL, route) {
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

    protected suspend inline fun <reified Request, reified Response> POST(
        route: String,
        body: Request,
        actions: (POSTModifier.() -> Unit) = {}
    ): Response? {
        val requestAdapter = moshi.inlineAdapter(Request::class.java)
        val responseAdapter = moshi.inlineAdapter(Response::class.java)

        val requestBody = requestAdapter.toJson(body).toRequestBody(JSON)

        val postModifier = POSTModifier()
        postModifier.actions()

        val url = createURL(baseURL, route)

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