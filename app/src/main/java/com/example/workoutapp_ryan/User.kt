package com.example.workoutapp_ryan

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val firstName: String,
    val lastName: String?
)
