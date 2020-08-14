package com.spaceapps.tasks.remote.di.modules

import com.spaceapps.tasks.remote.source.AuthorizationRemoteDataSource
import com.spaceapps.tasks.remote.source.AuthorizationRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @Binds
    fun bindAuthorizationDataSource(impl: AuthorizationRemoteDataSourceImpl): AuthorizationRemoteDataSource
}