package com.example.workoutapp_ryan.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*
 Sources:
 * https://developer.android.com/jetpack/androidx/releases/room
 * https://developer.android.com/reference/android/arch/persistence/room/PrimaryKey
 * https://github.com/philipplackner/AndroidRoomMigration
 */

@Dao
interface WeightDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeight(weight: WeightModel)

    @Query("SELECT * FROM weight")
    suspend fun getWeights(): List<WeightModel>

    @Query("DELETE FROM weight")
    suspend fun deleteAll()
}
