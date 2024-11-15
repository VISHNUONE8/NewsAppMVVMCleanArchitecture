package com.andrayudu.newsappcomposemvvmtej.domain.repository

import com.andrayudu.newsappcomposemvvmtej.data.model.ApiResponse
import com.andrayudu.newsappcomposemvvmtej.data.model.Article
import com.andrayudu.newsappcomposemvvmtej.data.util.ResultState
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadlines(country:String,page:Int): ResultState<ApiResponse>

    suspend fun getUSNewsHeadlines(): ResultState<ApiResponse>

    suspend fun getSearchedNews(searchQuery:String,country:String,page:Int) : ResultState<ApiResponse>

    suspend fun saveNews(article: Article)

    suspend fun deleteNews(article: Article)

    // this function is not a suspend fun because it returns a data stream ,
    // we dont want it to pause this function and resume it
    fun getSavedNews() : Flow<List<Article>>

}