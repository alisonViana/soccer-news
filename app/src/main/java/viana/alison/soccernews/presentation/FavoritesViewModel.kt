package viana.alison.soccernews.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import viana.alison.soccernews.data.model.News
import viana.alison.soccernews.domain.GetFavoritesUseCase
import viana.alison.soccernews.domain.SetFavoritesUseCase

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val setFavoritesUseCase: SetFavoritesUseCase
) : ViewModel() {

    fun getFavorites() = getFavoritesUseCase.getFavoritesNews().asLiveData()

    fun setFavoriteNews(news: News) = viewModelScope.launch {
        setFavoritesUseCase.execute(news)
    }

}