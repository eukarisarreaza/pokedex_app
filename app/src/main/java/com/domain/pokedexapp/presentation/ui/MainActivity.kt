package com.domain.pokedexapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.domain.pokedexapp.PokedexApp
import com.domain.pokedexapp.databinding.ActivityMainBinding
import com.domain.pokedexapp.presentation.viewmodel.MainViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as PokedexApp).mainComponent.inject(this)

        viewModel.onCreate()

        viewModel.isLoading.observe(this, {
            binding.loading.isVisible = it
        })
    }



}