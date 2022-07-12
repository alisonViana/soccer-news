package com.example.soccernews.ui.favorites;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.data.database.FavoriteDB;
import com.example.soccernews.data.model.News;
import com.example.soccernews.data.repositories.NewsRepositoryImpl;
import com.example.soccernews.data.services.FavoriteDao;

import java.util.List;

public class FavoritesViewModel extends ViewModel {

    private NewsRepositoryImpl newsRepository;

    public FavoritesViewModel() {}

    void setViewModelDependency(Context context) {
        FavoriteDB database = FavoriteDB.getInstance(context);
        FavoriteDao dao = database.dao();
        newsRepository = new NewsRepositoryImpl(dao);
    }

    public LiveData<List<News>> getFavoriteList() {
         return newsRepository.getFavoriteNews();
    }

    public void setFavoriteNews(News news) {
        AsyncTask.execute(() -> {
            if (news.getFavorite()) newsRepository.setFavoriteNews(news);
            else newsRepository.removeFavoriteNews(news);
        });
    }
}