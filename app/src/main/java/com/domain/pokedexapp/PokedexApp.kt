package com.domain.pokedexapp

import android.app.Application
import com.domain.pokedexapp.di.DaggerNetworkComponent
import com.domain.pokedexapp.di.NetworkComponent
import com.domain.pokedexapp.di.NetworkModule

class PokedexApp : Application() {

    lateinit var networkComponent : NetworkComponent


    override fun onCreate() {
        super.onCreate()
        networkComponent= DaggerNetworkComponent.builder()
            .networkModule(NetworkModule())
            .build()
    }

}