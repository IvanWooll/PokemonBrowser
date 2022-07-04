package com.ivan.pokemonbrowser.data.api.model

import com.ivan.pokemonbrowser.ui.pokedetail.model.PokeDetailItem
import com.ivan.pokemonbrowser.util.Utils

data class Pokemon(
    val name: String,
    val weight: Int, // weight in hectograms
    val height: Int, // height in decimetres
    val types: List<TypeItem>,
    val sprites: Sprite
) {
    fun toPokeDetailItem(): PokeDetailItem {
        val hectogramToKgs = Utils.hectogramToKgs(weight)
        val decimeterToCentimeters = Utils.decimeterToCentimeters(height)
        val types = types.map { it.type.name }

        return PokeDetailItem(
            name = name.capitalize(),
            weight = "$hectogramToKgs KGS",
            height = "$decimeterToCentimeters CM",
            types = types,
            frontImageUrl = sprites.frontDefault
        )
    }
}




