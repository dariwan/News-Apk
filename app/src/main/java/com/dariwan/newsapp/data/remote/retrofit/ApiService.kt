package com.dariwan.newsapp.data.remote.retrofit

import com.dariwan.newsapp.BuildConfig
import com.dariwan.newsapp.data.remote.response.AllResponseNews
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?country=us&apiKey=${BuildConfig.API_KEY}")
    suspend fun getAllNews(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): AllResponseNews
}