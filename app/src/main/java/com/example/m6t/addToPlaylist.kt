package com.example.m6t

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class addToPlaylist : AppCompatActivity() {
    lateinit var btnAddtocheckedPlaylist: Button
    lateinit var rv_checkedplaylist: RecyclerView
    lateinit var checked:ArrayList<Boolean>
    lateinit var playlistsName:ArrayList<String>
    lateinit var pAdapter:checkedPlaylistAdapter

    lateinit var tracks: ArrayList<track>
    lateinit var users: ArrayList<user>
    lateinit var currentUsername:String
    lateinit var currentUser:user
    lateinit var initialplaylist:ArrayList<playlist>
    lateinit var currentTrack:track


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_playlist)

        btnAddtocheckedPlaylist=findViewById(R.id.btn_add_to_checked_playlist)
        rv_checkedplaylist=findViewById(R.id.rv_addtoplaylist)
        checked=ArrayList()
        tracks= ArrayList()
        users=ArrayList()
        initialplaylist=ArrayList()
        playlistsName=ArrayList()
        currentTrack= track("","","")
        currentUsername=""

        currentUser=user("","",initialplaylist,initialplaylist)
        val currentTracks=intent.getParcelableArrayListExtra<track>("tracks")
        if (currentTracks!=null){
            tracks=currentTracks
        }
        val currentUsers=intent.getParcelableArrayListExtra<user>("users")
        if (currentUsers!=null) {
            users=currentUsers
        }
        val current=intent.getStringExtra("current")
        if (current!=null) {
            currentUsername=current
            for (user in users){
                if (user.username==currentUsername){
                    currentUser=user
                }
            }
        }
        val selectedTrack=intent.getParcelableExtra<track>("track")
        if (selectedTrack!=null){
            currentTrack=selectedTrack
        }

        for (playlist in currentUser.playlists){
            checked.add(false)
            playlistsName.add(playlist.title)
        }
        val layoutManager= LinearLayoutManager(applicationContext)
        pAdapter= checkedPlaylistAdapter(checked,playlistsName)
        rv_checkedplaylist.layoutManager=layoutManager
        rv_checkedplaylist.adapter=pAdapter
        btnAddtocheckedPlaylist.setOnClickListener {
            for (i in 0..currentUser.playlists.size-1){
                if (checked.get(i)==true){
                    currentUser.playlists.get(i).tracks.add(currentTrack)
                }
            }
            Toast.makeText(this, "sukses menambah track ke playlists", Toast.LENGTH_SHORT).show()
            finish()
            val intent= Intent(this,UserActivity::class.java)
            intent.putExtra("current",currentUsername)
            intent.putExtra("tracks",tracks)
            intent.putExtra("users",users)
            startActivity(intent)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.opt_logout->{
                finish()
                val intent= Intent(this,MainActivity::class.java)
                intent.putExtra("tracks",tracks)
                intent.putExtra("users",users)
                startActivity(intent)
            }else->{
        }
        }
        return super.onOptionsItemSelected(item)
    }

}