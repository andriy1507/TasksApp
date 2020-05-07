package com.spaceapps.tasks.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spaceapps.tasks.local.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class SpaceAppsTaskDb : RoomDatabase() {

}