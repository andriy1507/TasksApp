package com.spaceapps.tasks.local.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.spaceapps.tasks.local.model.TaskLocal
import com.spaceapps.tasks.local.model.TaskWithSubs

interface TasksLocalDataSource {

    fun addTasks(vararg tasks: TaskLocal): List<Long>

    fun deleteTasks(vararg tasks: TaskLocal)

    fun changeTasks(vararg tasks: TaskLocal)

    fun getTasks(): DataSource.Factory<Int, TaskWithSubs>

    fun getTaskById(id: Long): LiveData<TaskWithSubs?>
}