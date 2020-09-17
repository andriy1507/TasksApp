package com.spaceapps.tasks.repository

import com.spaceapps.tasks.core.model.Status
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core.repository.TasksRepository
import com.spaceapps.tasks.local.model.SubTaskLocal
import com.spaceapps.tasks.local.source.SubTasksLocalDataSource
import com.spaceapps.tasks.local.source.TasksLocalDataSource
import com.spaceapps.tasks.repository.mapper.toSubTask
import com.spaceapps.tasks.repository.mapper.toTask
import com.spaceapps.tasks.repository.mapper.toTaskLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TasksRepositoryImpl
@Inject constructor(
    private val tasksDataSource: TasksLocalDataSource,
    private val subTasksDataSource: SubTasksLocalDataSource
) : TasksRepository {

    override suspend fun getTaskById(id: Long): Task? {
        return tasksDataSource.getTaskById(id)?.toTask()
    }

    override suspend fun getAllTasks(): Flow<List<Task>> {
        return tasksDataSource.getTasks().map { list -> list.map { it.toTask() } }
    }

    override suspend fun deleteTasks(vararg tasks: Task) {
        tasksDataSource.deleteTasks(*tasks.map { it.toTaskLocal() }.toTypedArray())
    }

    override suspend fun changeTasks(vararg tasks: Task) {
        tasksDataSource.changeTasks(*tasks.map { it.toTaskLocal() }.toTypedArray())
        tasks.forEach { task ->
            subTasksDataSource.updateSubTasks(*task.subTasks.map {
                SubTaskLocal(
                    it.id,
                    it.text,
                    it.isDone,
                    task.id
                )
            }.toTypedArray())
        }
    }

    override suspend fun addTasks(vararg tasks: Task) {
        val ids = tasksDataSource.addTasks(*tasks.map { it.toTaskLocal() }.toTypedArray())
        val map: Map<Long, Task> = tasks.mapIndexed { index, task -> ids[index] to task }.toMap()
        map.forEach { (key, value) ->
            subTasksDataSource.addTasks(*value.subTasks.map {
                SubTaskLocal(
                    taskId = key,
                    text = it.text,
                    done = it.isDone
                )
            }.toTypedArray())
        }
    }

    override suspend fun getSubTasks(): Flow<List<SubTask>> {
        return subTasksDataSource.getSubTasks().map { list -> list.map { it.toSubTask() } }
    }
}