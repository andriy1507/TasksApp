package com.spaceapps.tasks.remote.source.impl

import com.spaceapps.tasks.remote.api.UserProfileApi
import com.spaceapps.tasks.remote.model.UserProfileModelRemote
import com.spaceapps.tasks.remote.source.UserProfileRemoteDataSource
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UserProfileRemoteDataSourceImpl
@Inject constructor(
    private val userProfileApi: UserProfileApi
) : UserProfileRemoteDataSource {
    override suspend fun getUserProfile(): UserProfileModelRemote? {
        return userProfileApi.getUserProfile()
    }

    override suspend fun setProfileImage(file: File): String? {
        val mediaType = "image/*"
        val multipart = MultipartBody.Part.createFormData("image", file.name, file.asRequestBody(mediaType.toMediaTypeOrNull()))
        return userProfileApi.setProfileImage(multipart)
    }
}