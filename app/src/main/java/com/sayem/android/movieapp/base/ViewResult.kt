package com.kernello.commonlib

/**
 * Created by sayem on 2/27/2019.
 */
sealed class ViewResult<out T : Any>{
    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading[data=$data]"
        }
    }



    data class Success<out T : Any>(val data: T) : ViewResult<T>()
    data class Loading<out T : Any>(val data: T?) : ViewResult<T>()
    data class Error(val exception: Exception) : ViewResult<Nothing>()
}
