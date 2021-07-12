package com.shibasispatnaik.kclient
import kotlin.js.Promise


actual class Completable<T> {
    val promise: Promise<T>

    actual constructor(executor: ResolveReject<T>) {
        promise = Promise(executor)
    }

    constructor(newPromise: Promise<T>) {
        promise = newPromise
    }

    actual fun <R> then(onFulfilled: ((T) -> R)?) = Completable(promise.then(onFulfilled))

    actual fun <R> catch(onRejected: (Throwable) -> R) = Completable(promise.catch(onRejected))


    actual companion object {
        actual fun <T> resolve(value: T) = Completable(Promise.resolve(value))
        actual fun reject(error: Throwable) = Completable(Promise.reject(error))

    }
}
fun<T> Promise<T>.toCompletable() = Completable(this)

fun t() {

    val y = 5
    val x  = Completable<String> { resolve, reject ->
        if (y == 5) {
            resolve("Yes")
        }
        else {
            reject(Throwable("No"))
        }
    }
}
