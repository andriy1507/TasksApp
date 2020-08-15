package com.spaceapps.tasks.core_utils.di

import android.content.Context
import com.spaceapps.tasks.core.di.UtilsProvider
import com.spaceapps.tasks.remote.di.OkHttpClientProvider
import com.spaceapps.tasks.remote.di.RemoteDataSourceComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        NotificationsModule::class,
        PicassoModule::class
    ],
    dependencies = [OkHttpClientProvider::class]
)
interface UtilsComponent : UtilsProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun provider(provider: OkHttpClientProvider): Builder

        fun build(): UtilsComponent
    }

    companion object {
        @JvmStatic
        fun init(context: Context): UtilsComponent {
            return DaggerUtilsComponent.builder()
                .provider(RemoteDataSourceComponent.init(context))
                .context(context)
                .build()
        }
    }
}