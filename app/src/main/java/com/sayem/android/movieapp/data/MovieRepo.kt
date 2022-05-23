package com.sayem.android.movieapp.data

import com.sayem.android.movieapp.data.network.MovieService
import com.sayem.android.movieapp.data.network.ServerConstants
import javax.inject.Inject

/**
 * Created by Sayem on 5/18/2022
 */

class MovieRepoImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepo {
    override suspend fun getMovieList(page: Int): ArrayList<Movie> {
        try {
            val movieResult = movieService.getMovies(page)
            val list = movieResult.results.map {
                Movie(
                    it.id,
                    it.title,
                    "${ServerConstants.IMAGE_CDN}${it.poster_path}",
                    it.overview,
                    it.vote_average,
                    it.vote_count,
                    it.homepage,
                )
            }.toCollection(ArrayList())
            return list
        } catch (e: Exception) {
            throw  e
        }
    }

    override suspend fun getMovieDetails(id: Int): Movie {
        try {
            val movieDetails = movieService.getMovie(id)
            return Movie(
                movieDetails.id,
                movieDetails.title,
                "${ServerConstants.IMAGE_CDN}${movieDetails.poster_path}",
                movieDetails.overview,
                movieDetails.vote_average,
                movieDetails.vote_count,
                movieDetails.homepage,
                movieDetails.release_date,
                genres = movieDetails.genres?.map {
                    it.name
                }?.toCollection(ArrayList())
            )
        } catch (e: Exception) {
            throw  e
        }
    }
}

interface MovieRepo {
    suspend fun getMovieList(page: Int): ArrayList<Movie>
    suspend fun getMovieDetails(id: Int): Movie
}