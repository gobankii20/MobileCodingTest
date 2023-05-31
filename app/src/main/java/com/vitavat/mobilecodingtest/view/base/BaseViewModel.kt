package com.vitavat.mobilecodingtest.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vitavat.mobilecodingtest.utils.SingleEvent
import io.reactivex.disposables.CompositeDisposable

interface LoadingStateViewModel {
    val loadingState: LiveData<SingleEvent<Boolean>>

    fun showLoading()
    fun hideLoading()
}

open class BaseViewModel : ViewModel(), LoadingStateViewModel {

    protected val disposable = CompositeDisposable()

    private val _loadingState = MutableLiveData<SingleEvent<Boolean>>()
    override val loadingState: LiveData<SingleEvent<Boolean>> = _loadingState

    override fun showLoading() {
        _loadingState.postValue(SingleEvent(true))
    }

    override fun hideLoading() {
        _loadingState.postValue(SingleEvent(false))
    }
}
