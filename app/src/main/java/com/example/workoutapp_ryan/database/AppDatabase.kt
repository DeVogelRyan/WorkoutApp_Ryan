package com.example.workoutapp_ryan.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

/*
 Sources:
 * https://developer.android.com/jetpack/androidx/releases/room
 * https://developer.android.com/reference/android/arch/persistence/room/PrimaryKey
 * https://github.com/philipplackner/AndroidRoomMigration
 */

@Database(
    entities = [User::class], version = 4,
    autoMigrations = [
       AutoMigration(from = 3, to = 4)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: UserDao


    @RenameColumn(tableName = "User", fromColumnName = "id", toColumnName = "firstName")
    class Migration2To3: AutoMigrationSpec

}
