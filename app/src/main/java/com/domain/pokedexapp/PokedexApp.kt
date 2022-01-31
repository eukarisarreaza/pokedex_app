package com.domain.pokedexapp

import android.app.Application
import com.domain.pokedexapp.di.*

class PokedexApp : Application() {

    lateinit var mainComponent : MainComponent
    lateinit var detailsComponent : DetailsComponent


    override fun onCreate() {
        super.onCreate()
        mainComponent= DaggerMainComponent.builder()
            .networkModule(NetworkModule())
            .roomModule(RoomModule())
            .mainModule(MainModule(this))
            .build()



        detailsComponent= DaggerDetailsComponent.builder()
            .networkModule(NetworkModule())
            .roomModule(RoomModule())
            .repositoryModule(RepositoryModule())
            .detailsModule(DetailsModule(this))
            .build()
    }

}