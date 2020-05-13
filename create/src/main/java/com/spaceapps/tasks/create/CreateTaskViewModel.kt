package com.spaceapps.tasks.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.core.extensions.asyncIO
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core.repository.TasksRepository
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateTaskViewModel
@Inject constructor(
    private val tasksRepository: TasksRepository
) : ViewModel() {


    fun saveTask(task: Task) = asyncIO {
        tasksRepository.addTasks(task)
    }

    fun updateTask(task: Task) = asyncIO {
        tasksRepository.changeTasks(task)
    }

    class Factory @Inject constructor(private val repository: TasksRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CreateTaskViewModel(repository) as T
        }

    }
}