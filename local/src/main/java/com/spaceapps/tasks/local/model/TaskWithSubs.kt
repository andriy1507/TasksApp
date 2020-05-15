package com.spaceapps.tasks.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithSubs(
    @Embedded
    val task: TaskLocal,
    @Relation(parentColumn = "id", entityColumn = "taskId")
    val subtasks: List<SubTaskLocal>
) : LocalEntity