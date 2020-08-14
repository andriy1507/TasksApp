package com.spaceapps.tasks.remote.di.modules

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.spaceapps.tasks.remote.BuildConfig
import com.spaceapps.tasks.remote.api.interceptors.AuthorizationInterceptor
import com.spaceapps.tasks.remote.source.AuthTokenStorage
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Logger
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

@Module
class HttpClientModule {

    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthorizationInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: StethoInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(networkInterceptor)
            .build()
    }

    @Provides
    fun provideNetworkInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : Logger {
            override fun log(message: String) {
                Timber.d("OkHttp: $message")
            }
        }).apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
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