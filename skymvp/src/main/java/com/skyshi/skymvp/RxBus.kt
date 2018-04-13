package com.skyshi.skymvp

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


/**
 * Created by yusufaw on 3/11/18.
 */

class RxBus {

    private val bus = PublishSubject.create<Any>()

    fun <T> register(eventClass: Class<T>): Observable<T> {
        return bus.filter({ it.javaClass == eventClass } ).map { it as T }
    }

    fun post(event: Any) {
        bus.onNext(event)
    }

    companion object {
        val instance = RxBus()
    }
}