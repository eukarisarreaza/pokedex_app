package com.domain.pokedexapp.di

import android.content.Context
import com.domain.pokedexapp.domain.GetPokemonUseCase
import com.domain.pokedexapp.domain.repository.PokemonRepository
import com.domain.pokedexapp.presentation.details_pokemon.viewmodel.DetailsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailsModule constructor(var context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideDetailsViewModel(getPokemonUseCase: GetPokemonUseCase): DetailsViewModel {
        return DetailsViewModel(getPokemonUseCase = getPokemonUseCase)
    }


    @Singleton
    @Provides
    fun provideGetPokemonUseCase(pokemonRepository: PokemonRepository): GetPokemonUseCase {
        return GetPokemonUseCase(pokemonRepository = pokemonRepository)
    }


}