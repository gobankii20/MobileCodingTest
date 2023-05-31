package com.vitavat.mobilecodingtest.view.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.vitavat.mobilecodingtest.extensions.buildLoading
import com.vitavat.mobilecodingtest.utils.SingleEvent

open class BaseFragment : Fragment() {

    private var loadingDialog: Dialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = view.context.buildLoading()
    }

    private fun startLoading() {
        loadingDialog?.show()
    }

    private fun stopLoading() {
        loadingDialog?.dismiss()
    }

    fun setLoadingView(stateLoading: LiveData<SingleEvent<Boolean>>) {
        stateLoading.observe(this) {
            it.consume()?.let { isLoading ->
                if (isLoading) {
                    startLoading()
                } else {
                    stopLoading()
                }
            }
        }
    }
}