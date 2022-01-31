package com.domain.pokedexapp.presentation.list_pokemons.viewmodel

import com.domain.pokedexapp.domain.GetAllPokemonUseCase
import com.domain.pokedexapp.domain.model.Pokemon
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith


//@RunWith(MockitoJUnitRunner::class)
@ExtendWith(value = [InstantExecutorExtension::class, TestSchedulerExtension::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainViewModelTest{
    @Mock
    private lateinit var getListPokemonUseCase: GetAllPokemonUseCase
    private lateinit var mainViewModel: MainViewModel



    @BeforeAll
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        mainViewModel= MainViewModel(getListPokemonUseCase)
    }


    @Test
    fun testAddMaxInteger() {
        assertEquals(42, Integer.sum(19, 23))
    }

    @Test
    fun testgetAllPokemon() {
        var limit: Long = 2
        var offest: Long = 1

        var itemPokemno= Pokemon(name = "ivysaur", url = "https://pokeapi.co/api/v2/pokemon/2/", urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png")

        val listItem: List<Pokemon> = arrayListOf(itemPokemno)

        `when`(getListPokemonUseCase.createObservable((GetAllPokemonUseCase.Params(limit = limit, offset = offest, criteria = "", fromServer = true))))
            .thenReturn(Single.just(listItem))

        var data= getListPokemonUseCase.createObservable((GetAllPokemonUseCase.Params(limit = limit, offset = offest, criteria = "", fromServer = true))).blockingGet()


        assertEquals(1, data.size)
        assertEquals("ivysaur", data[0].name)


    }

    @Test
    fun testSearch() {
        var limit: Long = 2
        var offest: Long = 1

        var itemPokemno= Pokemon(name = "ivysaur", url = "https://pokeapi.co/api/v2/pokemon/2/", urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png")

        val listItem: List<Pokemon> = arrayListOf(itemPokemno)

        `when`(getListPokemonUseCase.createObservable((GetAllPokemonUseCase.Params(limit = limit, offset = offest, criteria = "ivy", fromServer = false))))
            .thenReturn(Single.just(listItem))

        var testObserver: TestObserver<List<Pokemon>> = getListPokemonUseCase.createObservable(
            (GetAllPokemonUseCase.Params(limit = limit, offset = offest, criteria = "ivy", fromServer = false))).test()
        testObserver.awaitTerminalEvent()
        testObserver
            .assertNoErrors()
            .assertValue { l -> l.size == 1 }

    }



    /*@Test
    fun testSearchPokemon() {


        `when`(getListPokemonUseCase.createObservable((GetAllPokemonUseCase.Params(limit = 1, offset = 1,
            criteria = "query", fromServer = false))))
            .thenReturn(Single.error(Throwable("error")))

        var testObserver: TestObserver<List<Pokemon>> = mainViewModel.searchPok("query").test()


        testObserver.awaitTerminalEvent()
        testObserver
            .assertNoErrors()
            .assertValue { l -> l.size == 1 }


        assertEquals(mainViewModel.isLoading.value, false)

    }*/

}

