package com.domain.pokedexapp.di

import com.domain.pokedexapp.data.PokemonRepositoryImpl
import com.domain.pokedexapp.data.model.PokemonEntityMapper
import com.domain.pokedexapp.data.network.PokedexApiClient
import com.domain.pokedexapp.domain.GetAllPokemonUseCase
import com.domain.pokedexapp.domain.repository.PokemonRepository
import com.domain.pokedexapp.presentation.ui.PokemonRecyclerAdapter
import com.domain.pokedexapp.presentation.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

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


    @Singleton
    @Provides
    fun providePokemonRepository(pokemonApiClient: PokedexApiClient, pokemonEntityMapper: PokemonEntityMapper): PokemonRepository{
        return PokemonRepositoryImpl(pokemonApiClient= pokemonApiClient, mapper = pokemonEntityMapper)
    }

    @Singleton
    @Provides
    fun providePokemonEntityMapper(): PokemonEntityMapper{
        return PokemonEntityMapper()
    }

}