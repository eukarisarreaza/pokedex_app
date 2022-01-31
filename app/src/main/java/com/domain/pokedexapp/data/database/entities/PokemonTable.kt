package com.domain.pokedexapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cleanarchitecture.data.base.ModelEntity


@Entity( tableName = "pokemon_table")
data class PokemonTable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "url_image") val urlImage: String
) : ModelEntity()