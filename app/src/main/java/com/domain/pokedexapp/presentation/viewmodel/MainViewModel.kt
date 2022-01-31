package com.domain.pokedexapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domain.pokedexapp.domain.GetAllPokemonUseCase
import com.domain.pokedexapp.domain.model.Pokemon
import com.domain.pokedexapp.presentation.utils.RxUtils
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getListPokemonUseCase: GetAllPokemonUseCase
) : ViewModel(){
    var offset: Long= 1
    var count= 0
    var isLastPage: Boolean = false
    var isLoading2: Boolean = false

    var pokemonAll = MutableLiveData<List<Pokemon>>()
    var pokemonSearch = MutableLiveData<List<Pokemon>>()

    var isLoading = MutableLiveData<Boolean>()


    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun searchPokemon(query: String) =
        when (query.isNullOrEmpty()) {
        true -> Unit
        false -> query.let {
            addDisposable(getListPokemonUseCase.createObservable(GetAllPokemonUseCase.Params( criteria = query, fromServer = false))
                .compose(RxUtils.applySingleScheduler(isLoading))
                .doFinally{isLoading.postValue(false)}
                .subscribe(
                    {
                        pokemonSearch.value = it

                        //Log.e( "error", "Get repo success: $it")
                    },
                    {
                        //Log.e( "error", "Get repo error: $it")
                    }
                ))
        }
    }

    fun searchPok(query: String): Single<List<Pokemon>>{
        return getListPokemonUseCase.createObservable(GetAllPokemonUseCase.Params( criteria = query, fromServer = false))
//            .compose(RxUtils.applySingleScheduler(isLoading))
            .doFinally{isLoading.postValue(false)}
    }


    fun onCreate(){
        addDisposable(getListPokemonUseCase.createObservable(GetAllPokemonUseCase.Params(limit = 20, offset = offset, criteria = "", fromServer = true))
            .compose(RxUtils.applySingleScheduler(isLoading))
            .doFinally{isLoading.postValue(false)}
            .subscribe(
                {
                    pokemonAll.value = it
                    offset=it.size.toLong()
                    //Log.e( "error", "Get repo success: $it")
                },
                {
                    //Log.e( "error", "Get repo error: $it")
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