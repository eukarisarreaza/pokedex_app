package com.domain.pokedexapp.di

import com.domain.pokedexapp.data.network.PokedexApiClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    companion object {
        private const val NAME_BASE_URL = "https://pokeapi.co/api/v2/"
    }

    @Provides
    @Named(NAME_BASE_URL)
    fun provideBaseUrlString() = NAME_BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(converterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, @Named(NAME_BASE_URL) baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    fun providesGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit): PokedexApiClient = retrofit.create(PokedexApiClient::class.java)

}