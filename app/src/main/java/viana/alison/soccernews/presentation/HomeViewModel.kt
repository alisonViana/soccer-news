package viana.alison.soccernews.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import viana.alison.soccernews.data.model.News
import viana.alison.soccernews.domain.GetAllNewsUseCase
import viana.alison.soccernews.domain.SetFavoritesUseCase

class HomeViewModel(
    private val getAllNewsUseCase: GetAllNewsUseCase,
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

    /*
    fun getFavoriteIds(): LiveData<List<Int>> {
        return repository.getFavoriteId().asLiveData()
    } */

    fun setFavoriteNews(news: News) = viewModelScope.launch {
        setFavoritesUseCase.execute(news)
    }

    fun test() {
        Log.i("TAG", "Test")
    }


    sealed class State {
        object Loading: State()
        data class Success(val list: List<News>): State()
        data class Error(val error: Throwable): State()
    }

}