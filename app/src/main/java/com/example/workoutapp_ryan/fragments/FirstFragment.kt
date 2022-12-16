package com.example.workoutapp_ryan.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.workoutapp_ryan.mydata.APIInterface
import com.example.workoutapp_ryan.mydata.MyDataItem
import com.example.workoutapp_ryan.R
import com.example.workoutapp_ryan.adapter.ExerciseAdapter
import com.example.workoutapp_ryan.model.Exercise
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
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var isLoaded = false
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
        return inflater.inflate(R.layout.fragment_first, container, false)
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


        myRecycleView = requireView().findViewById<RecyclerView>(R.id.mRecyclerview)
        myRecycleView?.layoutManager = LinearLayoutManager(this.context)
        // myRecycleView?.adapter = null
        createExercises()
        isLoaded = true

    }

    private fun createExercises(): List<Exercise> {
        /* Sources:
         * https://www.youtube.com/watch?v=5gFrXGbQsc8&t=342s
         * https://square.github.io/retrofit/
         */
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
                exercises.clear()

                for (i in 0..20) {
                    Log.d("JSON", responseBody.get(i).name)
                    val name = responseBody.get(i).name + "#$i"
                    val gifUrl = responseBody.get(i).gifUrl.replace("http", "https")
                    exercises.add(Exercise(name, gifUrl))

                    // myRecycleView?.adapter?.notifyDataSetChanged()
                }
                myRecycleView?.adapter =
                    this@FirstFragment.context?.let { ExerciseAdapter(it, exercises) }
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
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