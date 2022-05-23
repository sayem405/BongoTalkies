package com.sayem.android.movieapp.data.network.models

import java.util.*

data class ServerMovie(
    val id : Int,
    val title : String,
    val vote_average : Float,
    val vote_count : Int,
    val overview : String,
    val poster_path : String,
    val homepage: String? = null
)

data class ServerMovieDetails(
    val id : Int,
    val title : String,
    val vote_average : Float,
    val vote_count : Int,
    val overview : String,
    val poster_path : String,
    val homepage: String?,
    val release_date : Date?,
    val genres : List<NetworkGenre>? = null
)