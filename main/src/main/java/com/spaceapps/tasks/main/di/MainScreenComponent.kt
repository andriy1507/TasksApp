package com.spaceapps.tasks.main.di

import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.ApplicationProvider
import com.spaceapps.tasks.main.MainFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [ApplicationProvider::class],
    modules = [MainViewModelModule::class]
)
interface MainScreenComponent {

    fun inject(fragment: MainFragment)

    @Component.Builder
    interface Builder {

        fun provider(provider: ApplicationProvider): Builder

        @BindsInstance
        fun fragment(fragment: MainFragment): Builder

        fun build(): MainScreenComponent
    }

    class Initializer {
        fun init(fragment: MainFragment): MainScreenComponent {
            return DaggerMainScreenComponent.builder()
                .fragment(fragment)
                .provider((fragment.context?.applicationContext as App).getProvider())
                .build()
        }
    }
}