package com.overlord.listenSQL.config

import com.github.jasync.sql.db.QueryResult
import com.github.jasync.sql.db.pool.ConnectionPool
import com.github.jasync.sql.db.postgresql.PostgreSQLConnection
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import kotlinx.coroutines.future.await


fun createClient(): ConnectionPool<PostgreSQLConnection> {
    return PostgreSQLConnectionBuilder.createConnectionPool(
        "jdbc:postgresql://localhost:5432/dev?user=postgres&password=postgres"
    )
}

val postgres = createClient()

suspend fun ConnectionPool<PostgreSQLConnection>.execute(
    query: String,
    values: List<Any>
): QueryResult = sendPreparedStatement(query, values).await()

