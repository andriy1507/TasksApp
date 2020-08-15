package com.spaceapps.tasks.remote.api.interceptors

import com.spaceapps.tasks.remote.source.AuthTokenStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val authTokenStorage: AuthTokenStorage) : Interceptor {
    companion object {
        private const val AUTH_HEADER_NAME = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(AUTH_HEADER_NAME, authTokenStorage.getAuthToken().orEmpty())
            .build()
        return chain.proceed(newRequest)
    }
}