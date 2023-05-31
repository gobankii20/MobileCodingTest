package com.vitavat.mobilecodingtest.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) {
    @Parcelize
    data class Article(
        @SerializedName("author")
        val author: String? = null,
        @SerializedName("content")
        val content: String? = null,
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("publishedAt")
        val publishedAt: String? = null,
        @SerializedName("source")
        val source: Source,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("url")
        val url: String? = null,
        @SerializedName("urlToImage")
        val urlToImage: String? = null
    ) : Parcelable {
        @Parcelize
        data class Source(
            @SerializedName("id")
            val id: String? = null,
            @SerializedName("name")
            val name: String? = null
        ) : Parcelable
    }
}