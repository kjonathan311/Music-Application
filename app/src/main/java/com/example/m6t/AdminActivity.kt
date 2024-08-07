package com.example.m6t

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

class AdminActivity : AppCompatActivity() {


    lateinit var frameAdmin:FrameLayout
    lateinit var homeAdmin: home_admin
    lateinit var createtrackAdmin: createTrack_admin
    lateinit var navbar: BottomNavigationView

    lateinit var tracks: ArrayList<track>
    lateinit var users: ArrayList<user>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        tracks= ArrayList()
        users=ArrayList()
        val currentTracks=intent.getParcelableArrayListExtra<track>("tracks")
        if (currentTracks!=null){
            tracks=currentTracks
        }
        val currentUsers=intent.getParcelableArrayListExtra<user>("users")
        if (currentUsers!=null) {
            users=currentUsers
        }

        navbar=findViewById(R.id.btm_admin)
        frameAdmin=findViewById(R.id.frame_admin)
        createtrackAdmin= createTrack_admin(tracks)

        fun show_homeAdmin(){
            homeAdmin= home_admin(tracks)
            val fragmentManager = supportFragmentManager.beginTransaction()
            homeAdmin.onAddListener = { updatedtracks->
                tracks=updatedtracks
            }
            fragmentManager.replace(R.id.frame_admin, homeAdmin)
            fragmentManager.commit()
        }
        show_homeAdmin()

        navbar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_fav->{
                    show_homeAdmin()
                }
                else -> {
                    val fragmentManager = supportFragmentManager.beginTransaction()
                    createtrackAdmin.onAddListener = { updatedtracks->
                        Toast.makeText(this, "Berhasil Menambah Track", Toast.LENGTH_SHORT).show()
                        tracks=updatedtracks
                        homeAdmin= home_admin(tracks)
                    }
                    fragmentManager.replace(R.id.frame_admin, createtrackAdmin)
                    fragmentManager.commit()
                }
            }
            true
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