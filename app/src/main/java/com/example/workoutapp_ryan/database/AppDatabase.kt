package com.example.workoutapp_ryan.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

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
