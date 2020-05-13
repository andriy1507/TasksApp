package com.spaceapps.tasks.local.di.module

import com.spaceapps.tasks.local.SpaceAppsTaskDb
import com.spaceapps.tasks.local.dao.CategoriesDao
import com.spaceapps.tasks.local.dao.TasksDao
import dagger.Module
import dagger.Provides

@Module
class DaoModule {

    @Provides
    fun provideTasksDao(db: SpaceAppsTaskDb): TasksDao {
        return db.getTasksDao()
    }

    @Provides
    fun provideCategoriesDao(db: SpaceAppsTaskDb):CategoriesDao{
        return db.getCategoriesDao()
    }
}