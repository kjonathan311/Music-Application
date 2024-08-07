package com.example.m6t

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class userhomeFragment(
) : Fragment() {

    lateinit var rvPlaylist: RecyclerView
    lateinit var rvLatest: RecyclerView
    lateinit var tvUsername:TextView

    lateinit var playlistA:playlistAdapter
    lateinit var latest: latestAdapter
    lateinit var users:ArrayList<user>
    lateinit var currentUser:user
    lateinit var tracks:ArrayList<track>
    var onAddPlaylistListener:((currenttrack:track)-> Unit)? = null
    var onClickPlaylistListener:((currentPlaylist:playlist)->Unit)?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_userhome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvUsername=view.findViewById(R.id.tv_username)
        rvPlaylist=view.findViewById(R.id.rv_playlists)
        rvLatest=view.findViewById(R.id.rv_latest)

        users= arguments?.getParcelableArrayList("users")!!
        currentUser= arguments?.getParcelable("current")!!
        tracks=arguments?.getParcelableArrayList("tracks")!!

        if (users!=null && currentUser!=null && tracks!=null){
            tvUsername.text=currentUser.username

            for (user in users){
                if (user.username==currentUser.username){
                    currentUser=user
                }
            }

            playlistA=playlistAdapter(currentUser.playlists)
            playlistA.onClickPlaylist=object :MyOnClickListenerforPlaylist{
                override fun onClick(playlist: playlist) {
                    val playlistPageUserFragment  = PlaylistPageUserFragment(currentUser,users,playlist)
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameUser,playlistPageUserFragment)
                        .commit()
                }
            }
            rvPlaylist.adapter=playlistA
            val gridManager = GridLayoutManager(view.context,2)
            rvPlaylist.layoutManager=gridManager


            latest= latestAdapter(tracks)
            latest.onClickListener=object :MyOnClickListener{
                override fun onClick(currenttrack: track) {
                    val intent=Intent(view.context,addToPlaylist::class.java)
                    intent.putExtra("track",currenttrack)
                    intent.putExtra("tracks",tracks)
                    intent.putExtra("users",users)
                    intent.putExtra("current",currentUser.username)
                    startActivity(intent)
                }
            }
            rvLatest.adapter=latest
            val layoutManager = LinearLayoutManager(view.context)
            rvLatest.layoutManager=layoutManager
        }

    }

}