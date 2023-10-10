package com.dariwan.newsapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dariwan.newsapp.data.remote.response.AllResponseNews
import com.dariwan.newsapp.databinding.ItemVerticalBinding
import com.dariwan.newsapp.utils.Constant
import com.dariwan.newsapp.view.detail.DetailActivity

class NewsVerticalAdapter: PagingDataAdapter<AllResponseNews.ListNews, NewsVerticalAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    class NewsViewHolder(private val binding: ItemVerticalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(news: AllResponseNews.ListNews){
            binding.tvContent.text = news.content
            binding.tvName.text = news.title
            binding.tvAuthor.text = news.author
            binding.tvDate.text = news.publishedAt
            Glide.with(itemView.context)
                .load(news.urlToImage)
                .centerCrop()
                .skipMemoryCache(true)
                .into(binding.imgVerticalNews)

            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra(Constant.BUNDLE_NEWS, news)

                val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    itemView.context as Activity,
                    Pair(binding.imgVerticalNews, "photo"),
                    Pair(binding.tvName, "name"),
                    Pair(binding.tvAuthor, "author"),
                    Pair(binding.tvDate, "date"),
                    Pair(binding.tvContent, "content")
                )
                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }
    }

    override fun onBindViewHolder(holder: NewsVerticalAdapter.NewsViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null){
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsVerticalAdapter.NewsViewHolder {
        val binding = ItemVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AllResponseNews.ListNews>() {
            override fun areItemsTheSame(oldItem: AllResponseNews.ListNews, newItem: AllResponseNews.ListNews): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: AllResponseNews.ListNews, newItem: AllResponseNews.ListNews): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}