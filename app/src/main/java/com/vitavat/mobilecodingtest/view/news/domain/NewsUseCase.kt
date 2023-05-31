package com.vitavat.mobilecodingtest.view.news.domain

import com.vitavat.mobilecodingtest.BuildConfig
import com.vitavat.mobilecodingtest.data.model.body.NewsBody
import com.vitavat.mobilecodingtest.data.model.response.NewsResponse
import com.vitavat.mobilecodingtest.view.news.datasource.NewsRepository
import io.reactivex.Observable


interface NewsUseCaseImp {
    fun execute(newsBodyRequest: NewsBody): Observable<NewsResponse>
}

class NewsUseCase(
    private val newsRepository: NewsRepository
) : NewsUseCaseImp {

    override fun execute(newsBodyRequest: NewsBody): Observable<NewsResponse> {
        return newsRepository.getListNews(
            q = newsBodyRequest.q,
            from = newsBodyRequest.from,
            sortBy = newsBodyRequest.sortBy,
            apiKey = BuildConfig.API_KEY
        )
    }
}