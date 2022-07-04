package com.ivan.pokemonbrowser.ui.pokelist


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.ivan.pokemonbrowser.common.Progress
import com.ivan.pokemonbrowser.databinding.ActivityPokeListBinding
import com.ivan.pokemonbrowser.ui.pokedetail.PokeDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeListActivity : AppCompatActivity() {


    private val binding by lazy { ActivityPokeListBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<PokeListViewModel>()

    private val pokemonAdapter: PokemonAdapter = PokemonAdapter { id -> onPokemonClicked(id) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.adapter = pokemonAdapter

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.fetchPokemon()
                }
            }
        })

        viewModel.pokeListViewStateLiveData.observe(this) {
            when (it) {
                PokeListViewState.Error ->
                    Toast.makeText(this, "There was a problem fetching the pokemons", Toast.LENGTH_SHORT).show()
                is PokeListViewState.Success -> pokemonAdapter.submitList(it.pokeList)
            }
        }

        viewModel.progressLiveData.observe(this) {
            binding.progress.visibility = when (it) {
                Progress.Finished -> View.GONE
                Progress.Loading -> View.VISIBLE
            }

        }

    }

    private fun onPokemonClicked(id: Int) {
        startActivity(Intent(PokeDetailActivity.getStartIntent(this, id)))
    }
}