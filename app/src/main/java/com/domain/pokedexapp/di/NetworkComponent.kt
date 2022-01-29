package com.domain.pokedexapp.di

import androidx.appcompat.app.AppCompatActivity
import com.domain.pokedexapp.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun inject(activity: MainActivity)
}