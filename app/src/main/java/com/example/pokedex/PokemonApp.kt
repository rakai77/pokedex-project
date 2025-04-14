package com.example.pokedex

import android.app.Application
import appModule
import com.example.core.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokemonApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokemonApp)
            androidLogger()
            modules(appModule, coreModule)
        }
    }
}