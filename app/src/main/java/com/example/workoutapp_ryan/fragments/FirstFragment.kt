package com.example.workoutapp_ryan.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.workoutapp_ryan.R
import com.example.workoutapp_ryan.adapter.ExerciseAdapter
import com.example.workoutapp_ryan.model.Exercise
import org.json.JSONArray
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val RecycleView = view.findViewById<RecyclerView>(R.id.mRecyclerview)
        RecycleView.adapter = ExerciseAdapter(this.requireContext(), createExercises())
        RecycleView.layoutManager = LinearLayoutManager(this.context)
    }

    private fun createExercises(): List<Exercise> {
        val exercises = mutableListOf<Exercise>()
        val url = "https://wger.de/api/v2/exerciseimage/?format=json&limit=30&offset=30"
        val queue = Volley.newRequestQueue(this.context)
        val stringRequest = object : StringRequest(url,
            Response.Listener { response ->
                val jsonArray = JSONObject(response).getJSONArray("results")
                Log.d("JSON", jsonArray.getJSONObject(0).getString("image"))
                for (i in 0..29) {
                    exercises.add(Exercise("Person #$i", jsonArray.getJSONObject(i).getString("image")))
                    Log.d("JSON", jsonArray.getJSONObject(i).getString("image"))
                }
            },
            Response.ErrorListener {
            })
        {}
        queue.add(stringRequest)
        return exercises
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}