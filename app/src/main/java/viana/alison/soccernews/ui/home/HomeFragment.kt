package viana.alison.soccernews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import viana.alison.soccernews.databinding.FragmentHomeBinding
import viana.alison.soccernews.presentation.HomeViewModel
import viana.alison.soccernews.ui.adapters.NewsListAdapter

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val adapter by lazy { NewsListAdapter() }
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.rvNews.adapter = adapter

        getNewsList()
        setListeners()

        return binding.root
    }

    private fun getNewsList() {
        viewModel.test()
        viewModel.getNews()
    }

    private fun setListeners() {
        viewModel.newsState.observe(viewLifecycleOwner) {
            when (it) {
                HomeViewModel.State.Loading -> {
                    binding.srlNews.isRefreshing = true
                }
                is HomeViewModel.State.Success -> {
                    binding.srlNews.isRefreshing = false
                    adapter.submitList(it.list)
                }
                is HomeViewModel.State.Error -> {
                    binding.srlNews.isRefreshing = false
                    Snackbar.make(binding.srlNews, it.error.message.toString(), Snackbar.LENGTH_SHORT).show()
                }
            }
        }
        binding.srlNews.setOnRefreshListener {
            viewModel.getNews()
        }

        adapter.favoriteButtonListener = { news ->
            viewModel.setFavoriteNews(news)
        }

    }

/*    override fun onDestroyView() {
        super.onDestroyView()
         binding = null
    }*/
}