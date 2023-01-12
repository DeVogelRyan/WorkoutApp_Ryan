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
    entities = [WeightModel::class], version = 1
)
abstract class WeightDB : RoomDatabase() {
    abstract val dao: WeightDao

    companion object {
        private var INSTANCE: WeightDB? = null
        fun getInstance(context: Context): WeightDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, WeightDB::class.java, "weights.db")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}
