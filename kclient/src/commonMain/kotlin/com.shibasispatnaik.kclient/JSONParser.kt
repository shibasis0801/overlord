package com.shibasispatnaik.kclient

expect object JSONParser {
    inline fun<reified T> parse(content: String): T?
    inline fun<reified T> stringify(data: T): String
}
