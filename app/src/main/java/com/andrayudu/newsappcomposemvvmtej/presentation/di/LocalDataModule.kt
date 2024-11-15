package com.andrayudu.newsappcomposemvvmtej.presentation.di

import com.andrayudu.newsappcomposemvvmtej.data.db.ArticleDAO
import com.andrayudu.newsappcomposemvvmtej.data.repository.dataSource.NewsLocalDataSource
import com.andrayudu.newsappcomposemvvmtej.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(articleDAO: ArticleDAO): NewsLocalDataSource {
      return NewsLocalDataSourceImpl(articleDAO)
    }

}













