package com.example.workoutapp_ryan.database

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<User>

    @Query("DELETE FROM user")
    suspend fun deleteAll()

}
