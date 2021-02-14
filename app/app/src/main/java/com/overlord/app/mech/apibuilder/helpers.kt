package com.overlord.app.mech.apibuilder

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
val http = OkHttpClient()

fun <T> Moshi.inlineAdapter(type: Class<T>): JsonAdapter<T> = adapter(type)

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

fun createURL(baseURL: String, route: String, fn: (HttpUrl.Builder.() -> Unit) = {}): HttpUrl {
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
