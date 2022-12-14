package com.example.workoutapp_ryan.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 Sources:
 * https://developer.android.com/jetpack/androidx/releases/room
 * https://developer.android.com/reference/android/arch/persistence/room/PrimaryKey
 * https://github.com/philipplackner/AndroidRoomMigration
 */

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(defaultValue = "0")
    val id: Int,

    val firstName: String,
    val lastName: String?
)
