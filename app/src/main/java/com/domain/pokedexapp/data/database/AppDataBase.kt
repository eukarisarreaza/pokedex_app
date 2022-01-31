package com.domain.pokedexapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.domain.pokedexapp.data.database.dao.PokemonDao
import com.domain.pokedexapp.data.database.entities.PokemonTable

@Database(entities = [PokemonTable::class], version = 1)
abstract class AppDataBase : RoomDatabase()  {
    abstract  fun pokemonDao(): PokemonDao
}