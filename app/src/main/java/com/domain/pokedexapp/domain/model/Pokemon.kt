package com.domain.pokedexapp.domain.model

import com.domain.pokedexapp.data.model.*
import com.google.gson.JsonArray

data class Pokemon(
    val abilities: List<Ability>?,
    val baseExperience: Long?,
    val forms: List<Species>?,
    val gameIndices: List<GameIndex>?,
    val height: Long?,
    val heldItems: List<HeldItem>?,
    val id: Long?,
    val isDefault: Boolean?,
    val locationAreaEncounters: String?,
    val moves: List<Move>?,
    val name: String?,
    val order: Long?,
    val pastTypes: JsonArray?,
    val species: Species?,
    val sprites: Sprites?,
    val stats: List<Stat>?,
    val types: List<Type>?,
    val weight: Long?
) : Model()




