package dev.jamile.marvelous.repository

import dev.jamile.marvelous.data.model.HeroResponse
import dev.jamile.marvelous.network.MarvelApiService
import dev.jamile.marvelous.network.Result

class HomeRepositoryImpl(private val apiService: MarvelApiService) : HomeRepository {
    override suspend fun getHeroes(): Result<HeroResponse> {
        val response = apiService.loadCharacters(15)
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.body()} ${response.message()}"))
    }
}