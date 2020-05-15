package com.spaceapps.tasks.local.dao

import androidx.paging.DataSource
import androidx.room.*
import com.spaceapps.tasks.local.model.TaskLocal

@Dao
interface TasksDao : BaseDao<TaskLocal> {

    @Insert
    override fun insert(vararg items: TaskLocal)

    @Delete
    override fun delete(vararg items: TaskLocal)

    @Update
    override fun update(vararg items: TaskLocal)

    @Query("SELECT * FROM TASKS")
    override fun selectAll(): DataSource.Factory<Int, TaskLocal>
}