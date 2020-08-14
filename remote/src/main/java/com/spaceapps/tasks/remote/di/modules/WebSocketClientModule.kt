package com.spaceapps.tasks.remote.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.spaceapps.tasks.remote.BuildConfig
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class WebSocketClientModule {

    @Provides
    fun providesApplication(context: Context):Application{
        return context.applicationContext as Application
    }

    @Provides
    fun provideScarlet(
        client:OkHttpClient,
        gson:Gson,
        application: Application
    ):Scarlet{
        return Scarlet.Builder()
            .webSocketFactory(client.newWebSocketFactory(BuildConfig.SERVER_WS_URL))
            .addMessageAdapterFactory(GsonMessageAdapter.Factory(gson))
            .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
            .lifecycle(AndroidLifecycle.ofApplicationForeground(application))
            .build()
    }
}