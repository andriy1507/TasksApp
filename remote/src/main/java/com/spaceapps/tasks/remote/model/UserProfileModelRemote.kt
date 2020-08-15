package com.spaceapps.tasks.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserProfileModelRemote(
    @SerializedName("id")
    val id: UUID?,
    @SerializedName("username")
    val userName: String?,
    @SerializedName("profileImage")
    val profileImage: String?
) : RemoteEntity
