package com.example.m6t

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class OtherUserPlaylistFragment(
    var currentUser:user,
    var users: ArrayList<user>,
    var currentPlaylist:playlist
) : Fragment() {
    lateinit var icon_selected: ImageView
    lateinit var tvTitle: TextView
    lateinit var tvtracks: TextView
    lateinit var selected_list: RecyclerView

    lateinit var initialplaylist:ArrayList<playlist>
    lateinit var adapterPlaylist:otherUserSelectedPlaylistAdapter
    lateinit var tvLiked:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other_user_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        icon_selected=view.findViewById(R.id.icon_otheruser_playlist)
        tvTitle=view.findViewById(R.id.tv_selected_title_otheruser)
        tvtracks=view.findViewById(R.id.tv_selectednum_otheruser)
        selected_list=view.findViewById(R.id.list_otheruser_playlist)
        tvLiked=view.findViewById(R.id.tv_liked)

        for (user in users){
            if (user.username==currentUser.username){
                for (playlist in user.Likedplaylists){
                    if (playlist.title==currentPlaylist.title&&playlist.user==currentPlaylist.user){
                        tvLiked.text="\uD83D\uDC9A"
                        break
                    }
                }
            }
        }

        tvLiked.setOnClickListener {
            if (tvLiked.text.toString()=="\uD83D\uDC9A"){
                for (user in users){
                    if (user.username==currentUser.username){
                        for (playlist in user.Likedplaylists){
                            if (playlist.title==currentPlaylist.title&&playlist.user==currentPlaylist.user){
                                user.Likedplaylists.remove(playlist)
                                currentUser=user
                                break
                            }
                        }
                    }
                }
                tvLiked.text="♡"
            }else{
                for (user in users){
                    if (user.username==currentUser.username){
                        user.Likedplaylists.add(currentPlaylist)
                        currentUser=user
                    }
                }
                tvLiked.text="\uD83D\uDC9A"
            }
        }

        tvTitle.text=currentPlaylist.title
        tvtracks.text=currentPlaylist.tracks.size.toString()+" tracks"
        if(currentPlaylist.icon=="1"){
            icon_selected.setImageResource(R.drawable.pic1)
        }else if(currentPlaylist.icon=="2"){
            icon_selected.setImageResource(R.drawable.pic2)
        }else if(currentPlaylist.icon=="3"){
            icon_selected.setImageResource(R.drawable.pic3)
        }else{
            icon_selected.setImageResource(R.drawable.pic4)
        }
        for (user in users){
            if (user.username==currentUser.username){
                currentUser=user
            }
        }
        adapterPlaylist= otherUserSelectedPlaylistAdapter(currentPlaylist.tracks)
        selected_list.adapter=adapterPlaylist
        val layoutManager = LinearLayoutManager(view.context)
        selected_list.layoutManager=layoutManager



    }

}