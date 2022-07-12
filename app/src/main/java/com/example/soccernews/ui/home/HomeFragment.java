package com.example.soccernews.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.soccernews.databinding.FragmentHomeBinding;
import com.example.soccernews.ui.NewsListAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsListAdapter adapter;
    private HomeViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel.setViewModelDependency(requireContext());
        adapter = new NewsListAdapter();
        binding.rvNews.setAdapter(adapter);

        getNewsList();
        setListeners();

        return binding.getRoot();
    }

    private void getNewsList() {
        viewModel.getNews().observe(getViewLifecycleOwner(), list -> {
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