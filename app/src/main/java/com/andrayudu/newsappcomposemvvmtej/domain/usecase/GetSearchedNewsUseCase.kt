package com.andrayudu.newsappcomposemvvmtej.domain.usecase

import com.andrayudu.newsappcomposemvvmtej.data.model.ApiResponse
import com.andrayudu.newsappcomposemvvmtej.data.util.ResultState
import com.andrayudu.newsappcomposemvvmtej.domain.repository.NewsRepository
import javax.inject.Inject

class GetSearchedNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery: String,country:String,page:Int): ResultState<ApiResponse> {
        return newsRepository.getSearchedNews(searchQuery,country, page)
    }
}