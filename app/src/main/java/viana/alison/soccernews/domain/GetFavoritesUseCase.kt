package viana.alison.soccernews.domain

import viana.alison.soccernews.data.repository.NewsRepository

class GetFavoritesUseCase(
    private val repository: NewsRepository
) {

    fun getFavoritesNews() = repository.getFavoriteNews()

}