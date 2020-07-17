package com.spaceapps.tasks.local.source.impl

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.spaceapps.tasks.local.dao.TasksDao
import com.spaceapps.tasks.local.model.TaskLocal
import com.spaceapps.tasks.local.model.TaskWithSubs
import com.spaceapps.tasks.local.source.TasksLocalDataSource
import javax.inject.Inject

class TasksLocalDataSourceImpl
@Inject constructor(private val tasksDao: TasksDao) : TasksLocalDataSource {

    override fun getTaskById(id: Long): LiveData<TaskWithSubs?> {
        return tasksDao.selectTaskWithSubTasksById(id)
    }

    override fun addTasks(vararg tasks: TaskLocal): List<Long> {
        return tasksDao.insert(*tasks)
    }

    override fun deleteTasks(vararg tasks: TaskLocal) {
        tasksDao.delete(*tasks)
    }

    override fun changeTasks(vararg tasks: TaskLocal) {
        tasksDao.update(*tasks)
    }

    override fun getTasks(): DataSource.Factory<Int, TaskWithSubs> {
        return tasksDao.selectAllWithSubTasks()
    }
}