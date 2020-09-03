package com.spaceapps.tasks.core.repository

import com.spaceapps.tasks.core.model.Status
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core.model.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun getTaskById(id: Long): Task?

    suspend fun getAllTasks(): Flow<List<Task>>

    suspend fun deleteTasks(vararg tasks: Task)

    suspend fun changeTasks(vararg tasks: Task)

    suspend fun addTasks(vararg tasks: Task)

    suspend fun getSubTasks(): Flow<List<SubTask>>
}