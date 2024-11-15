package com.andrayudu.newsappcomposemvvmtej.presentation.di

import com.andrayudu.newsappcomposemvvmtej.data.api.NewsApiService
import com.andrayudu.newsappcomposemvvmtej.data.repository.dataSource.NewsRemoteDataSource
import com.andrayudu.newsappcomposemvvmtej.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: NewsApiService
    ): NewsRemoteDataSource {
       return NewsRemoteDataSourceImpl(newsAPIService)
    }

}












