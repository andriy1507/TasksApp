package com.spaceapps.tasks.remote.source

import android.content.SharedPreferences
import com.spaceapps.tasks.remote.BuildConfig

class AuthTokenStorage(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val AUTH_TOKEN_KEY = "${BuildConfig.LIBRARY_PACKAGE_NAME}::AUTH_TOKEN_KEY"
    }

    fun storeAuthToken(token: String) {
        sharedPreferences.edit().putString(AUTH_TOKEN_KEY, token).apply()
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString(AUTH_TOKEN_KEY, null)
    }

    fun hasAuthToken(): Boolean {
        return sharedPreferences.contains(AUTH_TOKEN_KEY)
    }
}