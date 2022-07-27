package viana.alison.soccernews.presentation

import androidx.lifecycle.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import viana.alison.soccernews.data.model.News
import viana.alison.soccernews.domain.GetAllNewsUseCase
import viana.alison.soccernews.domain.GetFavoritesUseCase
import viana.alison.soccernews.domain.SetFavoritesUseCase

class HomeViewModel(
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val setFavoritesUseCase: SetFavoritesUseCase
) : ViewModel() {

    private val _newsState = MutableLiveData<State>()
    val newsState: LiveData<State> = _newsState

    fun getNews() = viewModelScope.launch {
        getAllNewsUseCase.execute()
            .onStart { _newsState.postValue(State.Loading) }
            .catch {
                _newsState.value = State.Error(it)
            }
            .collect {
                _newsState.value = State.Success(it)
                _newsState.postValue(State.Success(it))
            }
    }

    fun getFavoriteIds() = getFavoritesUseCase.getFavoriteIds().asLiveData()

    fun setFavoriteNews(news: News) = viewModelScope.launch {
        setFavoritesUseCase.invoke(news)
    }

    sealed class State {
        object Loading: State()
        data class Success(val list: List<News>): State()
        data class Error(val error: Throwable): State()
    }

}