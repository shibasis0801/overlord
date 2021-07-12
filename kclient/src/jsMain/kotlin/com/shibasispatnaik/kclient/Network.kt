package com.shibasispatnaik.kclient

import kotlinx.browser.window

object Network {
    inline fun<reified T> get(url: String, headers: Map<String, String>): Completable<T> {
        return window.fetch(url)
            .then {
                val content = it.json() as T
                content ?: throw Error("Parsing error")
            }
            .toCompletable()
    }

    fun post() {

    }

    // Figure out channel mechanisms for JS / JVM
    fun webSocket() {

    }

    // Other methods later
}