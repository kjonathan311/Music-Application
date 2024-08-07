package com.example.m6t

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

class UserActivity : AppCompatActivity() {

    lateinit var tracks: ArrayList<track>
    lateinit var users: ArrayList<user>
    lateinit var createPlaylistFragment: createPlaylistFragment
    lateinit var libraryFragment: LibraryFragment
    lateinit var currentUser:user
    lateinit var currentUsername:String
    lateinit var navbar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        navbar=findViewById(R.id.btm_nav_user)

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
        val current=intent.getStringExtra("current")
        if (current!=null) {
            currentUsername=current
            for (user in users){
                if (user.username==currentUsername){
                    currentUser=user
                }
            }
        }


        fun show_userHomeFragment(){
            val fragmentManager = supportFragmentManager.beginTransaction()
            val userhomeFragment= userhomeFragment()
            userhomeFragment.onAddPlaylistListener={

            }
            val bundle=Bundle()
            bundle.putParcelableArrayList("users",users)
            bundle.putParcelableArrayList("tracks",tracks)
            bundle.putParcelable("current",currentUser)
            userhomeFragment.arguments=bundle
            fragmentManager.replace(R.id.frameUser, userhomeFragment)
            fragmentManager.commit()
        }


        fun show_createPlaylist(){
            val fragmentManager = supportFragmentManager.beginTransaction()
            createPlaylistFragment=createPlaylistFragment()
            val bundle=Bundle()
            bundle.putParcelableArrayList("users",users)
            bundle.putParcelable("current",currentUser)
            createPlaylistFragment.arguments=bundle
            createPlaylistFragment.onAddListener={
                updateusers ->
                users=updateusers
                Toast.makeText(this, "berhasil menambah playlist!", Toast.LENGTH_SHORT).show()
            }
            fragmentManager.replace(R.id.frameUser, createPlaylistFragment)
            fragmentManager.commit()
        }

        show_userHomeFragment()


        navbar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_home_user->{
                    show_userHomeFragment()
                }
                R.id.menu_search_user->{
                    val fragmentManager = supportFragmentManager.beginTransaction()
                    val searchFragment=searchFragment()
                    val bundle=Bundle()
                    bundle.putParcelableArrayList("users",users)
                    bundle.putParcelableArrayList("tracks",tracks)
                    bundle.putParcelable("current",currentUser)
                    searchFragment.arguments=bundle
                    fragmentManager.replace(R.id.frameUser, searchFragment)
                    fragmentManager.commit()
                }
                R.id.menu_create_user->{
                    show_createPlaylist()
                }
                else -> {
                    val fragmentManager = supportFragmentManager.beginTransaction()
                    val profileFragment= ProfileFragment()
                    val bundle=Bundle()
                    bundle.putParcelableArrayList("users",users)
                    bundle.putParcelableArrayList("tracks",tracks)
                    bundle.putParcelable("current",currentUser)
                    profileFragment.arguments=bundle
                    fragmentManager.replace(R.id.frameUser, profileFragment)
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