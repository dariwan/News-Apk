package com.dariwan.newsapp.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class AllResponseNews (
    @SerializedName("status")
    val status: String,

    @SerializedName("articles")
    val articles: List<ListNews>
){
    @Parcelize
    data class ListNews(
        @SerializedName("author")
        val author: String,

        @SerializedName("title")
        val title: String,

        @SerializedName("url")
        val url: String,

        @SerializedName("urlToImage")
        val urlToImage: String,

        @SerializedName("publishedAt")
        val publishedAt: String,

        @SerializedName("content")
        val content: String,
    ):Parcelable
}