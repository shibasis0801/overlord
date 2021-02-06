package com.overlord.annotations

import kotlin.js.Promise

actual class CompletablePromise<T> actual constructor(executor: (resolve: (T) -> Unit, reject: (Throwable) -> Unit) -> Unit): Promise<T>(executor) {
    actual override fun <R> catch(onRejected: (Throwable) -> R): CompletablePromise<R> {
        return super.catch(onRejected) as CompletablePromise<R>
    }

    actual override fun <R> then(onFulfilled: ((T) -> R)?): CompletablePromise<R> {
        return super.then(onFulfilled) as CompletablePromise<R>
    }

    actual companion object {
        actual fun <T> resolve(value: T): CompletablePromise<T> {
            return Promise.resolve(value) as CompletablePromise
        }

        actual fun <T> reject(value: Throwable): CompletablePromise<Nothing> {
            return Promise.reject(value) as CompletablePromise<Nothing>
        }
    }
}



