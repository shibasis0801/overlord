package com.shibasispatnaik.kclient

import kotlin.js.JSON

actual object JSONParser {

    actual inline fun<reified T> parse(content: String): T? = try {
        JSON.parse<T>(content)
    } catch (e: Error) { null }

    actual inline fun<reified T> stringify(data: T): String = JSON.stringify(data)
}
