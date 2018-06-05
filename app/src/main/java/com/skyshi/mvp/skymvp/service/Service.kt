package com.skyshi.mvp.skymvp.service

import android.util.Log
import com.skyshi.skymvp.RxUtils
import com.skyshi.mvp.skymvp.model.ListProductResponse
import io.reactivex.Observable
import javax.inject.Inject

class Service @Inject constructor(private val networkService: NetworkService) {

    fun getListProduct(keywords: String, page: Int): Observable<ListProductResponse> {
        return networkService.getListProduct(keywords, page)
                .map {
                    Log.d("xxxxx", "haha")
                    it
                }
                .compose(RxUtils.applyObservableAsync())
    }
}