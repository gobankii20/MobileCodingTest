package com.vitavat.mobilecodingtest.di

import com.google.gson.GsonBuilder
import com.vitavat.mobilecodingtest.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
    ): Retrofit {
        val gsonBuilder = GsonBuilder().setLenient()
        val gson = gsonBuilder.serializeNulls().create()
        val gsonConverterFactory = GsonConverterFactory.create(gson)
        val rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(
            Schedulers.io()
        )

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(provideOkHttpBuilder().build())
            .build()
    }

    private fun provideOkHttpBuilder(
    ): OkHttpClient.Builder {
        val okHttpBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            logging.level = (HttpLoggingInterceptor.Level.BODY)
        }


        okHttpBuilder.apply {
            addInterceptor {
                val original: Request = it.request()
                val request: Request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .build()
                it.proceed(request)
            }
            addInterceptor(logging)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        }
        return okHttpBuilder
    }

    companion object {
        private const val TIMEOUT = 40L
    }
}













