package com.andrayudu.newsappcomposemvvmtej.data.repository.dataSourceImpl

import com.andrayudu.newsappcomposemvvmtej.data.api.NewsApiService
import com.andrayudu.newsappcomposemvvmtej.data.model.ApiResponse
import com.andrayudu.newsappcomposemvvmtej.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService,
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String,page: Int): Response<ApiResponse> {
        return newsApiService.getTopHeadlines(country, page)
    }

    override suspend fun getTopHeadlinesUs(): Response<ApiResponse> {
        return newsApiService.getTopHeadlinesUS()
    }


    override suspend fun getSearchedNews(
        searchQuery: String,
        country: String,
        page: Int
    ): Response<ApiResponse> {
        return  newsApiService.getSearchedTopHeadlines(searchQuery,country, page)
    }

}