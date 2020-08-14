package com.spaceapps.tasks.remote.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.spaceapps.tasks.remote.BuildConfig
import com.spaceapps.tasks.remote.api.interceptors.AuthorizationInterceptor
import com.spaceapps.tasks.remote.source.AuthTokenStorage
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class HttpClientModule {

    @Provides
    fun provideOkHttpClient(authInterceptor: AuthorizationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideAuthInterceptor(authTokenStorage: AuthTokenStorage): AuthorizationInterceptor {
        return AuthorizationInterceptor(authTokenStorage)
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().client(client)
            .baseUrl(BuildConfig.SERVER_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}