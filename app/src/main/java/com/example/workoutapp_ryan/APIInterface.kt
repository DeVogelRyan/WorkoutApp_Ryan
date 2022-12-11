package com.example.workoutapp_ryan

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIInterface {
    @Headers(
        "X-RapidAPI-Key: ${BuildConfig.API_KEY}",
        "X-RapidAPI-Host: exercisedb.p.rapidapi.com"
    )
    @GET("exercises")
    fun getData(): Call<List<MyDataItem>>
}