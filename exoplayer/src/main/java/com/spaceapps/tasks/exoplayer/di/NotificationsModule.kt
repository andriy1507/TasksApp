package com.spaceapps.tasks.exoplayer.di

import com.spaceapps.tasks.exoplayer.notification.MediaPlayerNotificationsBuilder
import com.spaceapps.tasks.exoplayer.notification.impl.MediaPlayerNotificationsBuilderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

@Module
@InstallIn(ServiceComponent::class)
interface NotificationsModule {

    @Binds
    fun bindNotificationBuilder(impl: MediaPlayerNotificationsBuilderImpl): MediaPlayerNotificationsBuilder
}