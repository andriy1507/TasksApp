package com.spaceapps.tasks.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.core.extensions.async
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core.repository.TasksRepository
import javax.inject.Inject

class CreateTaskViewModel
@Inject constructor(
    private val tasksRepository: TasksRepository
) : ViewModel() {


    fun saveTask(task: Task) = async {
        tasksRepository.addTasks(task)
    }

    fun updateTask(task: Task) = async {
        tasksRepository.changeTasks(task)
    }

    class Factory @Inject constructor(private val repository: TasksRepository) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CreateTaskViewModel(repository) as T
        }

    }
}