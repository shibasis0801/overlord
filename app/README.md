# Build your ideas with Android and Spring in Kotlin

## Notes
Please ensure you have a proper JDK in your configuration.
Use Android Studio Alpha as this contains Compose code. 
Compose is very similar to React, and you should definitely look into it.


## Code Structure
This has 3 gradle modules (code: kotlin, java-8, buildscript: groovy)

It has implementation for one Route using Retrofit(common) and RestController(Spring),Diagnostics API. It is the hello world implementation for this project. 

You will see that the data classes and the interface for both the app and the server are exactly the same. This helps you to abstract out a lot of functionality and call server side routes as if they were just plain async functions (RPC model). 

The async method of choice is kotlin coroutines as it is being adopted by Android team. 

### common : 
Pure Kotlin/Java Code shared across app and server

### app :
Android App with Jetpack (including compose)

### server : 
Spring Boot WebFlux (reactive stack)

Have to implement WebSockets with Flow and Channels on both sides to make WebSocket communication as effortless as this does for HTTP. dia



### Instructions

Open this project in Android Studio Alpha
First run the server from the command line using 
```./gradlew server:bootRun```

You can also add a gradle task in AndroidStudio (remember to allow runInParallel) to run from the GUI.

(When compose is out of alpha, you should be able to open this as an IntelliJ project directly)

If your machine and your android are on the same network, then there is no further setup. The app/build.gradle contains a BuildTime constant which has the IP address of your machine. The code is configured to pick it up. 

If you now run your android app normally, you should be able to see a TextView with Hello from Spring. 

Start building your idea now :D


