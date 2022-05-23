package com.sayem.android.movieapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Created by Sayem on 5/18/2022
 */
@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val thumbnailUrl: String,
    val overview : String,
    val rating : Float,
    val viewCount : Int,
    val homePage : String? = null,
    val releasedDate : Date? = null,
    val genres : List<String>? = null
) : Parcelable