package com.example.soccernews.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.soccernews.data.model.News;
import com.example.soccernews.data.services.FavoriteDao;

@Database(entities = {News.class}, version = 1)
public abstract class FavoriteDB extends RoomDatabase {
    public abstract FavoriteDao dao();

    public static FavoriteDB getInstance(Context context) {

        return Room.databaseBuilder(
                context.getApplicationContext(),
                FavoriteDB.class,
                "favorite-news-db").build();

    }
}
