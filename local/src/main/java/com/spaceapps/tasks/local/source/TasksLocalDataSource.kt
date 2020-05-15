package com.spaceapps.tasks.local.source

import androidx.paging.DataSource
import com.spaceapps.tasks.local.model.TaskLocal

interface TasksLocalDataSource {

    fun addTasks(vararg tasks: TaskLocal)

    fun deleteTasks(vararg tasks: TaskLocal)

    fun changeTasks(vararg tasks: TaskLocal)

    fun getTasks(): DataSource.Factory<Int, TaskLocal>
}