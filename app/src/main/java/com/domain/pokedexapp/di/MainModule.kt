package com.domain.pokedexapp.di

import android.content.Context
import com.domain.pokedexapp.data.PokemonRepositoryImpl
import com.domain.pokedexapp.data.database.dao.PokemonDao
import com.domain.pokedexapp.data.model.PokemonEntityMapper
import com.domain.pokedexapp.data.model.PokemonTableMapper
import com.domain.pokedexapp.data.network.PokedexApiClient
import com.domain.pokedexapp.domain.GetAllPokemonUseCase
import com.domain.pokedexapp.domain.repository.PokemonRepository
import com.domain.pokedexapp.presentation.list_pokemons.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule constructor(var context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context{
        return context
    }

    @Singleton
    @Provides
    fun provideMainViewModel(getAllPokemonUseCase: GetAllPokemonUseCase): MainViewModel{
        return MainViewModel(getListPokemonUseCase = getAllPokemonUseCase)
    }


    //casos de usos
    @Singleton
    @Provides
    fun provideGetAllPokemonUseCase(pokemonRepository: PokemonRepository): GetAllPokemonUseCase{
        return GetAllPokemonUseCase(pokemonRepository = pokemonRepository)
    }




}