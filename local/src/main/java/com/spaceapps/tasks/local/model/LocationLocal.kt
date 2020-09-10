package com.spaceapps.tasks.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LOCATION")
data class LocationLocal(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "latitude")
    var latitude: Double = .0,
    @ColumnInfo(name = "longitude")
    var longitude: Double = .0
) : LocalEntity