package com.example.workoutapp_ryan

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {


    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val appBarConfig = AppBarConfiguration(setOf(R.id.firstFragment, R.id.secondFragment, R.id.thirdFragment))
        setupActionBarWithNavController(navController, appBarConfig)
        bottomNavigationView.setupWithNavController(navController)

        APIRequest()
    }

     private fun APIRequest() {
         val request =
             Request.Builder().url("https://exercisedb.p.rapidapi.com/exercises/bodyPart/back").get()
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
     }
}