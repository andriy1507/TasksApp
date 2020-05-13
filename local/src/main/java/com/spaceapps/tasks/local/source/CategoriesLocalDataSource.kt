package com.spaceapps.tasks.local.source

import androidx.paging.DataSource
import com.spaceapps.tasks.local.model.CategoryLocal

interface CategoriesLocalDataSource {

    fun addCategories(vararg items: CategoryLocal)

    fun changeCategories(vararg items: CategoryLocal)

    fun removeCategories(vararg items: CategoryLocal)

    fun getAllCategories(): DataSource.Factory<Int, CategoryLocal>

}