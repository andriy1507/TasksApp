package com.spaceapps.tasks.local.di.module

import com.spaceapps.tasks.local.source.CategoriesLocalDataSource
import com.spaceapps.tasks.local.source.TasksLocalDataSource
import com.spaceapps.tasks.local.source.impl.CategoriesLocalDataSourceImpl
import com.spaceapps.tasks.local.source.impl.TasksLocalDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @Binds
    fun bindTasksLocalDataSource(impl: TasksLocalDataSourceImpl): TasksLocalDataSource

    @Binds
    fun bindCategoriesLocalDataSource(impl: CategoriesLocalDataSourceImpl): CategoriesLocalDataSource
}