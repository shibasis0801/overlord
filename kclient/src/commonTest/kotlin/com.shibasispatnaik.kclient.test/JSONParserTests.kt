package com.shibasispatnaik.kclient.test

import com.shibasispatnaik.kclient.JSONParser
import kotlin.test.Test
import kotlin.test.assertTrue

data class Person(val name: String, val age: Int)

class JSONParserTests {
    @Test
    fun checkParse() {
        val content = """{ "name": "Shibasis",  "age": 25 }"""
        val person = JSONParser.parse<Person>(content)
        assertTrue {
            person?.name == "Shibasis" && person.age == 25
        }
    }

    @Test
    fun checkStringify() {
        val person = Person("Shibasis", 25);
        val freeze = JSONParser.stringify(person)
        val thaw = JSONParser.parse<Person>(freeze)

        assertTrue {
            person.age == thaw?.age && person.name == thaw.name
        }
    }
}
