package dev.jamile.marvelous.network

import dev.jamile.marvelous.data.model.HeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET("characters")
    suspend fun loadCharacters(@Query("offset") offset: Int?): Response<HeroResponse>
}