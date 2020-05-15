package com.spaceapps.tasks.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SUBTASKS")
data class SubTaskLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "done")
    val done: Boolean = false,
    @ColumnInfo(name = "taskId")
    val taskId: Long
) : LocalEntity