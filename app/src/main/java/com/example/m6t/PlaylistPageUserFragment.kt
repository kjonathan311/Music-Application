package com.example.m6t

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PlaylistPageUserFragment(
    var currentUser:user,
    var users: ArrayList<user>,
    var currentPlaylist:playlist
) : Fragment() {

    lateinit var icon_selected: ImageView
    lateinit var tvTitle: TextView
    lateinit var tvtracks: TextView
    lateinit var selected_list: RecyclerView

    lateinit var initialplaylist:ArrayList<playlist>
    lateinit var adapterPlaylist:selectedPlaylistAdapter

    var onAddListener:((updateCurrentUser:ArrayList<user>)-> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_playlist_page_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        icon_selected=view.findViewById(R.id.icon_selected_playlist)
        tvTitle=view.findViewById(R.id.tv_selected_title)
        tvtracks=view.findViewById(R.id.tv_selectednum)
        selected_list=view.findViewById(R.id.list_selected_playlist)


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
        adapterPlaylist= selectedPlaylistAdapter(currentPlaylist.tracks)

        adapterPlaylist.onClickSelected=object :MyOnClickListenerforselected{
            override fun onClick(currentTrack: ArrayList<track>,delete:String) {
                for (user in users){
                    if(user==currentUser){
                        for(i in 0..currentUser.playlists.size-1){
                            if(currentUser.playlists.get(i)==currentPlaylist){
                                for(j in 0..user.playlists.get(i).tracks.size-1){
                                    if(user.playlists.get(i).tracks.get(j).title==delete){
                                        user.playlists.get(i).tracks.removeAt(0)
                                        val playlistPageUserFragment  = PlaylistPageUserFragment(currentUser,users,user.playlists.get(i))
                                        parentFragmentManager
                                            .beginTransaction()
                                            .replace(R.id.frameUser,playlistPageUserFragment)
                                            .commit()
                                        break
                                    }
                                }
                            }
                        }
                    }
                    break
                }
            }
        }
        selected_list.adapter=adapterPlaylist
        val layoutManager = LinearLayoutManager(view.context)
        selected_list.layoutManager=layoutManager

    }
}