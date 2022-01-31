package com.domain.pokedexapp.presentation.list_pokemons.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.domain.pokedexapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



}