package com.domain.pokedexapp.di

import android.content.Context
import androidx.room.Room
import com.domain.pokedexapp.data.database.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RoomModule {
    companion object {
        private const val DATABASE_NAME = "pokemon_database"
    }

    @Provides
    @Named(DATABASE_NAME)
    fun provideBaseUrlString() = DATABASE_NAME


    @Singleton
    @Provides
    fun provideRoom(context: Context) =
        Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, DATABASE_NAME).build()


    @Singleton
    @Provides
    fun providePokemonDao(db: AppDataBase)= db.pokemonDao()


}