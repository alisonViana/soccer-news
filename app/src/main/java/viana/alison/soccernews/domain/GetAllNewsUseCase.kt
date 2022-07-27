package viana.alison.soccernews.domain

import kotlinx.coroutines.flow.Flow
import viana.alison.soccernews.core.UseCase
import viana.alison.soccernews.data.model.News
import viana.alison.soccernews.domain.repository.NewsRepository

class GetAllNewsUseCase(
    private val repository: NewsRepository
) : UseCase.NoParam<List<News>>() {

    override suspend fun execute(): Flow<List<News>> {
        return repository.getAllNews()
    }

}