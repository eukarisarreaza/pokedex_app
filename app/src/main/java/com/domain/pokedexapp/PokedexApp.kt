package com.domain.pokedexapp

import android.app.Application
import com.domain.pokedexapp.di.DaggerMainComponent
import com.domain.pokedexapp.di.MainComponent
import com.domain.pokedexapp.di.MainModule
import com.domain.pokedexapp.di.NetworkModule

class PokedexApp : Application() {

    lateinit var mainComponent : MainComponent


    override fun onCreate() {
        super.onCreate()
        mainComponent= DaggerMainComponent.builder()
            .networkModule(NetworkModule())
            .mainModule(MainModule())
            .build()
    }

}