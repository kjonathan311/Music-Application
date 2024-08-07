package com.example.m6t

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class searchplaylistadapter (
    private val playlists: ArrayList<playlist>
    ): RecyclerView.Adapter<searchplaylistadapter.CustomViewHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            var itemView = LayoutInflater.from(parent.context)
            return CustomViewHolder(itemView.inflate(
                R.layout.playlist_search, parent ,false
            ))
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            if (playlists[position].icon=="1"){
                holder.icon.setImageResource(R.drawable.pic1)
            }else if(playlists[position].icon=="2"){
                holder.icon.setImageResource(R.drawable.pic2)
            }else if(playlists[position].icon=="3"){
                holder.icon.setImageResource(R.drawable.pic3)
            }else{
                holder.icon.setImageResource(R.drawable.pic4)
            }
            holder.tvTitle.text=playlists[position].title
            holder.tvDes.text="By "+playlists[position].user+" - "+playlists[position].tracks.size.toString()+" tracks"
            holder.layout.setOnClickListener {
                onClickSelected?.onClick(playlists[position])
            }
        }

        override fun getItemCount(): Int {
            return  playlists.size
        }


        class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val icon: ImageView =itemView.findViewById(R.id.icon_result_playlist)
            val tvTitle: TextView =itemView.findViewById(R.id.tv_result_title)
            val tvDes: TextView =itemView.findViewById(R.id.tv_description)
            val layout: LinearLayout =itemView.findViewById(R.id.layout_result)

        }

        var onClickSelected:OnClickListenerSearch?=null

    }
    interface OnClickListenerSearch{
        fun onClick(playlist:playlist) {

        }
    }

