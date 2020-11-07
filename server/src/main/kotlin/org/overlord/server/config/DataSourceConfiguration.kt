package org.overlord.server.config

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.core.DatabaseClient


// Need different params on Heroku.
// Also get from environment variables.
@Configuration
class DataSourceConfiguration: AbstractR2dbcConfiguration() {
    fun getEnv(value: String, defaultValue: String): String {
        val res = System.getenv(value);
        if (res != null)
            return res;
        else
            return defaultValue
    }

//    If r2dbc does not work on Heroku, will have to fallback as in pravega to jdbc
    fun credential(type: String): String {
        return when(type) {
            "url" -> getEnv("JDBC_DATABASE_URL", "r2dbc:postgresql://localhost:5432/dev")
            "username" -> getEnv("JDBC_DATABASE_USERNAME", "postgres")
            "password" -> getEnv("JDBC_DATABASE_PASSWORD", "postgres")
            else -> "ERROR_TYPE"
        }
    }

    @Bean
    override fun connectionFactory(): ConnectionFactory {
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
