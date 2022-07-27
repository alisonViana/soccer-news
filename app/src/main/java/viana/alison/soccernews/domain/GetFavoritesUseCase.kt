package viana.alison.soccernews.domain

import viana.alison.soccernews.domain.repository.NewsRepository

class GetFavoritesUseCase(
    private val repository: NewsRepository
) {

    fun getFavoriteNews() = repository.getFavoriteNews()
    fun getFavoriteIds() = repository.getFavoriteIds()

}