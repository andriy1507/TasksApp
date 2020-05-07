package com.spaceapps.tasks.local.di

import android.content.Context
import com.spaceapps.tasks.local.di.module.DaoModule
import com.spaceapps.tasks.local.di.module.DataSourceModule
import com.spaceapps.tasks.local.di.module.RoomModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        RoomModule::class,
        DataSourceModule::class,
        DaoModule::class
    ]
)
interface LocalDataSourceComponent : LocalDataSourceProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): LocalDataSourceComponent
    }

    class Initializer {
        fun init(context: Context): LocalDataSourceComponent {
            return DaggerLocalDataSourceComponent.builder()
                .context(context)
                .build()
        }
    }
}