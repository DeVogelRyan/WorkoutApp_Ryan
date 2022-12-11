package com.example.workoutapp_ryan.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.workoutapp_ryan.R
import com.example.workoutapp_ryan.model.Exercise

class ExerciseAdapter(private val context: Context, private val exercises: List<Exercise>)
    : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    private val TAG = "ExerciseAdapter"

    // Usually involves inflating a layout from XML and returning the holder - THIS IS EXPENSIVE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false))
    }

    // Returns the total count of items in the list
    override fun getItemCount() = exercises.size

    // Involves populating data into the item through holder - NOT expensive
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder at position $position")
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(exercise: Exercise) {
            itemView.findViewById<TextView>(R.id.tvName).text = exercise.name
            Glide.with(context).load(exercise.imgUrl).apply( RequestOptions().override(1200, 400)).into(itemView.findViewById(R.id.tvImage))
        }

    }
}