package com.vitavat.mobilecodingtest.view.news.datasource

import com.vitavat.mobilecodingtest.data.model.response.NewsResponse
import com.vitavat.mobilecodingtest.data.remote.ApiNewsService
import io.reactivex.Observable

interface NewsRepository {
    fun getListNews(
        q: String,
        from: String,
        sortBy: String,
        apiKey: String
    ): Observable<NewsResponse>
}

class NewsRepositoryImpl(
    val apiNewsService: ApiNewsService
):NewsRepository {

    override fun getListNews(
        q: String,
        from: String,
        sortBy: String,
        apiKey: String
    ): Observable<NewsResponse> {
        return apiNewsService.getListNews(
            q = q,
            from = from,
            sortBy = from,
            apiKey = apiKey
        )
    }
}