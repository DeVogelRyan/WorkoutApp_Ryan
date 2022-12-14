package com.example.workoutapp_ryan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.workoutapp_ryan.database.AppDatabase
import com.example.workoutapp_ryan.database.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Source:
         * https://www.youtube.com/watch?v=Chso6xrJ6aU
         */
        val navController = findNavController(R.id.navHostFragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val appBarConfig = AppBarConfiguration(setOf(R.id.firstFragment, R.id.secondFragment, R.id.thirdFragment))
        setupActionBarWithNavController(navController, appBarConfig)
        bottomNavigationView.setupWithNavController(navController)

        // Source: https://developer.android.com/guide/topics/resources/string-resource
        /*var counter = 0
        val array: Array<String> = resources.getStringArray(R.array.Nav_items_NL)
        for(items in bottomNavigationView.menu){
            items.title = array.get(counter)
            counter += 1
        }*/

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "users.db"
        ).build()


        /*lifecycleScope.launch{
            db.dao.deleteAll()
        }*/

      /*  lifecycleScope.launch{
            Log.d("Users", db.dao.getUsers().toString())
        }*/







    }
}