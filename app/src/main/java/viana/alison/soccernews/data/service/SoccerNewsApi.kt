package viana.alison.soccernews.data.service

import retrofit2.http.GET
import viana.alison.soccernews.data.model.News

interface SoccerNewsApi {
    @GET("news.json")
    suspend fun getNews(): List<News>
}