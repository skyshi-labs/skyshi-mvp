package com.skyshi.skymvp

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by yusufaw on 3/9/18.
 */

open class BasePresenter2<T: BaseView>: Presenter<T> {

    var view: T? = null
    lateinit var compositeDisposable: CompositeDisposable
    override fun attachView(view: T) {
        this.view = view
        compositeDisposable = CompositeDisposable()
    }

    override fun detachView() {
        view = null
        compositeDisposable.dispose()
    }

    private val isViewAttached: Boolean
        get() = view != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before"
            + " requesting data to the Presenter")

}