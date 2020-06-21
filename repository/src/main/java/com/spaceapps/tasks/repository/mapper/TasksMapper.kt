package com.spaceapps.tasks.repository.mapper

import com.spaceapps.tasks.core.model.SubTask
import com.spaceapps.tasks.core.model.Task
import com.spaceapps.tasks.local.model.TaskLocal
import com.spaceapps.tasks.local.model.TaskWithSubs

fun TaskLocal.toTask(): Task {
    return Task(title, timestamp, isDone, color, icon)
}

fun TaskWithSubs.toTask():Task {
    return Task(
        task.title,
        task.timestamp,
        task.isDone,
        task.color,
        task.icon,
        subtasks.map { SubTask(it.text, it.done) }
    )
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