package dev.jamile.marvelous.repository

import dev.jamile.marvelous.data.model.HeroResponse
import dev.jamile.marvelous.network.Result

interface HomeRepository {
    suspend fun getHeroes(): Result<HeroResponse>
}