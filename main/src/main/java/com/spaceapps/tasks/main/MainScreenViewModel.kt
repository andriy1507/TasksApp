package com.spaceapps.tasks.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.spaceapps.tasks.core.repository.TasksRepository

class MainScreenViewModel
@ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val tasksRepository: TasksRepository
) : ViewModel() {

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
}