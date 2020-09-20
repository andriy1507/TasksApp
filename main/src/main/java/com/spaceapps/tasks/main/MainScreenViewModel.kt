package com.spaceapps.tasks.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.spaceapps.tasks.core.repository.TasksRepository
import kotlinx.coroutines.flow.collect

class MainScreenViewModel
@ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val tasksRepository: TasksRepository
) : ViewModel() {

    val taskList = liveData { tasksRepository.getAllTasks().collect { emit(it) } }
}