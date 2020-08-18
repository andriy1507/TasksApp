package com.spaceapps.tasks.remote.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.spaceapps.tasks.remote.BuildConfig
import com.spaceapps.tasks.remote.api.interceptors.AuthorizationInterceptor
import com.spaceapps.tasks.remote.di.annotation.ScarletScope
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Logger
import timber.log.Timber

@Module
class WebSocketClientModule {

    @Provides
    fun providesApplication(context: Context): Application {
        return context.applicationContext as Application
    }

    @Provides
    @ScarletScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : Logger {
            override fun log(message: String) {
                Timber.tag("Scarlet").d(message)
            }
        })
    }

    @Provides
    @ScarletScope
    fun provideOkHttpClient(
        @ScarletScope
        interceptor: Interceptor,
        @ScarletScope
        loggingInterceptor: HttpLoggingInterceptor,
        authenticator: AuthorizationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authenticator)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @ScarletScope
    fun provideInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest = chain.request().newBuilder()
                    .addHeader("destination", "/app").build()
                return chain.proceed(newRequest)
            }

        }
    }

    @Provides
    fun provideScarlet(
        @ScarletScope
        client: OkHttpClient,
        gson: Gson,
        application: Application
    ): Scarlet {
        return Scarlet.Builder()
            .webSocketFactory(client.newWebSocketFactory(BuildConfig.SERVER_WS_URL))
            .addMessageAdapterFactory(GsonMessageAdapter.Factory(gson))
            .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
            .lifecycle(AndroidLifecycle.ofApplicationForeground(application))
            .build()
    }
}