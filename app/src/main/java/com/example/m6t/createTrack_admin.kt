package com.example.m6t

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.collections.ArrayList

class createTrack_admin (
    var tracks: ArrayList<track>
): Fragment() {
    lateinit var etTrack: EditText
    lateinit var etArtist: EditText
    lateinit var etM: EditText
    lateinit var etS: EditText
    lateinit var btnAdd: Button
    var onAddListener:((newTrack: ArrayList<track>)-> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_track_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etTrack=view.findViewById(R.id.et_create_title)
        etArtist=view.findViewById(R.id.et_create_artist)
        etM=view.findViewById(R.id.etn_mm)
        etS=view.findViewById(R.id.etn_ss)
        btnAdd=view.findViewById(R.id.btn_addTrack)
        btnAdd.setOnClickListener {
            tracks.add(track(etTrack.text.toString(),etArtist.text.toString(),etM.text.toString()+":"+etS.text.toString()))
            onAddListener?.invoke(tracks)
        }
    }
}