import io.ktor.client.*

@ExperimentalJsExport
@JsExport
@JsName("HelloWorld")
actual class HelloWorld {
    @JsName("getMessage")
    actual fun getMessage() = "Hello from Web"
}

actual val client = HttpClient() {
    engine {
        pipelining = true
    }
}
