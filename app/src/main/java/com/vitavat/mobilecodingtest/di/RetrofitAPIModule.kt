package com.vitavat.mobilecodingtest.di

import com.vitavat.mobilecodingtest.data.remote.ApiNewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RetrofitAPIModule {

    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit): ApiNewsService {
        return retrofit.create(ApiNewsService::class.java)
    }
}
