package com.domain.pokedexapp.presentation.list_pokemons.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.domain.pokedexapp.PokedexApp
import com.domain.pokedexapp.databinding.FragmentPokemonListBinding
import com.domain.pokedexapp.domain.model.Pokemon
import com.domain.pokedexapp.presentation.list_pokemons.viewmodel.MainViewModel
import javax.inject.Inject
import androidx.recyclerview.widget.RecyclerView
import com.domain.pokedexapp.R


class PokemonListFragment : Fragment() {
    @Inject
    lateinit var viewModel: MainViewModel
    private var adapter: PokemonRecyclerAdapter = PokemonRecyclerAdapter{
        adapterOnClick(pokemon = it)
    }
    lateinit var navController: NavController

        private lateinit var binding: FragmentPokemonListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as PokedexApp).mainComponent.inject(this)
        binding.recycler.adapter= adapter

        navController=Navigation.findNavController(view)

        viewModel.onCreate()

        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.loading.isVisible = it
        })

        viewModel.pokemonAll.observe(viewLifecycleOwner, {
            adapter.addAll(it as MutableList<Pokemon>)
        })
        val layoutManager = GridLayoutManager(activity?.applicationContext, 2)
        binding.recycler.setLayoutManager(layoutManager)
        val layoutManager2 = GridLayoutManager(activity?.applicationContext, 2)
        binding.recyclerSearch.setLayoutManager(layoutManager2)
        binding.recyclerSearch.visibility = View.INVISIBLE

        viewModel.pokemonSearch.observe(viewLifecycleOwner, {
            val adapter2=PokemonRecyclerAdapter{adapterOnClick(pokemon = it)}

            binding.recyclerSearch.adapter= adapter2
            adapter2.addAll(it as MutableList<Pokemon>)
        })



        binding.recycler.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                viewModel.onCreate()
            }

            override fun getTotalPageCount(): Int {
                return viewModel.count
            }

            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading2
            }

        })

        binding.textView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.isNotEmpty()) {
                    binding.recycler.visibility = View.INVISIBLE
                    binding.recyclerSearch.visibility = View.VISIBLE
                    viewModel.searchPokemon(s.toString())
                }else {
                    binding.recycler.visibility = View.VISIBLE
                    binding.recyclerSearch.visibility = View.INVISIBLE

                }
            }
        })
    }

    private fun adapterOnClick(pokemon: Pokemon) {

        navController.navigate(R.id.detailsPokemonFragment,  Bundle().apply {
            putString("name", pokemon.name)
        })
    }


}


abstract class PaginationScrollListener (layoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {

    val layoutManager: GridLayoutManager = layoutManager

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()
    abstract fun getTotalPageCount(): Int
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}