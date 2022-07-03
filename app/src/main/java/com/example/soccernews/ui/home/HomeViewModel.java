package com.example.soccernews.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.data.model.News;
import com.example.soccernews.data.services.SoccerNewsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<News>> newsList = new MutableLiveData<>();
    private final SoccerNewsApi serviceApi;

    public HomeViewModel() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://alisonviana.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceApi = retrofit.create(SoccerNewsApi.class);
        getNewsApi();
    }

    private void getNewsApi() {
        serviceApi.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "successful");
                    newsList.setValue(response.body());
                } else {
                    Log.i("TAG", "failure response");
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });
    }

    public LiveData<List<News>> getNews() {
        Log.i("TAG", "get");
        return newsList;
    }
}