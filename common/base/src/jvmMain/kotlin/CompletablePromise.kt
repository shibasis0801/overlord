package com.overlord.annotations

import java.util.concurrent.CompletableFuture

/*
then ->
thenApply
thenCombine
thenCompose
thenAccept

catch ->
exceptionally

resolve ->
complete
reject ->
completeExceptionally

all ->
allOf

resolve/reject
supplyAsync -> complete/completeExceptionally
 */
actual class CompletablePromise<T>  {

    // Must be exposed, must have secondary constructor to build a CP from a future
    var future = CompletableFuture<T>()
    private set


    actual constructor(executor: (resolve: (T) -> Unit, reject: (Throwable) -> Unit) -> Unit) {
        executor({
            future = CompletableFuture.supplyAsync { it }
        }, {
            future = CompletableFuture.supplyAsync { throw it }
        })
    }

    constructor(future: CompletableFuture<T>) {
        this.future = future
    }

    actual fun <R> then(onFulfilled: ((T) -> R)?): CompletablePromise<R> {
        return CompletablePromise(future.thenApply(onFulfilled))
    }

    actual fun <R> catch(onRejected: (Throwable) -> R): CompletablePromise<R> {
        return CompletablePromise(
                CompletableFuture.supplyAsync {
                    onRejected(Error("Implement catch in CompletablePromise, JVM"))
                }
        )
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
