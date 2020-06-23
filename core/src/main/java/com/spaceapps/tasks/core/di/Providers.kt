package com.spaceapps.tasks.core.di

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.spaceapps.tasks.core.repository.TasksRepository

interface ApplicationProvider : RepositoryProvider, UtilsProvider {

    fun provideContext(): Context

}

interface UtilsProvider {

    fun provideNotificationsManager(): NotificationManagerCompat
}

interface RepositoryProvider {

    fun provideTasksRepository(): TasksRepository
}