package com.sayem.android.movieapp.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kernello.commonlib.SingleEvent
import com.sayem.android.movieapp.domain.Movie
import com.sayem.android.movieapp.base.Result
import com.sayem.android.movieapp.domain.GetMovieDetails
import com.sayem.android.movieapp.domain.GetMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Sayem on 5/18/2022
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovies: GetMovies,
    private val getMovieDetails: GetMovieDetails
) : ViewModel() {

    private var pageIndex = 1
    val movieList = MutableLiveData<ArrayList<Movie>>()
    val showMovieDetails = MutableLiveData<SingleEvent<Movie>>()
    val movieDetailsResult = MutableLiveData<Result<Movie>>()
    val movieListError = MutableLiveData<Exception?>()

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            when (val result = getMovies(pageIndex)) {
                is Result.Error -> {
                    movieListError.value = result.ex
                }
                is Result.Success -> {
                    movieList.value = ArrayList<Movie>().apply {
                        movieList.value?.let { addAll(it) }
                        addAll(result.value)
                    }
                    pageIndex++
                }
            }
        }
    }

    fun retrieveMore() {
        getMovies()
    }

    fun movieItemClicked(data: Movie) {
        showMovieDetails.value = SingleEvent(data)
    }

    fun getMovieDetailsInformation(id : Int) {
        viewModelScope.launch {
            movieDetailsResult.value = getMovieDetails(id)!!
        }
    }

    fun refresh() {
        pageIndex = 1
        movieList.value = ArrayList()
        movieListError.value = null
        getMovies()
    }

}