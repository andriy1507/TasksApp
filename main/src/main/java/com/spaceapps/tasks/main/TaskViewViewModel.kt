package com.spaceapps.tasks.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.core.extensions.asyncIO
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core.repository.TasksRepository
import javax.inject.Inject

class TaskViewViewModel @Inject constructor(private val tasksRepository: TasksRepository) :
    ViewModel() {

    fun updateTask(task: Task) = asyncIO {
        tasksRepository.changeTasks(task)
    }

    class Factory @Inject constructor(private val tasksRepository: TasksRepository) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TaskViewViewModel(tasksRepository) as T
        }

    }
}