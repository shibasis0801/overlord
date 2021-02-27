package com.overlord.listenSQL.routing.http

import com.overlord.listenSQL.config.execute
import com.overlord.listenSQL.config.postgres
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.routeHTTP() {
    routing {
        get("/") {
            call.respond(mapOf(
                "name" to "listenSQL",
                "idea" to "LISTEN/NOTIFY postgres changes over GRPC/WebSockets/GraphQL",
                "target" to "Inspired by Firebase, apps to use SQL database easily without server logic"
            ))
        }

        get("/friends") {
            val result = postgres.execute("SELECT * FROM friends", listOf())
            call.respond(result.rows.columnNames())
        }
    }
}
