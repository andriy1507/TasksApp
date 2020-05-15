package com.spaceapps.tasks.repository.mapper

import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.local.model.TaskLocal

fun TaskLocal.toTask(): Task {
    return Task(title, timestamp, isDone, color, icon)
}

fun Task.toTaskLocal(): TaskLocal {
    return TaskLocal(
        title = title,
        timestamp = timestamp,
        isDone = isDone,
        color = color,
        icon = icon
    )
}