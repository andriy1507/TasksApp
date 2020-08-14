package com.spaceapps.tasks.remote.api

import com.spaceapps.tasks.remote.model.UsernamePasswordRemote
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthorizationApi {

    @GET("/auth/login")
    suspend fun login(@Body credentials: UsernamePasswordRemote): String

    @POST("/auth/register")
    suspend fun register(@Body credentials: UsernamePasswordRemote): String
}