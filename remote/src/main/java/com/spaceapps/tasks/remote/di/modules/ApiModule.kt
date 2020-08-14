package com.spaceapps.tasks.remote.di.modules

import com.spaceapps.tasks.remote.api.AuthorizationApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun provideAuthorizationApi(retrofit: Retrofit): AuthorizationApi {
        return retrofit.create(AuthorizationApi::class.java)
    }

}