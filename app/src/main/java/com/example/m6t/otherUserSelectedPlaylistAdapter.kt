package com.example.m6t

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class otherUserSelectedPlaylistAdapter (
    private val tracks: ArrayList<track>,
    ): RecyclerView.Adapter<otherUserSelectedPlaylistAdapter.CustomViewHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            var itemView = LayoutInflater.from(parent.context)
            return CustomViewHolder(itemView.inflate(
                R.layout.list_other_user_track, parent ,false
            ))
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.tvOrder.text = (position+1).toString()
            holder.tvTrack.text = tracks[position].title
            holder.tvArtist.text = tracks[position].artist
            holder.tvDuration.text = tracks[position].duration
        }

        override fun getItemCount(): Int {
            return  tracks.size
        }


        class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val tvOrder: TextView =itemView.findViewById(R.id.tv_order_other)
            val tvTrack: TextView =itemView.findViewById(R.id.tv_track_other)
            val tvArtist: TextView =itemView.findViewById(R.id.tv_artist_other)
            val tvDuration: TextView =itemView.findViewById(R.id.tv_duration_other)
        }
    }