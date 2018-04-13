package com.skyshi.skymvp

/**
 * Created by yusufaw on 2/15/18.
 */

interface BaseView {
    fun showWait()

    fun removeWait()

    fun onFailure(appErrorMessage: String)
}