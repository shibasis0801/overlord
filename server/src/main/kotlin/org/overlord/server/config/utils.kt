package org.overlord.server.config

fun getEnv(value: String, defaultValue: String): String {
    val res = System.getenv(value);
    return res ?: defaultValue
}
