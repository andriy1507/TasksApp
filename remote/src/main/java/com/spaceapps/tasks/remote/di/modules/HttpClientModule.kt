package com.spaceapps.tasks.remote.di.modules

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.spaceapps.tasks.remote.BuildConfig
import com.spaceapps.tasks.remote.api.interceptors.AuthorizationInterceptor
import com.spaceapps.tasks.remote.di.annotation.RetrofitScope
import com.spaceapps.tasks.remote.source.AuthTokenStorage
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Logger
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Module
class HttpClientModule {

    companion object {
        private const val CACHE_MAX_SIZE: Long = 10 * 1024 * 1024
    }

    @Provides
    fun provideCache(context: Context): Cache {
        val cacheDir = context.cacheDir
        return Cache(cacheDir, CACHE_MAX_SIZE)
    }

    @Provides
    @RetrofitScope
    fun provideOkHttpClient(
        authenticator: AuthorizationInterceptor,
        @RetrofitScope
        loggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: StethoInterceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authenticator)
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(5, 60, TimeUnit.SECONDS))
            .cookieJar(CookieJar.NO_COOKIES)
            .writeTimeout(60, TimeUnit.SECONDS)
            .cache(cache)
            .addNetworkInterceptor(networkInterceptor)
            .build()
    }

    @Provides
    fun provideNetworkInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }

    @Provides
    @RetrofitScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
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
    fun provideRetrofit(
        @RetrofitScope
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder().client(client)
            .baseUrl(BuildConfig.SERVER_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}