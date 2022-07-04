package com.ivan.pokemonbrowser.ui.pokelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivan.pokemonbrowser.AppConfig
import com.ivan.pokemonbrowser.common.Progress
import com.ivan.pokemonbrowser.data.api.repository.PokemonRepository
import com.ivan.pokemonbrowser.ui.pokelist.model.PokeListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) : ViewModel() {

    val progressLiveData = MutableLiveData<Progress>()

    val pokeListViewStateLiveData = MutableLiveData<PokeListViewState>()

    private var offset = 0
    private val pokeListItems = mutableListOf<PokeListItem>()

    init {
        // fetch initial list
        fetchPokemon()
    }

    fun fetchPokemon() {
        viewModelScope.launch {
            try {
                progressLiveData.postValue(Progress.Loading)
                val response = pokemonRepository.fetchPokemons(offset = offset)
                progressLiveData.postValue(Progress.Finished)
                if (response.isSuccessful) {
                    response.body()?.let { pokemonsResponse ->
                        offset += AppConfig.POKE_LIST_LIMIT
                        val items = pokemonsResponse.pokemonItems
                            .map { pokemonListItem -> pokemonListItem.toPokeListItem() }
                        pokeListItems.addAll(items)
                        pokeListViewStateLiveData.postValue(
                            PokeListViewState.Success(
                                listOf(pokeListItems).flatten() // this is required as the ListAdapter requires a new list in order to reflect changes when calling `submitList()`
                            )
                        )
                    }
                } else {
                    pokeListViewStateLiveData.postValue(PokeListViewState.Error)
                }
            } catch (exception: Exception) {
                pokeListViewStateLiveData.postValue(PokeListViewState.Error)
            }

        }
    }
}

sealed class PokeListViewState {
    class Success(val pokeList: List<PokeListItem>) : PokeListViewState()
    object Error : PokeListViewState()
}