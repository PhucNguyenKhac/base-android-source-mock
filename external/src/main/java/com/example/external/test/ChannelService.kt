package com.example.external.test

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChannelService {
    @GET("channels")
    suspend fun getInfo(
        @Query("part") part: String,
        @Query("id") id: String,
        @Query("key") key: String
    ) : ChannelEntity

    @GET("channels?part=snippet&id=UCkXmLjEr95LVtGuIm3l2dPg&key=AIzaSyBV7RM1z-3NeAxn8-jZktdYqLT5TqrbNXI")
    suspend fun getInfo(): ChannelEntity
}