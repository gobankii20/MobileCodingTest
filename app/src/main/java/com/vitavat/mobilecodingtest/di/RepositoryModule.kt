package com.vitavat.mobilecodingtest.di

import com.vitavat.mobilecodingtest.data.remote.ApiNewsService
import com.vitavat.mobilecodingtest.view.news.datasource.NewsRepositoryImpl
import com.vitavat.mobilecodingtest.view.news.datasource.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsNetwork(apiNewsService: ApiNewsService): NewsRepository {
        return NewsRepositoryImpl(apiNewsService)
    }

}
