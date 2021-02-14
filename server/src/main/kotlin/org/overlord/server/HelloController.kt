package org.overlord.server

import io.r2dbc.spi.Connection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


data class Message(val content: String)

data class Friend(val age: Int, val name: String)


@RestController
class HelloController {

    @Autowired
    lateinit var postgres: DatabaseClient

    @GetMapping("/echo/{client}/{message}")
    suspend fun echoGET(@PathVariable client: String, @PathVariable message: String): Message {
        return Message("Hello $client, you sent $message").apply(::println)
    }

    @PostMapping("/echo/{client}")
    suspend fun echoPOST(@PathVariable client: String, @RequestBody message: Message): Message {
        return Message(
            "${client}:${message.content}"
        )
    }

    @GetMapping("/friends")
    fun friends() = postgres.sql("SELECT * from friends")
            .fetch()
            .all()
            .map { it }

    @GetMapping("/")
    fun index() = mapOf(
            "name" to "Shibasis Patnaik",
            "purpose" to "Personal Server"
    )
}
