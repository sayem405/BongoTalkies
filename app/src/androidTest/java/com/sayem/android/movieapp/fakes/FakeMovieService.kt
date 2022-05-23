package com.sayem.android.movieapp.fakes

import android.content.res.Resources
import com.sayem.android.movieapp.data.network.MovieService
import com.sayem.android.movieapp.data.network.models.MoviesResult
import com.sayem.android.movieapp.data.network.models.ServerMovie
import com.sayem.android.movieapp.data.network.models.ServerMovieDetails
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Sayem on 5/23/2022
 */
class FakeMovieService(
    private val pageSize: Int,
    private val movieList: List<ServerMovie>,
    private val movieDetailsList: List<ServerMovieDetails>
) :
    MovieService {
    override suspend fun getMovies(page: Int): MoviesResult {
        val pageIndex = if (page > 0) page - 1 else 0
        val startIndex = (pageIndex).times(pageSize)
        val endIndex =
            if (startIndex + pageSize > movieList.size) movieList.size else startIndex + pageSize
        val list = movieList.subList(startIndex, endIndex)
        return MoviesResult(page, list)
    }

    override suspend fun getMovie(id: Int): ServerMovieDetails {
        return movieDetailsList.find { it.id == id }
            ?: throw Resources.NotFoundException("$id not found")
    }
}

val serverMovieList = ArrayList<ServerMovie>().apply {
    add(
        ServerMovie(
            1,
            "1",
            1f,
            1,
            "1",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg"
        )
    )
    add(
        ServerMovie(
            2,
            "2",
            2f,
            2,
            "2",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg"
        )
    )
    add(
        ServerMovie(
            3,
            "3",
            3f,
            3,
            "3",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg"
        )
    )
    add(
        ServerMovie(
            4,
            "4",
            4f,
            4,
            "4",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg"
        )
    )
}

val movieDetailsList = ArrayList<ServerMovieDetails>().apply {
    add(
        ServerMovieDetails(
            1,
            "1",
            1f,
            1,
            "1",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg",
            "www.google.com",
            Calendar.getInstance().time
        )
    )
    add(
        ServerMovieDetails(
            2,
            "2",
            2f,
            2,
            "2",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg",
            null,
            null
        )
    )
    add(
        ServerMovieDetails(
            3,
            "3",
            3f,
            3,
            "3",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg",
            "www.google.com",
            Calendar.getInstance().time
        )
    )
    add(
        ServerMovieDetails(
            4,
            "4",
            4f,
            4,
            "4",
            "https://image.tmdb.org/t/p/original/nSNle6UJNNuEbglNvXt67m1a1Yn.jpg",
            null,
            null
        )
    )
}