package com.domain.pokedexapp.domain

import com.domain.pokedexapp.base.UseCase
import com.domain.pokedexapp.domain.model.Pokemon
import com.domain.pokedexapp.domain.repository.PokemonRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetPokemonUseCase @Inject constructor(val pokemonRepository: PokemonRepository)
    : UseCase<GetPokemonUseCase.Params, Single<Pokemon>>() {

    data class Params(val name: String)

    override fun createObservable(params: Params?): Single<Pokemon> {
        if (params != null) {
            return pokemonRepository.getDetailsPokemon(params.name)
        }
        return Single.error(Throwable("Params input not valid"))
    }

}