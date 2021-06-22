import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import kotlinx.serialization.serializer

expect class HelloWorld {
    fun getMessage(): String
}


// For Protobuf, feature needs to be created
// OkHttp is automatically used

val http = HttpClient() {
    install(WebSockets)
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
    install(UserAgent) {
        agent = "ktor"
    }
    engine {
        pipelining = true
    }
}


suspend fun getRequest(request: String) = http.get<String>(request)

//sealed class RequestType {
//    object GET: RequestType()
//    object POST: RequestType()
//    object PUT: RequestType()
//}
//
//
//
//fun test(request: RequestType) = fun() {
//    // Return a function for every such request type
//    when(request) {
//        RequestType.GET -> {
//
//        }
//        RequestType.POST -> {
//
//        }
//        RequestType.PUT -> {
//
//        }
//    }
//}


/*

server("heroku.predictor") {
    route("/dialogflow", request = DialogRequest, response = DialogResponse, headers = DialogHeaders)

}

Move all this code into commons repo.
Rename the Repo as ktorfit,
Have all code for serialization and networking inside that folder

 */






























