package dev.jamile.marvelous.di

import dev.jamile.marvelous.base.DispatcherProvider
import dev.jamile.marvelous.viewmodel.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeViewModel(get(), get()) }
    factory { DispatcherProvider() }
}