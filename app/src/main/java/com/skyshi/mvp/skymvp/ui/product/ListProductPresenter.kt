package com.skyshi.mvp.skymvp.ui.product

import android.util.Log
import com.skyshi.mvp.skymvp.service.Service
import com.skyshi.skymvp.BasePresenter2
import com.skyshi.skymvp.BaseView
import com.skyshi.mvp.skymvp.model.ListProductResponse
import javax.inject.Inject

class ListProductPresenter @Inject constructor(private val service: Service) : BasePresenter2<ListProductPresenter.ListProductView>() {

    fun getLisProduct(page: Int = 1, keywords: String = "") {
        view?.showWait()
        service.getListProduct("sepeda", 1)
                .subscribe({
                    Log.d("xxx", "hehe : " + it.products.size)
                    view?.onListProductSuccess(it)
                    view?.removeWait()
                }, {
                    view?.removeWait()
                    view?.onFailure(it.message.toString())
                })
    }

    interface ListProductView: BaseView {
        fun onListProductSuccess(listProductResponse: ListProductResponse)
    }
}