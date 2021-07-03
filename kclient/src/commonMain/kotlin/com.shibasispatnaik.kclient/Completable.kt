package com.shibasispatnaik.kclient


typealias CompletableExecutor<T> = (resolve: (T) -> Unit, reject: (Throwable) -> Unit) -> Unit
expect class Completable<T>(executor: CompletableExecutor<T>) {

    public fun<R> then(onFulfilled: ((T) -> R)?): Completable<R>
    public fun<R> catch(onRejected: ((Throwable) -> R)): Completable<R>

    companion object {
//        public fun <S> all(promise: Array<out Completable<S>>): Completable<Array<out S>>
        public fun<T> resolve(value: T): Completable<T>
        public fun reject(error: Throwable): Completable<Nothing>
    }
}

