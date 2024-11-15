package com.andrayudu.newsappcomposemvvmtej.presentation.di

import com.andrayudu.newsappcomposemvvmtej.data.api.NewsApiService
import com.andrayudu.newsappcomposemvvmtej.data.util.Const

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl("https://newsapi.org")
             .build()
    }

    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit):NewsApiService{
        return retrofit.create(NewsApiService::class.java)
    }

    @ApiKey
    @Provides
    fun provideApiKey(): String = Const.API_KEY



}













