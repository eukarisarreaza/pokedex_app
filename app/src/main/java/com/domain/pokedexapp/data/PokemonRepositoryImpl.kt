package com.domain.pokedexapp.data

import com.domain.pokedexapp.data.model.PokemonEntity
import com.domain.pokedexapp.data.model.PokemonEntityMapper
import com.domain.pokedexapp.data.network.PokedexApiClient
import com.domain.pokedexapp.domain.model.Pokemon
import com.domain.pokedexapp.domain.repository.PokemonRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApiClient: PokedexApiClient,
    private val mapper : PokemonEntityMapper
) : PokemonRepository{

    override fun getListPokemon(limit: Int, offset: Int, fromServer: Boolean): Observable<List<Pokemon>> =
        when (fromServer){
            false ->Observable.create<List<Pokemon>> { sb ->
                //Transaction or network imitation
                Thread.sleep(2000)
                sb.onNext(emptyList())
            }.subscribeOn(Schedulers.io())

            true -> pokemonApiClient.getListPokemon(limit, offset)
                .map { response ->
                    response.results.map { mapper.mapToDomain(it)}
                }
                .onErrorResumeNext(getListPokemon(limit, offset, false))
        }




    override fun getDetailsPokemon(name: String): Single<PokemonEntity> {
        TODO("Not yet implemented")
    }
}
