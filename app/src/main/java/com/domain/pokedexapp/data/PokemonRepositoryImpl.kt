package com.domain.pokedexapp.data

import android.util.Log
import com.domain.pokedexapp.data.database.dao.PokemonDao
import com.domain.pokedexapp.data.database.entities.PokemonTable
import com.domain.pokedexapp.data.model.PokemonEntityMapper
import com.domain.pokedexapp.data.model.PokemonTableMapper
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
    private val mapper : PokemonEntityMapper,
    private val mapperTable: PokemonTableMapper,
    private val pokemonDao: PokemonDao
) : PokemonRepository{

    override fun getListPokemon(limit: Long?, offset: Long?, criteria: String, fromServer: Boolean): Single<List<Pokemon>> =
        when (fromServer){
            false -> pokemonDao.getFromCriteria(criteria).map { response->
                response.map {  mapperTable.mapToDomain(it) }
            }.doOnError{ Throwable("Not found!") }

            true -> pokemonApiClient.getListPokemon(limit!!, offset!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response ->
                    response.results!!.map { mapper.mapToDomain(it)}
                }
            .doOnSuccess {response->
                pokemonDao.insertAll(response.map { PokemonTable(name = it.name!!, url = it.url, urlImage = it.urlImage ) })
            }/*
            .doOnError {
                Log.e("error", "Get list error $it")
            }
            .onErrorResumeNext(
                getListPokemon(limit, offset, false)
            )*/
        }




    override fun getDetailsPokemon(name: String): Single<Pokemon> {
        return  pokemonApiClient.getDetailsPokemon(name).map { mapper.mapToDomain(it)}
    }
}
