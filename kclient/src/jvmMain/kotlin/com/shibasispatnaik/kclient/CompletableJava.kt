package com.shibasispatnaik.kclient

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

actual class Completable<T> {
    val future: CompletableFuture<T>

    actual constructor(executor: CompletableExecutor<T>) {
        future = CompletableFuture<T>()
        executor(future::complete, future::completeExceptionally)
    }

    constructor(newFuture: CompletableFuture<T>) {
        future = newFuture
    }

    actual fun <R> then(onFulfilled: ((T) -> R)?) = Completable<R>(future.thenApplyAsync(onFulfilled))
    actual fun <R> catch(onRejected: (Throwable) -> R) = Completable<R>(future.handle { _, u -> onRejected(u) })


    actual companion object {
        actual fun <T> resolve(value: T) = Completable<T> { resolve, _ -> resolve(value)  }
        actual fun reject(error: Throwable) = Completable<Nothing> { _, reject -> reject(error) }
    }
}

fun main(args: Array<String>) {
    Completable<String> { resolve, reject ->
        if (Math.random() < 0.7) {
            Thread.sleep(1000)
            resolve("Success")
        }
        else {
            reject(Error("SHIBASISERROR"))
        }
    }.then {
        println(it)
    }
}
