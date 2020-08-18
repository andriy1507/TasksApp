package com.spaceapps.tasks.create.di

import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.ApplicationProvider
import com.spaceapps.tasks.create.CreateTaskFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [ApplicationProvider::class],
    modules = [ViewModelModule::class]
)
interface CreateScreenComponent {

    fun inject(fragment: CreateTaskFragment)

    @Component.Builder
    interface Builder {

        fun provider(provider: ApplicationProvider):Builder

        @BindsInstance
        fun fragment(fragment: CreateTaskFragment):Builder

        fun build():CreateScreenComponent
    }

    companion object {
        @JvmStatic
        fun init(fragment: CreateTaskFragment):CreateScreenComponent{
            return DaggerCreateScreenComponent.builder()
                .fragment(fragment)
                .provider((fragment.context?.applicationContext as App).getProvider())
                .build()
        }
    }
}