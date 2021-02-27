package com.overlord.listenSQL.config

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import org.slf4j.event.Level

fun CORS(application: Application) {
    application.apply {
        install(CORS) {
            method(HttpMethod.Options)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)
            method(HttpMethod.Patch)
            header(HttpHeaders.Authorization)
            header("MyCustomHeader")
            allowCredentials = true
            anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
        }
    }
}

fun Logger(application: Application) {
    application.apply {
        install(CallLogging) {
            level = Level.INFO
            filter { call -> call.request.path().startsWith("/") }
        }
    }
}

