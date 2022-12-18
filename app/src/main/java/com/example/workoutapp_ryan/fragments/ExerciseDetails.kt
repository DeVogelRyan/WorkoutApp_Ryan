package com.example.workoutapp_ryan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.workoutapp_ryan.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseDetails : Fragment() {
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
        return inflater.inflate(R.layout.fragment_exercise_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = this.arguments
        val nameArgs = args?.get("name")
        val imgUrlArgs = args?.get("imgUrl")
        val bodyPartArgs = args?.get("bodyPart")
        val equipmentArgs = args?.get("equipment")
        val targetArgs = args?.get("target")
        val nameView = view.findViewById<TextView>(R.id.DetailsExerciseName)
        val imgUrlView = view.findViewById<ImageView>(R.id.DetailsimgUrl)
        val bodyPart = view.findViewById<TextView>(R.id.DetailsExerciseBodyPart)
        val equipment = view.findViewById<TextView>(R.id.DetailsExerciseEquipment)
        val target = view.findViewById<TextView>(R.id.DetailsExerciseTarget)
        nameView.text = nameArgs.toString()
        Glide.with(this.requireContext()).load(imgUrlArgs)
            .apply(RequestOptions().override(800, 800))
            .into(imgUrlView)
        bodyPart.text = "bodypart: ${bodyPartArgs.toString()}"
        equipment.text = "bodypart: ${equipmentArgs.toString()}"
        target.text = "bodypart: ${targetArgs.toString()}"

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExerciseDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExerciseDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}