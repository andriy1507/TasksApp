package com.spaceapps.tasks.local.dao

import androidx.paging.DataSource
import androidx.room.*
import com.spaceapps.tasks.local.model.CategoryLocal

@Dao
interface CategoriesDao : BaseDao<CategoryLocal> {

    @Insert
    override fun insert(vararg items: CategoryLocal)

    @Delete
    override fun delete(vararg items: CategoryLocal)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    override fun update(vararg items: CategoryLocal)

    @Query("SELECT * FROM CATEGORIES")
    override fun selectAll(): DataSource.Factory<Int, CategoryLocal>
}