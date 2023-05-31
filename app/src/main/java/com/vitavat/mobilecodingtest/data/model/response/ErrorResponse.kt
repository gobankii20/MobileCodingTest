package com.vitavat.mobilecodingtest.data.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("message")
    val message: String? = null,
)
