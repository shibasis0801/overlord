package com.shibasispatnaik.kclient

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
data class Student(val name: String, val age: Int)

typealias ResolveReject<T> = (resolve: (T) -> Unit, reject: (Throwable) -> Unit) -> Unit
expect class Completable<T>(executor: ResolveReject<T>) {

    public fun<R> then(onFulfilled: ((T) -> R)?): Completable<R>
    public fun<R> catch(onRejected: ((Throwable) -> R)): Completable<R>

    companion object {
        public fun<T> resolve(value: T): Completable<T>
        public fun reject(error: Throwable): Completable<Nothing>
    }
}

