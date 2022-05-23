package com.sayem.android.movieapp.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Sayem on 5/23/2022
 */
fun Date?.format(format: String, locale: Locale = Locale.US): String? {
    this ?: return null
    try {
        val sdf = SimpleDateFormat(format, locale)
        return sdf.format(this)
    } catch (ignored: Exception) {

    }
    return null
}