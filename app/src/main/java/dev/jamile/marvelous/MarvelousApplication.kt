package dev.jamile.marvelous

import android.app.Application
import android.content.Context
import dev.jamile.marvelous.di.networkModule
import dev.jamile.marvelous.di.repositoryModule
import dev.jamile.marvelous.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MarvelousApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupApp(baseContext)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupApp(context: Context) {
        startKoin {
            androidContext(context)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}