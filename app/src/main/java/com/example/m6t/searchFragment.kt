package com.example.m6t

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class searchFragment : Fragment() {

    lateinit var etSearch:EditText
    lateinit var btnSearch:ImageButton
    lateinit var rvSearch:RecyclerView
    lateinit var r1:RadioButton
    lateinit var r2:RadioButton
    lateinit var users:ArrayList<user>
    lateinit var currentUser:user
    lateinit var tracks:ArrayList<track>
    lateinit var selectedTracks:ArrayList<track>
    lateinit var selectedPlaylist:ArrayList<playlist>
    lateinit var layoutbar:LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etSearch=view.findViewById(R.id.et_search)
        btnSearch=view.findViewById(R.id.btnsearch)
        rvSearch=view.findViewById(R.id.rv_search_result)
        r1=view.findViewById(R.id.r1)
        r2=view.findViewById(R.id.r2)
        layoutbar=view.findViewById(R.id.layout_bar)

        selectedTracks=ArrayList()
        selectedPlaylist= ArrayList()
        r1.setOnClickListener {
            if (r1.isChecked){
                r2.isChecked=false
            }else{
                r2.isChecked=true
            }
        }
        r2.setOnClickListener {
            if (r2.isChecked){
                r1.isChecked=false
            }else{
                r1.isChecked=true
            }
        }

        users= arguments?.getParcelableArrayList("users")!!
        currentUser= arguments?.getParcelable("current")!!
        tracks=arguments?.getParcelableArrayList("tracks")!!

        btnSearch.setOnClickListener {
            selectedTracks.clear()
            selectedPlaylist.clear()
            layoutbar.visibility=View.VISIBLE
            if (r1.isChecked){
                for (track in tracks){
                    val t=track.title.lowercase()
                    val a=track.artist.lowercase()
                    if(t.contains(etSearch.text.toString().lowercase())||a.contains(etSearch.text.toString().lowercase())){
                        selectedTracks.add(track)
                    }
                }
                val latest= latestAdapter(selectedTracks)
                latest.onClickListener=object :MyOnClickListener{
                    override fun onClick(currenttrack: track) {
                        val intent= Intent(view.context,addToPlaylist::class.java)
                        intent.putExtra("track",currenttrack)
                        intent.putExtra("tracks",tracks)
                        intent.putExtra("users",users)
                        intent.putExtra("current",currentUser.username)
                        startActivity(intent)
                    }
                }
                rvSearch.adapter=latest
                val layoutManager = LinearLayoutManager(view.context)
                rvSearch.layoutManager=layoutManager
            }else if(r2.isChecked){
                layoutbar.visibility=View.GONE
                for (user in users){
                    for (playlist in user.playlists){
                        val p=playlist.title.lowercase()
                        if(p.contains(etSearch.text.toString().lowercase())){
                            selectedPlaylist.add(playlist)
                        }
                    }
                }
                val searched=searchplaylistadapter(selectedPlaylist)
                searched.onClickSelected=object :OnClickListenerSearch{
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
                rvSearch.adapter=searched
                val gridManager = LinearLayoutManager(view.context)
                rvSearch.layoutManager=gridManager

            }else{
                Toast.makeText(view.context, "pilih search by!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}