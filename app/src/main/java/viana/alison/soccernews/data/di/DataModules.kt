package viana.alison.soccernews.data.di

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import viana.alison.soccernews.data.repository.NewsRepository
import viana.alison.soccernews.data.repository.NewsRepositoryImp
import viana.alison.soccernews.data.service.SoccerNewsApi

object DataModules {

    fun load() {
        loadKoinModules(networkModule()
                + repositoryModule())
    }

    private fun repositoryModule(): Module {
        return module {
            single<NewsRepository> { NewsRepositoryImp(get()) }
        }
    }

    private fun networkModule(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.i("OK_HTTP", it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }
            single {
                createService<SoccerNewsApi>(get(), get())
            }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl("https://alisonviana.github.io/soccer-news-api/")
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)
    }

}