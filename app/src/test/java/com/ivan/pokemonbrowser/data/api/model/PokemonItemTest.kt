package com.ivan.pokemonbrowser.data.api.model

import com.ivan.pokemonbrowser.ui.pokelist.model.PokeListItem
import org.junit.Assert.*
import org.junit.Test

class PokemonItemTest {

    @Test
    fun `test conversion succeeds`() {
        val pokemonItem = PokemonItem(name = "pokemon", url = "https://pokeapi.co/api/v2/pokemon/1/")
        val convertedItem = pokemonItem.toPokeListItem()

        val testPokeLisItem = PokeListItem(name = "Pokemon", id = 1)

        assertEquals(testPokeLisItem, convertedItem)
    }

    @Test
    fun `test conversion fails`() {
        val pokemonItem = PokemonItem(name = "pokemon", url = "https://pokeapi.co/api/v2/pokemon/1/")
        val convertedItem = pokemonItem.toPokeListItem()

        val testPokeLisItem = PokeListItem(name = "pokemon", id = 2)

        assertNotEquals(testPokeLisItem, convertedItem)
    }
}