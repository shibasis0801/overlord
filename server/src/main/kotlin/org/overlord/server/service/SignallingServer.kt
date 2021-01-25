package org.overlord.server.service

import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


// Install coturn on Heroku to use TurnServer and implement Signalling using this
// Send rotating encrypted
@Component("SignallingServer")
class SignallingServer: WebSocketHandler {
    override fun getSubProtocols(): MutableList<String> {
        return super.getSubProtocols()
    }

    val clientMap = hashMapOf<String, WebSocketSession>()

    fun setupInputStream(session: WebSocketSession): Flux<WebSocketMessage> {
        val id = session.id
        clientMap[id] = session
        println("Adding session with id $id")
        return session.receive()
            .map {
                println("ID: $id, MESSAGE: ${it.payloadAsText}")
                session.textMessage("ID: $id, MESSAGE: ${it.payloadAsText}")
            }.doOnComplete {
                println("Removing session with id $id")
                clientMap.remove(id)
            }
    }

    override fun handle(session: WebSocketSession): Mono<Void> {
        val sendID = Mono.just(session.textMessage(session.id))

        return session
            .send(sendID)
            .and(session.send(setupInputStream(session)))
    }
}
