package com.spaceapps.tasks.remote.api

import com.spaceapps.tasks.remote.model.UsernamePasswordRemote
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthorizationApi {

    @GET("/auth/login")
    suspend fun login(@Query("username") userName:String, @Query("password") password:String): String

    @POST("/auth/register")
    suspend fun register(@Body credentials: UsernamePasswordRemote): String
}