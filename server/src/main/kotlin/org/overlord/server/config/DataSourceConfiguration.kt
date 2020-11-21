package org.overlord.server.config

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.core.DatabaseClient


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

    fun getLocalConnection() = PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .database("dev")
                        .username(credential("username"))
                        .password(credential("password"))
                        .build()
        )


    fun getHerokuConnection() = ConnectionFactories.get(
            ConnectionFactoryOptions.parse(credential("url"))
                    .mutate()
                    .option(ConnectionFactoryOptions.USER, credential("username"))
                    .option(ConnectionFactoryOptions.PASSWORD, credential("password"))
                    .option(ConnectionFactoryOptions.PROTOCOL, "r2dbc")
                    .build()
    )

    @Bean
    override fun connectionFactory(): ConnectionFactory {
        return if (credential("url").contains("localhost"))
            getLocalConnection() else
            getHerokuConnection()
    }
}
