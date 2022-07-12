package com.example.soccernews.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.soccernews.databinding.FragmentFavoritesBinding;
import com.example.soccernews.ui.NewsListAdapter;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private FavoritesViewModel viewModel;
    private NewsListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        viewModel.setViewModelDependency(requireContext());
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        adapter = new NewsListAdapter();
        binding.rvNews.setAdapter(adapter);

        getFavoriteList();
        setListeners();

        return binding.getRoot();
    }

    private void getFavoriteList() {
        viewModel.getFavoriteList().observe(getViewLifecycleOwner(), list -> {
            adapter.submitList(list);
        });
    }

    private void setListeners() {
        adapter.favoriteInterface = news -> {
            viewModel.setFavoriteNews(news);
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}