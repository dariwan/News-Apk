package com.dariwan.newsapp.view.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dariwan.newsapp.data.remote.response.AllResponseNews
import com.dariwan.newsapp.data.repository.NewsRepository
import com.dariwan.newsapp.di.Injection

class MainFragmentViewModel(newsRepository: NewsRepository, context: Context): ViewModel() {

    val news : LiveData<PagingData<AllResponseNews.ListNews>> = newsRepository.getAllNewsList().cachedIn(viewModelScope)

    class MainFragmentFactory(private val context: Context): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainFragmentViewModel(Injection.provideRepository(context), context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}