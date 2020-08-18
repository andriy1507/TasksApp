package com.spaceapps.tasks.remote.model

import com.google.gson.annotations.SerializedName

data class UsernamePasswordRemote(
    @SerializedName("username")
    val username:String,
    @SerializedName("password")
    val password:String
):RemoteEntity