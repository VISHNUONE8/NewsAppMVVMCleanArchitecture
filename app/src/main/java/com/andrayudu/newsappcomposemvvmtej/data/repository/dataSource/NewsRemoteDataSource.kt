package com.andrayudu.newsappcomposemvvmtej.data.repository.dataSource

import com.andrayudu.newsappcomposemvvmtej.data.model.ApiResponse
import retrofit2.Response

interface NewsRemoteDataSource {

    suspend fun getTopHeadlines(country:String,page:Int): Response<ApiResponse>
    suspend fun getTopHeadlinesUs(): Response<ApiResponse>
    suspend fun getSearchedNews(searchQuery:String,country:String,page:Int): Response<ApiResponse>
}