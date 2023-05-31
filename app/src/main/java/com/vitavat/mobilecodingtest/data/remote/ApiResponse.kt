package com.vitavat.mobilecodingtest.data.remote

import com.google.gson.Gson
import com.vitavat.mobilecodingtest.data.local.Constants
import com.vitavat.mobilecodingtest.data.model.response.ErrorResponse
import java.net.SocketTimeoutException

object ApiResponse {

    private val mGson = Gson()

    fun onErrorResponseServer(e: Throwable): String {
        val mMessageError: String = when (e) {
            is retrofit2.HttpException -> {
                val responseBody = (e).response()!!
                if (responseBody.code() == 401) {
                    "401"
                } else if (responseBody.code() == 413) {
                    responseBody.errorBody()?.string() ?: ""
                } else if (responseBody.code() == 403) {
                    "error 403"
                } else {
                    val dataMessage = responseBody.errorBody()?.string()
                    deseRializeObject(dataMessage)
                }
            }
            is SocketTimeoutException -> {
                Constants.MESSAGE_TIME_OUT
            }
            else -> {
                val responseBody = e.message ?: ""
                if (responseBody.contains("No address associated with hostname")) {
                    Constants.MESSAGE_NO_INTERNET
                } else
                    responseBody
            }
        }
        return mMessageError
    }

    private fun deseRializeObject(errorString: String?): String {
        return try {
            return mGson.fromJson(errorString, ErrorResponse::class.java).message ?: ""
        } catch (e: Exception) {
            e.message.toString()
        }
    }
}
