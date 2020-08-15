package com.spaceapps.tasks.core.di

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.spaceapps.tasks.core.repository.AuthorizationRepository
import com.spaceapps.tasks.core.repository.TasksRepository
import com.spaceapps.tasks.core.repository.UserProfileRepository
import com.squareup.picasso.Picasso

interface ApplicationProvider : RepositoryProvider, UtilsProvider {

    fun provideContext(): Context

}

interface UtilsProvider {

    fun provideNotificationsManager(): NotificationManagerCompat

    fun providePicasso(): Picasso
}

interface RepositoryProvider {

    fun provideTasksRepository(): TasksRepository

    fun provideAuthorizationRepository(): AuthorizationRepository

    fun provideUserProfileRepository(): UserProfileRepository
}