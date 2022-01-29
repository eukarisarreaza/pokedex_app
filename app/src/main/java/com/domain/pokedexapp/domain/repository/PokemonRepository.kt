package com.domain.pokedexapp.domain.repository

import com.domain.pokedexapp.domain.model.Pokemon
import io.reactivex.Single


interface PokemonRepository {
    fun getListPokemon(limit: Long, offset: Long, fromServer: Boolean): Single<List<Pokemon>>
    fun getDetailsPokemon(name: String): Single<Pokemon>
}