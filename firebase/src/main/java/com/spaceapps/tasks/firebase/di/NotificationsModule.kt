package com.spaceapps.tasks.firebase.di

import com.spaceapps.tasks.firebase.notification.FirebaseNotificationsBuilder
import com.spaceapps.tasks.firebase.notification.impl.FirebaseNotificationsBuilderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

@Module
@InstallIn(ServiceComponent::class)
interface NotificationsModule {

    @Binds
    fun bindNotificationBuilder(impl: FirebaseNotificationsBuilderImpl): FirebaseNotificationsBuilder
}