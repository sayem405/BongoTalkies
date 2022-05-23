package com.sayem.android.movieapp

import android.content.res.Resources
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.sayem.android.movieapp.base.Result
import com.sayem.android.movieapp.data.MovieRepo
import com.sayem.android.movieapp.data.MovieRepoImpl
import com.sayem.android.movieapp.domain.GetMovies
import com.sayem.android.movieapp.fakes.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Sayem on 5/23/2022
 */
@SmallTest
@ExperimentalCoroutinesApi
class GetMovieListTest {

    lateinit var getMoviesUseCase: GetMovies

    @Before
    fun setup() {
        val movieRepo = FakeMovieRepo(3, movieList)
        getMoviesUseCase = GetMovies(movieRepo)
    }

    @Test
    fun getFirstPageMovies() = runBlocking {
        val movies = getMoviesUseCase(1) as Result.Success
        Truth.assertThat(movies.value.size).isEqualTo(3)
    }


    @Test
    fun getSecondPageMovies() = runBlocking {
        val movies = getMoviesUseCase(1) as Result.Success
        Truth.assertThat(movies.value.size).isEqualTo(1)
    }
}