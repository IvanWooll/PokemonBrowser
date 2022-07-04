package com.ivan.pokemonbrowser.data.api.repository

import com.ivan.pokemonbrowser.data.api.PokemonApi
import com.ivan.pokemonbrowser.data.api.model.PokemonsResponse
import retrofit2.Response
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    val api: PokemonApi
) {

    /**
     * @return a list of Pokemon items
     * @offset an offset from 0 that the results should start from
     */
    suspend fun fetchPokemons(offset: Int = 0) = api.fetchPokemons(offset = offset)


    suspend fun fetchPokemon(id: Int) = api.fetchPokemonById(id)

}