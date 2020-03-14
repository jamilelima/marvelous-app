package dev.jamile.marvelous.data

import dev.jamile.marvelous.data.model.HeroResponse

interface HeroDataSource {
    fun listAllHeroes(success : (List<HeroResponse>) -> Unit, failure: () -> Unit)
}