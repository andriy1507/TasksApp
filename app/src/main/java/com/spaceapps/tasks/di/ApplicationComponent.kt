package com.spaceapps.tasks.di

import com.spaceapps.tasks.TasksApplication
import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.ApplicationProvider
import com.spaceapps.tasks.core.di.RepositoryProvider
import com.spaceapps.tasks.core.di.UtilsProvider
import com.spaceapps.tasks.core_utils.di.UtilsComponent
import com.spaceapps.tasks.repository.di.RepositoryComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class
    ],
    dependencies = [
        RepositoryProvider::class,
        UtilsProvider::class
    ]
)
interface ApplicationComponent : ApplicationProvider {

    fun inject(app: TasksApplication)

    @Component.Builder
    interface Builder {

        fun provider(provider: RepositoryProvider): Builder

        fun provider(provider: UtilsProvider): Builder

        @BindsInstance
        fun application(app: App): Builder

        fun build(): ApplicationComponent
    }
    companion object {
        @JvmStatic
        fun init(app: TasksApplication): ApplicationComponent {
            return DaggerApplicationComponent.builder()
                .application(app)
                .provider(RepositoryComponent.init(app))
                .provider(UtilsComponent.init(app))
                .build()
        }
    }
}