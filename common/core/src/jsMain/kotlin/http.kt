package com.overlord.core
import kotlinx.browser.window

// Write this with Axios. Not fetch. Axios will enable interceptors too

@JsName("Http")
@JsExport
actual class Http {
//    val client = axios().create()

    @JsName("get")
    actual fun get(message: String) {
        window.fetch(Config.getPath("js", message))
            .then { it.json() }
            .then { body -> console.log(body) }
            .catch { error -> console.error(error) }

// //Axios
//        client.get<Message>(Config.getPath("js", message))
//            .then {
//                console.log(it.content)
//            }
//            .catch {
//                console.error(it)
//            }

    }
}




@ExperimentalJsExport
@JsExport
fun testHelloWorld() {
    window.fetch("http://localhost:8080/echo/web/jscore")
}
