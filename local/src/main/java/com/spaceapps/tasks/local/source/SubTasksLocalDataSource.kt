package com.spaceapps.tasks.local.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.spaceapps.tasks.local.model.SubTaskLocal

interface SubTasksLocalDataSource {

    fun addTasks(vararg subTasks: SubTaskLocal)

    fun deleteSubTasks(vararg subTasks: SubTaskLocal)

    fun updateSubTasks(vararg subTasks: SubTaskLocal)

    fun getSubTasks():LiveData<List<SubTaskLocal>>
}