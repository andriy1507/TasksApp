package com.spaceapps.tasks.local.source.impl

import androidx.paging.DataSource
import com.spaceapps.tasks.local.dao.CategoriesDao
import com.spaceapps.tasks.local.model.CategoryLocal
import com.spaceapps.tasks.local.source.CategoriesLocalDataSource
import javax.inject.Inject

class CategoriesLocalDataSourceImpl
@Inject constructor(private val categoriesDao: CategoriesDao) : CategoriesLocalDataSource {
    override fun addCategories(vararg items: CategoryLocal) {
        categoriesDao.insert(*items)
    }

    override fun changeCategories(vararg items: CategoryLocal) {
        categoriesDao.update(*items)
    }

    override fun removeCategories(vararg items: CategoryLocal) {
        categoriesDao.delete(*items)
    }

    override fun getAllCategories(): DataSource.Factory<Int, CategoryLocal> {
        return categoriesDao.selectAll()
    }
}