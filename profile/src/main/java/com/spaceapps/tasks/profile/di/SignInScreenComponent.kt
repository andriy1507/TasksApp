package com.spaceapps.tasks.profile.di

import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.ApplicationProvider
import com.spaceapps.tasks.profile.SignInFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [ApplicationProvider::class], modules = [SignInScreenModule::class])
interface SignInScreenComponent {

    fun inject(fragment: SignInFragment)

    @Component.Builder
    interface Builder {

        fun provider(provider: ApplicationProvider):Builder

        @BindsInstance
        fun fragment(fragment: SignInFragment): Builder

        fun build(): SignInScreenComponent
    }

    companion object {
        @JvmStatic
        fun init(fragment: SignInFragment):SignInScreenComponent{
            return DaggerSignInScreenComponent.builder()
                .fragment(fragment)
                .provider((fragment.context?.applicationContext as App).getProvider())
                .build()
        }
    }
}