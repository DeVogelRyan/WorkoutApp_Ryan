package com.example.workoutapp_ryan.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.transition.TransitionInflater
import com.example.workoutapp_ryan.R
import com.example.workoutapp_ryan.database.AppDatabase
import com.example.workoutapp_ryan.recycleview.adapter.WeightAdapter
import com.example.workoutapp_ryan.recycleview.model.Weight
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var myRecycleView: RecyclerView? = null
    private var weights: MutableList<Weight> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.to_left)
        exitTransition = inflater.inflateTransition(R.transition.to_right)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myRecycleView = requireView().findViewById<RecyclerView>(R.id.RecycleviewWeight)
        myRecycleView?.layoutManager = LinearLayoutManager(this.context)
        // myRecycleView?.adapter = null
        initList()
        Log.d("Users", weights.toString())

    }

    private fun initList(): List<Weight> {

        val db = Room.databaseBuilder(
            this.requireContext(),
            AppDatabase::class.java, "users.db"
        ).build()

        lifecycleScope.launch {
            db.dao.getWeights().forEach {
                weights.add(Weight(it.createdAt, it.weight))
                Log.d("Users", it.weight.toString())
            }
            myRecycleView?.adapter =
                this@SecondFragment.context?.let { WeightAdapter(it, weights) }

        }
        return weights
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}