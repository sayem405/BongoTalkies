package com.sayem.android.movieapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Sayem on 5/18/2022
 */

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}