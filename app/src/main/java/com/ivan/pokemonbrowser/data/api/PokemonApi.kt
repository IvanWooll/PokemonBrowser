package com.ivan.pokemonbrowser.data.api

import com.ivan.pokemonbrowser.AppConfig
import com.ivan.pokemonbrowser.data.api.model.Pokemon
import com.ivan.pokemonbrowser.data.api.model.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonApi {

    @GET("pokemon")
    suspend fun fetchPokemons(
        @Query("limit") limit: Int = AppConfig.POKE_LIST_LIMIT, // default to 50 for convenience, can be changed
        @Query("offset") offset: Int = 0 // default initial value
    ): Response<PokemonsResponse>

    @GET("pokemon/{id}")
    suspend fun fetchPokemonById(
        @Path("id") id: Int,
    ): Response<Pokemon>
}



