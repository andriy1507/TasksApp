package com.spaceapps.tasks.core.repository

import androidx.paging.DataSource
import com.spaceapps.tasks.core.model.Category

interface CategoriesRepository {

    fun getAllCategories(): DataSource.Factory<Int, Category>

    fun changeCategories(vararg items: Category)

    fun deleteCategories(vararg items: Category)

    fun addCategories(vararg items: Category)
}