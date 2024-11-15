package com.andrayudu.newsappcomposemvvmtej.data.repository.dataSourceImpl

import com.andrayudu.newsappcomposemvvmtej.data.db.ArticleDAO
import com.andrayudu.newsappcomposemvvmtej.data.model.Article
import com.andrayudu.newsappcomposemvvmtej.data.repository.dataSource.NewsLocalDataSource

import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
) : NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDAO.insert(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDAO.getAllArticles()
    }

    override suspend fun deleteArticlesFromDB(article: Article) {
        articleDAO.deleteArticle(article)
    }
}