package com.spaceapps.tasks.repository

import androidx.paging.DataSource
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core.repository.TasksRepository
import com.spaceapps.tasks.local.source.TasksLocalDataSource
import com.spaceapps.tasks.repository.mapper.toTask
import com.spaceapps.tasks.repository.mapper.toTaskLocal
import javax.inject.Inject

class TasksRepositoryImpl
@Inject constructor(private val localDataSource: TasksLocalDataSource) : TasksRepository {
    override fun getAllTasks(): DataSource.Factory<Int, Task> {
        return localDataSource.getTasks().map { it.toTask() }
    }

    override fun deleteTasks(vararg tasks: Task) {
        localDataSource.deleteTasks(*tasks.map { it.toTaskLocal() }.toTypedArray())
    }

    override fun changeTasks(vararg tasks: Task) {
        localDataSource.changeTasks(*tasks.map { it.toTaskLocal() }.toTypedArray())
    }

    override fun addTasks(vararg tasks: Task) {
        localDataSource.addTasks(*tasks.map { it.toTaskLocal() }.toTypedArray())
    }
}