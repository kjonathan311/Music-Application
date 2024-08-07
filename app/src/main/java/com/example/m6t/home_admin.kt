package com.example.m6t

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class home_admin(
    var tracks:ArrayList<track>
) : Fragment() {
    private lateinit var rvAdmin: RecyclerView
    lateinit var currentadapter: trackAdapter

    var onAddListener:((newTrack: ArrayList<track>)-> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        rvAdmin=view.findViewById(R.id.rv_admin)
        currentadapter= trackAdapter(tracks)
        rvAdmin.adapter=currentadapter
        val layoutManager = LinearLayoutManager(view.context)
        rvAdmin.layoutManager=layoutManager
        onAddListener?.invoke(tracks)
    }
}