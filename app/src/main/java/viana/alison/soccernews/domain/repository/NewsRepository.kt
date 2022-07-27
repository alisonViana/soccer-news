package viana.alison.soccernews.domain.repository

import kotlinx.coroutines.flow.Flow
import viana.alison.soccernews.data.model.News

interface NewsRepository {
    suspend fun getAllNews(): Flow<List<News>>
    fun getFavoriteNews(): Flow<List<News>>
    fun getFavoriteIds(): Flow<List<Int>>
    suspend fun insertFavoriteNews(news: News)
    suspend fun deleteFavoriteNews(news: News)
}