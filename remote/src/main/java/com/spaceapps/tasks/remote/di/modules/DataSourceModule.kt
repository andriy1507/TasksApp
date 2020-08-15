package com.spaceapps.tasks.remote.di.modules

import com.spaceapps.tasks.remote.source.AuthorizationRemoteDataSource
import com.spaceapps.tasks.remote.source.UserProfileRemoteDataSource
import com.spaceapps.tasks.remote.source.impl.AuthorizationRemoteDataSourceImpl
import com.spaceapps.tasks.remote.source.impl.UserProfileRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @Binds
    fun bindAuthorizationDataSource(impl: AuthorizationRemoteDataSourceImpl): AuthorizationRemoteDataSource

    @Binds
    fun bindUserProfileDataSource(impl: UserProfileRemoteDataSourceImpl): UserProfileRemoteDataSource
}