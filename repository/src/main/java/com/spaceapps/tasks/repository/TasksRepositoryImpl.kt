package com.spaceapps.tasks.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core.repository.TasksRepository
import com.spaceapps.tasks.local.model.SubTaskLocal
import com.spaceapps.tasks.local.source.SubTasksLocalDataSource
import com.spaceapps.tasks.local.source.TasksLocalDataSource
import com.spaceapps.tasks.repository.mapper.toSubTask
import com.spaceapps.tasks.repository.mapper.toTask
import com.spaceapps.tasks.repository.mapper.toTaskLocal
import javax.inject.Inject

class TasksRepositoryImpl
@Inject constructor(
    private val tasksDataSource: TasksLocalDataSource,
    private val subTasksDataSource: SubTasksLocalDataSource
) : TasksRepository {

    override fun getTaskById(id: Long): LiveData<Task?> {
        return Transformations.map(tasksDataSource.getTaskById(id)) { it?.toTask() }
    }

    override fun getAllTasks(): DataSource.Factory<Int, Task> {
        return tasksDataSource.getTasks().map { it.toTask() }
    }

    override fun deleteTasks(vararg tasks: Task) {
        tasksDataSource.deleteTasks(*tasks.map { it.toTaskLocal() }.toTypedArray())
    }

    override fun changeTasks(vararg tasks: Task) {
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

    override fun addTasks(vararg tasks: Task) {
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

    override fun getSubTasks(): List<SubTask> {
        return subTasksDataSource.getSubTasks().map { it.toSubTask() }
    }
}