@ExperimentalJsExport
@JsExport
@JsName("HelloWorld")
actual class HelloWorld {
    @JsName("getMessage")
    actual fun getMessage() = "Hello from Web"
}
