package com.example.m6t

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class   checkedPlaylistAdapter(
    private val checked: ArrayList<Boolean>,
    private val playlistname:ArrayList<String>
): RecyclerView.Adapter<checkedPlaylistAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.checkbox_list, parent ,false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.checkCb.isChecked = checked[position]
        holder.checkCb.text=playlistname[position]
        holder.checkCb.setOnClickListener {
            if(checked[position]==false){
                checked[position]=true
            }else{
                checked[position]=false
            }
        }
    }

    override fun getItemCount(): Int {
        return  checked.size
    }


    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val checkCb: CheckBox =itemView.findViewById(R.id.checkboxPlaylist)
    }
}
