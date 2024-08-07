package com.example.m6t

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ProfileFragment : Fragment() {
    lateinit var users:ArrayList<user>
    lateinit var currentUser:user
    lateinit var tracks:ArrayList<track>

    lateinit var tvU_profile:TextView
    lateinit var tv_num_playlist_profile:TextView
    lateinit var tv_num_Likedplaylist_profile:TextView
    lateinit var rv_playlists_profile:RecyclerView
    lateinit var rv_LikedPlaylists_profile:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        users= arguments?.getParcelableArrayList("users")!!
        currentUser= arguments?.getParcelable("current")!!
        tracks=arguments?.getParcelableArrayList("tracks")!!

        tvU_profile=view.findViewById(R.id.tv_username_profile)
        tv_num_playlist_profile=view.findViewById(R.id.tv_num_playlists)
        tv_num_Likedplaylist_profile=view.findViewById(R.id.tv_num_likedPlaylists)
        rv_playlists_profile=view.findViewById(R.id.rv_playlists_profile)
        rv_LikedPlaylists_profile=view.findViewById(R.id.rv_liked_playlists)

        for (user in users){
            if (user.username==currentUser.username){
                tvU_profile.text=user.username
                tv_num_playlist_profile.text=user.playlists.size.toString()+" Playlists"
                tv_num_Likedplaylist_profile.text=user.Likedplaylists.size.toString()+" Liked Playlists"
                currentUser=user
            }
        }
        val playlistA=playlistAdapter(currentUser.playlists)
        playlistA.onClickPlaylist=object :MyOnClickListenerforPlaylist{
            override fun onClick(playlist: playlist) {
                val playlistPageUserFragment  = PlaylistPageUserFragment(currentUser,users,playlist)
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameUser,playlistPageUserFragment)
                    .commit()
            }
        }
        rv_playlists_profile.adapter=playlistA
        val gridManager = GridLayoutManager(view.context,2)
        rv_playlists_profile.layoutManager=gridManager


        val playlistB=searchplaylistadapter(currentUser.Likedplaylists)
        playlistB.onClickSelected=object :OnClickListenerSearch{
            override fun onClick(playlist: playlist) {
                if (playlist.user==currentUser.username){
                    val playlistPageUserFragment  = PlaylistPageUserFragment(currentUser,users,playlist)
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameUser,playlistPageUserFragment)
                        .commit()
                }else{
                    val OtherUserPlaylistFragment  = OtherUserPlaylistFragment(currentUser,users,playlist)
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameUser,OtherUserPlaylistFragment)
                        .commit()
                }
            }
        }
        rv_LikedPlaylists_profile.adapter=playlistB
        val gridManager2 = LinearLayoutManager(view.context)
        rv_LikedPlaylists_profile.layoutManager=gridManager2

    }
}