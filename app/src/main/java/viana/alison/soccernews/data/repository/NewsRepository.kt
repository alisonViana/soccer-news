package viana.alison.soccernews.data.repository

import kotlinx.coroutines.flow.Flow
import viana.alison.soccernews.data.model.News

interface NewsRepository {
    suspend fun getAllNews(): Flow<List<News>>
}