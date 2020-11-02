package com.overlord.core

actual class Http {
    actual fun get(message: String) {
        println("Not implemented for Native")
    }
}


// Use PromiseKit to enable Promises on iOS
//https://github.com/mxcl/PromiseKit/blob/master/Documentation/ObjectiveC.md
