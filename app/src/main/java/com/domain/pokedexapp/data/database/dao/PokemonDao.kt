package com.domain.pokedexapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.domain.pokedexapp.data.database.entities.PokemonTable
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon_table")
    fun getAll(): Single<List<PokemonTable>>

    @Query("SELECT * FROM pokemon_table WHERE name LIKE '%' || :criteria || '%' ")
    fun getFromCriteria(criteria : String): Single<List<PokemonTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: List<PokemonTable>): Completable

    @Query("DELETE FROM pokemon_table")
    fun deleteAll(): Completable
}