package com.andrayudu.newsappcomposemvvmtej.presentation.di

import android.content.Context
import com.andrayudu.newsappcomposemvvmtej.presentation.common.networkhelper.NetworkHelper
import com.andrayudu.newsappcomposemvvmtej.presentation.common.networkhelper.NetworkHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkHelper(
        @ApplicationContext context: Context
    ) : NetworkHelper {
        return NetworkHelperImpl(context)
    }

}