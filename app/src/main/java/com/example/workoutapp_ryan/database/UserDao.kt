package com.example.workoutapp_ryan.database

import androidx.room.*

/*
 Sources:
 * https://developer.android.com/jetpack/androidx/releases/room
 * https://developer.android.com/reference/android/arch/persistence/room/PrimaryKey
 * https://github.com/philipplackner/AndroidRoomMigration
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<User>

    @Query("DELETE FROM user")
    suspend fun deleteAll()

}
