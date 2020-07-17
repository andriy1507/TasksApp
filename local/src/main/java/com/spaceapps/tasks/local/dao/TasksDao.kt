package com.spaceapps.tasks.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.spaceapps.tasks.local.model.TaskLocal
import com.spaceapps.tasks.local.model.TaskWithSubs

@Dao
interface TasksDao : BaseDao<TaskLocal> {

    @Insert(onConflict = REPLACE)
    override fun insert(vararg items: TaskLocal): List<Long>

    @Delete
    override fun delete(vararg items: TaskLocal)

    @Update
    override fun update(vararg items: TaskLocal)

    @Query("SELECT * FROM TASKS")
    override fun selectAll(): DataSource.Factory<Int, TaskLocal>

    @Transaction
    @Query("SELECT * FROM TASKS")
    fun selectAllWithSubTasks(): DataSource.Factory<Int, TaskWithSubs>

    @Transaction
    @Query("SELECT * FROM TASKS WHERE id = :id LIMIT 1")
    fun selectTaskWithSubTasksById(id: Long): LiveData<TaskWithSubs?>
}