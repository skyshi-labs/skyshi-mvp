package com.skyshi.mvp.skymvp.model


data class ListProductResponse(
        val status: String,
        val promo_due: Any,
        val tag_page: Any,
        val banner: Any,
        val products: List<Product>,
        val categories: List<Category>,
        val message: Any,
        val facets: List<Any>,
        val labels: Any,
        val related_keywords: Any,
        val recommended_products: List<Any>,
        val product_deeplink: Any
)