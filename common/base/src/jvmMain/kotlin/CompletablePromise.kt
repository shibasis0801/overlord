package com.overlord.annotations

import java.util.concurrent.CompletableFuture

actual class CompletablePromise<T> actual constructor(executor: (resolve: (T) -> Unit, reject: (Throwable) -> Unit) -> Unit) {
    private val future = CompletableFuture<T>()

    actual fun <R> then(onFulfilled: ((T) -> R)?): CompletablePromise<R> {

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

