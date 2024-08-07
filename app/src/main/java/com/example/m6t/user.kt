package com.example.m6t

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class user(
    val username:String,
    val password:String,
    var playlists: ArrayList<playlist>,
    var Likedplaylists: ArrayList<playlist>
):Parcelable {

}