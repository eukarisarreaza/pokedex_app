package com.domain.pokedexapp.presentation.details_pokemon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domain.pokedexapp.domain.GetPokemonUseCase
import com.domain.pokedexapp.domain.model.Pokemon
import com.domain.pokedexapp.presentation.utils.RxUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
): ViewModel(){


    var pokemon = MutableLiveData<Pokemon>()

    var isLoading = MutableLiveData<Boolean>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()



    fun onCreate(name: String){
        addDisposable(getPokemonUseCase.createObservable(
            GetPokemonUseCase.Params(name = name))
            .compose(RxUtils.applySingleScheduler(isLoading))
            .doFinally{isLoading.postValue(false)}
            .subscribe(
                {
                    pokemon.value = it
                },
                {
                }
            ))
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
    override fun onCleared() {
        compositeDisposable.dispose()
    }

}