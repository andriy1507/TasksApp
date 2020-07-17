package com.spaceapps.tasks.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.spaceapps.tasks.local.model.SubTaskLocal

@Dao
interface SubTasksDao {

    @Insert(onConflict = REPLACE)
    fun insert(vararg items: SubTaskLocal): List<Long>

    @Delete
    fun delete(vararg items: SubTaskLocal)

    @Update
    fun update(vararg items: SubTaskLocal)

    @Query("SELECT * FROM SUBTASKS")
    fun selectAll(): LiveData<List<SubTaskLocal>>
}