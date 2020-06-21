package com.spaceapps.tasks.local.di.module

import com.spaceapps.tasks.local.source.SubTasksLocalDataSource
import com.spaceapps.tasks.local.source.TasksLocalDataSource
import com.spaceapps.tasks.local.source.impl.SubTasksLocalDataSourceImpl
import com.spaceapps.tasks.local.source.impl.TasksLocalDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @Binds
    fun bindTasksLocalDataSource(impl: TasksLocalDataSourceImpl): TasksLocalDataSource

    @Binds
    fun bindSubTasksLocalDataSource(impl: SubTasksLocalDataSourceImpl): SubTasksLocalDataSource
}