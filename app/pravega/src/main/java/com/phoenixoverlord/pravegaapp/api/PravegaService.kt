package com.phoenixoverlord.pravegaapp.api

import com.phoenixoverlord.pravegaapp.config.Server
import com.phoenixoverlord.pravegaapp.api.core.friend.FriendAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Needs Proper design
 * Ex: Inject this service by default.
 */
public class PravegaService(server: Server) {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val AI: Retrofit
    val API: Retrofit

    val friendAPI: FriendAPI

    private fun createAdapter(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    init {
        AI = createAdapter(server.AI)
        API = createAdapter(server.API)

        friendAPI = API.create(FriendAPI::class.java)
    }
}
