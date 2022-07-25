package viana.alison.soccernews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import viana.alison.soccernews.data.model.News
import viana.alison.soccernews.data.service.FavoriteDao

@Database(entities = [News::class], version = 1)
abstract class FavoriteDB : RoomDatabase() {

    abstract val favoriteDao: FavoriteDao

    companion object {

        @Volatile
        private var INSTANCE: FavoriteDB? = null

        fun getInstance(context: Context): FavoriteDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDB::class.java,
                    "favorite-news-db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}