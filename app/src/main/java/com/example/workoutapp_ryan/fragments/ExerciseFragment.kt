package com.example.workoutapp_ryan.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.transition.TransitionInflater
import com.example.workoutapp_ryan.R
import com.example.workoutapp_ryan.api.APIInterface
import com.example.workoutapp_ryan.api.model.MyDataItem
import com.example.workoutapp_ryan.database.ExerciseDB
import com.example.workoutapp_ryan.database.ExerciseModel
import com.example.workoutapp_ryan.recycleview.adapter.ExerciseAdapter
import com.example.workoutapp_ryan.recycleview.model.Exercise
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseFragment : Fragment(), ExerciseAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var myRecycleView: RecyclerView? = null
    private var exercises: MutableList<Exercise> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.to_left)
        exitTransition = inflater.inflateTransition(R.transition.to_left)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Sources:
         * https://developer.android.com/develop/ui/views/layout/recyclerview
         * https://www.youtube.com/watch?v=FiqiIJNALFs&t=640s
         * https://www.youtube.com/watch?v=GPP4hOTthRg
         * https://www.youtube.com/watch?v=4o6QwVe_2Yg
         * https://www.youtube.com/watch?v=rBQi_7L-Uc8
         * https://medium.com/huawei-developers/android-retrofit-recyclerview-searchview-usage-9e0be6e7ab08
         */


        myRecycleView = requireView().findViewById<RecyclerView>(R.id.RecycleviewExercise)
        myRecycleView?.layoutManager = LinearLayoutManager(this.context)

        val db = Room.databaseBuilder(
            this.requireContext(),
            ExerciseDB::class.java, "exercise.db"
        ).build()

        if (exercises.isEmpty()) {
            addToDB()
            getExercises()
        }
        getExercises()
        myRecycleView?.adapter =
            ExerciseAdapter(
                this.requireContext(),
                exercises,
                this
            )

    }

    private fun getExercises() {
        val db = Room.databaseBuilder(
            this.requireContext(),
            ExerciseDB::class.java, "exercise.db"
        ).build()
        exercises.clear()
        lifecycleScope.launch {
            db.dao.getExercises().forEach {
                exercises.add(Exercise(it.name, it.imgUrl, it.bodyPart, it.equipment, it.target))
                Log.d("Users", it.toString())
            }
            myRecycleView?.adapter =
                ExerciseAdapter(
                    this@ExerciseFragment.requireContext(),
                    exercises,
                    this@ExerciseFragment
                )
        }
    }

    private fun addToDB() {
        /* Sources:
         * https://www.youtube.com/watch?v=5gFrXGbQsc8&t=342s
         * https://square.github.io/retrofit/
         */

        Log.d("Users", "xDDD")
        val db = Room.databaseBuilder(
            this.requireContext(),
            ExerciseDB::class.java, "exercise.db"
        ).build()

        val retrofitbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://exercisedb.p.rapidapi.com/")
            .build()
            .create(APIInterface::class.java)

        val retrofitData = retrofitbuilder.getData()
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!
                lifecycleScope.launch {
                    for (i in 0..40) {
                        Log.d("JSON", responseBody[i].name)
                        val name = responseBody[i].name
                        val gifUrl = responseBody[i].gifUrl.replace("http", "https")
                        val bodyPart = responseBody[i].bodyPart
                        val equipment = responseBody[i].equipment
                        val target = responseBody[i].target
                        db.dao.insertExercise(
                            ExerciseModel(
                                0,
                                name,
                                gifUrl,
                                bodyPart,
                                equipment,
                                target
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
            }
        })
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
            ExerciseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /*
     Source:
     * https://www.youtube.com/watch?v=wKFJsrdiGS8
     */
    override fun onItemClick(position: Int) {
        findNavController().navigate(
            R.id.action_exerciseFragment_to_exerciseDetails,
            Bundle().apply {
                putString("name", exercises[position].name)
                putString("imgUrl", exercises[position].imgUrl)
                putString("bodyPart", exercises[position].bodyPart)
                putString("equipment", exercises[position].equipment)
                putString("target", exercises[position].target)
            })
    }

}