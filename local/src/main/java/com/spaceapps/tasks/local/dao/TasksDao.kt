package com.spaceapps.tasks.local.dao

import androidx.paging.DataSource
import androidx.room.*
import com.spaceapps.tasks.local.model.TaskLocal

@Dao
interface TasksDao {

    @Insert
    fun insertTasks(vararg tasks: TaskLocal)

    @Delete
    fun deleteTasks(vararg tasks: TaskLocal)

    @Update
    fun updateTasks(vararg tasks: TaskLocal)

    @Query("SELECT * FROM TASKS")
    fun selectAllTasks(): DataSource.Factory<Int, TaskLocal>
}