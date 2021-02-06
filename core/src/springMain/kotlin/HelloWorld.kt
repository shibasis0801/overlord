import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual class HelloWorld {
    actual fun getMessage() = "Hello from Spring"
}

actual val client = HttpClient(OkHttp) {
    engine {
        pipelining = true
    }
}
