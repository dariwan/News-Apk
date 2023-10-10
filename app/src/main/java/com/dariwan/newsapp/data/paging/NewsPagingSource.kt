package com.dariwan.newsapp.data.paging

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dariwan.newsapp.data.remote.response.AllResponseNews
import com.dariwan.newsapp.data.remote.retrofit.ApiService
import java.lang.Exception

class NewsPagingSource(private val apiService: ApiService, private val context: Context):PagingSource<Int, AllResponseNews.ListNews>() {
    override fun getRefreshKey(state: PagingState<Int, AllResponseNews.ListNews>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllResponseNews.ListNews> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getAllNews(position, params.loadSize).articles.toList()

            LoadResult.Page(
                data = responseData,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position -1,
                nextKey =  if (responseData.isNullOrEmpty()) null else position +1
            )
        } catch (e : Exception){
            return LoadResult.Error(e)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}