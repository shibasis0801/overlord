package org.overlord.server

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

data class Message(val content: String)

data class Friend(val age: Int, val name: String)

@RestController
class HelloController {

    @Autowired
    lateinit var postgres: DatabaseClient

    @GetMapping("/echo/{client}/{message}")
    suspend fun helloWorld(@PathVariable client: String, @PathVariable message: String): Message {
        return Message("Hello $client, you sent $message").apply(::println)
    }

    @GetMapping("/friends")
    fun friends() = postgres.execute("SELECT * from friends")
            .`as`(Friend::class.java)
            .fetch()
            .all()

    @GetMapping("/")
    fun helloWorld() = mapOf(
            "name" to "Shibasis Patnaik",
            "purpose" to "Personal Server"
    )
}
