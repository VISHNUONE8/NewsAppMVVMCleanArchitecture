package com.andrayudu.newsappcomposemvvmtej.domain.usecase

import com.andrayudu.newsappcomposemvvmtej.data.model.Article
import com.andrayudu.newsappcomposemvvmtej.domain.repository.NewsRepository
import javax.inject.Inject

class SaveNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.saveNews(article)
}