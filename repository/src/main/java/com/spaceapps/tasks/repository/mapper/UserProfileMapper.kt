package com.spaceapps.tasks.repository.mapper

import android.util.Log
import com.spaceapps.tasks.core.model.UserProfileModel
import com.spaceapps.tasks.remote.model.UserProfileModelRemote
import java.util.*

fun UserProfileModel.toUserProfileRemote(): UserProfileModelRemote {
    return UserProfileModelRemote(id, userName, profileImage)
}

fun UserProfileModelRemote.toUserProfileModel(): UserProfileModel {
    return UserProfileModel(id, userName.orEmpty(), profileImage.orEmpty())
}