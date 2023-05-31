package com.vitavat.mobilecodingtest.di

import com.vitavat.mobilecodingtest.view.news.datasource.NewsRepository
import com.vitavat.mobilecodingtest.view.news.domain.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideNewsUseCase(
        newsRepository: NewsRepository
    ): NewsUseCase {
        return NewsUseCase(newsRepository)
    }
}
