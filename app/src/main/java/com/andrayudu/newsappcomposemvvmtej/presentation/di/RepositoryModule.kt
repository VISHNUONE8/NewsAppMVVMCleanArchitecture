package com.andrayudu.newsappcomposemvvmtej.presentation.di

import com.andrayudu.newsappcomposemvvmtej.data.repository.dataSource.NewsLocalDataSource
import com.andrayudu.newsappcomposemvvmtej.data.repository.dataSource.NewsRemoteDataSource
import com.andrayudu.newsappcomposemvvmtej.domain.repository.NewsRepository
import com.andrayudu.newsappcomposemvvmtej.domain.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource,
    ): NewsRepository {
        return NewsRepositoryImpl(
            newsRemoteDataSource,
            newsLocalDataSource
        )
    }

}














