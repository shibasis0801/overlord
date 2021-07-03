package com.shibasispatnaik.kclient

import com.beust.klaxon.Klaxon

val klaxon = Klaxon()

actual object JSONParser {
    actual inline fun<reified T> parse(content: String): T? = klaxon.parse<T>(content)
    actual inline fun<reified T> stringify(data: T): String = klaxon.toJsonString(data)
}
