package com.example.soccernews.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.soccernews.data.model.News;
import com.example.soccernews.data.services.FavoriteDao;

import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {

    private final FavoriteDao dao;

    public NewsRepositoryImpl(FavoriteDao dao) {
        this.dao = dao;
    }

    @Override
    public LiveData<List<News>> getFavoriteNews() {
        return dao.getFavoriteNews();
    }

    @Override
    public List<Integer> getFavoriteId() {
        return dao.getFavoriteId();
    }

    @Override
    public void setFavoriteNews(News news) {
        dao.insert(news);
    }

    @Override
    public void removeFavoriteNews(News news) {
        dao.delete(news);
    }

}
