package com.vitavat.mobilecodingtest.view.news.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitavat.mobilecodingtest.data.model.response.NewsResponse
import com.vitavat.mobilecodingtest.databinding.ViewItemNewsBinding
import com.vitavat.mobilecodingtest.extensions.DateTimeFormatExt
import com.vitavat.mobilecodingtest.extensions.getFormatDate
import com.vitavat.mobilecodingtest.extensions.setImageView
import com.vitavat.mobilecodingtest.view.base.RecyclerviewAdapterDiffCallback

class NewsAdapter : ListAdapter<NewsResponse.Article, NewsAdapter.ViewHolder>(
    RecyclerviewAdapterDiffCallback()
) {

    var onItemClick: ((NewsResponse.Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val binding = ViewItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val articleModel = this.currentList[position]
        holder.bind(articleModel)
    }

    inner class ViewHolder(
        private val binding: ViewItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(newsResponse: NewsResponse.Article) = with(binding) {
            ivNews.setImageView(urlImage = newsResponse.urlToImage ?: "")
            tvTitle.text = newsResponse.title
            tvDescription.text = newsResponse.description
            tvCreateDate.text = "Updated: ${
                newsResponse.publishedAt?.getFormatDate(
                    inputFormat = DateTimeFormatExt.FORMAT_SERVER_DATE,
                    outputFormat = DateTimeFormatExt.FORMAT_DEFAULT_DATE
                )
            }"

            binding.root.setOnClickListener {
                onItemClick?.invoke(newsResponse)
            }
        }
    }
}