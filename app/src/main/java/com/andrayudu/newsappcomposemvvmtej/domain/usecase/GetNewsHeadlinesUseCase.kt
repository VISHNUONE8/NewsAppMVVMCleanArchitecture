package com.andrayudu.newsappcomposemvvmtej.domain.usecase

import com.andrayudu.newsappcomposemvvmtej.data.model.ApiResponse
import com.andrayudu.newsappcomposemvvmtej.data.util.ResultState
import com.andrayudu.newsappcomposemvvmtej.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun execute(country:String,page:Int): ResultState<ApiResponse> {
        return newsRepository.getNewsHeadlines(country, page)
    }
}