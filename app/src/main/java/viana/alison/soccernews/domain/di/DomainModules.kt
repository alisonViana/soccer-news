package viana.alison.soccernews.domain.di

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import viana.alison.soccernews.domain.GetAllNewsUseCase

object DomainModules {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { GetAllNewsUseCase(get()) }
        }
    }

}