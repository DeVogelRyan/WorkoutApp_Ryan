package com.example.workoutapp_ryan.mydata

/* Sources:
 * https://www.youtube.com/watch?v=5gFrXGbQsc8&t=342s
 * https://square.github.io/retrofit/
 */
data class MyDataItem(
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    val id: String,
    val name: String,
    val target: String
)