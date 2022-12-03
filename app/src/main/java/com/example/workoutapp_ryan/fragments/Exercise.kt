package com.example.workoutapp_ryan.fragments

data class Exercise(val name: String, val age: Int) {
    val imageUrl = "https://picsum.photos/200?random=$age"
}