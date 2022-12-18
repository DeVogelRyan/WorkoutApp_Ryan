package com.example.workoutapp_ryan.recycleview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp_ryan.R
import com.example.workoutapp_ryan.recycleview.model.Weight
import java.text.SimpleDateFormat

class WeightAdapter(private val context: Context, private val weights: List<Weight>) :
    RecyclerView.Adapter<WeightAdapter.ViewHolder>() {

    private val TAG = "WorkoutAdapter"

    // Usually involves inflating a layout from XML and returning the holder - THIS IS EXPENSIVE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.weight_item, parent, false)
        )
    }

    // Returns the total count of items in the list
    override fun getItemCount() = weights.size

    // Involves populating data into the item through holder - NOT expensive
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder at position $position")
        val weight = weights[position]
        if (position in 1 until itemCount - 1) {
            if (weights[position - 1].weightNumber > weights[position].weightNumber) {
                holder.bind(weight, true)
            }
            else if (weights[position - 1].weightNumber == weights[position].weightNumber) {
                holder.bind(weight)
            } else {
                holder.bind(weight, false)
            }
        } else {
            holder.bind(weight)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weight: Weight, IsLower: Boolean) {
            val formatter = SimpleDateFormat("dd/MM/yyyy") //formating according to my need
            itemView.findViewById<TextView>(R.id.weightDate).text =
                formatter.format(weight.createdAt)
            itemView.findViewById<TextView>(R.id.weightDisplay).text =
                weight.weightNumber.toString() + " kg"
            val status = itemView.findViewById<ImageView>(R.id.StatusImage)
            if (IsLower) {
                status.setImageResource(R.drawable.arrow_good)
            } else {
                status.setImageResource(R.drawable.arrow_bad)
                status.rotation = 180f
            }
        }

        fun bind(weight: Weight) {
            val formatter = SimpleDateFormat("dd/MM/yyyy") //formating according to my need
            itemView.findViewById<TextView>(R.id.weightDate).text =
                formatter.format(weight.createdAt)
            itemView.findViewById<TextView>(R.id.weightDisplay).text =
                weight.weightNumber.toString() + " kg"
            val status = itemView.findViewById<ImageView>(R.id.StatusImage)
            status.visibility = View.INVISIBLE;

        }

    }
}