package com.spaceapps.tasks.local.source

import com.spaceapps.tasks.local.model.TaskLocal
import com.spaceapps.tasks.local.model.TaskWithSubs
import kotlinx.coroutines.flow.Flow

interface TasksLocalDataSource {

    suspend fun addTasks(vararg tasks: TaskLocal): List<Long>

    suspend fun deleteTasks(vararg tasks: TaskLocal)

    suspend fun changeTasks(vararg tasks: TaskLocal)

    fun getTasks(): Flow<List<TaskWithSubs>>

    suspend fun getTaskById(id: Long): TaskWithSubs?
}