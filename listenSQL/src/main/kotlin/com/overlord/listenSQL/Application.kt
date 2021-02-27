package com.overlord.listenSQL

import io.ktor.routing.*
import com.overlord.listenSQL.config.CORS
import com.overlord.listenSQL.config.Logger
import com.overlord.listenSQL.routing.http.routeHTTP
import com.overlord.listenSQL.routing.websockets.routeWebSockets
import io.ktor.features.*
import io.ktor.locations.*
import io.ktor.serialization.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit =
    EngineMain.main(args)



/**
 * Please note that you can use any other name instead of *module*.
 * Also note that you can have more then one modules in your application.
 * */
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    CORS(this)
    Logger(this)


    install(Locations) {
    }

    install(ContentNegotiation) {
        json()
    }


    routing {
        get<MyLocation> {
            call.respondText("Location: name=${it.name}, arg1=${it.arg1}, arg2=${it.arg2}")
        }
        // Register nested routes
        get<Type.Edit> {
            call.respondText("Inside $it")
        }
        get<Type.List> {
            call.respondText("Inside $it")
        }
    }

    routeWebSockets()
    routeHTTP()
}

@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")
@Location("/type/{name}")
data class Type(val name: String) {
    @Location("/edit")
    data class Edit(val type: Type)

    @Location("/list/{page}")
    data class List(val type: Type, val page: Int)
}
