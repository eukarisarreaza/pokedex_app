package com.domain.pokedexapp.presentation.ui

import android.os.Bundle
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
import com.domain.pokedexapp.presentation.viewmodel.MainViewModel
import javax.inject.Inject
import androidx.recyclerview.widget.RecyclerView
import com.domain.pokedexapp.R


class PokemonListFragment : Fragment() {
    @Inject
    lateinit var viewModel: MainViewModel
    private var adapter: PokemonRecyclerAdapter = PokemonRecyclerAdapter(){
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