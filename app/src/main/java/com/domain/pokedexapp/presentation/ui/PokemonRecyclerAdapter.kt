package com.domain.pokedexapp.presentation.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.domain.pokedexapp.domain.model.Pokemon
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView

import android.widget.TextView
import com.domain.pokedexapp.R
import javax.inject.Inject


class PokemonRecyclerAdapter @Inject constructor(val onClick: (Pokemon) -> Unit) : RecyclerView.Adapter<PokemonRecyclerAdapter.PokemonViewHolder>(){

    private var listPokemon: MutableList<Pokemon> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonRecyclerAdapter.PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)

        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.movieTitle.text= listPokemon[position].name
    }


    override fun getItemCount(): Int {
        return listPokemon.size
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle: TextView
        val movieImage: ImageView

        init {
            movieTitle = itemView.findViewById(R.id.tvTitle)
            movieImage = itemView.findViewById(R.id.tvImage) as ImageView
        }
    }


    fun addAll(data: MutableList<Pokemon>) {
        for(pokemon in data){
            add(pokemon)
        }
    }

    fun add(dataItem: Pokemon) {
        listPokemon.add(dataItem)
        notifyItemInserted(listPokemon.size - 1)
    }

}