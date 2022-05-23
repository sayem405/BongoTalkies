package com.sayem.android.movieapp.data.network

import com.sayem.android.movieapp.data.network.models.MoviesResult
import com.sayem.android.movieapp.data.network.models.ServerMovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Sayem on 5/18/2022
 */
interface MovieService {
    @GET("/3/movie/top_rated?api_key=c37d3b40004717511adb2c1fbb15eda4&language=en-US")
    suspend fun getMovies(@Query("page") page : Int) : MoviesResult

    @GET("/3/movie/{id}?api_key=c37d3b40004717511adb2c1fbb15eda4&language=en-US")
    suspend fun getMovie(@Path("id") id : Int) : ServerMovieDetails
}