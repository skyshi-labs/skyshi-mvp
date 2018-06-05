package com.skyshi.mvp.skymvp.service

import com.skyshi.mvp.skymvp.model.ListProductResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("/v2/products.json")
    fun getListProduct(@Query("keywords") kewords: String,
                       @Query("p") page: Int
                       ): Observable<ListProductResponse>
}