package com.spaceapps.tasks.core_utils.di

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class NotificationsModule {

    @Provides
    fun provideNotificationsManager(context: Context): NotificationManagerCompat {
        return NotificationManagerCompat.from(context)
    }
}