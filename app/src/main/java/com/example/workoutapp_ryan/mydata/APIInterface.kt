package com.example.workoutapp_ryan.mydata

import com.example.workoutapp_ryan.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/* Sources:
 * https://www.youtube.com/watch?v=5gFrXGbQsc8&t=342s
 * https://square.github.io/retrofit/
 * https://www.youtube.com/watch?v=-2ckvIzs0nU
 */
interface APIInterface {
    @Headers(
        "X-RapidAPI-Key: ${BuildConfig.API_KEY}",
        "X-RapidAPI-Host: exercisedb.p.rapidapi.com"
    )
    @GET("exercises")
    fun getData(): Call<List<MyDataItem>>
}


