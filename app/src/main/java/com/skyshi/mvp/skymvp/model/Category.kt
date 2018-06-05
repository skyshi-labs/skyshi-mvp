package com.skyshi.mvp.skymvp.model

import com.skyshi.mvp.skymvp.model.Children


data class Category(
    val name: String,
    val id: Int,
    val count: Int,
    val permalink: String,
    val children: List<Children>
)