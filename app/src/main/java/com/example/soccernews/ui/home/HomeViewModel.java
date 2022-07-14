package com.example.soccernews.ui.home;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.data.database.FavoriteDB;
import com.example.soccernews.data.model.News;
import com.example.soccernews.data.repositories.NewsRepositoryImpl;
import com.example.soccernews.data.services.FavoriteDao;
import com.example.soccernews.data.services.SoccerNewsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR
    }

    private final MutableLiveData<List<News>> newsList = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();
    private final SoccerNewsApi serviceApi;
    private NewsRepositoryImpl newsRepository;
    private final List<Integer> favoriteIdsList = new ArrayList<>();

    public HomeViewModel() {
        state.setValue(State.DOING);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://alisonviana.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceApi = retrofit.create(SoccerNewsApi.class);
    }

    private void getNewsApi() {
        serviceApi.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()) {
                    state.setValue(State.DONE);
                    if (response.body() != null) {
                        for(News news: response.body()){
                            if(favoriteIdsList.contains(news.getId())) news.setFavorite(true);
                        }
                    }

                    newsList.setValue(response.body());
                    Log.i("TAG", "success");
                } else {
                    state.setValue(State.ERROR);
                    Log.i("TAG", "failure response");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                state.setValue(State.ERROR);
                Log.i("TAG", t.getMessage());
            }
        });
    }

    void setViewModelDependency(Context context) {
        FavoriteDB database = FavoriteDB.getInstance(context);
        FavoriteDao dao = database.dao();
        newsRepository = new NewsRepositoryImpl(dao);

        getFavoriteIds();
        getNewsApi();
    }

    public LiveData<List<News>> getNews() {
        return newsList;
    }

    private void getFavoriteIds() {
        AsyncTask.execute(() -> {
            favoriteIdsList.addAll(newsRepository.getFavoriteId());
        });
    }

    public void setFavoriteNews(News news) {
        AsyncTask.execute(() -> {
            if (news.getFavorite()) {
                newsRepository.setFavoriteNews(news);
            }
            else newsRepository.removeFavoriteNews(news);
        });
    }

    public LiveData<State> getState() { return state; }

    public void refreshNews() { getNewsApi(); }

}