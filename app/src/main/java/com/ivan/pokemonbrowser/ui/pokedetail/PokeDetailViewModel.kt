package com.ivan.pokemonbrowser.ui.pokedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivan.pokemonbrowser.AppConfig
import com.ivan.pokemonbrowser.data.api.repository.PokemonRepository
import com.ivan.pokemonbrowser.ui.pokedetail.model.PokeDetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val pokemonId = savedStateHandle.get<Int>(AppConfig.Keys.POKEMON_ID) ?: 0

    val viewStateLiveData = MutableLiveData<PokeDetailViewState>()


    fun fetchPokemonDetails() {
        viewModelScope.launch {
            try {
                val response = pokemonRepository.fetchPokemon(pokemonId)
                if (response.isSuccessful) {
                    response.body()?.let { pokemon ->
                        val pokeDetailItem = pokemon.toPokeDetailItem()
                        viewStateLiveData.postValue(PokeDetailViewState.Success(pokeDetailItem))
                    }
                } else {
                    viewStateLiveData.postValue(PokeDetailViewState.Error)
                }
            } catch (exception: Exception) {
                viewStateLiveData.postValue(PokeDetailViewState.Error)
            }

        }
    }
}

sealed class PokeDetailViewState {
    class Success(val pokeDetailItem: PokeDetailItem) : PokeDetailViewState()
    object Error : PokeDetailViewState()
}

