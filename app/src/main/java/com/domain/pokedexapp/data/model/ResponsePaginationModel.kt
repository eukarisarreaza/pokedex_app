package com.domain.pokedexapp.data.model

data class ResponsePaginationModel(
    val count: Long,
    val next: String,
    val previous: String? = null,
    val results: List<PokemonEntity>)
