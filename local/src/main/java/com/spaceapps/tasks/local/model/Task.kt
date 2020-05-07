package com.spaceapps.tasks.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "text")
    val text: String
)