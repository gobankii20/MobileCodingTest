package com.vitavat.mobilecodingtest.view.news.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.vitavat.mobilecodingtest.R
import com.vitavat.mobilecodingtest.databinding.FragmentNewsBinding
import com.vitavat.mobilecodingtest.extensions.visibilityViewIconClose
import com.vitavat.mobilecodingtest.view.base.BaseFragment
import com.vitavat.mobilecodingtest.view.news.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment() {

    private val binding by lazy { FragmentNewsBinding.inflate(layoutInflater) }

    private val viewModel: NewsViewModel by viewModels()

    private val adapterNews: NewsAdapter by lazy { NewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
        onClickListener()
    }

    private fun initView() {
        setLoadingView(viewModel.loadingState)
        initToolbarView()
        recyclerViewListNews()
        onSearchView()
    }

    private fun initToolbarView() = with(binding) {
        icToolbarView.buttonLeft.visibility = View.INVISIBLE
        icToolbarView.tvBack.visibility = View.INVISIBLE
        icToolbarView.tvTitle.text = requireContext().resources.getString(R.string.title_news)
    }

    private fun onSearchView() {
        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val keyword = p0.toString()
                viewModel.requestFilterNews(keyword = keyword)
                binding.ivClose.visibilityViewIconClose(keyword = keyword)
            }
        })
    }

    private fun recyclerViewListNews() = with(binding) {
        rvListNews.apply {
            adapter = adapterNews
        }
    }

    private fun initViewModel() = with(viewModel) {
        requestNews()
        listNews.observe(viewLifecycleOwner) { newsResponse ->
            adapterNews.submitList(newsResponse)
        }

        messageError.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClickListener() = with(binding) {
        adapterNews.onItemClick = {
            val bundle = bundleOf(NEWS_DATA to it)
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_newsFragment_to_newsDetailFragment, bundle)
        }

        ivClose.setOnClickListener {
            binding.edtSearch.setText("")
            viewModel.requestFilterNews(keyword = "")
        }
    }

    companion object {
        const val NEWS_DATA = "NEWS_DATA"
    }
}