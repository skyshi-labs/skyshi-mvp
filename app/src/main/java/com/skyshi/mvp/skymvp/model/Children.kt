package com.skyshi.mvp.skymvp.model


data class Children(
    val id: Int,
    val name: String,
    val permalink: String,
    val icon_name: Any,
    val children: List<Any>,
    val count: Int
)