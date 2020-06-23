package com.spaceapps.tasks.firebase.di

import com.spaceapps.tasks.core.App
import com.spaceapps.tasks.core.di.ApplicationProvider
import com.spaceapps.tasks.firebase.FirebaseCloudMessagingService
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [ApplicationProvider::class], modules = [NotificationsModule::class])
interface FirebaseCloudMessagingServiceComponent {

    fun inject(service: FirebaseCloudMessagingService)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun service(service: FirebaseCloudMessagingService): Builder

        fun provider(provider: ApplicationProvider): Builder

        fun build(): FirebaseCloudMessagingServiceComponent
    }

    class Initializer {
        fun init(service: FirebaseCloudMessagingService): FirebaseCloudMessagingServiceComponent {
            return DaggerFirebaseCloudMessagingServiceComponent.builder()
                .service(service)
                .provider((service.applicationContext as App).getProvider())
                .build()
        }
    }
}