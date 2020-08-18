package com.spaceapps.tasks.profile.di

import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.ApplicationProvider
import com.spaceapps.tasks.profile.ProfileFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [ApplicationProvider::class], modules = [ProfileScreenModule::class])
interface ProfileScreenComponent {

    fun inject(fragment: ProfileFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun fragment(fragment: ProfileFragment): Builder

        fun provider(provider: ApplicationProvider): Builder

        fun build(): ProfileScreenComponent
    }

    companion object {
        @JvmStatic
        fun init(fragment: ProfileFragment): ProfileScreenComponent {
            return DaggerProfileScreenComponent.builder()
                .fragment(fragment)
                .provider((fragment.context?.applicationContext as App).getProvider())
                .build()
        }
    }
}