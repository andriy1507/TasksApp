package com.spaceapps.tasks.core_utils.di

import android.content.Context
import com.spaceapps.tasks.core.di.UtilsProvider
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NotificationsModule::class])
interface UtilsComponent : UtilsProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): UtilsComponent
    }

    class Initializer {
        fun init(context: Context): UtilsComponent {
            return DaggerUtilsComponent.builder()
                .context(context)
                .build()
        }
    }
}