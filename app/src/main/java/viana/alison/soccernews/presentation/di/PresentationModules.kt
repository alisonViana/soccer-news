package viana.alison.soccernews.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import viana.alison.soccernews.presentation.HomeViewModel
import viana.alison.soccernews.presentation.FavoritesViewModel

object PresentationModules {

    fun load() {
        loadKoinModules(viewModelModules())
    }

    private fun viewModelModules(): Module {
        return module {
            viewModel { HomeViewModel() }
            viewModel { FavoritesViewModel() }
        }
    }
}