package com.example.workoutapp_ryan.recycleview.model

/* Sources:
    * https://developer.android.com/develop/ui/views/layout/recyclerview
    * https://www.youtube.com/watch?v=FiqiIJNALFs&t=640s
    * https://www.youtube.com/watch?v=GPP4hOTthRg
    * https://www.youtube.com/watch?v=4o6QwVe_2Yg
    * https://www.youtube.com/watch?v=rBQi_7L-Uc8
    * https://medium.com/huawei-developers/android-retrofit-recyclerview-searchview-usage-9e0be6e7ab08
*/
data class Exercise(
    val name: String,
    val imgUrl: String,
    val bodyPart: String,
    val equipment: String,
    val target: String
)