package com.example.workoutapp_ryan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        

        //APIRequest()
    }

   /* private fun APIRequest() {
        val request =
            Request.Builder().url("https://exercisedb.p.rapidapi.com/exercises").get()
                .addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
                .addHeader("X-RapidAPI-Host", "exercisedb.p.rapidapi.com").build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    Log.d("PRINT", response.body!!.string())
                }
            }
        })
    }*/
}