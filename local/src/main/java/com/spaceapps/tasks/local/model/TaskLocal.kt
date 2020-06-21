package com.spaceapps.tasks.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TASKS")
data class TaskLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long,
    @ColumnInfo(name = "isDone")
    val isDone: Boolean,
    @ColumnInfo(name = "color")
    val color: Int? = null,
    @ColumnInfo(name = "icon")
    val icon: Int? = null
) : LocalEntity