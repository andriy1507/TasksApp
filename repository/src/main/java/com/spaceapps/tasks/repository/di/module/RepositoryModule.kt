package com.spaceapps.tasks.repository.di.module

import com.spaceapps.tasks.core.repository.TasksRepository
import com.spaceapps.tasks.repository.TasksRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindTasksRepository(impl: TasksRepositoryImpl): TasksRepository
}