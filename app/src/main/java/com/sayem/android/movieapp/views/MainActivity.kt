package com.sayem.android.movieapp.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sayem.android.movieapp.R
import com.sayem.android.movieapp.base.SingleEventObserver
import com.sayem.android.movieapp.views.moviedetails.MovieDetailsFragment
import com.sayem.android.movieapp.views.movielist.MovieListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                MovieListFragment.newInstance(),
                MovieListFragment.TAG
            )
            .commit()

        viewModel.showMovieDetails.observe(this, SingleEventObserver {
            MovieDetailsFragment.newInstance(it).show(supportFragmentManager, MovieDetailsFragment.TAG)
        })
    }
}