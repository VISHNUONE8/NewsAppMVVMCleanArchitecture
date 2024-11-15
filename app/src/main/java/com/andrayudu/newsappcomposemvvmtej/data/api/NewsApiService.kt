package com.andrayudu.newsappcomposemvvmtej.data.api

import com.andrayudu.newsappcomposemvvmtej.data.model.ApiResponse
import com.andrayudu.newsappcomposemvvmtej.data.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String = Const.API_KEY
    ):Response<ApiResponse>

    @GET("v2/top-headlines")
    suspend fun getTopHeadlinesUS(
        @Query("country")
        country: String = "us",
        @Query("apiKey")
        apiKey:String = Const.API_KEY
    ):Response<ApiResponse>



    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("q")
        searchQuery:String,
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String = Const.API_KEY
    ):Response<ApiResponse>
}