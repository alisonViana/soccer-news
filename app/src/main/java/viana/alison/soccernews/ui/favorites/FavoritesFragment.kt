package viana.alison.soccernews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import viana.alison.soccernews.databinding.FragmentFavoritesBinding
import viana.alison.soccernews.presentation.FavoritesViewModel
import viana.alison.soccernews.ui.adapters.NewsListAdapter

class FavoritesFragment : Fragment() {

    private val binding by lazy { FragmentFavoritesBinding.inflate(layoutInflater) }
    private val adapter by lazy { NewsListAdapter() }
    private val viewModel by viewModel<FavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.rvNews.adapter = adapter

        getFavoriteNews()
        setListeners()

        return binding.root
    }

    private fun getFavoriteNews() {
        //viewModel.getFavorites()
    }

    private fun setListeners() {
        viewModel.getFavorites().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        /*viewModel.favoritesState.observe(viewLifecycleOwner) {
            when (it) {
                FavoritesViewModel.State.Loading -> {}
                is FavoritesViewModel.State.Success -> {
                    Log.i("TAG", "room success")
                    adapter.submitList(it.list)
                }
                is FavoritesViewModel.State.Error -> {
                    Snackbar.make(binding.rvNews, it.error.message.toString(), Snackbar.LENGTH_SHORT).show()
                }
            }
        }*/
    }

}