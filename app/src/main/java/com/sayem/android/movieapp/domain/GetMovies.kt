package com.sayem.android.movieapp.domain

import android.util.Log
import com.sayem.android.movieapp.data.MovieRepo
import com.sayem.android.movieapp.base.Result
import com.sayem.android.movieapp.base.AppDispatcher
import com.sayem.android.movieapp.base.UseCase
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Sayem on 5/19/2022
 */
class GetMovies @Inject constructor(
    private val movieRepo: MovieRepo,
) : UseCase<Int, Result<ArrayList<Movie>>> {
    override suspend fun invoke(param: Int): Result<ArrayList<Movie>> =
        withContext(AppDispatcher.io) {
            try {
                val result = movieRepo.getMovieList(param)
                Result.Success(result)
            } catch (ex: Exception) {
                Log.e(TAG, ex.toString())
                Result.Error(ex)
            }
        }

    companion object {
        const val TAG = "GetMovies"
    }
}