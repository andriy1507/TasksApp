package com.spaceapps.tasks.local.source

import com.spaceapps.tasks.local.model.SubTaskLocal

interface SubTasksLocalDataSource {

    fun addTasks(vararg subTasks: SubTaskLocal)

    fun deleteSubTasks(vararg subTasks: SubTaskLocal)

    fun updateSubTasks(vararg subTasks: SubTaskLocal)
}