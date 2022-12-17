package com.example.workoutapp_ryan.database

import androidx.room.*

/*
 Sources:
 * https://developer.android.com/jetpack/androidx/releases/room
 * https://developer.android.com/reference/android/arch/persistence/room/PrimaryKey
 * https://github.com/philipplackner/AndroidRoomMigration
 */

@Dao
interface WeightDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeight(weight: Weight)

    @Query("SELECT * FROM weight")
    suspend fun getWeights(): List<Weight>

    @Query("DELETE FROM weight")
    suspend fun deleteAll()

}
