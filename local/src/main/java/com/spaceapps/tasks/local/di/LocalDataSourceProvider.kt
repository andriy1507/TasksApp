package com.spaceapps.tasks.local.di

import com.spaceapps.tasks.local.source.CategoriesLocalDataSource
import com.spaceapps.tasks.local.source.TasksLocalDataSource

interface LocalDataSourceProvider {

    fun provideTasksLocalDataSource(): TasksLocalDataSource

    fun provideCategoriesLocalDataSource(): CategoriesLocalDataSource
}