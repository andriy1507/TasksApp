package com.spaceapps.tasks.repository.di

import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.RepositoryProvider
import com.spaceapps.tasks.local.di.LocalDataSourceComponent
import com.spaceapps.tasks.local.di.LocalDataSourceProvider
import com.spaceapps.tasks.repository.di.module.RepositoryModule
import dagger.Component

@Component(
    dependencies = [
        LocalDataSourceProvider::class
    ],
    modules = [
        RepositoryModule::class
    ]
)
interface RepositoryComponent : RepositoryProvider {

    @Component.Builder
    interface Builder {

        fun provider(provider: LocalDataSourceProvider): Builder

        fun build(): RepositoryComponent
    }

    class Initializer {
        fun init(app: App): RepositoryComponent {
            return DaggerRepositoryComponent.builder()
                .provider(LocalDataSourceComponent.Initializer().init(app.getContext()))
                .build()
        }
    }
}