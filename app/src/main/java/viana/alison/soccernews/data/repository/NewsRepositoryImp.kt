package viana.alison.soccernews.data.repository

import android.os.RemoteException
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import viana.alison.soccernews.data.model.News
import viana.alison.soccernews.data.service.FavoriteDao
import viana.alison.soccernews.data.service.SoccerNewsApi

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

    override suspend fun insertFavoriteNews(news: News) {
        try {
            dao.insert(news)
        } catch (ex: Exception) {
            // TODO exception handle
        }
    }

    override suspend fun deleteFavoriteNews(news: News) {
        try {
            dao.delete(news)
        } catch (ex: Exception) {
            // TODO exception handle
        }
    }

}