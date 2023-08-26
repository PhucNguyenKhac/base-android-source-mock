package com.example.external.channel

import retrofit2.http.GET
import retrofit2.http.Query

interface ChannelService {
    @GET("channels")
    suspend fun getInfoChannel(
        @Query("part") part: String,
        @Query("id") id: String,
        @Query("key") key: String
    ): ChannelEntity

    @GET("search")
    suspend fun searchChannelInfo(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("type") type: String,
        @Query("q") q: String,
        @Query("maxResults") maxResults: Int,
        @Query("order") order: String,
    ): SearchChannelEntity

}