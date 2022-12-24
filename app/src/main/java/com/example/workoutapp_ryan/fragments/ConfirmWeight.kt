package com.example.workoutapp_ryan.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.workoutapp_ryan.R
import com.example.workoutapp_ryan.database.WeightDB
import com.example.workoutapp_ryan.database.WeightModel
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConfirmWeight.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfirmWeight : Fragment() {
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


        return inflater.inflate(R.layout.fragment_confirm_weight, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weight = view.findViewById<TextView>(R.id.editWeight)
        val args = this.arguments
        val weightArgs = args?.get("weight")
        weight.text = weightArgs.toString()
        val db = Room.databaseBuilder(
            this.requireContext(),
            WeightDB::class.java, "weights.db"
        ).build()
        val btn = view.findViewById<Button>(R.id.saveToDb)
        btn.setOnClickListener {
            if (!isEmpty()) {
                lifecycleScope.launch {
                    db.dao.insertWeight(WeightModel(0, weight.text.toString().toFloat()))
                    Log.d("Users", db.dao.getWeights().toString())
                    Toast.makeText(context, getString(R.string.succes), Toast.LENGTH_SHORT).show()
                    weight.text = ""
                }
            }
            else {
                /*
                 Source:
                 * https://www.javatpoint.com/kotlin-android-toast
                 */
                Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isEmpty(): Boolean {
        val weight = requireView().findViewById<TextView>(R.id.editWeight)
        return weight.text.isEmpty()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailUserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConfirmWeight().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}