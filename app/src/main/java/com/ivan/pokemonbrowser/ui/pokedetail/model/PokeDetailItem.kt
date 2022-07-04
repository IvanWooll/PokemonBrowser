package com.ivan.pokemonbrowser.ui.pokedetail.model

data class PokeDetailItem(
    val name: String,
    val frontImageUrl: String,
    val types: List<String>,
    val weight: String,
    val height: String
)
