package com.spaceapps.tasks.repository.mapper

import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.local.model.SubTaskLocal
import com.spaceapps.tasks.local.model.TaskLocal
import com.spaceapps.tasks.local.model.TaskWithSubs

fun TaskLocal.toTask(): Task {
    return Task(id, title, timestamp, isDone, color, icon)
}

fun TaskWithSubs.toTask(): Task {
    return task.toTask().copy(subTasks = subtasks.map { it.toSubTask() })
}

fun SubTaskLocal.toSubTask(): SubTask {
    return SubTask(id, text, done)
}

fun Task.toTaskLocal(): TaskLocal {
    return TaskLocal(
        id = id,
        title = title,
        timestamp = timestamp,
        isDone = isDone,
        color = color,
        icon = icon
    )
}