package com.domain.pokedexapp.di

import com.domain.pokedexapp.presentation.list_pokemons.ui.PokemonListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, MainModule::class, RoomModule::class, RepositoryModule::class])
interface MainComponent {
    fun inject(activity: PokemonListFragment)
}