package com.spaceapps.tasks.remote.di

import android.content.Context
import com.spaceapps.tasks.remote.di.modules.*
import dagger.Binds
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ApiModule::class,
        HttpClientModule::class,
        DataSourceModule::class,
        TokenStorageModule::class,
        WebSocketClientModule::class,
        WebSocketServiceModule::class
    ]
)
interface RemoteDataSourceComponent : RemoteDataSourceProvider, OkHttpClientProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): RemoteDataSourceComponent
    }

    companion object {
        @JvmStatic
        fun init(context: Context): RemoteDataSourceComponent {
            return DaggerRemoteDataSourceComponent.builder()
                .context(context)
                .build()
        }
    }
}