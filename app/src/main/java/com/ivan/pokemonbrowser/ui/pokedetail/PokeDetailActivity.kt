package com.ivan.pokemonbrowser.ui.pokedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ivan.pokemonbrowser.databinding.ActivityPokeDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.ivan.pokemonbrowser.AppConfig
import com.ivan.pokemonbrowser.ui.pokedetail.model.PokeDetailItem

@AndroidEntryPoint
class PokeDetailActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context, pokemonId: Int) =
            Intent(context, PokeDetailActivity::class.java)
                .apply { putExtra(AppConfig.Keys.POKEMON_ID, pokemonId) }
    }

    private val binding by lazy { ActivityPokeDetailBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<PokeDetailViewModel>()

    private val typesAdapter = TypesAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        binding.recyclerViewTypes.adapter = typesAdapter

        viewModel.viewStateLiveData.observe(this) { viewState ->
            when (viewState) {
                PokeDetailViewState.Error -> binding.viewFlipper.displayedChild = 2
                is PokeDetailViewState.Success -> {
                    binding.viewFlipper.displayedChild = 1
                    displayPokeMon(viewState.pokeDetailItem)
                }
            }
        }

        viewModel.fetchPokemonDetails()
    }

    private fun displayPokeMon(pokeDetailItem: PokeDetailItem) {
        binding.apply {
            textViewPokemonName.text = pokeDetailItem.name
            textViewWeight.text = pokeDetailItem.weight
            textViewHeight.text = pokeDetailItem.height
            imageViewPokemonFront.load(pokeDetailItem.frontImageUrl)
            typesAdapter.submitList(pokeDetailItem.types)
        }
    }
}