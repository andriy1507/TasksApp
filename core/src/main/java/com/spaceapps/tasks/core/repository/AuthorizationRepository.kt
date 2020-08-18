package com.spaceapps.tasks.core.repository

interface AuthorizationRepository {

    suspend fun login(username: String, password: String): String

    suspend fun register(username: String, password: String): String

    fun storeAuthorizationToken(token:String)

    fun getAuthToken():String?
}