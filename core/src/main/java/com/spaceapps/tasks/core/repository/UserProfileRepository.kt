package com.spaceapps.tasks.core.repository

import com.spaceapps.tasks.core.model.UserProfileModel
import java.io.File

interface UserProfileRepository {

    suspend fun getUserProfile(): UserProfileModel?

    suspend fun setProfileImage(image:File):String?
}