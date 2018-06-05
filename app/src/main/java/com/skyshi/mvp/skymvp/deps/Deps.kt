package com.skyshi.mvp.skymvp.deps

import com.skyshi.mvp.skymvp.MainActivity
import com.skyshi.mvp.skymvp.base.BaseApp
import com.skyshi.mvp.skymvp.service.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface Deps {
    fun inject(baseApp: BaseApp)
    fun inject(mainActivity: MainActivity)
}