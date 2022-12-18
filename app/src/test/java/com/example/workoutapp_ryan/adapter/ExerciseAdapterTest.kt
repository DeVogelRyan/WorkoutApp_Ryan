package com.example.workoutapp_ryan.adapter

import com.example.workoutapp_ryan.recycleview.adapter.model.Exercise
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ExerciseAdapterTest {

    private val exercises = ArrayList<Exercise>()

    @Before
    fun setUp() {
        exercises.add(Exercise("name", "Ryan"))
    }

    @Test
    fun getItemCount() {
        assertTrue(exercises.size>0)
    }
}