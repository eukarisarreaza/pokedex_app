package com.domain.pokedexapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.domain.pokedexapp.data.model.PokemonEntity
import com.domain.pokedexapp.data.network.PokedexApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject




class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var apiClient: PokedexApiClient
    lateinit var myCompositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myCompositeDisposable = CompositeDisposable()
        (application as PokedexApp).networkComponent.inject(this)
        getPokemons()
    }

    private fun getPokemons() {
        myCompositeDisposable.add(
            apiClient.getDetailsPokemon("ditto")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({

                },{

                }))

    }


    private fun handleResponse(pokemon: PokemonEntity) {
        print(pokemon)
    }


    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable.clear()
    }


}