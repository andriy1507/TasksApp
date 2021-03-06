package com.spaceapps.tasks.local.di.module

import com.spaceapps.tasks.local.SpaceAppsTaskDb
import com.spaceapps.tasks.local.dao.SubTasksDao
import com.spaceapps.tasks.local.dao.TasksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class DaoModule {

    @Provides
    fun provideTasksDao(db: SpaceAppsTaskDb): TasksDao {
        return db.getTasksDao()
    }

    @Provides
    fun provideSubTasksDao(db: SpaceAppsTaskDb): SubTasksDao {
        return db.getSubTasksDao()
    }
}