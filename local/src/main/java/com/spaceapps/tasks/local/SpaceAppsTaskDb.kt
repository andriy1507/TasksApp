package com.spaceapps.tasks.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spaceapps.tasks.local.dao.SubTasksDao
import com.spaceapps.tasks.local.dao.TasksDao
import com.spaceapps.tasks.local.model.LocationLocal
import com.spaceapps.tasks.local.model.SubTaskLocal
import com.spaceapps.tasks.local.model.TaskLocal

@Database(
    entities = [TaskLocal::class, SubTaskLocal::class, LocationLocal::class],
    version = 2,
    exportSchema = true
)
abstract class SpaceAppsTaskDb : RoomDatabase() {

    abstract fun getTasksDao(): TasksDao

    abstract fun getSubTasksDao(): SubTasksDao
}