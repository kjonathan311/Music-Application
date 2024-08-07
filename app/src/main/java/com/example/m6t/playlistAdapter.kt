package com.example.m6t

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class playlistAdapter(
    var playlists: ArrayList<playlist>,
        ):RecyclerView.Adapter<playlistAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.playlist_list, parent ,false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.tvTitle.text=playlists[position].title
        if (playlists[position].icon=="1"){
            holder.icon.setImageResource(R.drawable.pic1)
        }else if(playlists[position].icon=="2"){
            holder.icon.setImageResource(R.drawable.pic2)
        }else if(playlists[position].icon=="3"){
            holder.icon.setImageResource(R.drawable.pic3)
        }else{
            holder.icon.setImageResource(R.drawable.pic4)
        }
        holder.tvnum.text=playlists[position].tracks.size.toString()+" tracks"
        holder.layout.setOnClickListener {
            onClickPlaylist?.onClick(playlists[position])
        }
    }

    override fun getItemCount(): Int {
        return  playlists.size
    }


    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val icon:ImageView=itemView.findViewById(R.id.iv_playlist)
        val tvTitle: TextView =itemView.findViewById(R.id.tv_title_playlist)
        val tvnum: TextView =itemView.findViewById(R.id.tv_numbertracks)
        val layout:LinearLayout=itemView.findViewById(R.id.playlist_layout_b)
    }

    var onClickPlaylist:MyOnClickListenerforPlaylist?=null
}

interface MyOnClickListenerforPlaylist{
    fun onClick(playlist: playlist) {

    }
}