package com.dariwan.newsapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dariwan.newsapp.data.paging.NewsPagingSource
import com.dariwan.newsapp.data.remote.response.AllResponseNews
import com.dariwan.newsapp.data.remote.retrofit.ApiService

class NewsRepository(private val apiService: ApiService, private val context: Context) {

    fun getAllNewsList(): LiveData<PagingData<AllResponseNews.ListNews>>{
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                NewsPagingSource(apiService, context)
            }
        ).liveData
    }
}