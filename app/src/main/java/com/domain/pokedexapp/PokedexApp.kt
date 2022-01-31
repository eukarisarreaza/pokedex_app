package com.domain.pokedexapp

import android.app.Application
import com.domain.pokedexapp.di.*

class PokedexApp : Application() {

    lateinit var mainComponent : MainComponent


    override fun onCreate() {
        super.onCreate()
        mainComponent= DaggerMainComponent.builder()
            .networkModule(NetworkModule())
            .roomModule(RoomModule())
            .mainModule(MainModule(this))
            .build()
    }

}