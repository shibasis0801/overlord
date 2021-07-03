package com.shibasispatnaik.kclient

import kotlin.js.Promise

actual class Completable<T> actual constructor(executor: CompletableExecutor<T>): Promise<T>(executor) {
    actual override fun <R> catch(onRejected: (Throwable) -> R) =
        super.catch(onRejected) as Completable<R>

    actual override fun <R> then(onFulfilled: ((T) -> R)?) =
        super.then(onFulfilled) as Completable<R>

    actual companion object {
//        public fun <S> all(promise: Array<out Promise<S>>): Promise<Array<out S>> = this.all(promise)

        actual fun <T> resolve(value: T) = Promise.resolve(value) as Completable
        actual fun reject(value: Throwable) = Promise.reject(value) as Completable<Nothing>

    }
}
