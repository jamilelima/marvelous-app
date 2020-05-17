package dev.jamile.marvelous.di

import dev.jamile.marvelous.repository.HomeRepository
import dev.jamile.marvelous.repository.HomeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<HomeRepository> { HomeRepositoryImpl(get()) }
}