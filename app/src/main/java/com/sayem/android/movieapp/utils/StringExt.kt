package com.sayem.android.movieapp.utils

/**
 * Created by Sayem on 5/23/2022
 */

fun List<String>?.getComaSeparatedString(): String? {
    if (this == null || this.isEmpty()) return null
    val s = StringBuilder(this[0])
    for (i in 1 until this.size) {
        s.append(",").append(this[i])
    }
    return s.toString()
}