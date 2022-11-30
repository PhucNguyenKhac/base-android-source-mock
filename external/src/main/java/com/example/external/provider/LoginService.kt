package com.example.external.provider

import retrofit2.http.GET

interface LoginService {
    @GET("v3/1e8bdf25-e1c3-47d2-8db1-bfa5b172ef62")
    suspend fun login(): LoginEntity
}