package com.spaceapps.tasks.local.dao

import androidx.paging.DataSource
import androidx.room.*
import com.spaceapps.tasks.local.model.SubTaskLocal

@Dao
interface SubTasksDao : BaseDao<SubTaskLocal> {

    @Insert
    override fun insert(vararg items: SubTaskLocal):List<Long>

    @Delete
    override fun delete(vararg items: SubTaskLocal)

    @Update
    override fun update(vararg items: SubTaskLocal)

    @Query("SELECT * FROM SUBTASKS")
    override fun selectAll(): DataSource.Factory<Int, SubTaskLocal>
}