package com.sayem.android.movieapp.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Sayem on 5/21/2022
 */
object AppDispatcher {
    val main : CoroutineDispatcher = Dispatchers.Main
    val io = Dispatchers.IO
    val worker : CoroutineDispatcher = Dispatchers.Default
}