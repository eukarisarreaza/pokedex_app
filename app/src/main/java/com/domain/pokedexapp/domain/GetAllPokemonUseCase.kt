package com.domain.pokedexapp.domain

import com.domain.pokedexapp.base.UseCase
import com.domain.pokedexapp.domain.model.Pokemon
import com.domain.pokedexapp.domain.repository.PokemonRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


open class GetAllPokemonUseCase @Inject constructor(
    val pokemonRepository: PokemonRepository
    ): UseCase<GetAllPokemonUseCase.Params, Single<List<Pokemon>>>() {


    data class Params(val limit: Int, val offset: Int)

    override fun createObservable(params: Params?): Single<List<Pokemon>> {
        params?.let { return pokemonRepository.getListPokemon(params.limit, params.offset, true) }
        return Single.error(Throwable(""))
    }
}