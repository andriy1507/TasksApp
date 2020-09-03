package com.spaceapps.tasks.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.spaceapps.tasks.core.extensions.async
import com.spaceapps.tasks.core.extensions.safeAsync
import com.spaceapps.tasks.core.model.Status
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.core.repository.TasksRepository

class TaskViewViewModel @ViewModelInject constructor(private val tasksRepository: TasksRepository) :
    ViewModel() {

    var task: LiveData<Task?> = MutableLiveData<Task?>()

    fun updateTask(task: Task) = async {
        tasksRepository.changeTasks(task)
    }

    fun getTask(id: Long) {
        task = liveData { emit(tasksRepository.getTaskById(id)) }
    }
}