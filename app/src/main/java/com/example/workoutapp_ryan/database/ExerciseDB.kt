package com.example.workoutapp_ryan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
 Sources:
 * https://developer.android.com/jetpack/androidx/releases/room
 * https://developer.android.com/reference/android/arch/persistence/room/PrimaryKey
 * https://github.com/philipplackner/AndroidRoomMigration
 */

@Database(
    entities = [ExerciseModel::class], version = 1
)
abstract class ExerciseDB : RoomDatabase() {
    abstract val dao: ExerciseDao

    companion object {
        private var INSTANCE: ExerciseDB? = null
        fun getInstance(context: Context): ExerciseDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, ExerciseDB::class.java, "exercise.db")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}
