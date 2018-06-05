package com.skyshi.mvp.skymvp.service

import android.app.Application
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(private val application: Application, internal var cacheFile: File) {

    @Provides
    @Singleton
    internal fun provideCall(): Retrofit {
//        var cache: Cache? = null
//        cache = Cache(cacheFile, (10 * 1024 * 1024).toLong())


        val httpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

//        val errorInterceptor = ErrorInterceptor(NetManager(application.applicationContext))

        val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor { chain ->
//                    val original = chain.request()
//
//                    val request = original.newBuilder()
//                            .header("Content-Type", "application/json")
//                            .removeHeader("Pragma")
//                            .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
//                            .build()
//
//                    val response = chain.proceed(request)
//                    response.cacheResponse()
//                    response
//                }
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(httpLoggingInterceptor)
//                .addInterceptor(errorInterceptor)
//                .addInterceptor(ChuckInterceptor(application.applicationContext))
//                .cache(cache)
                .build()

        return Retrofit.Builder()
                .baseUrl("https://api.bukalapak.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun providesService(networkService: NetworkService): Service {
        return Service(networkService)
    }
}