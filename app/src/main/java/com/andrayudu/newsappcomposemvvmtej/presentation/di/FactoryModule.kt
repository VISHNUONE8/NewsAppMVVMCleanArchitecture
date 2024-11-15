package com.andrayudu.newsappcomposemvvmtej.presentation.di

import android.app.Application
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.DeleteSavedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetNewsHeadlinesUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetSavedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.GetSearchedNewsUseCase
import com.andrayudu.newsappcomposemvvmtej.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//class FactoryModule {
//    @Singleton
//    @Provides
//  fun provideNewsViewModelFactory(
//        application: Application,
//        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
//        getSearchedNewsUseCase: GetSearchedNewsUseCase,
//        saveNewsUseCase: SaveNewsUseCase,
//        getSavedNewsUseCase: GetSavedNewsUseCase,
//        deleteSavedNewsUseCase: DeleteSavedNewsUseCase
//  ): NewsViewModelFactory {
//      return NewsViewModelFactory(
//          application,
//          getNewsHeadlinesUseCase,
//          getSearchedNewsUseCase,
//          saveNewsUseCase,
//          getSavedNewsUseCase,
//          deleteSavedNewsUseCase
//      )
//  }
//
//}








