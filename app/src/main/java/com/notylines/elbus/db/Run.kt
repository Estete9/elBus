package com.notylines.elbus.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "run_table")
data class Run(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "duration") val duration: String?,
    @ColumnInfo(name = "max_speed") val maxSpeed: String?,
    @ColumnInfo(name = "max_speed_duration") val maxSpeedDuration: String?,
    @ColumnInfo(name = "number_of_max_speed") val numberMaxSpeed: Int?,

    )