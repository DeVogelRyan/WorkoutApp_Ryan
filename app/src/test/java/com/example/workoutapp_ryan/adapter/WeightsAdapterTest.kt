package com.example.workoutapp_ryan.adapter

import com.example.workoutapp_ryan.recycleview.model.Weight
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class WeightsAdapterTest {

    private val weight = ArrayList<Weight>()

    @Before
    fun setUp() {
        weight.add(Weight( System.currentTimeMillis(),8.0f))
    }

    @Test
    fun getItemCount() {
        assertTrue(weight.size>0)
    }
}