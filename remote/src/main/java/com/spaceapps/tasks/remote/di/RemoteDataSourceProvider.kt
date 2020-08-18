package com.spaceapps.tasks.remote.di

import com.spaceapps.tasks.remote.source.AuthTokenStorage
import com.spaceapps.tasks.remote.source.AuthorizationRemoteDataSource
import com.spaceapps.tasks.remote.source.UserProfileRemoteDataSource

interface RemoteDataSourceProvider {

    fun provideAuthorizationRemoteDataSource(): AuthorizationRemoteDataSource

    fun provideAuthorizationTokenStorage(): AuthTokenStorage

    fun provideUserProfileRemoteDataSource(): UserProfileRemoteDataSource
}