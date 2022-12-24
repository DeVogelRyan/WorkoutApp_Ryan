package com.example.workoutapp_ryan.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExercise(weight: ExerciseModel)

    @Query("SELECT * FROM exercise")
    suspend fun getExercises(): List<ExerciseModel>

    @Query("DELETE FROM exercise")
    suspend fun deleteAll()
}