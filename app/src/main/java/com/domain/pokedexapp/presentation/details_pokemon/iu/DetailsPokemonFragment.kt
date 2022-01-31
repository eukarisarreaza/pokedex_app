package com.domain.pokedexapp.presentation.details_pokemon.iu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.domain.pokedexapp.PokedexApp
import com.domain.pokedexapp.databinding.FragmentDetailsPokemonBinding
import com.domain.pokedexapp.presentation.details_pokemon.viewmodel.DetailsViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject


class DetailsPokemonFragment : Fragment() {
    @Inject
    lateinit var viewModel: DetailsViewModel
    private var name: String? = null
    lateinit var navController: NavController

    private lateinit var binding: FragmentDetailsPokemonBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString("name")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentDetailsPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as PokedexApp).detailsComponent.inject(this)
        navController= Navigation.findNavController(view)

        viewModel.onCreate(name= name!!)

        viewModel.pokemon.observe(viewLifecycleOwner,{
            binding.itTitle.text= it.name
            Picasso.get().load(it.urlImage)
                .into(binding.itImage)

            binding.itWeight.text= "Peso: ${it.weight.toString()}"
            binding.itHeight.text= "Altura: ${it.height.toString()}"
            binding.itId.text= "ID: ${it.id.toString()}"

        })


    }

}