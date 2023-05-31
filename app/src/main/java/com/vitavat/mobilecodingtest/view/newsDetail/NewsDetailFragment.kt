package com.vitavat.mobilecodingtest.view.newsDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vitavat.mobilecodingtest.data.model.response.NewsResponse
import com.vitavat.mobilecodingtest.databinding.FragmentNewsDetailBinding
import com.vitavat.mobilecodingtest.extensions.DateTimeFormatExt
import com.vitavat.mobilecodingtest.extensions.getFormatDate
import com.vitavat.mobilecodingtest.extensions.parcelable
import com.vitavat.mobilecodingtest.extensions.setImageView
import com.vitavat.mobilecodingtest.view.news.view.NewsFragment

class NewsDetailFragment : Fragment() {

    private val binding by lazy { FragmentNewsDetailBinding.inflate(layoutInflater) }

    private var newsArgs: NewsResponse.Article? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getIntentData()
        initView()
    }

    private fun getIntentData() {
        newsArgs = arguments?.parcelable(NewsFragment.NEWS_DATA)
    }

    private fun initView() {
        bindDataNewsDetail()
        onClickListener()
    }

    private fun bindDataNewsDetail() = with(binding) {
        ivNews.setImageView(urlImage = newsArgs?.urlToImage ?: "")
        tvTitle.text = newsArgs?.title
        tvDescription.text = newsArgs?.description
        tvCreateDate.text = newsArgs?.publishedAt?.getFormatDate(
            inputFormat = DateTimeFormatExt.FORMAT_SERVER_DATE,
            outputFormat = DateTimeFormatExt.FORMAT_DEFAULT_DATE
        )
    }

    private fun onClickListener() = with(binding) {
        icToolbarView.buttonLeft.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}