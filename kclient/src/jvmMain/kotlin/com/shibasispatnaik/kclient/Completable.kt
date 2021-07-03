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

actual class Completable<T> actual constructor(executor: CompletableExecutor<T>): CompletableFuture<T>() {

//    This will not work. but is close. Read CompletableFuture.
    init {
        executor({
            completedFuture(it)
        }, {
            supplyAsync { it }
        })
    }

    actual fun <R> then(onFulfilled: ((T) -> R)?) =
        super.thenApplyAsync(onFulfilled) as Completable<R>

    actual fun <R> catch(onRejected: (Throwable) -> R) =
        super.handleAsync { _, err -> onRejected(err) } as Completable<R>


    actual companion object {
        actual fun <T> resolve(value: T) = supplyAsync { value } as Completable<T>
        actual fun reject(value: Throwable) = failedFuture<Nothing>(value) as Completable<Nothing>
    }
}

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


//Compiler Plugins, not exactly related but look into them for more power than reflection
