package com.example.m6t

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class playlist(
    val icon: String,
    val title:String,
    val user:String,
    var tracks: ArrayList<track>
        ):Parcelable{

}