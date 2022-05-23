package com.sayem.android.movieapp.base

/**
 * Created by Sayem on 5/19/2022
 */
interface UseCase<T, R> {
    suspend operator fun invoke(param : T):R
}