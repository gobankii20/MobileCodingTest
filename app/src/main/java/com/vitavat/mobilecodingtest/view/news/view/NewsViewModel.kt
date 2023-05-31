package com.vitavat.mobilecodingtest.view.news.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vitavat.mobilecodingtest.data.model.body.NewsBody
import com.vitavat.mobilecodingtest.data.model.response.NewsResponse
import com.vitavat.mobilecodingtest.data.remote.ApiResponse
import com.vitavat.mobilecodingtest.view.base.BaseViewModel
import com.vitavat.mobilecodingtest.view.news.domain.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : BaseViewModel() {


    private val _listNews = MutableLiveData<List<NewsResponse.Article>>()
    val listNews: LiveData<List<NewsResponse.Article>> = _listNews

    private val _listSearchTemp = MutableLiveData<List<NewsResponse.Article>>()

    private val _messageError = MutableLiveData<String>()
    val messageError: LiveData<String> = _messageError

    fun requestNews() {
        newsUseCase.execute(
            newsBodyRequest = NewsBody(
                q = "tesla",
                from = "2023-04-30",
                sortBy = "publishedAt"
            )
        ).observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribeBy(
                onNext = { response ->
                    _listNews.postValue(response.articles)
                    _listSearchTemp.postValue(response.articles)
                },
                onError = {
                    _messageError.postValue(ApiResponse.onErrorResponseServer(it))
                }
            )
            .addTo(disposable)
    }

    fun requestFilterNews(keyword: String) {
        if (keyword.isNotEmpty()) {
            val listFilter = _listSearchTemp.value?.filter { it.title?.contains(keyword) == true }
            listFilter?.let {
                _listNews.postValue(it)
            }
        } else {
            _listNews.postValue(_listSearchTemp.value)
        }

    }
}