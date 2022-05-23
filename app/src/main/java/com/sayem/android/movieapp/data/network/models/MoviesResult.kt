package com.sayem.android.movieapp.data.network.models

data class MoviesResult(
    val page : Int,
    val results : List<ServerMovie>
)