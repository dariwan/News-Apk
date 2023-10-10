package com.dariwan.newsapp.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dariwan.newsapp.R
import com.dariwan.newsapp.data.remote.response.AllResponseNews
import com.dariwan.newsapp.databinding.ActivityDetailBinding
import com.dariwan.newsapp.utils.Constant

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var news: AllResponseNews.ListNews
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        news = intent.getParcelableExtra(Constant.BUNDLE_NEWS)!!
        setDataHorizontal()
        setDataVertical()
    }

    private fun setDataVertical() {
        binding.tvName.text = news.title
        binding.tvAuthor.text = news.author
        binding.tvDate.text = news.publishedAt
        binding.tvContent.text = news.content
        Glide.with(this)
            .load(news.urlToImage)
            .centerCrop()
            .skipMemoryCache(true)
            .into(binding.imgDetail)
    }

    private fun setDataHorizontal() {
        binding.tvName.text = news.title
        binding.tvAuthor.text = news.author
        binding.tvDate.text = news.publishedAt
        binding.tvContent.text = news.content
        Glide.with(this)
            .load(news.urlToImage)
            .centerCrop()
            .skipMemoryCache(true)
            .into(binding.imgDetail)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}