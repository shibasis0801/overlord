package org.overlord.server.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter

@Configuration
class WebSocketConfiguration {
    @Autowired
    @Qualifier("SignallingServer")
    lateinit var webSocketHandler: WebSocketHandler

    @Bean
    fun webSocketHandlerMapping() = SimpleUrlHandlerMapping().apply {
        order = 10
        urlMap = hashMapOf(
            "/signalling" to webSocketHandler
        )
    }

    @Bean
    fun handlerAdapter() = WebSocketHandlerAdapter()
}
