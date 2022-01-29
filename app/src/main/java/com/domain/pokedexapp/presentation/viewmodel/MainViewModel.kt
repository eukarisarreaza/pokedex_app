package com.domain.pokedexapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domain.pokedexapp.domain.GetAllPokemonUseCase
import com.domain.pokedexapp.domain.model.Pokemon
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getListPokemonUseCase: GetAllPokemonUseCase
) : ViewModel(){
    var offset: Long= 1
    var count= 0

    val pokemonAll = MutableLiveData<List<Pokemon>>()
    val isLoading = MutableLiveData<Boolean>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun onCreate(){
        addDisposable(getListPokemonUseCase.createObservable(GetAllPokemonUseCase.Params(limit = 20, offset = offset))
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.postValue(true)}
            .doFinally{isLoading.postValue(false)}
            .subscribe(
                {
                    pokemonAll.value = it

                    Log.e( "error", "Get repo success: $it")
                },
                {
                    Log.e( "error", "Get repo error: $it")
                }
            ));
    }


    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
    override fun onCleared() {
        compositeDisposable.dispose()
    }
}