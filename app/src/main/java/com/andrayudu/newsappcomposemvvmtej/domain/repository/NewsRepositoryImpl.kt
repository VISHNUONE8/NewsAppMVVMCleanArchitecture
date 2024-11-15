package com.andrayudu.newsappcomposemvvmtej.domain.repository

import com.andrayudu.newsappcomposemvvmtej.data.model.ApiResponse
import com.andrayudu.newsappcomposemvvmtej.data.model.Article
import com.andrayudu.newsappcomposemvvmtej.data.repository.dataSource.NewsLocalDataSource
import com.andrayudu.newsappcomposemvvmtej.data.repository.dataSource.NewsRemoteDataSource
import com.andrayudu.newsappcomposemvvmtej.data.util.ResultState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    private fun responseToResultState(response: Response<ApiResponse>):ResultState<ApiResponse>{
        if (response.isSuccessful){
            response.body()?.let { result->
                return ResultState.Success(result)
            }
        }
        return ResultState.Error(message = response.message())
    }
    override suspend fun getNewsHeadlines(country:String,page:Int): ResultState<ApiResponse> {
        return responseToResultState(newsRemoteDataSource.getTopHeadlines(country,page))
    }

    override suspend fun getUSNewsHeadlines(): ResultState<ApiResponse> {
        return responseToResultState(newsRemoteDataSource.getTopHeadlinesUs())
    }

    override suspend fun getSearchedNews(searchQuery: String,country:String,page:Int): ResultState<ApiResponse> {
        return responseToResultState(
            newsRemoteDataSource.getSearchedNews(searchQuery,country, page)
        )
    }

    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.saveArticleToDB(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDataSource.deleteArticlesFromDB(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return  newsLocalDataSource.getSavedArticles()
    }
}