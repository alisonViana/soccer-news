package com.example.soccernews.data.services;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.soccernews.data.model.News;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM News")
    LiveData<List<News>> getFavoriteNews();

    @Query("SELECT id FROM News")
    List<Integer> getFavoriteId();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);

    @Delete
    void delete(News news);
}
