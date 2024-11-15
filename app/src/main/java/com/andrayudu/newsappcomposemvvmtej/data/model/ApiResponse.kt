package com.andrayudu.newsappcomposemvvmtej.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    // note here @serialized has been added in #168 lecture but we have ignored it.
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)