package com.spaceapps.tasks.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.spaceapps.tasks.local.model.SubTaskLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface SubTasksDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg items: SubTaskLocal): List<Long>

    @Delete
    suspend fun delete(vararg items: SubTaskLocal)

    @Update
    suspend fun update(vararg items: SubTaskLocal)

    @Query("SELECT * FROM SUBTASKS")
    fun selectAll(): Flow<List<SubTaskLocal>>
}