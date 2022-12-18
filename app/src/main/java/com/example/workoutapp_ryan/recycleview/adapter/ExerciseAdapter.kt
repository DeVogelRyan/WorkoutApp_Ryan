package com.example.workoutapp_ryan.recycleview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.request.RequestOptions
import com.example.workoutapp_ryan.R
import com.example.workoutapp_ryan.api.model.MyDataItem
import com.example.workoutapp_ryan.fragments.ExerciseDetails
import com.example.workoutapp_ryan.fragments.FirstFragment
import com.example.workoutapp_ryan.recycleview.model.Exercise
import com.example.workoutapp_ryan.recycleview.model.Weight
import retrofit2.Callback

/* Sources:
    * https://developer.android.com/develop/ui/views/layout/recyclerview
    * https://www.youtube.com/watch?v=FiqiIJNALFs&t=640s
    * https://www.youtube.com/watch?v=GPP4hOTthRg
    * https://www.youtube.com/watch?v=4o6QwVe_2Yg
    * https://www.youtube.com/watch?v=rBQi_7L-Uc8
    * https://medium.com/huawei-developers/android-retrofit-recyclerview-searchview-usage-9e0be6e7ab08
*/

class ExerciseAdapter(
    private val context: Context,
    private val exercises: MutableList<Exercise>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    private val TAG = "ExerciseAdapter"

    // Usually involves inflating a layout from XML and returning the holder - THIS IS EXPENSIVE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.exercise_item, parent, false)
        )
    }

    // Returns the total count of items in the list
    override fun getItemCount() = exercises.size

    // Involves populating data into the item through holder - NOT expensive
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder at position $position")
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(exercise: Exercise) {
            itemView.findViewById<TextView>(R.id.exerciseName).text = exercise.name
            Glide.with(context).load(exercise.imgUrl).apply(RequestOptions().override(1200, 400))
                .into(itemView.findViewById(R.id.weightstatus))
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener.onItemClick(position)
        }
    }

    /*
     Source:
     * https://www.youtube.com/watch?v=wKFJsrdiGS8
     */
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}