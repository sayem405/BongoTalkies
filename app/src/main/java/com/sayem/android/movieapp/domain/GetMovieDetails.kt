package com.sayem.android.movieapp.domain

import android.util.Log
import com.sayem.android.movieapp.base.AppDispatcher
import com.sayem.android.movieapp.base.Result
import com.sayem.android.movieapp.base.UseCase
import com.sayem.android.movieapp.data.Movie
import com.sayem.android.movieapp.data.MovieRepo
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Sayem on 5/23/2022
 */

class GetMovieDetails @Inject constructor(
    private val movieRepo: MovieRepo
) : UseCase<Int, Result<Movie>> {
    override suspend fun invoke(param: Int): Result<Movie> = withContext(AppDispatcher.io) {
        try {
            val result = movieRepo.getMovieDetails(param)
            Result.Success(result)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            Result.Error(e)
        }
    }

    companion object {
        const val TAG = "GetMovieDetails"
    }
}