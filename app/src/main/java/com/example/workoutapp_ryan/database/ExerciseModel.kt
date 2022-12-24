package com.example.workoutapp_ryan.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class ExerciseModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(defaultValue = "0")
    val exerciseID: Int,
    val name: String,
    val imgUrl: String,
    val bodyPart: String,
    val equipment: String,
    val target: String
)