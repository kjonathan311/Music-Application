package com.example.m6t

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class latestAdapter(
    private val tracks: ArrayList<track>,
): RecyclerView.Adapter<latestAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.latest_list, parent ,false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.tvOrder.text = (position+1).toString()
        holder.tvTrack.text = tracks[position].title
        holder.tvArtist.text = tracks[position].artist
        holder.tvDuration.text = tracks[position].duration
        holder.btnAddtoPlaylist.setOnClickListener {
            onClickListener?.onClick(tracks[position])
        }
    }

    override fun getItemCount(): Int {
        return  tracks.size
    }

    var onClickListener:MyOnClickListener?=null

    class CustomViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val tvOrder:TextView=itemView.findViewById(R.id.tv_order_latest)
        val tvTrack:TextView=itemView.findViewById(R.id.tv_track_latest)
        val tvArtist:TextView=itemView.findViewById(R.id.tv_artist_latest)
        val tvDuration:TextView=itemView.findViewById(R.id.tv_duration_latest)
        val btnAddtoPlaylist: Button =itemView.findViewById(R.id.btn_addtoPlaylist)
    }
}
interface MyOnClickListener{
    fun onClick(currentTrack:track) {

    }
}