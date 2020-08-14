package com.spaceapps.tasks.account.di

import com.spaceapps.tasks.account.SpaceAppsAuthenticatorService
import com.spaceapps.tasks.account.di.module.AuthenticatorModule
import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.ApplicationProvider
import dagger.Component

@Component(dependencies = [ApplicationProvider::class], modules = [AuthenticatorModule::class])
interface AuthenticatorServiceComponent {

    fun inject(service: SpaceAppsAuthenticatorService)

    @Component.Builder
    interface Builder {

        fun provider(provider: ApplicationProvider): Builder

        fun build(): AuthenticatorServiceComponent
    }

    companion object {
        @JvmStatic
        fun init(service: SpaceAppsAuthenticatorService): AuthenticatorServiceComponent {
            return DaggerAuthenticatorServiceComponent.builder()
                .provider((service.applicationContext as App).getProvider())
                .build()
        }
    }
}