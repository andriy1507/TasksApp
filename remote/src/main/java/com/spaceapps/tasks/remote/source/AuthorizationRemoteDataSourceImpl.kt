package com.spaceapps.tasks.remote.source

import com.spaceapps.tasks.remote.api.AuthorizationApi
import com.spaceapps.tasks.remote.model.UsernamePasswordRemote
import com.spaceapps.tasks.remote.ws.ChatSocketService
import javax.inject.Inject

class AuthorizationRemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthorizationApi,
    private val chatSocketService: ChatSocketService
) : AuthorizationRemoteDataSource {
    override suspend fun login(username: String, password: String): String {
        chatSocketService.sendChatMessage("Hello from ANDROID")
        return authApi.login(username, password)
    }

    override suspend fun register(username: String, password: String): String {
        return authApi.register(UsernamePasswordRemote(username, password))
    }
}