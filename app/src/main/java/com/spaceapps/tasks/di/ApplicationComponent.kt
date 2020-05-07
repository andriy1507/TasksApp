package com.spaceapps.tasks.di

import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.ApplicationProvider
import com.spaceapps.tasks.core.di.RepositoryProvider
import com.spaceapps.tasks.repository.di.RepositoryComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class
    ],
    dependencies = [
        RepositoryProvider::class
    ]
)
interface ApplicationComponent : ApplicationProvider {


    @Component.Builder
    interface Builder {

        fun provider(provider: RepositoryProvider): Builder

        @BindsInstance
        fun application(app: App): Builder

        fun build(): ApplicationComponent
    }

    class Initializer {
        fun init(app: App): ApplicationComponent {
            return DaggerApplicationComponent.builder()
                .application(app)
                .provider(RepositoryComponent.Initializer().init(app))
                .build()
        }
    }
}