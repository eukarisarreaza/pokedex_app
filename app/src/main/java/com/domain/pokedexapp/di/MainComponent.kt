package com.domain.pokedexapp.di

import com.domain.pokedexapp.presentation.ui.PokemonListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, MainModule::class])
interface MainComponent {
    fun inject(activity: PokemonListFragment)
}