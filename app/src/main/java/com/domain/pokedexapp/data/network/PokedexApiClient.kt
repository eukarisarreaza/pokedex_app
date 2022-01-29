package com.domain.pokedexapp.data.network

import com.domain.pokedexapp.data.model.PokemonEntity
import com.domain.pokedexapp.data.model.ResponsePaginationModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApiClient {
    @GET("v2/pokemon")
    fun getListPokemon(@Query("limit")limit : Long, @Query("offset") offset : Long) : Single<ResponsePaginationModel>

    @GET("v2/pokemon/{name}")
    fun getDetailsPokemon(@Path("name")name : String) : Single<PokemonEntity>

}