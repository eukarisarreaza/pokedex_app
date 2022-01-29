package com.domain.pokedexapp.data.network

import com.domain.pokedexapp.data.model.PokemonEntity
import com.domain.pokedexapp.data.model.ResponsePaginationModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexApiClient {
    @GET("pokemon?limit={limit}&offset={offset}")
    fun getListPokemon(@Path("limit")limit : Int, @Path("offset") offset : Int) : Observable<ResponsePaginationModel>

    @GET("pokemon/{name}")
    fun getDetailsPokemon(@Path("name")name : String) : Single<PokemonEntity>

}