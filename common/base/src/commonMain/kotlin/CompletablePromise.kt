package com.overlord.annotations

expect class CompletablePromise<T>(executor: (resolve: (T) -> Unit, reject: (Throwable) -> Unit) -> Unit) {
    fun<R> then(onFulfilled: ((T) -> R)?): CompletablePromise<R>
    fun<R> catch(onRejected: ((Throwable) -> R)): CompletablePromise<R>

    companion object {
        fun<T> resolve(value: T): CompletablePromise<T>
        fun<T> reject(value: Throwable): CompletablePromise<Nothing>
    }
}
