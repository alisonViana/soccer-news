package viana.alison.soccernews.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import viana.alison.soccernews.core.UseCase
import viana.alison.soccernews.data.model.News
import viana.alison.soccernews.data.repository.NewsRepository

class SetFavoritesUseCase(
    private val repository: NewsRepository
) : UseCase.NoSource<News>() {

    override suspend fun execute(param: News): Flow<Unit> {
        if (param.favorite) repository.insertFavoriteNews(param)
        else repository.deleteFavoriteNews(param)

        return flow{}
    }

}