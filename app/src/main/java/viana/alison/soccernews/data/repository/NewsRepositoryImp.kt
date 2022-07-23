package viana.alison.soccernews.data.repository

import android.os.RemoteException
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
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

}