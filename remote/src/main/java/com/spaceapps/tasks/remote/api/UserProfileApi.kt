package com.spaceapps.tasks.remote.api

import com.spaceapps.tasks.remote.model.UserProfileModelRemote
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UserProfileApi {

    @POST("/profile/image")
    @Multipart
    suspend fun setProfileImage(@Part("image") image: MultipartBody.Part): String?

    @GET("/profile")
    suspend fun getUserProfile(): UserProfileModelRemote?
}