package com.domain.pokedexapp.domain.model

import com.domain.pokedexapp.data.model.*
import com.google.gson.JsonArray

data class Pokemon(
    val abilities: List<Ability>?= null,
    val baseExperience: Long?= null,
    val forms: List<Species>?= null,
    val gameIndices: List<GameIndex>?= null,
    val height: Long?= null,
    val heldItems: List<HeldItem>?= null,
    val id: Long?= null,
    val isDefault: Boolean?= null,
    val locationAreaEncounters: String?= null,
    val moves: List<Move>?= null,
    val name: String?,
    val order: Long?= null,
    val pastTypes: JsonArray?= null,
    val species: Species?= null,
    val sprites: Sprites?= null,
    val stats: List<Stat>?= null,
    val types: List<Type>?= null,
    val weight: Long?= null,
    val urlImage: String,
    val url: String,
) : Model()




