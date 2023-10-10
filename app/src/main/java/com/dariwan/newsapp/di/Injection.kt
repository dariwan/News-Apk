package com.dariwan.newsapp.di

import android.content.Context
import com.dariwan.newsapp.data.remote.retrofit.ApiConfig
import com.dariwan.newsapp.data.repository.NewsRepository

object Injection {
    fun provideRepository(context: Context): NewsRepository{
        val apiService = ApiConfig.getApiService()
        return NewsRepository(apiService, context)
    }
}