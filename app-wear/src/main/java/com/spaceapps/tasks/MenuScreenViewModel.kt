package com.spaceapps.tasks

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.spaceapps.tasks.core.extensions.async
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core.repository.TasksRepository
import kotlinx.coroutines.flow.collect

class MenuScreenViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val repository: TasksRepository
) : ViewModel() {

    fun saveTask(title: String?) = async {
        repository.addTasks(Task(title = title.orEmpty(), isDone = false, timestamp = System.currentTimeMillis()))
    }

    val tasks = liveData { repository.getAllTasks().collect { emit(it) } }
}