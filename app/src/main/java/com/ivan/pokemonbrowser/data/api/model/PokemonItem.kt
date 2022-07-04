package com.ivan.pokemonbrowser.data.api.model

import com.ivan.pokemonbrowser.ui.pokelist.model.PokeListItem
import com.ivan.pokemonbrowser.util.Utils

data class PokemonItem(val name: String, val url: String) {
    fun toPokeListItem(): PokeListItem =
        PokeListItem(
            name = name.capitalize(),
            id = Utils.idFromUrl(url)
        )
}