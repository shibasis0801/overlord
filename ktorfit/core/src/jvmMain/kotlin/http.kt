package com.overlord.core

import okhttp3.*
import java.io.IOException

actual class Http {
    val client = OkHttpClient()

    actual fun get(message: String) {
        val request = Request.Builder()
            .url(Config.getPath("jvm", message))
            .build()
        client.newCall(request)
            .enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    System.err.println(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    println(response.body?.toString())
                }
            })
    }
}
