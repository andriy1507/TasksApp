package com.spaceapps.tasks.local.source.impl

import androidx.paging.DataSource
import com.spaceapps.tasks.local.dao.TasksDao
import com.spaceapps.tasks.local.model.TaskLocal
import com.spaceapps.tasks.local.source.TasksLocalDataSource
import javax.inject.Inject

class TasksDataSourceImpl
@Inject constructor(private val tasksDao: TasksDao) : TasksLocalDataSource {

    override fun addTasks(vararg tasks: TaskLocal) {
        tasksDao.insertTasks(*tasks)
    }

    override fun deleteTasks(vararg tasks: TaskLocal) {
        tasksDao.deleteTasks(*tasks)
    }

    override fun changeTasks(vararg tasks: TaskLocal) {
        tasksDao.updateTasks(*tasks)
    }

    override fun getTasks(): DataSource.Factory<Int, TaskLocal> {
        return tasksDao.selectAllTasks()
    }
}