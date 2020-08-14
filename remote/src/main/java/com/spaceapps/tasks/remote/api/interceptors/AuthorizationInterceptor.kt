package com.spaceapps.tasks.remote.api.interceptors

import com.spaceapps.tasks.remote.source.AuthTokenStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val authTokenStorage: AuthTokenStorage) : Interceptor {
    companion object {
        private const val AUTH_HEADER_NAME = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (authTokenStorage.hasAuthToken()) {
            chain.request().newBuilder()
                .addHeader(AUTH_HEADER_NAME, "Bearer ${authTokenStorage.getAuthToken().orEmpty()}")
        }
        return chain.proceed(chain.request())
    }
}