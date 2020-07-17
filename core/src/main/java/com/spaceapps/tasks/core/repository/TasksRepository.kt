package com.spaceapps.tasks.core.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.spaceapps.tasks.core.model.Task

interface TasksRepository {

    fun getTaskById(id: Long): LiveData<Task?>

    fun getAllTasks(): DataSource.Factory<Int, Task>

    fun deleteTasks(vararg tasks: Task)

    fun changeTasks(vararg tasks: Task)

    fun addTasks(vararg tasks: Task)
}