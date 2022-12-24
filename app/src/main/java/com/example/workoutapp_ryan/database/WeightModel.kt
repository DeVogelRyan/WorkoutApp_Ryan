package com.example.workoutapp_ryan.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight")
data class WeightModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(defaultValue = "0")
    val weightID: Int,
    val weight: Float,
    val createdAt: Long = System.currentTimeMillis()
)