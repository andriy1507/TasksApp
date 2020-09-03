package com.spaceapps.tasks.local.source.impl

import com.spaceapps.tasks.local.dao.TasksDao
import com.spaceapps.tasks.local.model.TaskLocal
import com.spaceapps.tasks.local.model.TaskWithSubs
import com.spaceapps.tasks.local.source.TasksLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksLocalDataSourceImpl
@Inject constructor(private val tasksDao: TasksDao) : TasksLocalDataSource {

    override suspend fun getTaskById(id: Long): TaskWithSubs? {
        return tasksDao.selectTaskWithSubTasksById(id)
    }

    override suspend fun addTasks(vararg tasks: TaskLocal): List<Long> {
        return tasksDao.insert(*tasks)
    }

    override suspend fun deleteTasks(vararg tasks: TaskLocal) {
        tasksDao.delete(*tasks)
    }

    override suspend fun changeTasks(vararg tasks: TaskLocal) {
        tasksDao.update(*tasks)
    }

    override suspend fun getTasks(): Flow<List<TaskWithSubs>> {
        return tasksDao.selectAllWithSubTasks()
    }
}