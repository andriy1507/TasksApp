package com.spaceapps.tasks.repository

import com.spaceapps.tasks.core.model.UserProfileModel
import com.spaceapps.tasks.core.repository.UserProfileRepository
import com.spaceapps.tasks.remote.source.UserProfileRemoteDataSource
import com.spaceapps.tasks.repository.mapper.toUserProfileModel
import java.io.File
import javax.inject.Inject

class UserProfileRepositoryImpl
@Inject constructor(
    private val userProfileDataSource: UserProfileRemoteDataSource
) : UserProfileRepository {
    override suspend fun getUserProfile(): UserProfileModel? {
        return userProfileDataSource.getUserProfile()?.toUserProfileModel()
    }

    override suspend fun setProfileImage(image: File): String? {
        return userProfileDataSource.setProfileImage(image)
    }
}