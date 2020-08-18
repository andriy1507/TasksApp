package com.spaceapps.tasks.remote.api

import com.spaceapps.tasks.remote.model.UserProfileModelRemote
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import retrofit2.http.*
import java.util.*

interface UserProfileApi {

    @POST("/profile/image")
    @Multipart
    suspend fun setProfileImage(@Part image: MultipartBody.Part): String?

    @GET("/profile")
    suspend fun getUserProfile(): UserProfileModelRemote?
}