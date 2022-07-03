package com.example.soccernews.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.soccernews.data.model.News;
import com.example.soccernews.databinding.FragmentHomeBinding;
import com.example.soccernews.ui.NewsListAdapter;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsListAdapter adapter;
    private HomeViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        adapter = new NewsListAdapter();
        binding.rvNews.setAdapter(adapter);

        getNewsList();

        return binding.getRoot();
    }

    private void getNewsList() {
        viewModel.getNewsList().observe(getViewLifecycleOwner(), list -> {
            adapter.submitList(list);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}