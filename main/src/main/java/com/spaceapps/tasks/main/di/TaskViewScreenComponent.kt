package com.spaceapps.tasks.main.di

import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.ApplicationProvider
import com.spaceapps.tasks.main.TaskViewBottomSheet
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [ApplicationProvider::class],
    modules = [TaskViewViewModelModule::class]
)
interface TaskViewScreenComponent {

    fun inject(fragment: TaskViewBottomSheet)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun fragment(fragment: TaskViewBottomSheet): Builder

        fun provider(provider: ApplicationProvider): Builder

        fun build(): TaskViewScreenComponent
    }

    class Initializer {
        fun init(fragment: TaskViewBottomSheet): TaskViewScreenComponent {
            return DaggerTaskViewScreenComponent.builder()
                .provider((fragment.context?.applicationContext as App).getProvider())
                .fragment(fragment)
                .build()
        }
    }
}