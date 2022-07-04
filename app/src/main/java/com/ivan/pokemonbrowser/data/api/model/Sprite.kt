package com.ivan.pokemonbrowser.data.api.model

import com.squareup.moshi.Json

data class Sprite(
    @Json(name = "front_default")
    val frontDefault: String
)