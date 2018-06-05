package com.skyshi.mvp.skymvp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.skyshi.mvp.skymvp.deps.DaggerDeps
import com.skyshi.mvp.skymvp.deps.Deps
import com.skyshi.mvp.skymvp.service.NetworkModule
import java.io.File

open class BaseApp: AppCompatActivity() {
    lateinit var deps: Deps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cacheFile = File(cacheDir, "responses")

        deps = DaggerDeps
                .builder()
                .networkModule(NetworkModule(application, cacheFile))
                .build()
        deps.inject(this)

    }
}