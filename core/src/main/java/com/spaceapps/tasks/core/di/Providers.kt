package com.spaceapps.tasks.core.di

import com.spaceapps.tasks.core.repository.TasksRepository

interface ApplicationProvider : RepositoryProvider

interface RepositoryProvider {

    fun provideTasksRepository(): TasksRepository
}