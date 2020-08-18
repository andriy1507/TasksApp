package com.spaceapps.tasks.repository.di

import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.RepositoryProvider
import com.spaceapps.tasks.local.di.LocalDataSourceComponent
import com.spaceapps.tasks.local.di.LocalDataSourceProvider
import com.spaceapps.tasks.remote.di.RemoteDataSourceComponent
import com.spaceapps.tasks.remote.di.RemoteDataSourceProvider
import com.spaceapps.tasks.repository.di.module.RepositoryModule
import dagger.Component

@Component(
    dependencies = [
        LocalDataSourceProvider::class,
        RemoteDataSourceProvider::class
    ],
    modules = [
        RepositoryModule::class
    ]
)
interface RepositoryComponent : RepositoryProvider {

    @Component.Builder
    interface Builder {

        fun provider(provider: LocalDataSourceProvider): Builder

        fun provider(provider: RemoteDataSourceProvider): Builder

        fun build(): RepositoryComponent
    }

    companion object {
        @JvmStatic
        fun init(app: App): RepositoryComponent {
            return DaggerRepositoryComponent.builder()
                .provider(LocalDataSourceComponent.init(app.getContext()))
                .provider(RemoteDataSourceComponent.init(app.getContext()))
                .build()
        }
    }
}