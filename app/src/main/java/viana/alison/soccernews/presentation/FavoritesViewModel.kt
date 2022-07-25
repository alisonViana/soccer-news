package viana.alison.soccernews.presentation

import androidx.lifecycle.*
import viana.alison.soccernews.data.model.News
import viana.alison.soccernews.domain.GetFavoritesUseCase

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _favoritesState = MutableLiveData<State>()
    val favoritesState: LiveData<State> = _favoritesState

    private val _favoriteNews = MutableLiveData<LiveData<List<News>>>()
    val favoriteNews: LiveData<LiveData<List<News>>> = _favoriteNews

    fun getFavorites() = getFavoritesUseCase.getFavoritesNews().asLiveData()

    sealed class State {
        object Loading: State()
        data class Success(val list: List<News>): State()
        data class Error(val error: Throwable): State()
    }

}