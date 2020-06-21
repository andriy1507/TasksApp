package com.spaceapps.tasks.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spaceapps.tasks.local.dao.SubTasksDao
import com.spaceapps.tasks.local.dao.TasksDao
import com.spaceapps.tasks.local.model.SubTaskLocal
import com.spaceapps.tasks.local.model.TaskLocal

@Database(entities = [TaskLocal::class, SubTaskLocal::class], version = 1, exportSchema = false)
abstract class SpaceAppsTaskDb : RoomDatabase() {

    abstract fun getTasksDao(): TasksDao

    abstract fun getSubTasksDao(): SubTasksDao
}