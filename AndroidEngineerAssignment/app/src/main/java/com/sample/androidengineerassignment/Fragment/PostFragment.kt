package com.sample.androidengineerassignment.Fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.androidengineerassignment.Adapter.recycleradapter

import com.sample.androidengineerassignment.R

/**
 * A simple [Fragment] subclass.
 */
class PostFragment : Fragment() {

    lateinit var recyclerPost: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recycleradapter: recycleradapter
    val postheading = arrayListOf<String>(
        "BEAUTIFUL STORY OF TIRUPATI BALAJI: MYSTERIES, LOVE, DEBT, KALIYUGA",
        "THESE ANCIENT SHIVA TEMPLES ON DIFFERENT LOCATIONS, LIE ON STRAIGHT LINE",
        "MAHAKALESHWAR JYOTIRLINGAM: LORD OF TIME AND DEATH",
        "THE EIGHT IMMORTALS (CHIRANJIVI) ACCORDING TO HINDU MYTHOLOGY: WHO WILL REMAIN ALIVE..",
        "THE MYSTERIOUS FACTS OF MOUNT KAILASH, AN ABODE OF LORD SHIVA",
        "HEALTH BENEFITS OF LIMESTONE: FROM GOOD HEIGHT TO CURING IMPOTENCE",
        "THESE PEOPLE CLAIMED THAT THEY DID TIME TRAVEL IN THEIR REAL LIFE"
    )
    val imgurl = arrayListOf<String>(
        "https://aastik.in/wp-content/uploads/2017/03/e0c73592c8ca318891ab8d256e223ce8-218x150.jpg",
        "https://aastik.in/wp-content/uploads/2017/05/adiyogi-shiva-218x150.jpg",
        "https://aastik.in/wp-content/uploads/2016/09/Lord-Mahakal-Jyotir-linga-218x150.jpg",
        "https://aastik.in/wp-content/uploads/2017/05/dragon-warrior-4-by-camilkuo-on-deviantart-218x150.jpg",
        "https://aastik.in/wp-content/uploads/2017/03/IMG_0595-218x150.jpg",
        "https://aastik.in/wp-content/uploads/2017/03/heartsickness-428103_1920-218x150.jpg",
        "https://aastik.in/wp-content/uploads/2017/08/quantum-time-travel-218x150.jpg"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        // Inflate the layout for this fragment
        recyclerPost = view.findViewById(R.id.recyclerPost)
        layoutManager = LinearLayoutManager(activity)
        recycleradapter = recycleradapter(activity as Context, postheading, imgurl)

        recyclerPost.adapter = recycleradapter
        recyclerPost.layoutManager = layoutManager
        return view
    }


}
