package viana.alison.soccernews.data.service

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import viana.alison.soccernews.data.model.News

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM News")
    fun getFavoriteNews(): Flow<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: News)

    @Delete
    suspend fun delete(news: News)

}