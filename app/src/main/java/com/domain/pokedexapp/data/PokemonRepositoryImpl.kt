package com.domain.pokedexapp.data

import android.util.Log
import com.domain.pokedexapp.data.model.PokemonEntityMapper
import com.domain.pokedexapp.data.network.PokedexApiClient
import com.domain.pokedexapp.domain.model.Pokemon
import com.domain.pokedexapp.domain.repository.PokemonRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApiClient: PokedexApiClient,
    private val mapper : PokemonEntityMapper
) : PokemonRepository{

    override fun getListPokemon(limit: Long, offset: Long, fromServer: Boolean): Single<List<Pokemon>> =
        when (fromServer){
            false ->Single.create<List<Pokemon>> { sb ->
                //Transaction or network imitation
                Thread.sleep(2000)
                sb.onSuccess(emptyList())
            }.subscribeOn(Schedulers.io())

            true -> pokemonApiClient.getListPokemon(limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response ->
                    response.results!!.map { mapper.mapToDomain(it)}
                }
                .doOnSuccess {
                    Log.e("error", "Get list success $it")
                }
                .doOnError {
                    Log.e("error", "Get list error $it")
                }
                /*.onErrorResumeNext(
                    getListPokemon(limit, offset, false)
                )*/
        }




    override fun getDetailsPokemon(name: String): Single<Pokemon> {
        return  pokemonApiClient.getDetailsPokemon(name).map { mapper.mapToDomain(it)}
    }
}
