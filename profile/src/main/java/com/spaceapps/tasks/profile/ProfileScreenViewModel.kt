package com.spaceapps.tasks.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.core.extensions.asyncIO
import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core.repository.TasksRepository
import javax.inject.Inject

class ProfileScreenViewModel @Inject constructor(private val repository: TasksRepository) :
    ViewModel() {

    var subTasks:LiveData<List<SubTask>> = MutableLiveData()

    fun getSubTasks() = asyncIO {
        subTasks = repository.getSubTasks()
    }

    class Factory @Inject constructor(private val repository: TasksRepository) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProfileScreenViewModel(repository) as T
        }
    }
}