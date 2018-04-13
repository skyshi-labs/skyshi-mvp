package com.skyshi.skymvp

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by yusufaw on 3/27/18.
 */

abstract class SkyRecyclerListener(private val linearLayoutManager: LinearLayoutManager): RecyclerView.OnScrollListener() {

    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var currentPage = 0
    private var visibleThreshold = 3
    private var firstVisibleItem: Int = 0
    private var loading = true
    private var previousTotal = 0

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        recyclerView?.let {
            visibleItemCount = it.childCount
            totalItemCount = linearLayoutManager.itemCount
            firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

            if (loading && totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }

            if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                currentPage++
                onLoadMore(currentPage)
                loading = true
            }
        }
    }


    fun reset() {
        previousTotal = 0
        loading = true
        visibleThreshold = 3
        firstVisibleItem = 0
        visibleItemCount = 0
        totalItemCount = 0
        currentPage = 0
    }

    abstract fun onLoadMore(currentPage: Int)
}