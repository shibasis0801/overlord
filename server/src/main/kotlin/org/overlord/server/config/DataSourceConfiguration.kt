package org.overlord.server.config

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.Connection
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.r2dbc.connection.ConnectionFactoryUtils
import org.springframework.r2dbc.core.DatabaseClient
import reactor.core.publisher.Mono


// Need different params on Heroku.
// Also get from environment variables.
@Configuration
class DataSourceConfiguration: AbstractR2dbcConfiguration() {

//    If r2dbc does not work on Heroku, will have to fallback as in pravega to jdbc
    fun credential(type: String): String {
        return when(type) {
            "url" -> "r2dbc:${getEnv("DATABASE_URL", "postgresql://localhost:5432/dev")}"
            "username" -> getEnv("JDBC_DATABASE_USERNAME", "postgres")
            "password" -> getEnv("JDBC_DATABASE_PASSWORD", "postgres")
            else -> "ERROR_TYPE"
        }
    }

    @Bean
    override fun connectionFactory(): ConnectionFactory {
//        val localURL = "r2dbc:postgresql://localhost:5432/dev?password=postgres&user=postgres"
//        val URL = getEnv("JDBC_DATABASE_URL", localURL).replace("jdbc", "r2dbc");
//        return ConnectionFactories.get(URL)
        return PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                .host("localhost")
                .database("dev")
                .username(credential("username"))
                .password(credential("password"))
                .build()
        )
    }
}
