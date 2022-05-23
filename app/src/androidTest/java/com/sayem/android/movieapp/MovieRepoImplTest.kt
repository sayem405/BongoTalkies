package com.sayem.android.movieapp

import android.content.res.Resources
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.sayem.android.movieapp.data.MovieRepo
import com.sayem.android.movieapp.data.MovieRepoImpl
import com.sayem.android.movieapp.fakes.FakeMovieService
import com.sayem.android.movieapp.fakes.movieDetailsList
import com.sayem.android.movieapp.fakes.serverMovieList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Sayem on 5/23/2022
 */
@SmallTest
@ExperimentalCoroutinesApi
class MovieRepoImplTest {

    lateinit var movieRepo: MovieRepo

    @Before
    fun setup() {
        val movieService = FakeMovieService(3, serverMovieList, movieDetailsList)
        movieRepo = MovieRepoImpl(movieService)
    }

    @Test
    fun getFirstPageMovies() = runBlocking {
        val movies = movieRepo.getMovieList(1)
        Truth.assertThat(movies.size).isEqualTo(3)
    }


    @Test
    fun getSecondPageMovies() = runBlocking {
        val movies = movieRepo.getMovieList(2)
        Truth.assertThat(movies.size).isEqualTo(1)
    }

    @Test
    fun getMoviesDetailsWithAvailableId() = runBlocking {
        val movie = movieRepo.getMovieDetails(2)
        Truth.assertThat(movie.id).isEqualTo(2)
    }

    @Test
    fun getMoviesDetailsWithNotAvailableId() = runBlocking {
        val result = try {
            movieRepo.getMovieDetails(5)
        } catch (e: Exception) {
            e
        }
        Truth.assertThat(result).isInstanceOf(Resources.NotFoundException::class.java)
    }
}