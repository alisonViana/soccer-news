package com.example.soccernews.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.soccernews.data.model.News;

import java.util.List;

public interface NewsRepository {
    LiveData<List<News>> getFavoriteNews();
    void setFavoriteNews(News news);
    void removeFavoriteNews(News news);
}
