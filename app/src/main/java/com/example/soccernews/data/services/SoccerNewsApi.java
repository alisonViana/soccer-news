package com.example.soccernews.data.services;

import com.example.soccernews.data.model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerNewsApi {
    @GET("news.json")
    Call<List<News>> getNews();
}
