package com.spaceapps.tasks.di

import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.ApplicationProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class
    ]
)
interface ApplicationComponent : ApplicationProvider {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): ApplicationComponent
    }

    class Initializer {
        fun init(app: App): ApplicationComponent {
            return DaggerApplicationComponent.builder()
                .application(app)
                .build()
        }
    }
}