package com.vitavat.mobilecodingtest.data.remote

import com.vitavat.mobilecodingtest.data.model.response.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNewsService {

    @GET("everything")
    fun getListNews(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Observable<NewsResponse>

}