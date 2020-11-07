package com.phoenixoverlord.pravega.api.core.friend

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.RoomDatabase
import com.phoenixoverlord.pravega.extensions.logDebug
import retrofit2.http.GET
import java.lang.Error
import java.util.concurrent.CompletableFuture
import kotlin.reflect.KClass


public interface FriendAPI {
    @GET("/friends")
    fun getAllFriends(): CompletableFuture<Map<Int, Friend>>
}
