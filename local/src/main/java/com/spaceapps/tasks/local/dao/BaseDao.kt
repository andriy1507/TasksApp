package com.spaceapps.tasks.local.dao

import androidx.paging.DataSource
import com.spaceapps.tasks.local.model.LocalEntity

interface BaseDao<L : LocalEntity> {

    fun insert(vararg items: L)

    fun delete(vararg items: L)

    fun update(vararg items: L)

    fun selectAll(): DataSource.Factory<Int, L>
}