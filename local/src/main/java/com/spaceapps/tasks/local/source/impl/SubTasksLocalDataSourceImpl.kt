package com.spaceapps.tasks.local.source.impl

import com.spaceapps.tasks.local.dao.SubTasksDao
import com.spaceapps.tasks.local.model.SubTaskLocal
import com.spaceapps.tasks.local.source.SubTasksLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubTasksLocalDataSourceImpl @Inject constructor(
    private val subTasksDao: SubTasksDao
) : SubTasksLocalDataSource {
    override suspend fun addTasks(vararg subTasks: SubTaskLocal) {
        subTasksDao.insert(*subTasks)
    }

    override suspend fun deleteSubTasks(vararg subTasks: SubTaskLocal) {
        subTasksDao.delete(*subTasks)
    }

    override suspend fun updateSubTasks(vararg subTasks: SubTaskLocal) {
        subTasksDao.update(*subTasks)
    }

    override suspend fun getSubTasks(): Flow<List<SubTaskLocal>> {
        return subTasksDao.selectAll()
    }
}