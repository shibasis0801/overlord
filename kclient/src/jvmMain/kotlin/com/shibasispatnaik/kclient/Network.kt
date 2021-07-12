package com.shibasispatnaik.kclient

import okhttp3.*
import java.io.IOException

object Network {
    val http = OkHttpClient()
    inline fun<reified T> get(url: String, headers: Map<String, String>): Completable<T> {
        val request = Request.Builder()
            .url(url)
            .apply { headers.forEach(::addHeader) }
            .build()
        return Completable { resolve, reject ->
            http.newCall(
                request
            ).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    reject(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body.toString()
                    val obj = JSONParser.parse<T>(body)
                    if (obj != null) {
                        resolve(obj)
                    }
                    else {
                        reject(Error("Parse failure"))
                    }
                }
            })
        }
    }

    fun post() {

    }

    // Figure out channel mechanisms for JS / JVM
    fun webSocket() {

    }

    // Other methods later
}