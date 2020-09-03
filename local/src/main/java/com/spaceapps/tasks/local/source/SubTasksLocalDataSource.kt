package com.spaceapps.tasks.local.source

import com.spaceapps.tasks.local.model.SubTaskLocal
import kotlinx.coroutines.flow.Flow

interface SubTasksLocalDataSource {

    suspend fun addTasks(vararg subTasks: SubTaskLocal)

    suspend fun deleteSubTasks(vararg subTasks: SubTaskLocal)

    suspend fun updateSubTasks(vararg subTasks: SubTaskLocal)

    suspend fun getSubTasks(): Flow<List<SubTaskLocal>>
}