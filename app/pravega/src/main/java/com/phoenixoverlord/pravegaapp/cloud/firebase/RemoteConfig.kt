package com.phoenixoverlord.pravegaapp.cloud.firebase


import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import java.util.concurrent.CompletableFuture


fun getFrom(remoteConfig: FirebaseRemoteConfig, keys: Set<String>)
        = keys.associateWith { remoteConfig.getString(it) }

fun remoteConfig(defaultValues: Map<String, Any>): CompletableFuture<Map<String, String>> {
    val future = CompletableFuture<Map<String, String>>()

    val remoteConfig = FirebaseRemoteConfig.getInstance()
    remoteConfig.setDefaultsAsync(defaultValues)
        .addOnSuccessListener {
            remoteConfig.fetch(2)
                .addOnCompleteListener {task ->
                    if (task.isSuccessful) {
                        remoteConfig.activate()
                        future.complete(getFrom(remoteConfig, defaultValues.keys))
                    }
                    else {
                        future.completeExceptionally(task.exception)
                    }
                }
        }

    return future
}