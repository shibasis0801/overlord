package com.overlord.annotations

import java.util.concurrent.CompletableFuture

actual class CompletablePromise<T> actual constructor(executor: (resolve: (T) -> Unit, reject: (Throwable) -> Unit) -> Unit) {
    // Must be exposed, must have secondary constructor to build a CP from a future
    val future = CompletableFuture<T>()

    actual fun <R> then(onFulfilled: ((T) -> R)?): CompletablePromise<R> {
        TODO("Not yet implemented")
    }

    actual fun <R> catch(onRejected: (Throwable) -> R): CompletablePromise<R> {
        TODO("Not yet implemented")
    }

    actual companion object {
        actual fun <T> resolve(value: T): CompletablePromise<T> {
            TODO("Not yet implemented")
        }

        actual fun <T> reject(value: Throwable): CompletablePromise<Nothing> {
            TODO("Not yet implemented")
        }
    }
}

//Compiler Plugins, not exactly related but look into them for more power than reflection
