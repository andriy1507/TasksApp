package com.spaceapps.tasks.remote.source

import com.spaceapps.tasks.remote.model.UserProfileModelRemote
import java.io.File

interface UserProfileRemoteDataSource {

    suspend fun getUserProfile(): UserProfileModelRemote?

    suspend fun setProfileImage(file: File): String?
}