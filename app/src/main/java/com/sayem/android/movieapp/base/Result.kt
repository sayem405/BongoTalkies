package com.sayem.android.movieapp.base

import java.lang.Exception

/**
 * Created by Sayem on 5/18/2022
 */
sealed class Result<out T> {
    data class Success<out R>(val value: R): Result<R>()
    data class Error(val ex : Exception) : Result<Nothing>()
}
