package com.example.m6t

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast


class createPlaylistFragment (
        ): Fragment() {

    lateinit var initialtrack:ArrayList<track>
    lateinit var btnCreatePlaylist: Button
    lateinit var btnPrev: Button
    lateinit var btnProceed: Button
    lateinit var img: ImageView
    lateinit var etTitle: EditText

    var onAddListener:((updateusers:ArrayList<user>)-> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCreatePlaylist=view.findViewById(R.id.btn_createPlaylist)
        btnPrev=view.findViewById(R.id.btn_prev)
        btnProceed=view.findViewById(R.id.btn_proceed)
        img=view.findViewById(R.id.iv_browse_icon)
        etTitle=view.findViewById(R.id.et_createtitle_playlist)

        initialtrack=ArrayList()

        var users:ArrayList<user>?=arguments?.getParcelableArrayList("users")
        var currentUser: user? = arguments?.getParcelable("current")


        var images=1
        btnPrev.setOnClickListener {
            if (images!=1){
                images--
                if (images==1){
                    img.setImageResource(R.drawable.pic1)
                }else if(images==2){
                    img.setImageResource(R.drawable.pic2)
                }else if(images==3){
                    img.setImageResource(R.drawable.pic3)
                }else{
                    img.setImageResource(R.drawable.pic4)
                }
            }
        }
        btnProceed.setOnClickListener {
            if (images!=4){
                images++
                if (images==1){
                    img.setImageResource(R.drawable.pic1)
                }else if(images==2){
                    img.setImageResource(R.drawable.pic2)
                }else if(images==3){
                    img.setImageResource(R.drawable.pic3)
                }else{
                    img.setImageResource(R.drawable.pic4)
                }
            }
        }
        btnCreatePlaylist.setOnClickListener {
            if (etTitle.text.toString()==""){
                Toast.makeText(view.context, "harus isi nama playlist!", Toast.LENGTH_SHORT).show()
            }else{
                if (users!=null && currentUser!=null){
                    for (user in users){
                        if (user.username==currentUser.username){
                            user.playlists.add(playlist(images.toString(),etTitle.text.toString(),user.username,initialtrack))
                            Toast.makeText(view.context, "Playlist Added!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }


        }

    }
}