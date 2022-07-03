package com.example.soccernews.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.data.model.News;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<News>> newsList;

    public HomeViewModel() {
        this.newsList = new MutableLiveData<>();

        List<News> news = new ArrayList<>();
        news.add(new News("Titulo noticia", "Corpo da notícia"));
        news.add(new News("Titulo noticia", "Corpo da notícia"));
        news.add(new News("Titulo noticia", "Corpo da notícia"));
        news.add(new News("Titulo noticia", "Corpo da notícia"));

        this.newsList.setValue(news);
    }

    public LiveData<List<News>> getNewsList() {
        Log.i("TAG", "get");
        return newsList;
    }
}