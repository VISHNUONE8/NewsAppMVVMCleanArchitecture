package com.andrayudu.newsappcomposemvvmtej.presentation.di

import com.andrayudu.newsappcomposemvvmtej.domain.repository.NewsRepository
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.DeleteSavedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetNewsHeadlinesUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetSavedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetSearchedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetUSNewsHeadlinesUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
   @Singleton
   @Provides
   fun provideGetNewsheadLinesUseCase(
       newsRepository: NewsRepository
   ): GetNewsHeadlinesUseCase {
      return GetNewsHeadlinesUseCase(newsRepository)
   }
   @Singleton
   @Provides
   fun provideGetUSNewsheadLinesUseCase(
       newsRepository: NewsRepository
   ): GetUSNewsHeadlinesUseCase {
      return GetUSNewsHeadlinesUseCase(newsRepository)
   }

   @Singleton
   @Provides
   fun provideGetSearchedNewsUseCase(
      newsRepository: NewsRepository
   ): GetSearchedNewsUseCase {
      return GetSearchedNewsUseCase(newsRepository)
   }

   @Singleton
   @Provides
   fun provideSaveNewsUseCase(
      newsRepository: NewsRepository
   ): SaveNewsUseCase {
      return SaveNewsUseCase(newsRepository)
   }

   @Singleton
   @Provides
   fun provideGetSavedNewsUseCase(
      newsRepository: NewsRepository
   ): GetSavedNewsUseCase {
      return GetSavedNewsUseCase(newsRepository)
   }

   @Singleton
   @Provides
   fun provideDeleteSavedNewsUseCase(
      newsRepository: NewsRepository
   ): DeleteSavedNewsUseCase {
      return DeleteSavedNewsUseCase(newsRepository)
   }
}


















