package com.overlord.annotations

expect class CompletablePromise<T>(executor: (resolve: (T) -> Unit, reject: (Throwable) -> Unit) -> Unit) {
    fun<R> then(onFulfilled: ((T) -> R)?): CompletablePromise<R>
    fun<R> catch(onRejected: ((Throwable) -> R)): CompletablePromise<R>
}

expect fun<T> CompletablePromise<T>.resolve(value: T): CompletablePromise<T>
expect fun<T> CompletablePromise<T>.reject(value: Throwable): CompletablePromise<Nothing>

