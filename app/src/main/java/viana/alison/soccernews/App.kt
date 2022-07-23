package viana.alison.soccernews

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import viana.alison.soccernews.data.di.DataModules
import viana.alison.soccernews.domain.di.DomainModules
import viana.alison.soccernews.presentation.di.PresentationModules

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        // Load Modules
        DataModules.load()
        DomainModules.load()
        PresentationModules.load()


    }
}