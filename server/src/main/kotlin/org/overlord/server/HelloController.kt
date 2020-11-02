package org.overlord.server

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

data class Message(val content: String)

@RestController
class HelloController {
    @GetMapping("/echo/{client}/{message}")
    suspend fun helloWorld(@PathVariable client: String, @PathVariable message: String): Message {
        return Message("Hello $client, you sent $message").apply(::println)
    }
}
