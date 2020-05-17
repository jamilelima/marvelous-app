package dev.jamile.marvelous.di

import dev.jamile.marvelous.network.MarvelApiService
import dev.jamile.marvelous.network.RequestInterceptor
import dev.jamile.marvelous.utils.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(RequestInterceptor.setupOkHttp().build())
            .build()
    }
    single { get<Retrofit>().create(MarvelApiService::class.java) }
}