package com.spaceapps.tasks.remote.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.spaceapps.tasks.remote.BuildConfig
import com.spaceapps.tasks.remote.source.AuthTokenStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class TokenStorageModule {

    companion object {
        private const val SHARED_PREF_NAME = "${BuildConfig.LIBRARY_PACKAGE_NAME}_SHARED_PREF"
    }

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    fun provideAuthTokenStorage(prefs: SharedPreferences): AuthTokenStorage {
        return AuthTokenStorage(prefs)
    }
}