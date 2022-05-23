package com.sayem.android.movieapp.base

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Sayem on 5/23/2022
 */
abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener {
    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold =
        5 // The minimum amount of items to have below your current scroll position before loading more.
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    private var currentPage = 1
    private var mLayoutManager: RecyclerView.LayoutManager
    private var isUseLinearLayoutManager = false
    private var isUseGridLayoutManager = false

    constructor(linearLayoutManager: LinearLayoutManager) {
        mLayoutManager = linearLayoutManager
        isUseLinearLayoutManager = true
    }

    constructor(gridLayoutManager: GridLayoutManager) {
        mLayoutManager = gridLayoutManager
        isUseGridLayoutManager = true
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = mLayoutManager.itemCount
        if (isUseLinearLayoutManager && mLayoutManager is LinearLayoutManager) {
            firstVisibleItem =
                (mLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        }
        if (isUseGridLayoutManager && mLayoutManager is GridLayoutManager) {
            firstVisibleItem = (mLayoutManager as GridLayoutManager).findFirstVisibleItemPosition()
        }
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount
            <= firstVisibleItem + visibleThreshold
        ) {
            // End has been reached

            // Do something
            currentPage++
            onLoadMore(currentPage)
            loading = true
        }
    }

    abstract fun onLoadMore(currentPage: Int)

    companion object {
        var TAG = EndlessRecyclerOnScrollListener::class.java.simpleName
    }
}