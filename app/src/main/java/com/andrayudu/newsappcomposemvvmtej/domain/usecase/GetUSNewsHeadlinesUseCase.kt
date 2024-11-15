package com.andrayudu.newsappcomposemvvmtej.domain.usecase

import com.andrayudu.newsappcomposemvvmtej.data.model.ApiResponse
import com.andrayudu.newsappcomposemvvmtej.data.util.ResultState
import com.andrayudu.newsappcomposemvvmtej.domain.repository.NewsRepository
import javax.inject.Inject


class GetUSNewsHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun execute(): ResultState<ApiResponse> {
        return newsRepository.getUSNewsHeadlines()
    }
}