package viana.alison.soccernews.data.repository

import android.os.RemoteException
import android.util.Log
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import viana.alison.soccernews.data.model.News
import viana.alison.soccernews.data.service.FavoriteDao
import viana.alison.soccernews.data.service.SoccerNewsApi
import viana.alison.soccernews.domain.repository.NewsRepository

class NewsRepositoryImp(
    private val newsApi: SoccerNewsApi,
    private val dao: FavoriteDao
): NewsRepository {

    override suspend fun getAllNews() = flow {
        try {
            val newsList = newsApi.getNews()
            emit(newsList)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message())
        }
    }

    override fun getFavoriteNews() = dao.getFavoriteNews()

    override fun getFavoriteIds() = dao.getFavoriteIds()

    override suspend fun insertFavoriteNews(news: News) {
        try {
            dao.insert(news)
        } catch (ex: Exception) {
            Log.i("TAG", "FavoriteDB: insert error - ${ex.message}")
        }
    }

    override suspend fun deleteFavoriteNews(news: News) {
        try {
            dao.delete(news)
        } catch (ex: Exception) {
            Log.i("TAG", "FavoriteDB: delete error - ${ex.message}")
        }
    }

}