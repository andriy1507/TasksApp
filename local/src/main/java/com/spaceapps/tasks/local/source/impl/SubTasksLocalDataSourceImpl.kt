package com.spaceapps.tasks.local.source.impl

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.spaceapps.tasks.local.dao.SubTasksDao
import com.spaceapps.tasks.local.model.SubTaskLocal
import com.spaceapps.tasks.local.source.SubTasksLocalDataSource
import javax.inject.Inject

class SubTasksLocalDataSourceImpl @Inject constructor(
    private val subTasksDao: SubTasksDao
) : SubTasksLocalDataSource {
    override fun addTasks(vararg subTasks: SubTaskLocal) {
        subTasksDao.insert(*subTasks)
    }

    override fun deleteSubTasks(vararg subTasks: SubTaskLocal) {
        subTasksDao.delete(*subTasks)
    }

    override fun updateSubTasks(vararg subTasks: SubTaskLocal) {
        subTasksDao.update(*subTasks)
    }

    override fun getSubTasks(): List<SubTaskLocal> {
        return subTasksDao.selectAll()
    }
}