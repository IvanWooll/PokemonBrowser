package com.ivan.pokemonbrowser.data.api.model

import com.ivan.pokemonbrowser.ui.pokedetail.model.PokeDetailItem
import org.junit.Assert.*
import org.junit.Test

class PokemonTest {

    @Test
    fun `test conversion succeeds`() {
        val pokemon = Pokemon(
            name = "pokemon",
            weight = 1,
            height = 1,
            types = listOf(
                TypeItem(type = Type(name = "grass", url = "")),
                TypeItem(type = Type(name = "fire", url = ""))
            ),
            sprites = Sprite(frontDefault = "image_url")
        )
        val convertedItem = pokemon.toPokeDetailItem()
        val testPokeDetailItem = PokeDetailItem(
            name = "Pokemon",
            weight = "0.1 KGS",
            height = "10 CM",
            types = listOf("grass", "fire"),
            frontImageUrl = "image_url"
        )

        assertEquals(testPokeDetailItem, convertedItem)
    }

    @Test
    fun `test conversion fails`() {
        val pokemon = Pokemon(
            name = "pokemon",
            weight = 2,
            height = 3,
            types = listOf(
                TypeItem(type = Type(name = "grass", url = "")),
                TypeItem(type = Type(name = "fire", url = ""))
            ),
            sprites = Sprite(frontDefault = "image_url")
        )
        val convertedItem = pokemon.toPokeDetailItem()
        val testPokeDetailItem = PokeDetailItem(
            name = "Pokemon",
            weight = "0.1 KGS",
            height = "10 CM",
            types = listOf("grass", "fire"),
            frontImageUrl = "image_url"
        )

        assertNotEquals(testPokeDetailItem, convertedItem)
    }
}