package com.sayem.android.movieapp.fakes

import android.content.res.Resources
import com.sayem.android.movieapp.data.MovieRepo
import com.sayem.android.movieapp.domain.Movie

/**
 * Created by Sayem on 5/23/2022
 */
class FakeMovieRepo constructor(
    private val pageSize: Int,
    private val movieList: List<Movie>,
) : MovieRepo {
    override suspend fun getMovieList(page: Int): ArrayList<Movie> {
        val pageIndex = if (page > 0) page - 1 else 0
        val startIndex = (pageIndex).times(pageSize)
        val endIndex =
            if (startIndex + pageSize > movieList.size) movieList.size else startIndex + pageSize
        return movieList.subList(startIndex, endIndex).toCollection(ArrayList())
    }

    override suspend fun getMovieDetails(id: Int): Movie {
        return movieList.find { it.id == id }
            ?: throw Resources.NotFoundException("$id not found")
    }
}

val movieList = ArrayList<Movie>().apply {
    add(
        Movie(
            1,
            "1",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg",
            "1",
            1f,
            1,
        )
    )
    add(
        Movie(
            2,
            "2",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg",
            "2",
            2f,
            2,
        )
    )
    add(
        Movie(
            3,
            "3",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg",
            "3",
            3f,
            3,
        )
    )
    add(
        Movie(
            4,
            "4",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg",
            "4",
            4f,
            4,
        )
    )
}