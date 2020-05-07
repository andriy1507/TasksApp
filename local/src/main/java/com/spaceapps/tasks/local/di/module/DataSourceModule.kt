package com.spaceapps.tasks.local.di.module

import com.spaceapps.tasks.local.source.TasksLocalDataSource
import com.spaceapps.tasks.local.source.impl.TasksDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @Binds
    fun bindTasksLocalDataSource(impl: TasksDataSourceImpl): TasksLocalDataSource
}