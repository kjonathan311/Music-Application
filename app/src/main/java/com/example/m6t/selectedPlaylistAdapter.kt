package com.example.m6t

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class selectedPlaylistAdapter (
    private val tracks: ArrayList<track>,
): RecyclerView.Adapter<selectedPlaylistAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.track_list, parent ,false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.tvOrder.text = (position+1).toString()
        holder.tvTrack.text = tracks[position].title
        holder.tvArtist.text = tracks[position].artist
        holder.tvDuration.text = tracks[position].duration
        holder.btnDel.setOnClickListener {
//            for (track in tracks){
//                if (track.title==holder.tvTrack.text.toString()){
//                    tracks.remove(track)
//                    break
//                }
//            }
            onClickSelected?.onClick(tracks,holder.tvTrack.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return  tracks.size
    }


    class CustomViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val tvOrder:TextView=itemView.findViewById(R.id.tv_order)
        val tvTrack:TextView=itemView.findViewById(R.id.tv_track)
        val tvArtist:TextView=itemView.findViewById(R.id.tv_artist)
        val tvDuration:TextView=itemView.findViewById(R.id.tv_duration)
        val btnDel: Button =itemView.findViewById(R.id.btn_delete)
    }

    var onClickSelected:MyOnClickListenerforselected?=null

}
interface MyOnClickListenerforselected{
    fun onClick(currentTrack:ArrayList<track>,delete: String) {

    }
}