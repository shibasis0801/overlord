package com.overlord.annotations

actual class CompletablePromise<T> actual constructor(executor: (resolve: (T) -> Unit, reject: (Throwable) -> Unit) -> Unit) {
    actual fun <R> then(onFulfilled: ((T) -> R)?): CompletablePromise<R> {
        TODO("Not yet implemented")
    }

    actual fun <R> catch(onRejected: (Throwable) -> R): CompletablePromise<R> {
        TODO("Not yet implemented")
    }
}

actual fun <T> CompletablePromise<T>.resolve(value: T): CompletablePromise<T> {
    TODO("Not yet implemented")
}

actual fun <T> CompletablePromise<T>.reject(value: Throwable): CompletablePromise<Nothing> {
    TODO("Not yet implemented")
}
