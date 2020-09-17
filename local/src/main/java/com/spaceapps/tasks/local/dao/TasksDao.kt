package com.spaceapps.tasks.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.spaceapps.tasks.local.model.TaskLocal
import com.spaceapps.tasks.local.model.TaskWithSubs
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg items: TaskLocal): List<Long>

    @Delete
    suspend fun delete(vararg items: TaskLocal)

    @Update
    suspend fun update(vararg items: TaskLocal)

    @Query("SELECT * FROM TASKS")
    fun selectAll(): Flow<List<TaskLocal>>

    @Transaction
    @Query("SELECT * FROM TASKS")
    fun selectAllWithSubTasks(): Flow<List<TaskWithSubs>>

    @Transaction
    @Query("SELECT * FROM TASKS WHERE id = :id LIMIT 1")
    suspend fun selectTaskWithSubTasksById(id: Long): TaskWithSubs?
}