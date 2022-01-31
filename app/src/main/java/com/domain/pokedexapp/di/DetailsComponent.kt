package com.domain.pokedexapp.di

import com.domain.pokedexapp.presentation.details_pokemon.iu.DetailsPokemonFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, DetailsModule::class, RepositoryModule::class, RoomModule::class])
interface DetailsComponent {
    fun inject(activity: DetailsPokemonFragment)
}