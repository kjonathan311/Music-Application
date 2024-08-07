package com.example.m6t

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class track (
    val title:String,
    val artist:String,
    val duration:String
        ):Parcelable{

}