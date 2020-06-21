package com.spaceapps.tasks.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.spaceapps.tasks.core.repository.TasksRepository
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(private val tasksRepository: TasksRepository) :
    ViewModel() {

    private val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(20)
        .setEnablePlaceholders(false)
        .setPrefetchDistance(5)
        .setPageSize(10)
        .build()

    var tasks = LivePagedListBuilder(tasksRepository.getAllTasks(), config).build()

    fun invalidateDataSource() {
        tasks = LivePagedListBuilder(tasksRepository.getAllTasks(), config).build()
    }

    class Factory @Inject constructor(private val repository: TasksRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainScreenViewModel(repository) as T
        }

    }
}