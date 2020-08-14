package com.spaceapps.tasks.remote.source

interface AuthorizationRemoteDataSource {

    suspend fun login(username: String, password: String): String

    suspend fun register(username: String, password: String): String
}