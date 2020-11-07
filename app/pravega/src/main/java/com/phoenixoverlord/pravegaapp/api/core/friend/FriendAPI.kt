package com.phoenixoverlord.pravegaapp.api.core.friend

import retrofit2.http.GET
import java.util.concurrent.CompletableFuture


public interface FriendAPI {
    @GET("/friends")
    fun getAllFriends(): CompletableFuture<Map<Int, Friend>>
}
