package com.spaceapps.tasks.repository

import com.spaceapps.tasks.core.repository.AuthorizationRepository
import com.spaceapps.tasks.remote.source.AuthTokenStorage
import com.spaceapps.tasks.remote.source.AuthorizationRemoteDataSource
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val authTokenStorage: AuthTokenStorage,
    private val authRemoteDataSource: AuthorizationRemoteDataSource
) : AuthorizationRepository {
    override suspend fun login(username: String, password: String): String {
        return authRemoteDataSource.login(username, password)
    }

    override suspend fun register(username: String, password: String): String {
        return authRemoteDataSource.register(username, password)
    }

    override fun storeAuthorizationToken(token: String) {
        authTokenStorage.storeAuthToken(token)
    }

    override fun getAuthToken(): String? {
        return authTokenStorage.getAuthToken()
    }
}