package com.spaceapps.tasks.local.di

import com.spaceapps.tasks.local.source.SubTasksLocalDataSource
import com.spaceapps.tasks.local.source.TasksLocalDataSource

interface LocalDataSourceProvider {

    fun provideTasksLocalDataSource(): TasksLocalDataSource

    fun provideSubTasksLocalDataSource(): SubTasksLocalDataSource
}