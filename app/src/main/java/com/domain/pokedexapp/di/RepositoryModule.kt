package com.domain.pokedexapp.di

import com.domain.pokedexapp.data.PokemonRepositoryImpl
import com.domain.pokedexapp.data.database.dao.PokemonDao
import com.domain.pokedexapp.data.model.PokemonEntityMapper
import com.domain.pokedexapp.data.model.PokemonTableMapper
import com.domain.pokedexapp.data.network.PokedexApiClient
import com.domain.pokedexapp.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providePokemonRepository(pokemonApiClient: PokedexApiClient, pokemonEntityMapper: PokemonEntityMapper,
                                 pokemonDAO: PokemonDao, mapperTable: PokemonTableMapper
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokemonApiClient= pokemonApiClient, mapper = pokemonEntityMapper, pokemonDao = pokemonDAO, mapperTable = mapperTable)
    }

    @Singleton
    @Provides
    fun providePokemonEntityMapper(): PokemonEntityMapper {
        return PokemonEntityMapper()
    }

    @Singleton
    @Provides
    fun providePokemonTableMapper(): PokemonTableMapper {
        return PokemonTableMapper()
    }
}