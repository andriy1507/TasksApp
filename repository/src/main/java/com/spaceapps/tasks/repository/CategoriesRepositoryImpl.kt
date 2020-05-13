package com.spaceapps.tasks.repository

import androidx.paging.DataSource
import com.spaceapps.tasks.core.model.Category
import com.spaceapps.tasks.core.repository.CategoriesRepository
import com.spaceapps.tasks.local.source.CategoriesLocalDataSource
import com.spaceapps.tasks.repository.mapper.toCategory
import com.spaceapps.tasks.repository.mapper.toCategoryLocal
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val localDataSource: CategoriesLocalDataSource
) : CategoriesRepository {
    override fun getAllCategories(): DataSource.Factory<Int, Category> {
        return localDataSource.getAllCategories().map { it.toCategory() }
    }

    override fun changeCategories(vararg items: Category) {
        localDataSource.changeCategories(*items.map { it.toCategoryLocal() }.toTypedArray())
    }

    override fun deleteCategories(vararg items: Category) {
        localDataSource.removeCategories(*items.map { it.toCategoryLocal() }.toTypedArray())
    }

    override fun addCategories(vararg items: Category) {
        localDataSource.addCategories(*items.map { it.toCategoryLocal() }.toTypedArray())
    }

}