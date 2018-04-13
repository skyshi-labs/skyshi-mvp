package com.skyshi.skymvp

/**
 * Created by yusufaw on 3/9/18.
 */

interface Presenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}