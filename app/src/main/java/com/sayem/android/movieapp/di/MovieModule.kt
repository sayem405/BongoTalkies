package com.sayem.android.movieapp.di

import com.sayem.android.movieapp.data.MovieRepo
import com.sayem.android.movieapp.data.MovieRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Sayem on 5/18/2022
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MovieModule {
    @Binds
    abstract fun bindMovieRepo(
        movieRepoImpl: MovieRepoImpl
    ): MovieRepo
}